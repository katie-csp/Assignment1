// Author: Katie Bailey.
// Date: 01/19/2026
// Class: CSC 422 100
// Project: Assignment 1
// File Name: Main.java
// Description: This file contains the code for assignment 1.
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final int MAX_PETS = 5;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in); 
        ArrayList<Pet> pets = FileHandler.loadPets();

        System.out.println("\nPet Database Program.\n");

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Remove a pet");
            System.out.println("4) Exit program");
            System.out.print("Your choice: ");

            String userChoice = userInput.nextLine().trim();
            System.out.println(); // Space after user's input to improve readability.

            // Section of code that shows all pets in the database.
            if (userChoice.equals("1")) {
                printPets(pets);
            } 

            // Section of code that adds more pets to the database.
            else if (userChoice.equals("2")) {

                if (pets.size() >= MAX_PETS) {
                    System.out.println("Error: Database is full.\n");
                    continue;
                }

                int addedCount = 0;

                while (pets.size() < MAX_PETS) {
                    System.out.print("add pet (name, age): ");
                    String input = userInput.nextLine().trim();

                    if (input.equalsIgnoreCase("done")) {
                        break;
                    }

                    String[] parts = input.split(" ");

                    if (parts.length != 2) {
                        System.out.println("Error: " + input + " is not a valid input.");
                        continue;
                    }

                    String name = parts[0];
                    int age;

                    try {
                        age = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: " + input + " is not a valid input.");
                        continue;
                    }

                    if (age < 1 || age > 20) {
                        System.out.println("Error: " + age + " is not a valid age.");
                        continue;
                    }

                    pets.add(new Pet(name, age));
                    addedCount++;
                }

                System.out.println(addedCount + " pets added.\n");
            }

            // Section of code that updates an existing pet in the database.
            else if (userChoice.equals("3")) {
                printPets(pets);
                System.out.print("Enter ID of pet to remove: ");

                try {
                    int id = Integer.parseInt(userInput.nextLine().trim());

                    if (id < 0 || id >= pets.size()) {
                        System.out.println("Error: ID " + id + " does not exist.\n");
                    } else {
                        pets.remove(id);
                        System.out.println("Pet removed.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid ID.\n");
                }
            }

            // Section of code that removes an existing pet from the database.
            else if (userChoice.equals("4")) {
                FileHandler.savePets(pets);
                System.out.println("Pet data saved. Exiting program.\n");
                break;
            }

            else {
                System.out.println("Invalid choice. Please try again.\n");
            }
        }

        userInput.close();
    }

    // Helper function to print the list of pets in a formatted table.
    public static void printPets(ArrayList<Pet> petList) {
        System.out.println("+----------------------+");
        System.out.printf("| %-3s | %-7s | %-4s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");

        for (int index = 0; index < petList.size(); index++) {
            Pet currentPet = petList.get(index);
            System.out.printf(
                "| %-3d | %-7s | %-4d |\n",
                index,
                currentPet.name,
                currentPet.age
            );
        }

        System.out.println("+----------------------+");
        System.out.println(petList.size() + " rows in set.\n");
    }
}



