import java.util.Scanner;

public class Prompter {
  private Game game;
  private Jar jar;
  
  
  public Prompter() {
    //this.game = game;
    this.jar = jar;
  }
  

  public void sendAdminMessage() {
    System.out.println("****************************");
    System.out.println("    ADMINISTRATOR SETUP");
    System.out.println("****************************");
  }


  public String askForItemName() {
    String itemName;
    Scanner scanner = new Scanner(System.in);
    System.out.print("What type of item?   ");
    itemName = scanner.nextLine();
    return itemName;
  }
  

  public String askForMaxItems(String itemName) {
    boolean isMaxItemsSet = false;
    String MAXIMUM_NUMBER;
    Scanner scanner = new Scanner(System.in);
    System.out.printf("What is the maximum number of %s?  ", itemName);
    MAXIMUM_NUMBER = scanner.nextLine();
    return MAXIMUM_NUMBER;
  }
  
  
  
  public void sendPlayerMessage(String itemName, int MAXIMUM_NUMBER) {
    System.out.println(" ");
    System.out.println("***********************");
    System.out.println("        PLAYER");
    System.out.println("***********************");
    System.out.printf("How many %s are in the jar? Pick a number between 1 and %d %n",
                      itemName, MAXIMUM_NUMBER);
    System.out.printf("Your guess must be less than %d %n%n",
                       MAXIMUM_NUMBER);
    System.out.print("Ready? (press ENTER to start guessing) ");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }


  public String getNumberToGuess() {
    String numberRetrieved;
    System.out.println(" ");
    System.out.print("Guess:  ");
    Scanner scanner = new Scanner(System.in);
    numberRetrieved = scanner.nextLine();
    return numberRetrieved;
  }
  
}