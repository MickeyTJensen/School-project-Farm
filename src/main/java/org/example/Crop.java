package org.example;

public class Crop extends Entity {

    private String type;

    public Crop(String name, String type, int quantity, String description) {
        super(name, quantity, description);
        this.type = type;
    }

    public Crop(int id, String name, String type, int quantity, String description) {
        super(id, name, quantity, description);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%d,%s\n",
                getId(), getName(), getType(), getQuantity(), getDescription());
    }
}