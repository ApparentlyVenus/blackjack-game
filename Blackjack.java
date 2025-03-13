package learn.java;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Blackjack {
	public static void main(String[] arg) {
		 
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		
		List<String> deck = new ArrayList<String>();
		
	deck.add ("Ace of Hearts"); 
	deck.add ("2 of Hearts");
	deck.add ("3 of Hearts");
	deck.add ("4 of Hearts");
	deck.add ("5 of Hearts");
	deck.add ("6 of Hearts");
	deck.add ("7 of Hearts");
	deck.add ("8 of Hearts");
	deck.add ("9 of Hearts");
	deck.add ("10 of Hearts");
	deck.add ("Jack of Hearts");
	deck.add ("Queen of Hearts");
	deck.add ("King of Hearts");
	deck.add ("Ace of Diamonds");
	deck.add ("2 of Diamonds");
	deck.add ("3 of Diamonds");
	deck.add ("4 of Diamonds");
	deck.add ("5 of Diamonds");
	deck.add ("6 of Diamonds");
	deck.add ("7 of Diamonds");
	deck.add ("8 of Diamonds");
	deck.add ("9 of Diamonds");
	deck.add ("10 of Diamonds");
	deck.add ("Jack of Diamonds");
	deck.add ("Queen of Diamonds");
	deck.add ("King of Diamonds");
	deck.add ("Ace of Clubs");
	deck.add ("2 of Clubs");
	deck.add ("3 of Clubs");
	deck.add ("4 of Clubs");
	deck.add ("5 of Clubs");
	deck.add ("6 of Clubs");
	deck.add ("7 of Clubs");
	deck.add ("8 of Clubs");
	deck.add ("9 of Clubs");
	deck.add ("10 of Clubs");
	deck.add ("Jack of Clubs");
	deck.add ("Queen of Clubs");
	deck.add ("King of Clubs");
	deck.add ("Ace of Spades");
	deck.add ("2 of Spades");
	deck.add ("3 of Spades");
	deck.add ("4 of Spades");
	deck.add ("5 of Spades");
	deck.add ("6 of Spades");
	deck.add ("7 of Spades");
	deck.add ("8 of Spades");
	deck.add ("9 of Spades");
	deck.add ("10 of Spades");
	deck.add ("Jack of Spades");
    deck.add ("Queen of Spades");
    deck.add ("King of Spades");
    
    List<String> playerhand = new ArrayList<String>();
	List<String> dealerhand = new ArrayList<String>();
	
	drawcards(deck, playerhand, rand, 2);
	drawcards(deck, dealerhand, rand, 1);
	
	int playervalue = blackjackhandvalueCalculator(playerhand);
	int dealervalue = blackjackhandvalueCalculator(dealerhand);
	
	int dealerredrawcount = 0;
			
	while (dealervalue < 16 && dealerredrawcount < 3) {
		drawcards(deck, dealerhand, rand, 1);
		dealervalue = blackjackhandvalueCalculator(dealerhand);
		dealerredrawcount += 1;
	}
	
	System.out.println("Player Hand: " + playerhand+" Value: "+playervalue);
	System.out.println("Dealer Hand: " + dealerhand+" + ????? Value: "+dealervalue);
	
	int standcount = 0;
	
	while (playervalue < 21 && dealervalue < 21 && standcount < 1) {
		
		System.out.println("Action: Hit or Stand");
		String action = scan.nextLine();
	
if (action.equalsIgnoreCase("hit")) {
		
	    drawcards(deck, playerhand, rand, 1);
	    playervalue = blackjackhandvalueCalculator(playerhand);
	    
	    System.out.println("Player Hand: " + playerhand+" Value: "+playervalue);
	    System.out.println("Dealer Hand: " + dealerhand+" + ????? Value: "+dealervalue);
	    
}
	else if (action.equalsIgnoreCase("stand")) {
		standcount += 1;
		
		drawcards (deck, dealerhand, rand, 1);
		dealervalue = blackjackhandvalueCalculator(dealerhand);
		
		System.out.println("Player Hand: " + playerhand+" Value: "+playervalue);
		System.out.println("Dealer Hand: " + dealerhand+" Value: "+dealervalue);
	    
	}
else {
	System.out.println(action+" is not a valid action.");
		
     }
    }  
	
	outcome (playervalue, dealervalue);
	scan.close();
}    
public static void drawcards(List<String> deck, List<String> hand, Random rand, int count) {
    // Method to draw cards
	
        for(int i = 1; i <= count; i++) {
	           
            int randomdraw = rand.nextInt(1, deck.size());

	           List<Integer> generatednumbers = new ArrayList<Integer>();
	           generatednumbers.add(randomdraw);
	           
	           if (generatednumbers.contains(randomdraw));{
	        	   
	        	   randomdraw = rand.nextInt(1,deck.size());
	        	   
	           }
	     
	           hand.add(deck.get(randomdraw));
	           deck.remove(randomdraw);
	               
	    }
} 
public static int blackjackhandvalueCalculator(List<String> hand) {
	// Method to calculate the value of a hand
	
	int value = 0;
	int acecount = 0;
	
	for (String card : hand) {
		String[] parts = card.split(" "); // this splits the card into parts ex: "2 of Hearts"
		String rank = parts [0];          // "2" "of" "Hearts" --> "2" is of Index 0
	switch (rank) {
	case "2": value += 2; break;
	case "3": value += 3; break;
	case "4": value += 4; break;
	case "5": value += 5; break;
	case "6": value += 6; break;
	case "7": value += 7; break;
	case "8": value += 8; break;
	case "9": value += 9; break;
	case "10": case "Jack": case "Queen": case "King": value += 10; break;
	case "Ace": 
		acecount +=1;
		if (value + 11 > 21) {
			value += 1;
			}
		else {
			value += 11;
		}
        break;
	}
	if (value > 21 && acecount > 0) {
		value = value - 10;
	  }
   }
	
	return value;

}
public static void outcome(int playervalue, int dealervalue) {
	
	if (playervalue == 21) {
		System.out.println("You Win!");
	}
	else if (dealervalue == 21) {
		System.out.println("You Lose!");
	}
	else if (dealervalue > 21) {
		System.out.println("You Win!");
	}
	else if (playervalue < 21 && dealervalue < 21) {
		
	int operation = playervalue-dealervalue;
	int outcome = (operation>0)?1 : (operation<0)?2 : 3; 
	// shortened if else statement (ternary operation)
	// c = if(n > 0) (then)? 1 : (else if)(n < 0) (then)? 2 (else): 3;             
	switch (outcome) {
	case 3: System.out.println("Push!"); break;
	case 2: System.out.println("You Lose!"); break;
	case 1: System.out.println("You Win!"); break;
	 }
	}
	else {
		System.out.println("You Lose!");
	}
  }
}


