package org.example.restclientpractice;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CharacterController {

    RestClient.Builder builder = RestClient.builder();
    private final CharacterAPIService characterAPIService = new CharacterAPIService(builder);

    @GetMapping("/characters")
    List<CharacterAPIResponseResults> getAllCharacters(){
        return characterAPIService.getAllCharacters();
    }

    @GetMapping("/characters/{id}")
    CharacterAPIResponseResults getCharacterById(@PathVariable int id){
        return characterAPIService.getCharacterById(id);
    }

    @GetMapping("/characters?status=alive")
    List<CharacterAPIResponseResults> getAliveCharacters(){
        return characterAPIService.getAliveCharacters();
    }

    @GetMapping("/species-statistic")
    public int getCharacterCountBySpecies(@RequestParam String species){
        return characterAPIService.getCharactersBySpecies(species).count();
    }


}