package poker;

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
	
	public Player(int aId, int[] aHand){
		this(aId, 0, aHand);
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
	


}
