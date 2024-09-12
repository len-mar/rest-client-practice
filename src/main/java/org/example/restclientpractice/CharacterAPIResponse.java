package org.example.restclientpractice;

import java.util.List;

public record CharacterAPIResponse(List<CharacterAPIResponseResults> results) {
}
