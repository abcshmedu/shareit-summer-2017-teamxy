package edu.hm.schatter.shareit.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Data strucutre representing a medium.
 */
@Entity
@Table(name = "TMedium")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Medium implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String title;

    /**
     * Standard constructor.
     * @param title The title of the medium.
     */
    public Medium(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title must not be null");
        }
        this.title = title;
    }

    /**
     * Getter.
     * @return The title of the medium.
     */
    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Medium medium = (Medium) o;

        return title.equals(medium.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
