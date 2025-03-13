package com.equipeAcelera.EventifyAPI.utils;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.DataNotFoundException;

public class GeocodingUtils {
    
    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static Map<String, Double> getLatitudeLongitude(String location){
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().set("User-Agent", "EventfyApp/1.0 (equipeeventfy@gmail.com)");
            return execution.execute(request, body);
        });

        String url = NOMINATIM_URL + "?q=" + location + "&format=json&addressdetails=1&countrycodes=BR";

        ResponseEntity<Map[]> response = restTemplate.getForEntity(url, Map[].class);

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null && response.getBody().length > 0){
            Map<String, String> latAndLon = (Map<String, String>) response.getBody()[0];

            return Map.of(
                "latitude", Double.parseDouble(latAndLon.get("lat")),
                "longitude", Double.parseDouble(latAndLon.get("lon"))
            );
        } else {
            throw new DataNotFoundException("Location not found!");
        }

    }
}
