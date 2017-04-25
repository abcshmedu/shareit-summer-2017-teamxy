package edu.hm.schatter.shareit.models;

public class Copy {
    private final Medium medium;
    private final String owner;

    public Copy(Medium medium, String owner) {
        if (medium == null){
            throw new IllegalArgumentException("medium must not be null");
        }

        if (owner == null){
            throw new IllegalArgumentException("owner must not be null");
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
