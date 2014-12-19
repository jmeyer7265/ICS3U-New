package com.bayviewglen.blackjack;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class BlackJack {
    public static int ACE_LOW = 1;
    public static int ACE_HIGH = 11;
    public static int TWO = 2;
    public static int THREE = 3;
    public static int FOUR = 4;
    public static int FIVE = 5;
    public static int SIX = 6;
    public static int SEVEN = 7;	//NO MAGIC NUMBERS!!!!
    public static int EIGHT = 8;
    public static int NINE = 9;
    public static int TEN = 10;
    public static int JACK = 10;	
    public static int QUEEN = 10;	
    public static int KING = 10;	

    public static void main(String[] args) {
        NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(); //uses the currency figure specific to a locale
        Scanner userInput = new Scanner(System.in); // initializes scanner
        Locale playerLocale = null;
        int wallet = 500; // creates a wallet that is static, but can be changed in-game
        boolean done = false; // these loops allow the user to skip the name and local process if they play again
        boolean close = false;
        while (!close) {
            while (!done) {

                String name = getUserName(userInput); //gets name from method
                
                boolean quit = false;
                if (name.equalsIgnoreCase("quit")) { // if the user name is 'quit', the game closes
                    quit = true;
                    done = true;
                    close = true;
                }
                playerLocale = getLocale(userInput, wallet); //gets locale from method
                while (!quit) {
                
                	int bet = getBet(wallet, userInput); //gets bet from method and takes it from wallet
                    wallet -= bet;
                    int userValue = 0;
                    int dealerValue = 0;
                    String[] card = { // I used arrays to randomly select a card value from a list
                        "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
                    };
                    String[] suit = {
                        "H", "C", "S", "D"
                    };
                    String playerCard = "";
                    String dealerCard = ""; // initializing variables for later use
                    int dealerPoints = 0;
                    int dealerAces = 0;
                    for (int i = 0; i <= 1; i++) {
                        int randomNumber = (int)(Math.random() * 13) + 1; // gives the dealer a card, including suit
                        int randomSuit = (int)(Math.random() * 4) + 1;
                        dealerCard += (card[randomNumber - 1]);
                        if(randomNumber == JACK || randomNumber == QUEEN ||randomNumber == KING){
                        	dealerPoints += TEN;
                        }else{
                        dealerPoints += randomNumber;
                        }
                        dealerCard += (suit[randomSuit - 1]);

                        dealerCard += " ";
                    }
                    System.out.println("Dealer:" + dealerCard.substring(0, 3) + " XX"); // hides the dealers second card for the first turn
                    int playersPoints = 0;
                    int playersAces = 0;
                    for (int i = 0; i <= 1; i++) {
                        int randomNumber = (int)(Math.random() * 13) + 1;
                        int randomSuit = (int)(Math.random() * 4) + 1; // gives the player a card, including suit
                        playerCard += (card[randomNumber - 1]);
                        if (randomNumber == ACE_LOW) {
                            playersPoints += ACE_HIGH;
                            playersAces++;

                        } else if (randomNumber == JACK || randomNumber == QUEEN || randomNumber == KING) {
                            playersPoints += TEN;

                        } else {
                            playersPoints += randomNumber;
                        }
                        playerCard += (suit[randomSuit - 1]);
                        playerCard += " ";
                    }
                    System.out.println("You:" + playerCard);

                    boolean hit = true;
                    while (hit) {

                        if (playersPoints < 21) {
                            System.out.println("Please pick from one of the options below: "); // user options for their turn, depending on their money count
                            if (bet * 2 > wallet) {
                                System.out.println("1.Hit\t\t2.Stay");
                            } else {
                                System.out.println("1.Hit\t\t2.Stay\t\t3.Double Down");
                            }
                            String choice = userInput.nextLine();
                            int userChoice = 0;
                            try {
                                userChoice = Integer.parseInt(choice); // changing userinput to an int
                            } catch (NumberFormatException ex) {}
                            if (userChoice == 1) {

                                System.out.println("Dealer:" + dealerCard.substring(0, 3) + " XX");

                                int randomNumber = (int)(Math.random() * 13) + 1;
                                int randomSuit = (int)(Math.random() * 4) + 1;		// if 'hit', gives the user another card
                                playerCard += (card[randomNumber - 1]);
                                if (randomNumber == ACE_LOW) {
                                    playersPoints += ACE_HIGH;
                                    playersAces++;
                                } else if (randomNumber == JACK || randomNumber == QUEEN || randomNumber == KING) {
                                    playersPoints += TEN;
                                } else {
                                    playersPoints += randomNumber;
                                }
                                playerCard += (suit[randomSuit - 1]);

                                playerCard += " ";
                                System.out.println("You:" + playerCard);

                                if (playersPoints > 21 && playersAces > 0) {	//changes the aces from high to low
                                    playersPoints -= 10;
                                    playersAces--;
                                }
                                if (playersPoints > 21) {
                                    System.out.println("You busted!");		// player busts if > 21, bet is taken. player turn loop ends
                                    wallet -= bet;
                                    hit = false;
                                }

                            } else if (userChoice == 2) {
                                System.out.println("You have " + moneyFormatter.format(wallet)); // displays money, player turn loop ends
                                hit = false;

                                if (dealerPoints < 17) {
                                    for (int i = 0; i <= 1; i++) {
                                        int randomNumber = (int)(Math.random() * 13) + 1;	//dealer AI
                                        int randomSuit = (int)(Math.random() * 4) + 1;
                                        dealerCard += (card[randomNumber - 1]);
                                        if (randomNumber == ACE_LOW) {
                                            dealerPoints += ACE_HIGH;
                                            dealerAces++;

                                        } else if (randomNumber == JACK || randomNumber == QUEEN || randomNumber == KING) {
                                            dealerPoints += TEN;
                                        } else {
                                            dealerPoints += randomNumber;
                                        }
                                        dealerCard += (suit[randomSuit - 1]);
                                        dealerCard += " ";
                                        System.out.println("Dealer: " + dealerCard);
                                        System.out.println("Player: " + playerCard);
                                    }
                                } else if (dealerPoints > 17) {
                                    System.out.println("");
                                }
                                if (dealerPoints > 21 || dealerPoints < playersPoints) {	//dealer AI decides who wins

                                    System.out.println("You Win!");
                                    wallet += bet * 2;
                                } else {
                                    System.out.println("");
                                }
                                if (dealerPoints > playersPoints && dealerPoints < 21) {

                                    System.out.println(" You lose :(");
                                    wallet -= bet;
                                    System.out.println("");
                                }
                                if (dealerPoints == playersPoints) {
                                    System.out.println(" You lose :(");
                                    wallet -= bet;
                                    System.out.println("");
                                }
                            } else if (userChoice == 3 && bet * 2 < wallet) {
                                System.out.println("Dealer:" + dealerCard);			//doubles bet, makes sure that bet*2 isn't > wallet
                                int randomNumber = (int)(Math.random() * 13) + 1;
                                int randomSuit = (int)(Math.random() * 4) + 1;
                                playerCard += (card[randomNumber - 1]);
                                if (randomNumber == ACE_LOW) {
                                    playersPoints += ACE_HIGH; 		//gives the user one card
                                    playersAces++;
                                } else if (randomNumber == JACK || randomNumber == QUEEN || randomNumber == KING) {
                                    playersPoints += TEN;
                                } else {
                                    playersPoints += randomNumber;
                                }
                                playerCard += (suit[randomSuit - 1]);

                                playerCard += " ";
                                System.out.println("You:" + playerCard);

                                wallet -= bet * 2;

                                System.out.println("You have " + moneyFormatter.format(wallet));
                                System.out.println("");
                                hit = false;
                                if (dealerPoints < 17) {
                                    for (int i = 0; i <= 1; i++) {
                                        randomNumber = (int)(Math.random() * 13) + 1;
                                        randomSuit = (int)(Math.random() * 4) + 1;
                                        dealerCard += (card[randomNumber - 1]);        //dealer AI
                                        if (randomNumber == ACE_LOW) {
                                            dealerPoints += ACE_HIGH;
                                            dealerAces++;

                                        } else if (randomNumber == JACK || randomNumber == QUEEN || randomNumber == KING) {
                                            dealerPoints += TEN;
                                        } else {
                                            dealerPoints += randomNumber;
                                        }
                                        dealerCard += (suit[randomSuit - 1]);
                                        dealerCard += " ";
                                        System.out.println("Dealer: " + dealerCard);
                                        System.out.println("Player: " + playerCard);
                                    }
                                } else if (dealerPoints > 17) {
                                    System.out.println("");
                                }
                                if (dealerPoints > 21 || dealerPoints < playersPoints) {

                                    System.out.println("You Win!");			//dealer decides who wins
                                    wallet += bet * 2;
                                } else {
                                    System.out.println("");
                                }
                                if (dealerPoints > playersPoints && dealerPoints < 21) {

                                    System.out.println(" You lose :(");
                                    wallet -= bet;
                                    System.out.println("");
                                }
                                if (dealerPoints == playersPoints) {
                                    System.out.println(" You lose :(");
                                    wallet -= bet;
                                    System.out.println("");
                                }
                            } else {
                                System.out.println("INVALID INPUT. Please enter again");
                            }
                        } else if (playersPoints == 21) {
                            System.out.println("You Win!");
                            wallet += bet * 2;
                        } else if (playersPoints > 21) {
                            if (playersPoints > 21 && playersAces > 0) {
                                playersPoints -= 10;
                                playersAces--;
                            } else {

                            }

                        }
                    }
                    if (wallet == 0) {			// asks the user if they want to play again
                        done = true;
                    } else {
                        System.out.println("would you like to play again? " + "type 1 for yes 	 2 for no");
                        String userEnd = userInput.nextLine();
                        int end = 0;
                        try {
                            end = Integer.parseInt(userEnd);
                        } catch (NumberFormatException ex) {}


                        if (end == 2) {
                          quit = true;
                        
                        }
                        }
                    }

                }
            }
        	
        
        }
    

    private static int getBet(int wallet, Scanner userInput) {		//getbet method
        boolean validBet = false;
        int userBet = 0;
        while (!validBet) {
            System.out.println("Please enter your bet:");
            String bet = userInput.nextLine();

            try {
                userBet = Integer.parseInt(bet);
            } catch (NumberFormatException ex) {}			//parses bet entered to compare against wallet, also makes sure that bet cant be 0, negative, etc.

            if (userBet > wallet) {
                System.out.println("You do not have the money! Enter again: ");
            } else if (userBet <= 0) {
                System.out.println("INVALID BET. Please enter again.");
            } else {

                validBet = true;
            }
        }
        return userBet;

    }

    private static Locale getLocale(Scanner userInput, int wallet) {
        boolean validLocale = false;
        while (!validLocale) {
            System.out.println("1. Canada");
            System.out.println("2. French Canada");
            System.out.println("3. United States");
            System.out.println("4. China");
            System.out.println("5. France ");		//locale method
            System.out.println("6. Germany");
            System.out.println("7. Italy");
            System.out.println("8. Japan");
            System.out.println("9. Korea");
            System.out.println("10. Taiwan");
            System.out.println("11. United Kingdom");
            System.out.println("");
            System.out.println("Please enter the number that corresponds to your location:");

            String countryName = userInput.nextLine();

            int country = 0;
            try {
                country = Integer.parseInt(countryName);		//parses to match a locale #
            } catch (NumberFormatException ex) {

            }

            if (country == 1) {
                NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.CANADA);
                System.out.println("you picked the location " + Locale.CANADA + ". You have " + moneyFormatter.format(wallet));
                return Locale.CANADA;

            } else if (country == 2) {
                NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);
                System.out.println("you picked the location " + Locale.CANADA_FRENCH + ". You have " + moneyFormatter.format(wallet));
                return Locale.CANADA_FRENCH;

            } else if (country == 3) {
                NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
                System.out.println("you picked the location " + Locale.US + ". You have " + moneyFormatter.format(wallet));
                return Locale.US;
            } else if (country == 4) {
                NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.CHINA);
                System.out.println("you picked the location " + Locale.CHINA + ". You have " + moneyFormatter.format(wallet));
                return Locale.CHINA;

            } else if (country == 5) {
                NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                System.out.println("you picked the location " + Locale.FRANCE + ". You have " + moneyFormatter.format(wallet));
                return Locale.FRANCE;

            } else if (country == 6) {
                NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
                System.out.println("you picked the location " + Locale.GERMANY + ". You have " + moneyFormatter.format(wallet));
                return Locale.GERMANY;

            } else if (country == 7) {
                NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
                System.out.println("you picked the location " + Locale.ITALY + ". You have " + moneyFormatter.format(wallet));
                return Locale.ITALY;

            } else if (country == 8) {
                NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.JAPAN);
                System.out.println("you picked the location " + Locale.JAPAN + ". You have " + moneyFormatter.format(wallet));
                return Locale.JAPAN;

            } else if (country == 9) {
                NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
                System.out.println("you picked the location " + Locale.KOREA + ". You have " + moneyFormatter.format(wallet));
                return Locale.KOREA;

            } else if (country == 10) {
                NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.TAIWAN);
                System.out.println("you picked the location " + Locale.TAIWAN + ". You have " + moneyFormatter.format(wallet));
                return Locale.TAIWAN;

            } else if (country == 11) {
                NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.UK);
                System.out.println("you picked the location " + Locale.UK + ". You have " + moneyFormatter.format(wallet));
                return Locale.UK;

            } else {
                System.out.println("INVALID INPUT! Please enter again");		// in case the user is stupid 
                System.out.print("");
            }
        }
        return null;
    }

    private static String getUserName(Scanner userInput) {		//gets user name
        boolean validName = false;
        String name = "";
        while (!validName) {
            System.out.println("Please enter your name: ");
            name = userInput.nextLine();

            if (!name.trim().equals("")) {		//name is valid if it has no spaces
                validName = true;

            }
        }

        return name;

    }
}