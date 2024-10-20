package patrycja.lab3;

import java.io.Serializable;

public class CountryBean implements Serializable {
    
    private String code;
    private String name;
    private int population;
    private double surfaceArea;

    // Konstruktory
    public CountryBean() {}

    // Gettery i settery
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getSurfaceArea() { // Getter dla powierzchni
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) { // Setter dla powierzchni
        this.surfaceArea = surfaceArea;
    }
}
