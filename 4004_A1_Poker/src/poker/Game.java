package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
	final static int ROUNDS = 2;
	final static int MIN_PLAYERS = 2;
	final static int MAX_PLAYERS = 4;
	
	static String[] SUIT = {"Hearts", "Spades", "Diamonds", "Clubs" };
	static String[] RANK = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", 
			"Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace" };
	
	int opener, minimumBet, round;
	boolean[] cards;
	ArrayList<Player> players, activePlayers;
	
	public Player getPlayer(int i){
		return players.get(i);
	}
	
	public Game(){
		players = new ArrayList<Player>();
		
		cards = new boolean[56];				
		
		for(int i = 0; i < cards.length; i++){
			cards[i] = true;
		}
		
		round = 1;
	}
	
	
	public void update(){
		populatePlayers();
		
		while(round < 2){
			opening();
			play();
			discard();
		}
	}
	
	public void discard(){
		Scanner reader = new Scanner(System.in);
		int answer;
		int[] answerSplit;
		String input;
		String[] inputSplit;
		
		for(Player p: players){
			do{ // max 3
				System.out.println("How many cards would you like to discard? (Min is 0, Max is 3): ");
				answer = Integer.parseInt(reader.nextLine());
				if(answer > 3){
					System.out.println("The maximum # of cards that can be discarded is 3");
				}
			}while(answer > 3);
			
			if(answer == 0){
				continue;
			} else { // parse color
				System.out.println("List the cards you would like to discard seperated by a colon");
				input = reader.nextLine();
				inputSplit = input.split(",");
				answerSplit = new int[inputSplit.length];
				
				for(int i = 0; i < inputSplit.length; i++){
					answerSplit[i] = Integer.parseInt(inputSplit[i]);					
				}
				
				p.discard(answerSplit);
				p.replace(generateHand(answer));
			}
		}
		
		++round;
		reader.close();
	}
	
	public void play(){
		Scanner reader = new Scanner(System.in);
		int answer;
		
		for(Player p : players){
			if(opener != -1 && p.getID() == opener){
				opener = -1;
				continue;
			}
			
			System.out.println("ID" + p.getID() + "Cards: " + HandToString(p.getCards()));
			do{
				System.out.println("(1): fold, (2): see/call/raise");
				answer = Integer.parseInt(reader.nextLine());
				
				if(answer != 1 && answer != 2){
					System.out.println("Enter either (1): fold, or (2): see/call/raise");
				}
			}while(answer != 1 && answer != 2);
				
			switch(answer){
			case 1:
				players.remove(p);
			case 2:
				answer = 0;
				
				do{
					System.out.println("What would you like to bet (Min is " + minimumBet +"): ");
					answer = Integer.parseInt(reader.nextLine());
					
					if(answer < minimumBet){
						System.out.println("Your bet is too low.");
					}
				}while(answer < minimumBet);
			}
		}
		
		reader.close();
	}
	
	public boolean opening(){
		Scanner reader = new Scanner(System.in);
		int answer;
		
		for(Player p : players){
			System.out.println("ID: " + p.getID() + " Cards: " + HandToString(p.getCards()));
			System.out.println("Would you like to open (0: no, any other numeric value will be taken as a yes):");
			answer = Integer.parseInt(reader.nextLine());
			if(answer == 0){
				continue;
			}
			else {
				opener = p.getID();
				minimumBet = answer;
				return true;
			}		
		}		
		return false;
	}
	
	public void populatePlayers(){
		int ID=0, answer;
		String hand, input;
		String[] inputSplit;
		
		Scanner reader = new Scanner(System.in);
		
		while(players.size() < MAX_PLAYERS){
			System.out.println("Please enter a id and hand divided by a comma: ");
			input = reader.nextLine();
			inputSplit = input.split(",");
			
			ID = Integer.parseInt(inputSplit[0]);
			hand = inputSplit[1].trim();
			
			if(addPlayer(ID, hand) == -1){
				System.out.println("Invalid input. Please try again");
			}
			
			if(players.size() >= MIN_PLAYERS){
				System.out.println("If you would like to start the game press 0: ");
				answer = Integer.parseInt(reader.nextLine());	
				
				if(answer == 0){
					return;
				}
			}
		}
		
		reader.close();
	}
	
	public int addPlayer(int aId, String aHand){
		int[] tHand;		
		Player player;
		
		tHand = StringToHand(aHand);
		
		if(tHand[0] == -1){			
			return -1;
		}
		
		for(Player p : players){
			if(p.id == aId){
				return -1;
			}
		}
		
		player = new Player(aId, tHand);
		players.add(player);
		
		for(int i : tHand){
			cards[i] = false;			
		}
		
		return 0;
	}
	
	public int generateCard(){
		Random r = new Random();
		int card; 
		
		do{
			card = r.nextInt(4) * RANK.length + r.nextInt(RANK.length);
		}while(!cards[card]);
		
		return card;
	}
	
	public int[] generateHand(int size){
		int[] hand = new int[size];
		
		for(int i = 0; i < hand.length; i++){
			hand[i] = generateCard();
		}
		
		return hand;
	}
	
	public String HandToString(int[] cards){
		String hand = "";
		for(int i = 0; i < cards.length; i++){
			int rankIndex = (int)cards[i] % RANK.length;
			int suitIndex = (int)Math.floor(cards[i]/RANK.length); 
			hand += RANK[rankIndex] + SUIT[suitIndex] + " ";
		}
		return hand.trim();
	}
	
	public int[] StringToHand(String aHand){
		String[] handSplit = aHand.split(" ");
		String[] cardSplit;
		int[] hand = new int[5];
		int[] error = new int[]{-1};
		int card;
		
		if(handSplit.length != 5){
			return error;
		}
		
		for(int i = 0; i < hand.length; i++){
			cardSplit = handSplit[i].split("(?=\\p{Upper})");
			
			int groupValue = Arrays.asList(RANK).indexOf(cardSplit[0]) ;
			int typeValue = Arrays.asList(SUIT).indexOf(cardSplit[1]) * RANK.length;
			
			if(groupValue == -1 || typeValue == -1){
				return error;
			}
			
			card = groupValue + typeValue;
			
			// error check
			if(cards[card]){
				hand[i] = card;
				cards[card] = false;
			} 
			else {
				return error;
			}
		}
		
		return hand;
		
	}
	
}
