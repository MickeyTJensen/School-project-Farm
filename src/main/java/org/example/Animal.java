package org.example;

import java.util.ArrayList;
import java.util.List;

public class Animal extends Entity {

    private int age;
    private String species;
    private String cropType;
    private String acceptableCropType;
    private List<String> acceptableCropTypes;

    public boolean acceptableCropType(Crop crop) {
        return this.cropType.equals(crop.getType());
    }

    public Animal(String name, int age, String species, String cropType, int quantity, String description) {
        super(name, quantity, description);
        this.age = age;
        this.species = species;
        this.cropType = cropType;
        this.acceptableCropType = cropType;
        this.acceptableCropTypes = new ArrayList<>();
    }

    public Animal(int id, String name, int age, String species, String cropType, int quantity, String description) {
        super(id, name, quantity, description);
        this.age = age;
        this.species = species;
        this.cropType = cropType;
        this.acceptableCropType = cropType;
        this.acceptableCropTypes = new ArrayList<>();
    }

    public int getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }

    public String getCropType() {
        return cropType;
    }

    @Override
    public String toCSV() {
        String regularData = String.format("%s,%s,%d,%s,%s,%d,%s",
                getId(), getName(), getAge(), getSpecies(), getCropType(), getQuantity(), getDescription());

        return regularData + "@" + (acceptableCropTypes != null ? String.join(",", acceptableCropTypes) : "") + "\n";
    }
}