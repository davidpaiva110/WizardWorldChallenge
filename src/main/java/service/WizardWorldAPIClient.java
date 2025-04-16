package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Elixir;
import model.Ingredient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Set;

/**
 * This class is responsible for interacting with the Wizard World API.
 * It fetches ingredients and elixirs from the API.
 * <p>
 * If something goes wrong, it throws a RuntimeException.
 * A custom exception could be created for better error handling.
 * But for simplicity, I decided to use RuntimeException here.
 */
public class WizardWorldAPIClient {

    private static final String BASE_URL = "https://wizard-world-api.herokuapp.com";
    private static final String INGREDIENTS_PATH = "/Ingredients";
    private static final String ELIXIRS_PATH = "/Elixirs";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public WizardWorldAPIClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }


    /**
     * Fetches ingredients from the Wizard World API.
     *
     * @return a set of ingredients
     */
    public Set<Ingredient> fetchIngredients() {

        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + INGREDIENTS_PATH))
                .build();

        final HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return Set.of(objectMapper.readValue(response.body(), Ingredient[].class));
            } else {
                throw new RuntimeException("Failed to fetch ingredients: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error fetching ingredients", e);
        }
    }

    /**
     * Fetches elixirs from the Wizard World API.
     *
     * @return a set of elixirs
     */
    public Set<Elixir> fetchElixirs(){
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + ELIXIRS_PATH))
                .build();

        try {
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return Set.of(objectMapper.readValue(response.body(), Elixir[].class));
            } else {
                throw new RuntimeException("Failed to fetch elixirs: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error fetching elixirs", e);
        }
    }

}
