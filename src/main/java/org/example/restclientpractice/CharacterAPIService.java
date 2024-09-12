package org.example.restclientpractice;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;

@Service
public class CharacterAPIService {

    private final RestClient restClient;

    public CharacterAPIService(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public List<CharacterAPIResponseResults> getAllCharacters() {
        CharacterAPIResponse response = this.restClient.get().uri("/character").retrieve().body(CharacterAPIResponse.class);
        assert response != null;
        return response.results();
    }
}