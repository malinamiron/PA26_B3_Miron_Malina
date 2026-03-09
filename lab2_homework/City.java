/**
 * Reprezintă o locație de tip oraș în cadrul sistemului.
 * Această clasă extinde {@link Location} și adaugă informații specifice 
 * privind numărul de locuitori (populația).
 * * <p>Fiind marcată ca {@code final}, această clasă nu poate fi extinsă ulterior.</p>
 */
public final class City extends Location {

    /** Numărul de locuitori ai orașului. */
    private int population;

    /**
     * Construiește un nou oraș cu numele, coordonatele și populația specificată.
     *
     * @param name       Numele orașului.
     * @param x          Coordonata X pe hartă.
     * @param y          Coordonata Y pe hartă.
     * @param population Numărul de locuitori.
     */
    public City(String name, int x, int y, int population) {
        super(name, x, y);
        this.population = population;
    }

    /**
     * Returnează populația actuală a orașului.
     *
     * @return Numărul de locuitori ca valoare întreagă.
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Returnează o reprezentare sub formă de șir de caractere a obiectului City.
     * Include detaliile de bază ale locației (nume, coordonate) urmate de populație.
     *
     * @return Un {@code String} ce conține informațiile orașului.
     */
    @Override
    public String toString() {
        return "City: " + super.toString() +
                ", population=" + population;
    }
}