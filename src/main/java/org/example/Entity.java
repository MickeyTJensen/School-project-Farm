package org.example;

public class Entity {

    private static int nextId = 1;

    private int id;

    private String name;
    private int quantity;
    private String description;
    public Entity(String name, int quantity, String description) {
        this.id = nextId++;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }

    public static int getNextId() {
        return nextId++;
    }

    public static void setNextId(int nextId) {
        Entity.nextId = nextId;
    }

    public Entity(int id, String name, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;

        // Ensure that the nextId is greater than the current id
        nextId = Math.max(nextId, id + 1);
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String toCSV() {
        return "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        } else {
            System.out.println("Error: Quantity cannot be negative.");
        }
    }
}


