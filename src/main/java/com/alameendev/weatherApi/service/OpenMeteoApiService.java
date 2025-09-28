package com.alameendev.weatherApi.service;


import com.alameendev.weatherApi.dtos.LatitudeLongitudeDTO;
import com.alameendev.weatherApi.dtos.WeatherForLocationDTO;
import com.alameendev.weatherApi.dtos.WeatherForecastDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpenMeteoApiService {

    private final WebClient webClient;

    @Value("${url.open-meteo-api}")
    private String openMeteoApiUrl;

    public OpenMeteoApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<WeatherForLocationDTO> getCurrentWeatherInfoForPlaces(List<LatitudeLongitudeDTO> latitudeLongitudeDTOList) {

        List<WeatherForLocationDTO> weatherForLocationDTOList = new ArrayList<>();
        for (int i = 0; i < latitudeLongitudeDTOList.size(); i++) {
            LatitudeLongitudeDTO latitudeLongitudeDTO = latitudeLongitudeDTOList.get(i);
            URI uri = UriComponentsBuilder
                    .fromUriString(openMeteoApiUrl + "/forecast")
                    .queryParam("latitude", latitudeLongitudeDTO.getLat())
                    .queryParam("longitude", latitudeLongitudeDTO.getLon())
                    .queryParam("current_weather", "true")
                    .build()
                    .toUri();
            WeatherForLocationDTO weatherForLocationDTO = webClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(WeatherForLocationDTO.class)
                    .block();
            weatherForLocationDTO.setName(latitudeLongitudeDTO.getName());
            weatherForLocationDTO.setCountry(latitudeLongitudeDTO.getCountry());
            weatherForLocationDTO.setState(latitudeLongitudeDTO.getState());
            weatherForLocationDTOList.add(weatherForLocationDTO);
        }
        return weatherForLocationDTOList;
    }

    public WeatherForecastDTO getWeatherForecastForLocationWithDays(LatitudeLongitudeDTO latitudeLongitudeDTO, LocalDate startDate, LocalDate endDate) {
        URI uri = UriComponentsBuilder
                .fromUriString(openMeteoApiUrl + "/forecast")
                .queryParam("latitude", latitudeLongitudeDTO.getLat())
                .queryParam("longitude", latitudeLongitudeDTO.getLon())
                .queryParam("daily", "temperature_2m_max,temperature_2m_min")
                .queryParam("timezone", "auto")
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .build()
                .toUri();

        WeatherForecastDTO weatherForecastDTO = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(WeatherForecastDTO.class)
                .block();
        weatherForecastDTO.setName(latitudeLongitudeDTO.getName());
        weatherForecastDTO.setState(latitudeLongitudeDTO.getState());
        weatherForecastDTO.setCountry(latitudeLongitudeDTO.getCountry());
        return weatherForecastDTO;
    }


}
