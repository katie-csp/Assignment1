// Author: Katie Bailey.
// Date: 01/24/2026
// Class: CSC 422 100
// Project: Assignment 2
// File Name: FileHandler.java
// Description: This file contains the code for assignment 2. Handles the logic behind
// Loading and saving the pet data.
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private static final String PET_FILE_NAME = "pets.txt";

    // Function to load pets from file.
    public static ArrayList<Pet> loadPets() {
        ArrayList<Pet> loadedPets = new ArrayList<>();

        File petFile = new File(PET_FILE_NAME);
        if (!petFile.exists()) {
            return loadedPets;
        }

        try (Scanner petFileScanner = new Scanner(petFile)) { 
            while (petFileScanner.hasNextLine()) {
                String line = petFileScanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length != 2) continue;

                String name = parts[0]; // Holds the pet's name and splits it at the comma.
                int age; // Holds the pet's age.

                try {
                    age = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    continue;
                }

                loadedPets.add(new Pet(name, age));
            }
        } catch (Exception e) {
            System.out.println("Error: Unable to load pet data from file.");
        }
        return loadedPets;
    }

    // Function to save pets to file.
    public static void savePets(ArrayList<Pet> petsToSave) {
        try (PrintWriter petFileWriter = new PrintWriter(new FileWriter(PET_FILE_NAME))) {
            for (Pet petsToWrite : petsToSave) { 
                petFileWriter.println(petsToWrite.name + "," + petsToWrite.age);
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to save pet data to file.");
        }
    }
}
  
