package service;

import model.Elixir;

import java.util.Set;
import java.util.stream.Collectors;

public class Matcher {

    private final Set<Elixir> elixirs;

    public Matcher(final Set<Elixir> elixirs) {
        this.elixirs = elixirs;
    }

    /**
     * Finds elixirs that can be made with the given ingredients.
     *
     * @param userIngredientIds a set of ingredient ids
     * @return a set of elixirs that can be made with the given ingredients
     */
    public Set<Elixir> findMakeableElixirs(final Set<String> userIngredientIds) {
        return this.elixirs.stream()
                .filter(elixir -> elixir.ingredients() != null && !elixir.ingredients().isEmpty())
                .filter(elixir -> elixir.ingredients().stream()
                        .allMatch(ingredient -> userIngredientIds.contains(ingredient.id())) )
                .collect(Collectors.toSet());
    }

}