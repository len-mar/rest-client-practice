package org.example.restclientpractice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    RestClient.Builder builder = RestClient.builder();
    private final CharacterAPIService characterAPIService = new CharacterAPIService(builder);

    @GetMapping
    List<CharacterAPIResponseResults> getAllCharacters(){
        return characterAPIService.getAllCharacters();
    }

    @GetMapping("/{id}")
    CharacterAPIResponseResults getCharacterById(@PathVariable int id){
        return characterAPIService.getCharacterById(id);
    }


}