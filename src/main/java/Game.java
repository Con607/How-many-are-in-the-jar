public class Game {
    public static void main(String[] args) {
      //Prompter prompter = new Prompter();
      Jar jar = new Jar();
      
      jar.adminSetup();
      jar.fill();
      jar.startGame();
      
    }
}
