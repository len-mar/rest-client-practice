package org.example.restclientpractice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CharacterController {

    // todo: why?
    private final CharacterAPIService characterAPIService;
//
//    public CharacterController(CharacterAPIService characterAPIService){
//        this.characterAPIService = characterAPIService;
//    }

    //    RestClient.Builder builder = RestClient.builder();
    //    private final CharacterAPIService characterAPIService = new CharacterAPIService(builder);

    @GetMapping("/characters")
    List<CharacterAPIResponseResults> getAllCharacters(){
        return characterAPIService.getAllCharacters();
    }

    @GetMapping("/characters/{id}")
    CharacterAPIResponseResults getCharacterById(@PathVariable int id){
        return characterAPIService.getCharacterById(id);
    }

    @GetMapping("/characters/alive")
    List<CharacterAPIResponseResults> getAliveCharacters(){
        return characterAPIService.getAliveCharacters();
    }

    @GetMapping("/characters/species-statistic")
    public int getCharacterCountBySpecies(@RequestParam String species){
        return characterAPIService.getInfoObjectBySpecies(species).count();
    }

}