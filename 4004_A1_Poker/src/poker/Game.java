package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {
	final static int ROUNDS = 2;
	
	static String[] SUIT = {"Hearts", "Spades", "Diamonds", "Clubs" };
	static String[] RANK = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", 
			"Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace" };
	
	boolean[] cards, discarded;
	ArrayList<Player> players, activePlayers;
	
	public Player getPlayer(int i){
		return players.get(i);
	}
	
	public Game(){
		players = new ArrayList<Player>();
		
		cards = new boolean[56];		
		discarded = new boolean[56];
		
		for(int i = 0; i < cards.length; i++){
			cards[i] = true;
		}
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
		return 0;
	}
	
	public int generateCard(){
		Random r = new Random();
		
		return r.nextInt(4) * RANK.length + r.nextInt(RANK.length);
	}
	
	public int[] generateHand(){
		int[] hand = new int[5];
		
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
