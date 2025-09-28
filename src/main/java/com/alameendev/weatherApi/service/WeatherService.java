package com.alameendev.weatherApi.service;


import com.alameendev.weatherApi.dtos.LatitudeLongitudeDTO;
import com.alameendev.weatherApi.dtos.PhotonResponseDTO;
import com.alameendev.weatherApi.dtos.WeatherForLocationDTO;
import com.alameendev.weatherApi.dtos.WeatherForecastDTO;
import com.alameendev.weatherApi.exceptions.InvalidCityNameException;
import com.alameendev.weatherApi.exceptions.InvalidDateRangeException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private final PhotonKomootApiService photonKomootApiService;
    private final OpenMeteoApiService openMeteoApiService;

    public WeatherService(PhotonKomootApiService photonKomootApiService, OpenMeteoApiService openMeteoApiService) {
        this.photonKomootApiService = photonKomootApiService;
        this.openMeteoApiService = openMeteoApiService;
    }

    public List<WeatherForLocationDTO> getWeatherForTheLocation(String cityName) {
        if (cityName.isEmpty() || cityName.isBlank()) {
            throw new InvalidCityNameException("no name");
        }
        PhotonResponseDTO photonResponseDTO = photonKomootApiService.getLatitudeLongitudeForCity(cityName);
        if (photonResponseDTO.getFeatures().isEmpty()) {
            throw new InvalidCityNameException(cityName);
        }
        List<PhotonResponseDTO.Feature> featureList = photonResponseDTO.getFeatures();
        List<LatitudeLongitudeDTO> latitudeLongitudeDTOList = new ArrayList<>();
        for (int i = 0; i < featureList.size(); i++) {
            LatitudeLongitudeDTO latitudeLongitudeDTO = getLatitudeLongitudeDTO(featureList, i);
            latitudeLongitudeDTOList.add(latitudeLongitudeDTO);
        }
        return openMeteoApiService.getCurrentWeatherInfoForPlaces(latitudeLongitudeDTOList);
    }

    public WeatherForecastDTO getWeatherForecastForCityWithDays(String cityName, Integer noOfDays) {
        if (cityName.isEmpty() || cityName.isBlank()) {
            throw new InvalidCityNameException("no name");
        }
        if (noOfDays < 1 || noOfDays > 16) {
            throw new InvalidDateRangeException();
        }
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(noOfDays - 1);
        PhotonResponseDTO photonResponseDTO = photonKomootApiService.getLatitudeLongitudeForCity(cityName);
        if (photonResponseDTO.getFeatures().isEmpty()) {
            throw new InvalidCityNameException(cityName);
        }
        List<PhotonResponseDTO.Feature> featureList = photonResponseDTO.getFeatures();
        List<LatitudeLongitudeDTO> latitudeLongitudeDTOList = new ArrayList<>();
        for (int i = 0; i < featureList.size(); i++) {
            LatitudeLongitudeDTO latitudeLongitudeDTO = getLatitudeLongitudeDTO(featureList, i);
            latitudeLongitudeDTOList.add(latitudeLongitudeDTO);
        }
        return openMeteoApiService.getWeatherForecastForLocationWithDays(latitudeLongitudeDTOList.get(0), startDate, endDate);
    }

    private static LatitudeLongitudeDTO getLatitudeLongitudeDTO(List<PhotonResponseDTO.Feature> featureList, int i) {
        PhotonResponseDTO.Feature feature = featureList.get(i);
        LatitudeLongitudeDTO latitudeLongitudeDTO = new LatitudeLongitudeDTO();
        latitudeLongitudeDTO.setLat(String.valueOf(feature.getGeometry().getCoordinates().get(1)));
        latitudeLongitudeDTO.setLon(String.valueOf(feature.getGeometry().getCoordinates().get(0)));
        latitudeLongitudeDTO.setName(feature.getProperties().getName());
        latitudeLongitudeDTO.setState(feature.getProperties().getState());
        latitudeLongitudeDTO.setCountry(feature.getProperties().getCountry());
        return latitudeLongitudeDTO;
    }

}
