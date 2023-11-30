package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class CropManager {
    private ArrayList<Crop> crops;
    private ArrayList<Animal> animals;

    public CropManager() {
        crops = new ArrayList<>();
        animals = new ArrayList<>();
    }

    public void listOfCrops(){
        crops.add(new Crop(12,"Wheat", "Grain", 1, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(13,"Corn", "Grain", 10, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(14,"Barley", "Grain", 5, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(15,"Oats", "Grain", 5, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(16,"Alfalfa", "Grass", 3, "Feed to: Horse, Cow, Sheep and Donkey"));
        crops.add(new Crop(17,"Timothy Hay", "Grass", 4, "Feed to: Horse, Cow, Sheep and Donkey"));
        crops.add(new Crop(18,"Carrots", "Vegetables", 6, "Feed to: Pig, and Rabbit"));
        crops.add(new Crop(19,"Lettuce", "Vegetables", 12, "Feed to: Pig and Rabbit"));
        crops.add(new Crop(20,"Soybeans", "Grain", 14, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(21,"Rye", "Grain", 8, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(22,"Wheat", "Grain", 1, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(23,"Red Meat", "Meat", 10, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(24,"Chicken", "Meat", 10, "Feed to: Chicken, Duck and Turkey"));
    }

    public void cropsMenu(Scanner scanner, AnimalManager animalManager) {
        while (true) {
            System.out.println("-----Crops Menu-----");
            System.out.println("1. View Crops");
            System.out.println("2. Add Crop");
            System.out.println("3. Remove Crop");
            System.out.println("4. Back to Main Menu");
            System.out.println("Choose Your Option: ");

            int option = readUserInput(scanner);

            switch (option) {
                case 1:
                    viewCrops(scanner);
                    break;
                case 2:
                    addCrop(scanner);
                    break;
                case 3:
                    removeCrop(scanner);
                    break;
                case 4:
                    return; // Exit the method to go back to the main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    public void addCrop(Scanner scanner) {
        System.out.println("Enter the name of the crop:");
        String name = scanner.nextLine();

        // Crop type selection menu
        System.out.println("Select a crop type:");
        System.out.println("1. Grass");
        System.out.println("2. Grain");
        System.out.println("3. Vegetable");
        System.out.println("4. Meat");

        String type;
        int choice;
        do {
            System.out.print("Enter the Crops number: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    type = "Grass";
                    break;
                case 2:
                    type = "Grain";
                    break;
                case 3:
                    type = "Vegetable";
                    break;
                case 4:
                    type = "Meat";
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
                    type = null;
            }
        } while (type == null);

        System.out.println("Enter the quantity of the crop:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter a description for the crop:");
        String description = scanner.nextLine();

        int id = Entity.getNextId();
        Crop newCrop = new Crop(id, name, type, quantity, description);

        // Add the new crop to the list of crops
        crops.add(newCrop);

        System.out.println("You added a " + newCrop.getName() + " to the farm.");
    }


    public void viewCrops(Scanner scanner) {
        if (crops.isEmpty()) {
            System.out.println("There are no crops on the farm.");
        } else {
            System.out.println("List of crops:");
            for (Crop crop : crops) {
                System.out.print("ID: " + crop.getId() + " ");
                System.out.print("Name: " + crop.getName() + " ");
                System.out.print("Type: " + crop.getType() + " ");
                System.out.print("Description: " + crop.getDescription() + " ");
                System.out.print("Quantity: " + crop.getQuantity() + " ");
                System.out.println();
            }
            System.out.println();
        }
    }
    public ArrayList<Crop> getCrops() {
        return crops;
    }

    public Crop selectCrop(Scanner scanner) {
        System.out.println("Select a crop by entering its ID:");

        viewCrops(scanner);

        int selectedCropId = readUserInput(scanner);

        for (Crop crop : crops) {
            if (crop.getId() == selectedCropId) {
                return crop;
            }
        }

        System.out.println("Invalid crop selection.");
        return null;
    }
    public void removeCrop(Scanner scanner) {
        System.out.println("Select a crop to remove: ");
        for (int i = 0; i < crops.size(); i++) {
            if (crops.get(i).getQuantity() > 0) {
                System.out.println((i + 1) + ". " + crops.get(i).getName());
            }
        }

        // Get user input for the selected crop
        int selectedCropIndex;
        do {
            System.out.print("Enter the corresponding number: ");
            selectedCropIndex = scanner.nextInt();
            scanner.nextLine();

            if (selectedCropIndex < 1 || selectedCropIndex > crops.size() || crops.get(selectedCropIndex - 1).getQuantity() == 0) {
                System.out.println("Invalid choice. Please enter a valid number.");
            }
        } while (selectedCropIndex < 1 || selectedCropIndex > crops.size() || crops.get(selectedCropIndex - 1).getQuantity() == 0);

        Crop selectedCrop = crops.get(selectedCropIndex - 1);

        // Remove the selected crop from the list
        crops.remove(selectedCrop);

        System.out.println(selectedCrop.getName() + " removed from the farm.");
    }

    private int readUserInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }
    }
}


