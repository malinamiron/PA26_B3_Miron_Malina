import java.util.Objects;

/**
 * Reprezintă conceptul abstract al unei locații geografice într-un sistem de coordonate 2D.
 * Aceasta servește ca clasă de bază pentru toate tipurile specifice de puncte de interes.
 * * <p>Clasa este definită ca {@code sealed}, permițând moștenirea doar către clasele:
 * {@link City}, {@link AirPort} și {@link GasStation}.</p>
 */
public abstract sealed class Location
        permits City, AirPort, GasStation {

    /** Numele identificator al locației. */
    protected String name;

    /** Coordonata orizontală (X) a locației. */
    protected int x;

    /** Coordonata verticală (Y) a locației. */
    protected int y;

    /**
     * Construiește o nouă locație cu un nume și coordonate specifice.
     *
     * @param name Numele locației.
     * @param x    Poziția pe axa X.
     * @param y    Poziția pe axa Y.
     */
    public Location(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * Returnează numele locației.
     *
     * @return {@code String} reprezentând numele.
     */
    public String getName() {
        return name;
    }

    /**
     * Returnează coordonata X.
     *
     * @return Valoarea întregie a coordonatei X.
     */
    public int getX() {
        return x;
    }

    /**
     * Returnează coordonata Y.
     *
     * @return Valoarea întregie a coordonatei Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Oferă o reprezentare textuală a locației, incluzând numele și coordonatele sale.
     *
     * @return Un șir de caractere formatat cu atributele clasei.
     */
    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Verifică dacă această locație este egală cu un alt obiect.
     * Două locații sunt considerate egale dacă au același nume și aceleași coordonate (x, y).
     *
     * @param o Obiectul de comparat.
     * @return {@code true} dacă obiectele sunt identice din punct de vedere al datelor; {@code false} altfel.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Location location)) return false;
        return getX() == location.getX() &&
                getY() == location.getY() &&
                getName().equals(location.getName());
    }
}