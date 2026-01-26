
public class FileHandler {
  private static final String file_name = "pets.txt";

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
  
  
