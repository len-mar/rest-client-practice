package org.example.restclientpractice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api/characters/")
public class CharacterController {

    RestClient.Builder builder = RestClient.builder();
    private final CharacterAPIService characterAPIService = new CharacterAPIService(builder);

    // todo: implement get mapping that outputs json with those three attributes
    @GetMapping
    List<CharacterAPIResponseResults> getAllCharacters(){
        return characterAPIService.getAllCharacters();
    }
}