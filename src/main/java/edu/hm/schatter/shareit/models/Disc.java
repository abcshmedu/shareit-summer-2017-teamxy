package edu.hm.schatter.shareit.models;

public class Disc extends Medium {
    public static final int MIN_FSK = 0;
    public static final int MAX_FSK = 18;

    private final String barcode;
    private final String director;
    private final int fsk;

    private Disc() {
        super("");
        barcode = "";
        director = "";
        fsk = 0;
    }

    public Disc(String title, String barcode, String director, int fsk) {
        super(title);

        if (barcode == null) {
            throw new IllegalArgumentException("barcode must not be null");
        }

        if (director == null) {
            throw new IllegalArgumentException("director must not be null");
        }

        if (!isValidFSK(fsk)) {
            throw new IllegalArgumentException("fsk must be between 0 and 18");
        }

        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getDirector() {
        return director;
    }

    public int getFsk() {
        return fsk;
    }

    public boolean hasValidFSK() {
        return isValidFSK(fsk);
    }

    private boolean isValidFSK(int fsk) {
        return fsk <= MAX_FSK && fsk >= MIN_FSK;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }

        if (this == o) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }

        Disc disc = (Disc) o;

        if (fsk != disc.fsk) {
            return false;
        }
        if (!barcode.equals(disc.barcode)) {
            return false;
        }
        return director.equals(disc.director);

    }

    @Override
    public int hashCode() {
        int result = barcode.hashCode();
        result = 31 * result + director.hashCode();
        result = 31 * result + fsk;
        return result;
    }
}
