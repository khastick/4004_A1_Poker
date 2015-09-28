package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import poker.*;

public class PokerTest {

	@Test
	public void testStringToHand(){
		Game game = new Game();
		
		game.addPlayer(0, "OneHearts TwoHearts AceSpades KingDiamonds EightClubs");		
		
		int [] playerCards, someCards;
		
		playerCards = game.getPlayer(0).getCards();
		someCards = new int[]{0,1,27,40,49};
		
		assertTrue(Arrays.equals(playerCards, someCards));
	}
	
	@Test
	public void testHandToString(){
		Game game = new Game();
		String inputHand = "OneHearts TwoHearts AceSpades KingDiamonds EightClubs";
		String testHand;
		
		game.addPlayer(0, inputHand);	
				
		Player player = game.getPlayer(0);		
		
		testHand = game.HandToString(player.getCards());
		
		assertTrue(inputHand.equals(testHand));
	}
	
	@Test
	public void testDiscard(){
		Game game = new Game();
		String inputHand = "OneHearts TwoHearts AceSpades KingDiamonds EightClubs";				
		Player player;	
		int[] discardCards = new int[]{1,2,3};
		int[] playerCards, someCards;
		
		game.addPlayer(0, inputHand);
		player = game.getPlayer(0);
					
		player.discard(discardCards);
		
		playerCards = player.getCards();
		someCards = new int[]{0, -1,-1,-1,49};
		
		assertTrue(Arrays.equals(playerCards, someCards));		
	}
	
	@Test
	public void testReplace(){
		Game game = new Game();
		String inputHand = "OneHearts TwoHearts AceSpades KingDiamonds EightClubs";				
		Player player;	
		int[] discardCards = new int[]{1,2,3};
		int[] replacements = new int[]{20,21,22};
		int[] playerCards, someCards;
		
		game.addPlayer(0, inputHand);
		player = game.getPlayer(0);
		
		player.discard(discardCards);
		player.replace(replacements);
		
		playerCards = player.getCards();
		someCards = new int[]{0, 20,21,22,49};
		
		assertTrue(Arrays.equals(playerCards, someCards));	
	}
	
	@Test
	public void testDuplicates(){
		Game game = new Game();
		String inputHand = "OneHearts TwoHearts AceSpades KingDiamonds EightClubs";							
		
		game.addPlayer(0, inputHand);
		
		assertTrue(game.addPlayer(1, inputHand) == -1);
		
	}
	
	@Test
	public void testMultiplePlayersInvalidID(){
		Game game = new Game();
		String inputHand1, inputHand2;
		
		inputHand1 = "OneHearts TwoHearts AceSpades KingDiamonds EightClubs";
		inputHand2 = "ThreeHearts AceClubs QueenSpades QueenDiamonds FiveClubs";
		
		game.addPlayer(0, inputHand1);
		
		assertTrue(game.addPlayer(0, inputHand2) == -1);		
	}
	
	@Test
	public void testMultiplePlayersValid(){
		Game game = new Game();
		String inputHand1, inputHand2;
		
		inputHand1 = "OneHearts TwoHearts AceSpades KingDiamonds EightClubs";
		inputHand2 = "ThreeHearts AceClubs QueenSpades QueenDiamonds FiveClubs";
		
		game.addPlayer(0, inputHand1);
		
		assertTrue(game.addPlayer(1, inputHand2) == 0);		
	}
}
