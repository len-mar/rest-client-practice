package org.example.restclientpractice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    // this intercepts a get request to this url
    // important: structure of perform mockmvc response needs to match our objects EXACTLY
    // careful with commas and spaces in the first (since this is parsed as a string)
    @Test
    void getAllCharacters() throws Exception {
        mockRestServiceServer.expect(requestTo("https://rickandmortyapi.com/api/character"))
                .andExpect(method(HttpMethod.GET))
                .andRespond((withSuccess("""
                                {
                                    "info": {
                                        "count": 32
                                    },
                                    "results": [
                                        {
                                            "id": 1,
                                            "name": "Rick Sanchez",
                                            "species": "Human"
                                        }
                                    ]
                                }
                                """, MediaType.APPLICATION_JSON)));
        mockMvc.perform(get("/api/characters"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                    [
                                        {
                                            "id": 1,
                                            "name": "Rick Sanchez",
                                            "species": "Human"
                                        }
                                    ]
                """));
    }

    @Test
    void getCharacterById() throws Exception {
        mockRestServiceServer.expect(requestTo("https://rickandmortyapi.com/api/character/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond((withSuccess("""
                                        {
                                            "id": 1,
                                            "name": "Rick Sanchez",
                                            "species": "Human"
                                        }
                                """, MediaType.APPLICATION_JSON)));
        mockMvc.perform(get("/api/characters/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                        {
                                            "id": 1,
                                            "name": "Rick Sanchez",
                                            "species": "Human"
                                        }
                """));
    }

    @Test
    void getAliveCharacters() throws Exception{
        mockRestServiceServer.expect(requestTo("https://rickandmortyapi.com/api/character?status=alive"))
                .andExpect(method(HttpMethod.GET))
                .andRespond((withSuccess("""
                                    {
                                      "info": {
                                        "count": 32
                                      },
                                      "results": [
                                        {
                                          "id": 3,
                                          "name": "Summer Smith",
                                          "species": "Human"
                                        },
                                        {
                                          "id": 4,
                                          "name": "Beth Smith",
                                          "species": "Human"
                                        }
                                      ]
                                    }
                                """, MediaType.APPLICATION_JSON)));
        // this get struggles with putting ?status=alive like request params in the url directly
        mockMvc.perform(get("/api/characters/alive"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                    [
                                      {
                                        "id": 3,
                                        "name": "Summer Smith",
                                        "species": "Human"
                                      },
                                      {
                                        "id": 4,
                                        "name": "Beth Smith",
                                        "species": "Human"
                                      }
                                    ]
                """));
    }

    // todo: implement
    @Test
    void getCharacterCountBySpecies() throws Exception {
        mockRestServiceServer.expect(requestTo("https://rickandmortyapi.com/api/character?species=human"))
                .andExpect(method(HttpMethod.GET))
                .andRespond((withSuccess("""
                                        {
                                            "id": 1,
                                            "name": "Rick Sanchez",
                                            "species": "Human"
                                        }
                                """, MediaType.APPLICATION_JSON)));
        mockMvc.perform(get("/api/characters/species-statistic").param("species", "human"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                        {
                                            "id": 1,
                                            "name": "Rick Sanchez",
                                            "species": "Human"
                                        }
                """));
    }
}
