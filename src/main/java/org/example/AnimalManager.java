package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class AnimalManager {

    private ArrayList<Animal> animals;
    private ArrayList<Crop> crops;
    Scanner scanner = new Scanner(System.in);
    private CropManager cropManager;


    public AnimalManager(CropManager cropManager) {
        this.cropManager = cropManager;
        animals = new ArrayList<>();
        crops = new ArrayList<>();
    }

    public void listOfAnimals() {
        animals.add(new Animal(1, "Horse", 8, "Mammal", "Grass", 5, "Default description"));
        animals.add(new Animal(2, "Cow", 6, "Mammal", "Grass", 10, "We raise cows for dairy production."));
        animals.add(new Animal(3, "Chicken", 2, "Bird", "Grain", 20, "We have chickens that lay eggs."));
        animals.add(new Animal(4, "Sheep", 4, "Mammal", "Grass", 15, "Our sheep provides wool.."));
        animals.add(new Animal(5, "Pig", 3, "Mammal", "Vegetables", 8, "We raise pigs for pork production."));
        animals.add(new Animal(6, "Goat", 4, "Mammal", "Grass", 12, "We have goats that produce milk and cheese."));
        animals.add(new Animal(7, "Rabbit", 1, "Mammal", "Vegetables", 1, "We raise rabbits as pets."));
        animals.add(new Animal(8, "Duck", 2, "Bird", "Grain", 12, "Our ducks are kept for their eggs."));
        animals.add(new Animal(9, "Turkey", 2, "Bird", "Grain", 5, "We have turkeys for meat production."));
        animals.add(new Animal(10, "Donkey", 7, "Mammal", "Grass", 4, "We use donkeys for farm work."));
        animals.add(new Animal(11, "Tiger", 7, "Mammal", "Meat", 4, "Wraaaaarw!"));
    }

    public void AnimalMenu(Scanner scanner, CropManager cropManager) {
        while (true) {
            System.out.println("-----Animals Menu-----");
            System.out.println("1. View Animals");
            System.out.println("2. Add Animal");
            System.out.println("3. Remove Animal");
            System.out.println("4. Feed Animal");
            System.out.println("5. Back to Main Menu");
            System.out.println("Choose Your Option: ");

            int option = readUserInput(scanner);

            switch (option) {
                case 1:
                    viewAnimals(scanner);
                    break;
                case 2:
                    addAnimal(scanner);
                    break;
                case 3:
                    removeAnimal(scanner);
                    break;
                case 4:
                    Animal selectedAnimal = selectAnimal(scanner, (ArrayList<Animal>) animals);
                    if (selectedAnimal != null) {
                        feedAnimal(selectedAnimal, scanner);
                    }
                    break;
                case 5:
                    return; // Exit the method to go back to the main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    public void addAnimal(Scanner scanner) {
        System.out.println("Enter the name of the animal:");
        String name = scanner.nextLine();

        System.out.println("Enter the age of the animal:");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the species of the animal:");
        String species = scanner.nextLine();

        // Crop type selection menu
        System.out.println("Select a crop type for the animal:");
        System.out.println("1. Grass");
        System.out.println("2. Grain");
        System.out.println("3. Vegetable");
        System.out.println("4. Meat");

        String cropType;
        int choice;
        do {
            System.out.print("Enter the corresponding number: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cropType = "Grass";
                    break;
                case 2:
                    cropType = "Grain";
                    break;
                case 3:
                    cropType = "Vegetable";
                    break;
                case 4:
                    cropType = "Meat";
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
                    cropType = null;
            }
        } while (cropType == null);

        System.out.println("Enter the quantity of the animal:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter a description for the animal:");
        String description = scanner.nextLine();

        int id = Entity.getNextId();
        Animal newAnimal = new Animal(id, name, age, species, cropType, quantity, description);

        // Add the new animal to the list
        animals.add(newAnimal);

        System.out.println("You added a " + newAnimal.getName() + " to the farm.");
    }

    public void viewAnimals(Scanner scanner) {

        if (animals.isEmpty()) {
            System.out.println("There are no animals on the farm.");
        } else {
            System.out.println("List of animals:");
            for (Animal animal : animals) {
                System.out.print("ID: " + animal.getId() + " ");
                System.out.print("Name: " + animal.getName() + " ");
                System.out.print("Age: " + animal.getAge() + " ");
                System.out.print("Species: " + animal.getSpecies() + " ");
                System.out.print("Crop Type: " + animal.getCropType() + " ");
                System.out.print("Description: " + animal.getDescription() + " ");
                System.out.print("Quantity: " + animal.getQuantity() + " ");
                System.out.println();
            }
            System.out.println();
        }
    }

    public Animal selectAnimal(Scanner scanner, ArrayList<Animal> animalsList) {

            System.out.println("Select an animal by entering its ID:");

            for (Animal animal : animalsList) {
                System.out.println("ID: " + animal.getId() + ", Name: " + animal.getName());
            }

            int selectedAnimalId = readUserInput(scanner);

            for (Animal animal : animalsList) {
                if (animal.getId() == selectedAnimalId) {
                    return animal;
                }
            }

            System.out.println("Invalid animal selection.");
            return null;
        }



    public void removeAnimal(Scanner scanner) {
        System.out.println("Select an animal to remove: ");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println((i + 1) + ". " + animals.get(i).getName());
        }

        // Get user input for the selected animal
        int selectedAnimalIndex;
        do {
            System.out.print("Enter the corresponding number: ");
            selectedAnimalIndex = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (selectedAnimalIndex < 1 || selectedAnimalIndex > animals.size()) {
                System.out.println("Invalid choice. Please enter a valid number.");
            }
        } while (selectedAnimalIndex < 1 || selectedAnimalIndex > animals.size());

        Animal selectedAnimal = animals.get(selectedAnimalIndex - 1);

        // Remove the selected animal from the list
        animals.remove(selectedAnimal);

        System.out.println(selectedAnimal.getName() + " removed from the farm.");
    }

    public void feedAnimal(Animal selectedAnimal, Scanner scanner) {
        boolean acceptableCropType = false;

        for (Crop crop : cropManager.getCrops()) {
            if (selectedAnimal.acceptableCropType(crop)) {
                acceptableCropType = true;
                break;
            }
        }

        if (!acceptableCropType) {
            System.out.println("There is no acceptable crop type specified for this animal. Please add a new crop.");
            // Här kan du lägga till en anrop för att lägga till en ny gröda om du vill.
            return;
        }

        Crop selectedCrop;
        do {
            selectedCrop = cropManager.selectCrop(scanner);

            if (selectedCrop == null) {
                System.out.println("No crop selected or crop not found.");
                return;
            }

            if (!selectedAnimal.acceptableCropType(selectedCrop)) {
                System.out.println(selectedAnimal.getName() + " cannot eat " + selectedCrop.getType());
            }
        } while (!selectedAnimal.acceptableCropType(selectedCrop));

        if (selectedCrop.getQuantity() > 0) {
            selectedCrop.decreaseQuantity();
            System.out.println(selectedAnimal.getName() + " has been fed with " + selectedCrop.getType() +
                    " and decreased by 1.");
        } else {
            System.out.println("Not enough " + selectedCrop.getType() + " available.");
        }
    }

    private static int readUserInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }
    }
    public ArrayList<Animal> getAnimals() {
        return animals;
    }
}


