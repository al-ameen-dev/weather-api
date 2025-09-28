package com.alameendev.weatherApi.service;


import com.alameendev.weatherApi.dtos.PhotonResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class PhotonKomootApiService {

    private final WebClient webClient;

    @Value("${url.photon-komoot-api}")
    private String photonKomootApiUrl;


    public PhotonKomootApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public PhotonResponseDTO getLatitudeLongitudeForCity(String cityName) {
        URI uri = UriComponentsBuilder
                .fromUriString(photonKomootApiUrl + "/api")
                .queryParam("q", cityName)
                .queryParam("osm_tag", "place:city")
                .queryParam("limit", "1")
                .build()
                .toUri();
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(PhotonResponseDTO.class)
                .block();
    }

    public PhotonResponseDTO getLatitudeLongitudeInfoByFuzzySearch(String placeName) {
        URI uri = UriComponentsBuilder
                .fromUriString(photonKomootApiUrl + "/api")
                .queryParam("q", placeName)
                .build()
                .toUri();
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(PhotonResponseDTO.class)
                .block();
    }

}
