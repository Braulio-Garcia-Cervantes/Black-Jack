 

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Deck {
	
	private static int sum = 0, balance = 0, add, bet, intValue, dealerFirstCard, dealerSum = 0, loss, card, val, forPlay; 
	private static String player, playerDeal;
	private static Scanner input;
	private static ArrayList <Card> cards; //a list of "card" objects which will create our deck.
	private static boolean bust = false;
	
	
    public static void main(String[] args) {
        
    	input = new Scanner(System.in);
    	
    	
    	System.out.println("--------Welcome to BlackJack!--------" + "\n" +  "\n" + "Objective:" + "\n" + "Beat the Dealer: Have a hand value closer to 21 than the dealer's hand." + "\n" + "Avoid Busting: Do not exceed a hand value of 21.");
    	System.out.println();
    	System.out.println("Number Cards: Face value (2 through 10)." + "\n" + "Face Cards: Jack, Queen, and King are each worth 10 points." + "\n" + "Aces: Can be worth 1 or 11 points, depending on which value benefits the hand more.");
    	System.out.println();
    	System.out.println("-----------");
    	System.out.println("1 = Hit" + "\n" + "2 = Stand" + "\n" + "3 = Cashout");
    	System.out.println("-----------");
    	System.out.println("Player Name:");
    	player = input.next();
    	
    	//Default player name if none is provided.
    	if(player.isEmpty())
    	{
    		player = "Player 1";
    		
    	}
    	else
    	{
    		
    		System.out.println(player + " has entered the game.");
    		System.out.println();
    	}
    	
    	System.out.println("Enter $$ Balance for Play $$");
    	balance = input.nextInt();
    	
    	
    	
		
		try {
			Thread.sleep(2000); //slight delay to give it a live server-like feeling 
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("Dealer has entered the game.");
    	
    	gamePlay();
    	
    	
    	
    	
    	
}
    
    public static void display() //holds what we always want to be seen by the user at the beginning of every round
    {
    	System.out.println("-----------");
    	System.out.println("$5 Bet Minimum");
    	System.out.println("Balance: $" + balance);
    	System.out.println("-----------");
    	
    }
    
    public static void gamePlay() //meat of the game
    {
    	display();
    	
    	do
    	{
    		bet();
    		
    		//after a valid bet has been entered, game starts:
    		 
    		   firstRound();
    		   
    		   System.out.println("1) Hit" + "\n" + "2) Stand");
    		   int HorS = input.nextInt();
    		   
    		   while(HorS == 1)
    		   {
    			   hit();
    			   
    			   
    			   
    			   System.out.println("1) Hit" + "\n" + "2) Stand");
        		    HorS = input.nextInt();
    			   
    		   }
    		    if(HorS == 2)
    		   {
    			   
    			   stand();
    		   }
    		   
    		    		
    		
    	
    		
    	}while(add != 3 || bet != 3);
    	
    	
    }
    
    public static void bet()
    {
    	
    	System.out.println("Enter $$ Bet $$");
		
		
		while(true)
		 {
			 try {
				  
				 bet = input.nextInt();
				 
					break; //if successful, break out of loop
				 }
				 catch(InputMismatchException e)
				 {
					 
					 System.out.println("Invalid Entry.");
					 input.next(); //clear the invalid entry
					 System.out.println("Enter $$ Bet $$");
				 }
			 
			 balance = balance - bet;
			
			 
		 }
		
		if(bet == 3)
		{
			
			System.out.println("Thanks for playing BlackJack!");
	    	System.exit(0);
		}
		
		while(bet < 5)
		{
			
			 System.out.println("ALL bets must be $5 minimum!");
			 bet = input.nextInt();
		}
		
		
		
		while(balance < bet)
		{
			
			System.out.println("You don't have enough balance for this bet!" + "\n" + "Add Balance?" + "\n" + "0 = Add" + "\n" + "3 = Cashout");
			bet = input.nextInt();
			
			if(bet == 0)
			{
				System.out.println("Enter Balance for Play:");
				 forPlay = input.nextInt();
				balance = balance + forPlay;
				
				gamePlay();
				
			}
			else if(bet == 3)
			{
				System.out.println("Thanks for playing BlackJack!");
		    	System.exit(0);
				
			}
		}
		
		if(balance < 5)
		{
			System.out.println("Balance: " + balance +  "\n" + "Would you like to add to your balance or cash out the remains?" + "\n" + "0 = Add" + "\n" + "3 = Cashout");
   		 add = input.nextInt();
   		 
   		 if(add == 0)
   		 {
   			System.out.println("Enter Balance for Play:");
			forPlay = input.nextInt();
			balance = balance + forPlay;
			
			gamePlay();
   			 
   		 }
   		 else if(add == 3)
   		 {
   			System.out.println("Thanks for playing BlackJack!");
   	    	System.exit(0);
   			 
   		 }
			
		}
		
		
    }
    
    
    
    //--------------------------------------------------------------------------------------------------------------------------
    
    
    
    
    //create a class of a card object and store the attributes of a card that we need, as data:
    static class Card
    {
    	private String suit;
    	private String rank;
    	
    	 public Card (String suit, String rank) //allows us to use a "card" object that holds the attributes of a real card.
    	{
    		this.suit = suit;
    		this.rank = rank;	
    		
    	}
    	 
    	 public String getRank() {
    	        return rank;
    	    }

    	    public String getSuit() {
    	        return suit;
    	    }
    	    
    	    //In order to be able to add up the card values, assign values:
    	    public int getValue() {
    	        switch (rank) {
    	            case "Jack":
    	            case "Queen":
    	            case "King":
    	                return 10;
    	            case "Ace":
    	            	//see which value benefits the player more
    	            	if(sum + 11 <= 21)
    	            	{
    	            		return 11;
    	            		
    	            	}
    	            	else 
    	            	{
    	            		return 1;
    	            		
    	            	}
    	            	
    	            default:
    	                return Integer.parseInt(rank); // Converts "2" through "10" to their integer values
    	        }
    	    }
    	
    	public String toString() //a call on this method will print out what the card is for the user to see.
    	{
    		return rank + " of " + suit;
    		
    	}
    
    	
    }
    
    //constructor that initializes the 52 card deck and populates our list with the card objects:
    static class PlayDeck {
        private ArrayList<Card> cards;

        public PlayDeck() {
            cards = new ArrayList<>();
            String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
            String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

            for (String suit : suits) {
                for (String rank : ranks) {
                    cards.add(new Card(suit, rank));
                }
            }
        }

        public void shuffle() {
            Collections.shuffle(cards); //randomizes the cards
        }

        public String dealCard() {
            if (cards.isEmpty()) {
                throw new IllegalStateException("The deck is empty!");
            }
            Card card = cards.remove(cards.size() - 1);
             intValue = card.getValue();
             
            return card.toString();
        }
    }


    	    
    	   
    	
    
    
    public static void firstRound()
    {
    	PlayDeck deck = new PlayDeck();
		deck.shuffle();
		
		System.out.println("Dealer: Dealing cards...");
		
		try {
			Thread.sleep(2000); //slight delay to give it a live server-like feeling 
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
    	System.out.println("Dealer: Pulled a ");
		System.out.println();
		System.out.println(" --------------");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println("| " + deck.dealCard() + " |");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println(" --------------");
		
		dealerFirstCard = intValue;
		
		System.out.println(" and has another unknown card face down.");
		
		System.out.println();
		
		String first = deck.dealCard();
		int sum1 = intValue;
		
		
		String second = deck.dealCard();
		int sum2 = intValue;
		
		
		System.out.println(player + ": To start, you were dealt a ");
		System.out.println();
		System.out.println(" --------------");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println("| " + first + " |");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println(" --------------");
		
		
	
		System.out.println("and a ");
		System.out.println();
		System.out.println(" --------------");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println("| " + second + " |");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println(" --------------");
		
		
		sum = sum1 + sum2;
		
		
		if(sum1 == 10 && sum2 == 11)
		{
			System.out.println(player + ": You got a BlackJack!");
			balance = balance + bet + (bet * 2); //black jack winnings are double 
			System.out.println("Balance: $" + balance);
			
			System.out.println("1) Bet" + "\n" + "3) Cashout");
			loss = input.nextInt();
			
			if(loss == 1)
			{
				
				gamePlay();
			}
			else if(loss == 3)
			{
				System.out.println("Thanks for playing BlackJack!");
	   	    	System.exit(0);
				
			}
			
		}
		
		
		
    	
    }
    public static void hit()
    {
    	PlayDeck deck = new PlayDeck();
		deck.shuffle();
		
		System.out.println("Dealer: Dealing cards...");
		
		try {
			Thread.sleep(2000); //slight delay to give it a live server-like feeling 
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

		playerDeal();
		
		sum = sum + card; 
		
	
		
		if(sum > 21)
		{
			bust();
			
		}
		else if (sum == 21)
		{
			System.out.println(player + ": You Win!");
    		balance = balance + bet;
    		System.out.println("Balance: $" + balance);
    		System.out.println("1) Bet" + "\n" + "3) Cashout");
    		loss = input.nextInt();
    		
    		if(loss == 1)
    		{
    			
    			gamePlay();
    		}
    		else if(loss == 3)
    		{
    			System.out.println("Thanks for playing BlackJack!");
       	    	System.exit(0);
    			
    		}
			
		}
		
    }
    
    public static void stand()
    {
    	dealerDeal();
    	
    	dealerSum = dealerFirstCard + intValue + val; //this is the value of the "unknown card"
    	
    	if(dealerSum < 17)
    	{
    		PlayDeck deck = new PlayDeck();
    		deck.shuffle();
    		
    		System.out.println("and a");
    		System.out.println();
    		System.out.println(" --------------");
    		System.out.println("|             |");
    		System.out.println("|             |");
    		System.out.println("| " + deck.dealCard() + " |");
    		System.out.println("|             |");
    		System.out.println("|             |");
    		System.out.println(" --------------");
    		
    		dealerSum = dealerSum + intValue; 
    		
    		if(dealerSum > sum && dealerSum <= 21 && dealerSum >= 17)
	    	{
	    		
	    		loss();
	    		
	    		
	    	}
    		else if(dealerSum > 21)
        	{
        		System.out.println("Dealer: BUST!");
        		System.out.println(player + ": You Win!");
        		balance = balance;
        		System.out.println("Balance: $" + balance);
        		System.out.println("1) Bet" + "\n" + "3) Cashout");
        		loss = input.nextInt();
        		
        		if(loss == 1)
        		{
        			
        			gamePlay();
        		}
        		else if(loss == 3)
        		{
        			System.out.println("Thanks for playing BlackJack!");
           	    	System.exit(0);
        			
        		}
        		
        	}
        	else if(sum > dealerSum && sum <= 21)
        	{
        		System.out.println(player + ": You Win!");
        		balance = balance + bet;
        		System.out.println("Balance: $" + balance);
        		System.out.println("1) Bet" + "\n" + "3) Cashout");
        		loss = input.nextInt();
        		
        		if(loss == 1)
        		{
        			
        			gamePlay();
        		}
        		else if(loss == 3)
        		{
        			System.out.println("Thanks for playing BlackJack!");
           	    	System.exit(0);
        			
        		}
        		
        	}
        	else if(dealerSum == sum)
        	{
        		System.out.println("Dealer: Push");
        		System.out.println(player + ": Push");
        		balance = balance; // player gets bet returned to them if tied
        		System.out.println("Balance: $" + balance);
        		System.out.println("1) Bet" + "\n" + "3) Cashout");
        		loss = input.nextInt();
        		
        		if(loss == 1)
        		{
        			
        			gamePlay();
        		}
        		else if(loss == 3)
        		{
        			System.out.println("Thanks for playing BlackJack!");
           	    	System.exit(0);
        			
        		}
        		
        	}
    		
    		
    		
    	
    	
    	}
    	
    	if(dealerSum > sum && dealerSum <= 21 && dealerSum >= 17)
    	{
    		
    		loss();
    		
    		
    	}
		else if(dealerSum > 21)
    	{
    		System.out.println("Dealer: BUST!");
    		System.out.println(player + ": You Win!");
    		balance = balance + bet ;
    		System.out.println("Balance: $" + balance);
    		System.out.println("1) Bet" + "\n" + "3) Cashout");
    		loss = input.nextInt();
    		
    		if(loss == 1)
    		{
    			
    			gamePlay();
    		}
    		else if(loss == 3)
    		{
    			System.out.println("Thanks for playing BlackJack!");
       	    	System.exit(0);
    			
    		}
    		
    	}
    	else if(sum > dealerSum && sum <= 21)
    	{
    		System.out.println(player + ": You Win!");
    		balance = balance + bet;
    		System.out.println("Balance: $" + balance);
    		System.out.println("1) Bet" + "\n" + "3) Cashout");
    		loss = input.nextInt();
    		
    		if(loss == 1)
    		{
    			
    			gamePlay();
    		}
    		else if(loss == 3)
    		{
    			System.out.println("Thanks for playing BlackJack!");
       	    	System.exit(0);
    			
    		}
    		
    	}
    	else if(dealerSum == sum)
    	{
    		System.out.println("Dealer: Push");
    		System.out.println(player + ": Push");
    		balance = balance + bet; // player gets bet returned to them if tied
    		System.out.println("Balance: $" + balance);
    		System.out.println("1) Bet" + "\n" + "3) Cashout");
    		loss = input.nextInt();
    		
    		if(loss == 1)
    		{
    			
    			gamePlay();
    		}
    		else if(loss == 3)
    		{
    			System.out.println("Thanks for playing BlackJack!");
       	    	System.exit(0);
    			
    		}
    		
    	}
    	
    	
    }
    
    public static boolean bust()
    {
    	System.out.println(player + ": BUST!");
    	bust = true;
    	loss();
    	
    	return bust;
    }
    
    public static void dealerDeal()
    {
    	PlayDeck deck = new PlayDeck();
		deck.shuffle();
		
    	System.out.println("Dealer: Pulled a ");
		System.out.println();
		System.out.println(" --------------");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println("| " + deck.dealCard() + " |");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println(" --------------");
		
		val = intValue;
		
		System.out.println("and a");
		System.out.println();
		System.out.println(" --------------");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println("| " + deck.dealCard() + " |");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println(" --------------");
    	
    }
    
    public static void playerDeal()
    {
    	PlayDeck deck = new PlayDeck();
		deck.shuffle();
		
		playerDeal = deck.dealCard();
		card = intValue;
		
		
    	System.out.println(player + ": You were dealt a ");
		System.out.println();
		System.out.println(" --------------");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println("| " + playerDeal + " |");
		System.out.println("|             |");
		System.out.println("|             |");
		System.out.println(" --------------");
		
		
    }
    
    public static void loss()
    {
    	balance = balance - bet;
    	System.out.println("You lose.");
    	System.out.println("Balance: $" + balance);
		System.out.println("1) Bet" + "\n" + "3) Cashout");
		loss = input.nextInt();
		
		if(loss == 1)
		{
			
			gamePlay();
		}
		else if(loss == 3)
		{
			System.out.println("Thanks for playing BlackJack!");
   	    	System.exit(0);
			
		}
    	
    }
    
    
    
    
    
}
