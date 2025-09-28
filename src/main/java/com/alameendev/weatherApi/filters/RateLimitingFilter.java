package com.alameendev.weatherApi.filters;

import com.alameendev.weatherApi.dtos.ErrorResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;

    public RateLimitingFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private Bucket createNewBucket() {
        Refill refill = Refill.intervally(3, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(3, refill);
        return Bucket.builder().addLimit(limit).build();
    }

    private Bucket resolveBucket(String ip) {
        return buckets.computeIfAbsent(ip, i -> createNewBucket());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String userIp = request.getRemoteAddr();
        Bucket bucket = resolveBucket(userIp);
        String path = request.getRequestURI();

        if (path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui") || path.equals("/swagger-ui.html")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO("Too many requests. Please try again later.",
                    HttpStatus.TOO_MANY_REQUESTS.value());

            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType("application/json");
            objectMapper.writeValue(response.getWriter(), errorResponse);
        }
    }

}
