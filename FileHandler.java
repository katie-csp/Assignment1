// Author: Katie Bailey.
// Date: 01/24/2026
// Class: CSC 422 100
// Project: Assignment 2
// File Name: FileHandler.java
// Description: This file contains the code for assignment 2. Handles the logic behind
// Loading and saving the pet data.
public class FileHandler {
  private static final String file_name = "pets.txt";

  // Function that loads pets from the file into the ArrayList
  public static ArrayList<Pet> loadPets() {
    ArrayList<Pet> pets = new ArrayList<>();

    File file = new File(file_name);
    if (!file.exists()) {
      return pets;
    }

    try (Scanner fileScanner = new Scanner(file)) { 
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine().trim();
        if (line.isEmpty()) continue;

        String[] parts = line.split(",");
        if (parts.length != 2) continue;

        pets.add(new Pet(name, age));
      }
    } catch (Exception e) {
      System.out.println("Error: Unable to load pet data from file.")
    }

    return pets;
  }

  // Function that saves pets from the ArrayList to the file.
  public static void savePets(ArrayList<Pet> pets) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(file_name))) {
      for (Pet pet : pets) { 
        writer.println(pet.name + "," + pet.age);
      }
    } catch (IOException e) {
      System.out.println("Error: Unable to save pet data to file.");
    }
  }
}
  
  
