package model;

import java.util.Set;

public record Elixir(
        String id,
        String name,
        String effect,
        String sideEffects,
        String characteristics,
        String time,
        String difficulty,
        Set<Ingredient> ingredients,
        Set<Inventors> inventors,
        String manufacturer) {

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder(name);

        result.append("{\n");

        if (effect != null) result.append("  effect='").append(effect).append("'\n");
        if (sideEffects != null) result.append("  sideEffects='").append(sideEffects).append("'\n");
        if (characteristics != null) result.append("  characteristics='").append(characteristics).append("'\n");
        if (time != null) result.append("  time='").append(time).append("'\n");
        if (difficulty != null) result.append("  difficulty='").append(difficulty).append("'\n");
        if (ingredients != null) result.append("  ingredients=").append(ingredients).append("\n");
        if (inventors != null && !inventors.isEmpty()) result.append("  inventors=").append(inventors).append("\n");
        if (manufacturer != null) result.append("  manufacturer='").append(manufacturer).append("'\n");

        result.append('}');
        return result.toString();
    }

}
