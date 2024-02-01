import java.util.*;

public class guessnumber {

   public static void main(String[] args) {

         Scanner scanner = new Scanner(System.in);

         int lowerBound = 1;
         int upperBound = 100;
         int maxAttempts = 10;
         int score = 0;
         int rounds = 0;

         System.out.println("Welcome to the Number Guessing Game!");
         System.out.println("Try to guess the number between " + lowerBound + " and " + upperBound);

         do {
            rounds++;
            int randomNumber = generateRandomNumber(lowerBound, upperBound);
            int attempts = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("\nRound " + rounds + " | Guess the number");

            do {
                  System.out.print("Enter your guessed number : ");
                  int userGuess = scanner.nextInt();
                  attempts++;

                  if (userGuess == randomNumber) {
                     System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                     hasGuessedCorrectly = true;
                  } else if (userGuess < randomNumber) {
                     System.out.println("Too low! Try again.");
                  } else {
                     System.out.println("Too high! Try again.");
                  }

            } while (!hasGuessedCorrectly && attempts < maxAttempts);

            if (!hasGuessedCorrectly) {
                  System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was: " + randomNumber);
            } else {
                  score = score + 10;
                  System.out.println("Your current score: " + score);
            }

            System.out.print("Do you want to play again? (Press 'y' to continue): ");
         } while (scanner.next().equalsIgnoreCase("y"));

      System.out.println("\nTotal rounds played = " + rounds);
      System.out.println("Total score: " + score);
      System.out.println("\nThanks for playing!");

   }

   private static int generateRandomNumber(int lowerBound, int upperBound) {
      Random random = new Random();
      return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
   }

}
