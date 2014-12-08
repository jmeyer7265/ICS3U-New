package com.bayviewglen.blackjack;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class BlackJack {

	public static void main(String[] args) {
		 NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
		Scanner userInput = new Scanner(System.in);
		Locale playerLocale = null;
		boolean done = false;
		while (!done) {
			int wallet = 500;
			String name = getUserName(userInput);
			playerLocale = getLocale(userInput, wallet);
			String bet = getBet(wallet, userInput);
			
			int userValue=0;
			int dealerValue=0;
			String [] card = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
			String [] suit = {"H","C","S","D"};
			String playerCard ="";
			String dealerCard ="";
		
			for(int i = 0; i <=1; i++){
				int randomNumber = (int)( Math.random()*13);
				int randomSuit = (int)( Math.random()*4);
				dealerCard += (card[randomNumber]);
				
				dealerCard += (suit[randomSuit]);
				
				dealerCard += " ";
			}
			System.out.println( "Dealer:" + dealerCard.substring(0,3) + " XX");
			
			for(int i = 0; i <=1; i++){
				int randomNumber = (int)( Math.random()*13);
				int randomSuit = (int)( Math.random()*4);
				playerCard += (card[randomNumber]);
				
				playerCard += (suit[randomSuit]);
				
				playerCard += " ";
			}
			System.out.println( "You:" + playerCard);
			
			boolean validChoice = false;
			
			while(!validChoice){
				System.out.println("Please pick from one of the options below: ");
				System.out.println("1.hit		2.stay		3.double down");
				String choice = userInput.nextLine();
				int userChoice = 0;
				try {
					userChoice = Integer.parseInt(choice);
					} catch (NumberFormatException ex) {
					}
				if(userChoice == 1){
					System.out.println( "Dealer:"+ dealerCard.substring(0,3) + " XX");
					for(int i = 0; i <=0; i++){
						int randomNumber = (int)( Math.random()*13);
						int randomSuit = (int)( Math.random()*4);
						playerCard += (card[randomNumber]);
						
						playerCard += (suit[randomSuit]);
						
						playerCard += " ";
					}
					System.out.println( "You:" + playerCard);
				
					validChoice = true;
					
				}else if (userChoice == 2){	
					validChoice = true;

				}else if (userChoice == 3){
					int doubleDown = Integer.parseInt(bet);
					doubleDown = doubleDown*2;
				}else {
					System.out.println("INVALID INPUT. Please enter again");
					
				}
			
			}
		}
				
	}

	
	
	
	





	private static String getBet(int wallet, Scanner userInput) {
		boolean validBet = false;

		while (!validBet) {
			System.out.println("Please enter your bet:");
			String bet = userInput.nextLine();
			int userBet = 0;
			try {
				userBet = Integer.parseInt(bet);
				} catch (NumberFormatException ex) {
				}
				if(userBet>= wallet){
				System.out.println("You do not have the money! Enter again: ");
				}else if(userBet <=0){
					System.out.println("INVALID BET. Please enter again.");
				}else if(userBet <= wallet ){
					wallet -= userBet;
					validBet = true;
				}
				}
		return null;
				
		
			
	}

	private static Locale getLocale(Scanner userInput,  int wallet) {
		boolean validLocale = false;
		while (!validLocale) {
			System.out.println("1. Canada");
			System.out.println("2. French Canada");
			System.out.println("3. United States");
			System.out.println("4. China");
			System.out.println("5. France ");
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
				country = Integer.parseInt(countryName);
			} catch (NumberFormatException ex) {

			}
			
			if (country == 1) {
				 NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.CANADA);
				System.out.println("you picked the location " + Locale.CANADA + ". You have " + moneyFormatter.format(wallet));
				return Locale.CANADA;
			
			}else if (country ==2){
				NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);
				System.out.println("you picked the location " + Locale.CANADA_FRENCH +  ". You have " + moneyFormatter.format(wallet));
				return Locale.CANADA_FRENCH;
				
			}else if (country ==3){
				NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
				System.out.println("you picked the location " + Locale.US +  ". You have " + moneyFormatter.format(wallet));
				return Locale.US;
			}else if (country ==4){
				NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.CHINA);
				System.out.println("you picked the location " + Locale.CHINA +  ". You have " + moneyFormatter.format(wallet));
				return Locale.CHINA;
				
			}else if (country ==5){
				NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
				System.out.println("you picked the location " + Locale.FRANCE +  ". You have " + moneyFormatter.format(wallet));
				return Locale.FRANCE;
				
			}else if (country ==6){
				NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
				System.out.println("you picked the location " + Locale.GERMANY +  ". You have " + moneyFormatter.format(wallet));
				return Locale.GERMANY;
				
			}else if (country ==7){
				NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
				System.out.println("you picked the location " + Locale.ITALY +  ". You have " + moneyFormatter.format(wallet));
				return Locale.ITALY;
				
			}else if (country ==8){
				NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.JAPAN);
				System.out.println("you picked the location " + Locale.JAPAN +  ". You have " + moneyFormatter.format(wallet));
				return Locale.JAPAN;
			
			}else if (country ==9){
				NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
				System.out.println("you picked the location " + Locale.KOREA +  ". You have " + moneyFormatter.format(wallet));
				return Locale.KOREA;
			
			}else if (country ==10){
				NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.TAIWAN);
				System.out.println("you picked the location " + Locale.TAIWAN +  ". You have " + moneyFormatter.format(wallet));
				return Locale.TAIWAN;
			
			}else if (country ==11){
				NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.UK);
				System.out.println("you picked the location " + Locale.UK +  ". You have " + moneyFormatter.format(wallet));
				return Locale.UK;
			
			}else{
				System.out.println("INVALID INPUT! Please enter again");
				System.out.print("");
			}
		}
		return null;
	}

	private static String getUserName(Scanner userInput) {
		boolean validName = false;
		String name = "";
		while (!validName) {
			System.out.println("Please enter your name: ");
			name = userInput.nextLine();

			if (!name.trim().equals(""))
				validName = true;
		}

		return name;
	

	

		
			
			
		
		
			
	
		
		
	
	
	}
}


