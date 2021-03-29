public class Game {
    //code to hide the movie with underscore
    public static String hidingMovieName(String movie){
        int length = movie.length();
        //Creating hidden format of the sting
            String hiddenMovieName = "";
            for(int i = 0;i<length;i++){
                if (movie.charAt(i) != ' '){
                    hiddenMovieName += "_";
                }
                else{
                    hiddenMovieName += " ";
                }
            }
        return hiddenMovieName;
    }


}