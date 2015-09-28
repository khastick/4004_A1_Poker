package poker;

import java.util.Arrays;
import java.util.Iterator;

public class Player {
	static String[] SUIT = {"Hearts", "Spades", "Diamonds", "Clubs" };
	static String[] RANK = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", 
			"Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace" };
	static int SUIT_SIZE = 14;
	
	int[] cards;
	int id, chips;
	
	public int[] getCards(){
		return cards;
	}
	
	public Player(int aId, int aChips, int[] aCards){
		id = aId;
		chips = aChips;
		cards = aCards;
	}
	
	public Player(String aHand){
		id = 0;
		chips = 0;
		cards = new int[5];
		StringToHand(aHand);
	}
	
	public void play(){
		
	}
	
	public int[] Check(){
		return new int[]{0,0};
	}
	
	public int[] Call(){
		return new int[]{1,0};
	}
	
	public int[] Raise(){
		return new int[]{2,0};
	}
	
	public int[] Open(){
		return new int[]{3,0};
	}
	
	public int[] fold(){
		return new int[]{4,0};
	}
	
	public int[] discard(int[] index){
		int[] discarded = new int[index.length];
		
		for(int i = 0; i < index.length; i++){
			discarded[i] = cards[index[i]];
			cards[index[i]] = -1;
		}
		
		return discarded;
	}
	
	public void replace(int[] hand){		
		for(int j = 0, i = 0; i < cards.length; i++){			
				if(cards[i] == -1){
					cards[i] = hand[j];	
					++j;
				}					
		}
	}
	
	public String HandToString(){
		String hand = "";
		for(int i = 0; i < cards.length; i++){
			int rankIndex = (int)cards[i] % SUIT_SIZE;
			int suitIndex = (int)Math.floor(cards[i]/SUIT_SIZE); 
			hand += RANK[rankIndex] + SUIT[suitIndex] + " ";
		}
		return hand.trim();
	}
	
	public void StringToHand(String aHand){
		String[] handSplit = aHand.split(" ");
		String[] cardSplit;
		
		for(int i = 0; i < cards.length; i++){
			cardSplit = handSplit[i].split("(?=\\p{Upper})");
			
			int groupValue = Arrays.asList(RANK).indexOf(cardSplit[0]) ;
			int typeValue = Arrays.asList(SUIT).indexOf(cardSplit[1]) * SUIT_SIZE;
			
			cards[i] = groupValue + typeValue;
		}
		
	}

}
