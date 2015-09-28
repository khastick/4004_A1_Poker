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
	
	@Test
	public void testStringToPlayer(){
		Game game = new Game();
		String input;
		
		input = "0, OneHearts TwoHearts AceSpades KingDiamonds EightClubs";					
		
		assertTrue(game.stringToPlayer(input) == 0);	
	}
	
	@Test
	public void testStringToPlayerIvalid(){
		Game game = new Game();
		String input;
		
		input = "OneHearts TwoHearts AceSpades KingDiamonds EightClubs, 0";					
		
		assertTrue(game.stringToPlayer(input) == -1);	
	}
	
	@Test 
	public void testTooManyCards(){
		Game game = new Game();
		String input;
		
		input = "0, OneHearts TwoHearts AceSpades KingDiamonds EightClubs AceDiamonds";					
		
		assertTrue(game.stringToPlayer(input) == -1);	
	}
	
	@Test 
	public void testTooFewCards(){
		Game game = new Game();
		String input;
		
		input = "0, OneHearts TwoHearts AceSpades";					
		
		assertTrue(game.stringToPlayer(input) == -1);	
	}
	
	@Test
	public void testCorrectCardName(){
		Game game = new Game();
		String input;
		
		input = "0, OneHearts MayHearts AceSpades KingDiamonds EightClubs";					
		
		assertTrue(game.stringToPlayer(input) == -1);	
	}
	
	@Test
	public void testAmountOfPlayers(){
		Game game = new Game();
		String input1, input2, input3, input4, input5;
		
		input1 = "0, OneHearts TwoHearts ThreeHearts FourHearts FiveHearts";
		input2 = "1, SixHearts SevenHearts EightHearts NineHearts TenHearts";
		input3 = "2, OneSpades TwoSpades ThreeSpades FourSpades FiveSpades";
		input4 = "3, SixSpades SevenSpades EightSpades NineSpades TenSpades";
		input5 = "4, OneDiamonds TwoDiamonds ThreeDiamonds FourDiamonds FiveDiamonds";
		
		game.stringToPlayer(input1);
		game.stringToPlayer(input2);
		game.stringToPlayer(input3);
		assertTrue(game.stringToPlayer(input4) == 0);
		
		assertTrue(game.stringToPlayer(input5) == -1);	
	}
}
