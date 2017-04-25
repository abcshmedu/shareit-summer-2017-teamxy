package edu.hm.fachklassen;

public class Disc {
    private final String barcode;
    private final String director;
    private final int fsk;

    private Disc() {
        barcode = "";
        director = "";
        fsk = 0;
    }

    public Disc(String barcode, String director, int fsk) {
        if (barcode == null){
            throw new IllegalArgumentException("barcode must not be null");
        }

        if (director == null){
            throw new IllegalArgumentException("director must not be null");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disc disc = (Disc) o;

        if (fsk != disc.fsk) return false;
        if (!barcode.equals(disc.barcode)) return false;
        return director.equals(disc.director);

    }

    @Override
    public int hashCode() {
        int result = barcode.hashCode();
        result = 31 * result + director.hashCode();
        result = 31 * result + fsk;
        return result;
    }

    @Override
    public String toString() {
        return "Disc{barcode='" + barcode + '\'' + ", director='" + director + '\'' + ", fsk=" + fsk + '}';
    }
}
