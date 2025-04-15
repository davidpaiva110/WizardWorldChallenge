package model;

public record Inventors(String id, String firstName, String lastName ) {
    @Override
    public String toString() {
        return '\'' + firstName + ' ' + lastName + '\'';
    }
}
