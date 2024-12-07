/*
javac -cp .;stddraw.jar Storm.java
java -cp .;stddraw.jar Storm AL131967
*/
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.ArrayList;
public class movieList {
   // initialize record
   public record Movie(String DirName, String genres, String actorName, String movieTitle, String imdbScore) {
   }

   public static void main (final String[] args) throws Exception {

      final ArrayList<Movie> findMovie = new ArrayList<Movie>();
      // get website stuff
      final String path = 
            "https://raw.githubusercontent.com/emmalu21/Recommendation-System/refs/heads/main/movie_data.csv";
      final URL url = new URL (path);
      try (final InputStream is = url.openStream()) {
         final Scanner sc = new Scanner(is);

         // skip first line
         sc.nextLine();   
         // collect all movie data
         while (sc.hasNextLine()) {
            final String line = sc.nextLine();
            final String [] lineA = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            final String DirName = lineA[1];
            final String genres = lineA[4];
            final String actorName = lineA[5];
            final String movieTitle = lineA[6].substring(0, lineA[6].length() - 1);
            final String imdbScore = lineA[14];
            // final double hurLat = Double.parseDouble(lineA[6].substring(0, lineA[6].length() - 1));

            final Movie info = new Movie (DirName, genres, actorName, movieTitle, imdbScore);
            findMovie.add(info);
         }


         // read from cmd input
         final Scanner input = new Scanner(System.in, "US-ASCII");

         boolean validMovie = false;
         System.out.println("Enter a movie: ");
         String MovieNameInput = input.nextLine();
         MovieNameInput = MovieNameInput.toLowerCase().replaceAll("\\s+", "");

         for (final Movie mData: findMovie) {
            // only if movie names match
            if (MovieNameInput.equals(mData.movieTitle.toLowerCase().replaceAll("\\s+", ""))) {
               validMovie = true;
               System.out.println("Movie: " + mData.movieTitle);
               System.out.println("Directed by: " + mData.DirName);
               System.out.println("Main actor: " + mData.actorName);
               System.out.println("Genres: " + mData.genres);
               System.out.println("Rating: " + mData.imdbScore);
               break; // exit out of loop
            }
         }


         // if invalid ID
         if (!validMovie) {
            System.out.println("Movie not in System");
         }
      }
   }
}

