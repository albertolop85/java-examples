package examples;

import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
	
	private static final String QUESTION_MARK = "?";
	private static final int BOMB = -1; 
	private static final String EMPTY = " ";
	
	private static final int TOTAL_BOMBS = 10; 

	private String[][] fieldVisible = new String[10][10];
	private int[][] fieldHidden = new int[10][10];
	
	private boolean winnerGame = false;

    public static void main(String[] args) throws Exception
    {
        Minesweeper M = new Minesweeper();
        M.startGame();
    }
    
    private void startGame() throws Exception {
    	
    	setupField();
    	showVisible();
    	
    	boolean completed = false;
    	
    	do {
    		completed = newTurn();
    	} while (!completed);
    	
    	if (winnerGame) {
    		System.out.print("You WON");
    	} else {
    		System.out.print("You LOSE");
    	}
    }

	private void setupField() {
    	setupBombs(TOTAL_BOMBS);
    	setupOthers();
    	setupVisible();
    }
    
    private void setupBombs(int bombs) {
    	
    	for (int count = 0; count < bombs; count++) {
    		
    		Random random = new Random();
    		int i = random.nextInt(bombs);
    		int j = random.nextInt(bombs);
    		
    		fieldHidden[i][j] = BOMB;
    	}
    }
    
    private void setupOthers() {
    	
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			setupHiddenCell(i, j);
    		}
    	}
    }
    
    private void setupVisible() {
    	
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			fieldVisible[i][j] = QUESTION_MARK;
    			System.out.print("| " + (fieldHidden[i][j] == BOMB ? "X" : fieldHidden[i][j]) + " ");
    		}
    		System.out.println("|");
    	}
    }
    
    private void setupHiddenCell(int i, int j) {
    	
    	if (fieldHidden[i][j] == BOMB) {
    		return;
    	}
    	
    	int count = 0;
    	
    	// Check upper row
    	if (i > 0) {
    		
    		if (j > 0) {
        		count += returnBombs(i-1, j-1);
        	}
    		
    		count += returnBombs(i-1, j);
        	
        	if (j < 9) {
        		count += returnBombs(i-1, j+1);
        	}
    	}
    	
    	// Check current row
    	if (j > 0) {
    		count += returnBombs(i, j-1);
    	}
    	
    	if (j < 9) {
    		count += returnBombs(i, j+1);
    	}
    	
    	// Check below row
    	if (i < 9) {
    		
    		if (j > 0) {
        		count += returnBombs(i+1, j-1);
        	}
    		
    		count += returnBombs(i+1, j);
        	
        	if (j < 9) {
        		count += returnBombs(i+1, j+1);
        	}
    	}
    	
    	fieldHidden[i][j] = count;
    }
    
    private int returnBombs(int i, int j) {
    	
    	if (fieldHidden[i][j] == BOMB) {
    		return 1;
    	} else {
    		return 0;
    	}
    }
    
    private void showVisible() {
    	
    	System.out.println("\n");
    	
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			System.out.print("| " + fieldVisible[i][j] + " ");
    		}
    		System.out.println("|");
    	}
    	
    	System.out.println("\n");
	}
    
    private boolean newTurn() throws Exception {
    	
		boolean completed = false;
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Please give x coordinate (index): ");
		int i = sc.nextInt();
		System.out.print("Please give y coordinate (index): ");
		int j = sc.nextInt();
		
		
		
		if (fieldHidden[i][j] == BOMB) {
			fieldVisible[i][j] = "X";
			completed = true;
		} else {
			showHiddenContent(i, j);
			completed = checkIfComplete();
		}
		
		showVisible();
		
		return completed;
	}
    
    private boolean checkIfComplete() {
    	
    	int counterBombs = 0;
    	
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			if (fieldVisible[i][j].equals(QUESTION_MARK)) {
    				counterBombs++;
    			}
    		}
    	}
    	
    	if (counterBombs == 10) {
    		winnerGame = true;
    	}
    	
    	return counterBombs == 10;
	}

	private void showHiddenContent(int i, int j) {
		
		if (i >= 0 && i < 10 && j >= 0 && j < 10) {
			
			if (fieldVisible[i][j].equals(EMPTY)) {
				return;
			}
			
			fieldVisible[i][j] = fieldHidden[i][j] == 0 ? EMPTY : String.valueOf(fieldHidden[i][j]);
			
			if (fieldHidden[i][j] == 0) {
				
				for (int x = i-1; x <= i+1; x++) {
					
					for (int y = j-1; y <= j+1; y++) {
						
						showHiddenContent(x, y);
					}
				}
			}
		}
    	
    }
}
