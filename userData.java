
import java.io.*;
import java.util.Scanner;

public class userData {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = "movie_ratings.csv"; // The CSV file to store data

        // Open or create the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            System.out.println("Movie Ratings Program");
            System.out.println("Enter 'exit' when you are done.");

            // Write the header if the file is empty
            File file = new File(fileName);
            if (file.length() == 0) {
                writer.write("Movie Name,Rating\n"); // CSV header
            }

            while (true) {
                // Ask the user for movie name
                System.out.print("Enter movie name: ");
                String movieName = sc.nextLine();
                if (movieName.equalsIgnoreCase("exit")) {
                    break; // Exit the loop if the user types "exit"
                }

                // Ask the user for movie rating
                System.out.print("Enter rating for " + movieName + ": ");
                String rating = sc.nextLine();

                // Write the data into the CSV file
                writer.write(movieName + "," + rating + "\n");
                System.out.println("Movie and rating saved!");
            }
            System.out.println("Program ended. All data has been saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}