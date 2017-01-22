import java.util.Random;

class Jar {
  private String itemName;
  public int MAXIMUM_NUMBER;
  public String MAXIMUM_NUMBER_STRING;
  boolean isItemSet = false;
  boolean isMaxItemsSet = false;
  int numberOfItems;
  String numberToGuess;
  boolean numberInRange;
  int attempts = 1;
  
  Prompter prompter = new Prompter();
  
  public void adminSetup() {
    prompter.sendAdminMessage();

    getItemName();
    
    getMaxItems();
  }
  
  public void fill() {
    Random random = new Random();
    numberOfItems = random.nextInt(MAXIMUM_NUMBER -1) + 1; // -1 in max value and +1 to the result to avoid number zero.
    //System.out.printf("The random number is %d.%n", numberOfItems);
  }

  public void startGame() {
    prompter.sendPlayerMessage(itemName, MAXIMUM_NUMBER);

    boolean isGuessNumberANumber = false;
    boolean isGuessNumberInRange = false;
    boolean isGuessNumberCorrect = false;
    boolean isGuessNumberBigger = false;
    do {
      numberToGuess = prompter.getNumberToGuess();
      try {
          isGuessNumberANumber = isGuessNumberANumber(numberToGuess);
          isGuessNumberInRange = isGuessNumberInRange(Integer.parseInt(numberToGuess));
          isGuessNumberCorrect = isGuessNumberCorrect(Integer.parseInt(numberToGuess));
        } catch(IllegalArgumentException iae) {
          System.out.printf("%s.  Please try again. %n%n",
                            iae.getMessage());
        }
    } while(!isGuessNumberANumber || !isGuessNumberInRange || !isGuessNumberCorrect);

    System.out.println(" ");
    System.out.println("Nice job!");
    System.out.printf("You got it in %d attempt(s). Try to beat your score!", attempts);
    System.out.println(" ");
  }

  private void getItemName() {
    boolean isItemSet = false;
    do {
      itemName = prompter.askForItemName();
      //System.out.printf("itemName = %s", itemName);
      try {
        isItemSet = isItemNameAcceptable(itemName);
      } catch(IllegalArgumentException iae) {
        System.out.printf("%s.  Please try again. %n%n",
                          iae.getMessage());
      }
    } while(!isItemSet);
  }

  private void getMaxItems() {
    boolean isMaxItemsSet = false;
    do {
      MAXIMUM_NUMBER_STRING = prompter.askForMaxItems(itemName);
      //System.out.printf("MAXIMUM_NUMBER = %d", MAXIMUM_NUMBER);
      try {
        isMaxItemsSet = isMaxItemsAcceptable(MAXIMUM_NUMBER_STRING);
      } catch(IllegalArgumentException iae) {
        System.out.printf("%s.  Please try again. %n%n",
                          iae.getMessage());
      }
    } while(!isMaxItemsSet);
    MAXIMUM_NUMBER = Integer.parseInt(MAXIMUM_NUMBER_STRING);
    //System.out.printf("MAXIMUM_NUMBER_STRING = %s and MAXIMUM_NUMBER = %d %n",
                      //MAXIMUM_NUMBER_STRING, MAXIMUM_NUMBER);
  }

  private boolean isGuessNumberANumber(String numberToGuess) {
    boolean isNumber = numberToGuess.matches("[0-9]+");
    if(!isNumber) {
      throw new IllegalArgumentException("You have to type an integer number");
    }
    return isNumber;
  }
  
  private boolean isGuessNumberInRange(int numberToGuess) {
    boolean isNumberInRange = (numberToGuess <= MAXIMUM_NUMBER && numberToGuess > 0);
    if(!isNumberInRange) {
      throw new IllegalArgumentException("Your guess must be less than " + 
                                        Integer.toString(MAXIMUM_NUMBER) + " and bigger than 0");
    }
    return isNumberInRange;
  }

  private boolean isGuessNumberCorrect(int numberToGuess) {
    boolean isNumberCorrect = numberToGuess == numberOfItems;
    if(!isNumberCorrect) {
      boolean isGuessNumberBigger = isGuessNumberBigger(numberToGuess);
      attempts += 1;
      if(!isGuessNumberBigger) {
        throw new IllegalArgumentException("Your guess is too low");
      } else {
        throw new IllegalArgumentException("Your guess is too high");
      }
    }
    return isNumberCorrect;
  }

  private boolean isGuessNumberBigger(int numberToGuess) {
    boolean isNumberBigger = numberToGuess > numberOfItems;
    return isNumberBigger;
  }

  private boolean isItemNameAcceptable(String itemName) {
    boolean isItemSet = itemName.length() > 0;
    if(!isItemSet) {
      throw new IllegalArgumentException("Item name cant be blank");
    }
    return isItemSet;
  }

  private boolean isMaxItemsAcceptable(String MAXIMUM_NUMBER_STRING) {
    boolean isMaxItemsSet = MAXIMUM_NUMBER_STRING.matches("[0-9]+");
    if(!isMaxItemsSet) {
      throw new IllegalArgumentException("You have to type an integer number");
    }
    return isMaxItemsSet;
  }
  
}