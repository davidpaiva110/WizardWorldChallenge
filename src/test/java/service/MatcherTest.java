package service;

import model.Elixir;
import model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MatcherTest {

    private Matcher matcher;
    private Elixir fergusFungalBudge;
    private Elixir rudimentaryBodyPotion;
    private Elixir essenceOfInsanity;
    private Elixir manegroPotion;

    @BeforeEach
    void setUp() {
        Ingredient neemOil = new Ingredient("4ff5aaf2-776f-43c6-9896-c79c67dc90c5", "Neem oil");
        Ingredient jewelweed = new Ingredient("846be123-c40f-4156-91f4-800305df7485", "Jewelweed");
        Ingredient onionJuice = new Ingredient("a08e7390-a362-4013-b413-11b151fae20e", "Onion juice");
        Ingredient naginiVenom = new Ingredient("97128d90-bf57-49e5-b314-417baddb2d78", "Nagini's venom");
        Ingredient unicornBlood = new Ingredient("9b5b5a1e-bc8e-461e-8d8f-41b6fc824f37", "Unicorn blood");
        Ingredient frogBrains = new Ingredient("2ce52cc3-6b48-44ee-8857-d000b6267a8f", "Frog brains");
        Ingredient beetleEyes = new Ingredient("8080858f-7cf0-423c-8d43-97854fc0b725", "Beetle Eyes");

        fergusFungalBudge = new Elixir(
                "0106fb32-b00d-4d70-9841-4b7c2d2cca71", "Fergus Fungal Budge", "Treats ringworm, fungicide",
                "Potential negative side effects if used by elves", null, null, "Unknown",
                Set.of(neemOil, jewelweed, onionJuice), Collections.emptySet(), null
        );

        rudimentaryBodyPotion = new Elixir(
                "06beea01-1e2a-4344-9da3-c27abc4a4580", "Rudimentary body potion",
                "Helps restore non-corporeal wizards to rudimentary bodies/sustains rudimentary bodies",
                null, null, null, "Unknown",
                Set.of(naginiVenom, unicornBlood), Collections.emptySet(), null
        );

        essenceOfInsanity = new Elixir(
                "07944d00-cc87-4b53-b0ab-cfc8efeb8b50", "Essence of Insanity", "Mental instability",
                null, "Green in colour", null, "Advanced",
                Set.of(frogBrains, beetleEyes), Collections.emptySet(), null
        );

        manegroPotion = new Elixir(
                "021b40b3-68ba-4fde-a595-dbb07500674d", "Manegro Potion", "Rapid hair growth",
                null, null, null, "Unknown",
                Collections.emptySet(), Collections.emptySet(), null
        );

        matcher = new Matcher(Set.of(fergusFungalBudge, rudimentaryBodyPotion, essenceOfInsanity, manegroPotion));
    }

    @Test
    void shouldMatchElixirsWithExactIngredients() {
        // GIVEN
        final Set<String> userIngredients = Set.of(
                "4ff5aaf2-776f-43c6-9896-c79c67dc90c5", // Neem oil
                "846be123-c40f-4156-91f4-800305df7485", // Jewelweed
                "a08e7390-a362-4013-b413-11b151fae20e"  // Onion juice
        );

        // WHEN
        final Set<Elixir> result = matcher.findMakeableElixirs(userIngredients);

        // THEN
        assertEquals(1, result.size());
        assertTrue(result.contains(fergusFungalBudge));
    }

    @Test
    void shouldMatchMultipleElixirs() {
        // GIVEN
        final Set<String> userIngredients = Set.of(
                "97128d90-bf57-49e5-b314-417baddb2d78", // Nagini's venom
                "9b5b5a1e-bc8e-461e-8d8f-41b6fc824f37", // Unicorn blood
                "2ce52cc3-6b48-44ee-8857-d000b6267a8f", // Frog brains
                "8080858f-7cf0-423c-8d43-97854fc0b725"  // Beetle Eyes
        );

        // WHEN
        final Set<Elixir> result = matcher.findMakeableElixirs(userIngredients);

        // THEN
        assertEquals(2, result.size());
        assertTrue(result.contains(rudimentaryBodyPotion));
        assertTrue(result.contains(essenceOfInsanity));
    }

    @Test
    void shouldReturnEmptyIfNoElixirsCanBeMade() {
        // GIVEN
        final Set<String> userIngredients = Set.of("non-existent-id");

        // WHEN
        final Set<Elixir> result = matcher.findMakeableElixirs(userIngredients);

        // THEN
        assertTrue(result.isEmpty());
    }

}