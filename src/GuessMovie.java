import java.io.File;
import java.util.Scanner;

//The main class
public class GuessMovie {
    public static void main(String[] args) throws Exception {   

        boolean startGame = true;
        while(startGame)
        {
            //Game Introduction for the user
            System.out.println("\n-----Guess The Movie Game-----");
            System.err.println("A movie name as hidden will be shown, you have to guess the movie (ONE LETTER AT A TIME) to WIN the game. You FAIL the game when wrong guess exceeds 10.\n");
            boolean gameWon = false;

            File file = new File("src/movies.txt");
            Scanner scanner = new Scanner(file);
            //generate a random number bw 1 and 25
            int randomNumber = (int) (Math.random() * 25 + 1);

            //Reading the line from file where line number is equal random number
                int lineNumber = 0;
                String movieName = "";
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    lineNumber++;
                    if(lineNumber == randomNumber){
                        movieName = line;
                        break;
                    }
                }
            scanner.close();
            
            // Creating hidden format of the sting
            String hiddenMovieName = Game.hidingMovieName(movieName);

            char hiddenMovieNameArray[] = hiddenMovieName.toCharArray();
            int wrongGuessCount = 0;
            String wrongLetters = "";

            while (wrongGuessCount <= 10){
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("You are guessing:"+hiddenMovieName);
                System.out.println("You have guessed ("+ wrongGuessCount +") wrong letters :"+ wrongLetters);
                System.out.print("Guess a letter:");
                char guess = scanner2.next().charAt(0);

                int first = -1;
                first = movieName.indexOf(guess);
                if(first!=-1){
                    int last = movieName.lastIndexOf(guess);
                    hiddenMovieNameArray[first] = guess;
                    while (first != last){
                        first = movieName.indexOf(guess, first+1);
                        hiddenMovieNameArray[first] = guess;
                    }
                    
                }
                else{
                    wrongLetters+= guess+ " ";
                    wrongGuessCount++;
                }

                hiddenMovieName = new String(hiddenMovieNameArray);

                //Checking all letter guessed or not
                if (movieName.equals(hiddenMovieName)){
                    System.out.println("You win!\nYou have guessed \'"+movieName+"\' correctly.");
                    gameWon = true;
                    scanner2.reset();
                    break;
                }
                
            }
            //execute when wrong guess count exceeds limit 10, So fail
            if (!gameWon)
            System.out.println("You Fail");
            
            //to restart the game
            System.out.print("Want to restart the game (yes or no):");
            Scanner scanner3 = new Scanner(System.in);
            char choice = scanner3.next().charAt(0);
            choice = Character.toLowerCase(choice);
            if(choice == 'n'){
                startGame = false;
            }
            scanner3.reset();
        }

    }
}
