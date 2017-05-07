package edu.hm.schatter.shareit.models;

public class Copy {
    private final Medium medium;
    private final String owner;

    public Copy(Medium medium, String owner) {
        if (medium == null) {
            throw new IllegalArgumentException("medium must not be null");
        }

        if (owner == null) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Copy copy = (Copy) o;

        if (!medium.equals(copy.medium)) {
            return false;
        }
        return owner.equals(copy.owner);

    }

    @Override
    public int hashCode() {
        int result = medium.hashCode();
        result = 31 * result + owner.hashCode();
        return result;
    }
}
