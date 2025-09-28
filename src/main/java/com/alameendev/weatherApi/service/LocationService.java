package com.alameendev.weatherApi.service;


import com.alameendev.weatherApi.dtos.LatitudeLongitudeDTO;
import com.alameendev.weatherApi.dtos.PhotonResponseDTO;
import com.alameendev.weatherApi.dtos.WeatherForLocationDTO;
import com.alameendev.weatherApi.exceptions.InvalidCityNameException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    private final PhotonKomootApiService photonKomootApiService;
    private final OpenMeteoApiService openMeteoApiService;

    public LocationService(PhotonKomootApiService photonKomootApiService, OpenMeteoApiService openMeteoApiService) {
        this.photonKomootApiService = photonKomootApiService;
        this.openMeteoApiService = openMeteoApiService;
    }

    public List<WeatherForLocationDTO> getWeatherInfoForLocations(String locationName) {
        if (locationName.isEmpty() || locationName.isBlank() || !StringUtils.isAlphanumeric(locationName)) {
            throw new InvalidCityNameException("no name");
        }
        PhotonResponseDTO photonResponseDTO = photonKomootApiService.getLatitudeLongitudeInfoByFuzzySearch(locationName);
        if(photonResponseDTO.getFeatures().isEmpty()){
            throw new InvalidCityNameException(locationName);
        }
        List<PhotonResponseDTO.Feature> featureList = photonResponseDTO.getFeatures();
        List<LatitudeLongitudeDTO> latitudeLongitudeDTOList = new ArrayList<>();
        for (int i = 0; i < featureList.size(); i++) {
            LatitudeLongitudeDTO latitudeLongitudeDTO = getLatitudeLongitudeDTO(featureList, i);
            latitudeLongitudeDTOList.add(latitudeLongitudeDTO);
        }
        return openMeteoApiService.getCurrentWeatherInfoForPlaces(latitudeLongitudeDTOList);
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
