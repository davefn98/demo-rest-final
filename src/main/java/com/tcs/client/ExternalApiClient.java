package com.tcs.client;

import com.tcs.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalApiClient {
    private static final Logger logger = LoggerFactory.getLogger(ExternalApiClient.class);
    private static final String API_URL = "https://jsonplaceholder.typicode.com/users";
    private final RestTemplate restTemplate;

    public ExternalApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PersonDTO[] fetchPersons() {
        try {
            return restTemplate.getForObject(API_URL, PersonDTO[].class);
        } catch (RestClientException ex) {
            logger.error("Error fetching persons from external API: {}", ex.getMessage(), ex);
            throw new ExternalApiException("Failed to fetch persons from external API", ex);
        }
    }
}