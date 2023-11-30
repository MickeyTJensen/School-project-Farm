package org.example;

import java.io.*;
import java.util.Scanner;

public class Farm {
    private String name;
    private AnimalManager animalManager;
    private CropManager cropManager;

    public Farm(String name) {
        this.name = name;
        this.cropManager = new CropManager();
        this.animalManager = new AnimalManager(this.cropManager);
        folder.mkdir();
        loadAnimals();
        loadCrops();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            MainMenu(scanner);
        }

        scanner.close();
    }

    private void MainMenu(Scanner scanner) {
        System.out.println("-----Jensens FarmVille-----");
        System.out.println("1. Overlook the farm");
        System.out.println("2. Animals Menu");
        System.out.println("3. Crops Menu");
        System.out.println("4. Save");
        System.out.println("5. ----Exit----");
        System.out.println("Choose Your Option: ");

        int option = readUserInput(scanner);

        switch (option) {
            case 1:
                overlookFarm(scanner, animalManager, cropManager);
                break;
            case 2:
                animalManager.AnimalMenu(scanner, cropManager);
                break;
            case 3:
                cropManager.cropsMenu(scanner, animalManager);
                break;
            case 4:
                saveAnimalData();
                saveCropData();
                break;
            case 5:
                System.out.println("Exiting... Thanks for visiting Jensens FarmVille!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void overlookFarm(Scanner scanner, AnimalManager animalManager, CropManager cropManager) {
        System.out.println("Jensens FarmVille");
        System.out.println("Animals: ");
        animalManager.viewAnimals(scanner);
        System.out.println("Crops: ");
        cropManager.viewCrops(scanner);
    }


    File folder = new File("folder");

    File animalsFile = new File("folder/animals.txt");

    File cropsFile = new File("folder/crops.txt");


    public void saveAnimalData() {
        try {
            FileWriter fw = new FileWriter(animalsFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Animal animal : animalManager.getAnimals()){
                bw.write(animal.toCSV());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {

        }
    }
    private void saveCropData() {
        try {
            FileWriter fw = new FileWriter(cropsFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Crop crop : cropManager.getCrops()){
                bw.write(crop.toCSV());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {

        }
    }


    private void loadAnimals() {
        try (BufferedReader br = new BufferedReader(new FileReader(animalsFile))) {
            String line = br.readLine();
            while (line != null) {
                try {
                    String[] strings = line.split(",");
                    int id = Integer.parseInt(strings[0]);
                    String name = strings[1];
                    int age = Integer.parseInt(strings[2]);
                    String species = strings[3];
                    String cropType = strings[4];
                    int quantity = Integer.parseInt(strings[5]);
                    String description = strings[6];

                    Animal animal = new Animal(id, name, age, species, cropType, quantity, description);
                    animalManager.getAnimals().add(animal);
                } catch (Exception e) {


                }

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error loading animal data: " + e.getMessage());
        }
    }
    private void loadCrops() {
        try (BufferedReader br = new BufferedReader(new FileReader(cropsFile))) {
            String line = br.readLine();
            while (line != null) {
                try {
                    String[] strings = line.split(",");
                    int id = Integer.parseInt(strings[0]);
                    String name = strings[1];
                    String type = strings[2];
                    int quantity = Integer.parseInt(strings[3]);
                    String description = strings[4];

                    Crop crop = new Crop(id, name, type, quantity, description);
                    cropManager.getCrops().add(crop);
                } catch (Exception e) {


                }

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error loading crop data: " + e.getMessage());
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
}