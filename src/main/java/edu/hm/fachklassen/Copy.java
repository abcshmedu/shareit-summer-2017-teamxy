package edu.hm.fachklassen;

public class Copy {
    private final Medium medium;
    private final String owner;

    public Copy(Medium medium, String owner) {
        if (medium == null){
            throw new IllegalArgumentException("must not be null");
        }

        if (owner == null){
            throw new IllegalArgumentException("must not be null");
        }

        this.medium = medium;
        this.owner = owner;
    }

    public Medium getMedium() {
        return medium;
    }

    public String getUsername() {
        return owner;
    }
}
