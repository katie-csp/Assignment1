// Author: Katie Bailey.
// Date: 01/19/2026
// Class: CSC 422 100
// Project: Assignment 1
// File Name: Main.java
// Description: This file contains the code for assignment 1.
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in); 
        ArrayList<Pet> pets = new ArrayList<>();

        System.out.println("\nPet Database Program.\n");

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Update an existing pet");
            System.out.println("4) Remove an existing pet");
            System.out.println("5) Search pets by name");
            System.out.println("6) Search pets by age");
            System.out.println("7) Exit program");
            System.out.print("Your choice: ");

            String userChoice = userInput.nextLine().trim();
            System.out.println(); // Space after user's input to improve readability.

            // Section of code that shows all pets in the database.
            if (userChoice.equals("1")) {
                printPets(pets);
            } 

            // Section of code that adds more pets to the database.
            else if (userChoice.equals("2")) {
                while (true) {
                    System.out.print("add pet (name, age): ");
                    String input = userInput.nextLine().trim();
                    if (input.equalsIgnoreCase("done")) break;

                    String[] parts = input.split(" ");
                    if (parts.length != 2) {
                        System.out.println("Invalid input. Enter 'name age'.\n");
                        continue;
                    }

                    try {
                        String name = parts[0];
                        int age = Integer.parseInt(parts[1]);
                        pets.add(new Pet(name, age));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age. Enter a number.\n");
                    }
                }
                System.out.println("Pets added.\n");
            } 

            // Section of code that updates an existing pet in the database.
            else if (userChoice.equals("3")) {
                printPets(pets);
                System.out.print("Enter ID of pet to update: ");
                try {
                    int id = Integer.parseInt(userInput.nextLine().trim());
                    if (id >= 0 && id < pets.size()) {
                        Pet updatedPet = pets.get(id);
                        System.out.print("Enter new name: ");
                        updatedPet.name = userInput.nextLine().trim();
                        System.out.print("Enter new age: ");
                        updatedPet.age = Integer.parseInt(userInput.nextLine().trim());
                        System.out.println("Pet updated.\n");
                    } else {
                        System.out.println("Invalid ID.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.\n");
                }
            } 

            // Section of code that removes an existing pet from the database.
            else if (userChoice.equals("4")) {
                printPets(pets);
                System.out.print("Enter ID of pet to remove: ");
                try {
                    int id = Integer.parseInt(userInput.nextLine().trim());
                    if (id >= 0 && id < pets.size()) {
                        pets.remove(id);
                        System.out.println("Pet removed.\n");
                    } else {
                        System.out.println("Invalid ID.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.\n");
                }
            } 

            // Section of code that searches pets by name.
            else if (userChoice.equals("5")) {
                System.out.print("Enter name to search: ");
                String nameSearch = userInput.nextLine().trim().toLowerCase();
                ArrayList<Integer> petID = new ArrayList<>();
                for (int i = 0; i < pets.size(); i++) {
                    if (pets.get(i).name.toLowerCase().equals(nameSearch)) {
                        petID.add(i);
                    }
                }

                printPetsWithIds(pets, petID);
            } 

            // Section of code that searches pets by age.
            else if (userChoice.equals("6")) {
                System.out.print("Enter age to search: ");
                int ageSearch = Integer.parseInt(userInput.nextLine().trim());
                ArrayList<Integer> petID = new ArrayList<>();
                    for (int i = 0; i < pets.size(); i++) {
                        if (pets.get(i).age == ageSearch) {
                            petID.add(i);
                        }
                    }

                    printPetsWithIds(pets, petID);
            } 
            // Section of code that exits the program.
            else if (userChoice.equals("7")) {
                System.out.println("Exiting program.\n");
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

    // Helper function to print pets with their IDs from a list of petIDs.
    public static void printPetsWithIds(ArrayList<Pet> pets, ArrayList<Integer> petID) {
        System.out.println("+----------------------+");
        System.out.printf("| %-3s | %-7s | %-4s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");

        for (int petIndex : petID) {
            Pet matchedPet = pets.get(petIndex);
            System.out.printf(
                "| %-3d | %-7s | %-4d |\n",
                petIndex,
                matchedPet.name,
                matchedPet.age
            );
        }

        System.out.println("+----------------------+");
        System.out.println(petID.size() + " rows in set.\n");
    }
}

