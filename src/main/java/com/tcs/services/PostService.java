package com.tcs.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tcs.client.ExternalApiClient;
import com.tcs.dto.PersonDTO;

@Service
public class PostService {
    private final ExternalApiClient externalApiClient;

    public PostService(ExternalApiClient externalApiClient) {
        this.externalApiClient = externalApiClient;
    }

    public List<PersonDTO> getPosts() {
        PersonDTO[] posts = externalApiClient.fetchPersons();
        return List.of(posts);
    }
}