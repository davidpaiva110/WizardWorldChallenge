package model;

public record Ingredient(String id, String name) {
    @Override
    public String toString() {
        return '\'' + name + '\'';
    }
}
