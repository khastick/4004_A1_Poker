package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import poker.Player;

public class PokerTest {

	@Test
	public void testStringToHand(){
		Player player = new Player("OneHearts TwoHearts AceSpades KingDiamonds EightClubs");		
		int [] playerCards, someCards;
		
		playerCards = player.getCards();
		someCards = new int[]{0,1,27,40,49};
		
		assertTrue(Arrays.equals(playerCards, someCards));
	}
	
	@Test
	public void testHandToString(){
		String inputHand = "OneHearts TwoHearts AceSpades KingDiamonds EightClubs";
		String testHand;
		Player player = new Player(inputHand);		
		
		testHand = player.HandToString();
		
		assertTrue(inputHand.equals(testHand));
	}
	
	@Test
	public void testDiscard(){
		String inputHand = "OneHearts TwoHearts AceSpades KingDiamonds EightClubs";
		Player player = new Player(inputHand);
		int[] discardCards = new int[]{1,2,3};
		int[] playerCards, someCards;
		
		player.discard(discardCards);
		
		playerCards = player.getCards();
		someCards = new int[]{0, -1,-1,-1,49};
		
		assertTrue(Arrays.equals(playerCards, someCards));		
	}
	
	@Test
	public void testReplace(){
		String inputHand = "OneHearts TwoHearts AceSpades KingDiamonds EightClubs";
		Player player = new Player(inputHand);
		int[] discardCards = new int[]{1,2,3};
		int[] replacements = new int[]{20,21,22};
		int[] playerCards, someCards;
		
		player.discard(discardCards);
		player.replace(replacements);
		
		playerCards = player.getCards();
		someCards = new int[]{0, 20,21,22,49};
		
		assertTrue(Arrays.equals(playerCards, someCards));	
	}
}
