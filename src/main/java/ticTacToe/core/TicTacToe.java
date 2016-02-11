package ticTacToe.core;

import java.util.Random;

public class TicTacToe {

	private char[][] board;
	private char currentPlayerMark;
	private long startMillis;
	private final String game = "TicTacToe";

	public TicTacToe() {
		board = new char[3][3];
		currentPlayerMark = 'x';
		initializeBoard();
	}
	
	public String getGame() {
		return game;
	}


	private void initializeBoard() {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	public void printBoard() {
		System.out.println("---0---1---2--");

		for (int i = 0; i < 3; i++) {
			System.out.print((char)(i + 'A') + "| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("--------------");
		}
	}

	public boolean checkForWin() {
		return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
	}

	private boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
				return true;
			}
		}
		return false;
	}

	private boolean checkRowsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonalsForWin() {
		return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true)
				|| (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
	}


	private boolean checkRowCol(char c1, char c2, char c3) {
		return ((c1 != '-') && (c1 == c2) && (c2 == c3));
	}

	public boolean placeMark(int row, int col) {
		if ((row >= 0) && (row < 3)) {
			if ((col >= 0) && (col < 3)) {
				if (board[row][col] == '-') {
					board[row][col] = currentPlayerMark;
					return true;
				}
			}
		}

		return false;
	}
	
	public boolean isBoardFull() {
        boolean isFull = true;
		
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                }
            }
        }
		
        return isFull;
    }
	
	public void changePlayer() {
        if (currentPlayerMark == 'x') {
            currentPlayerMark = 'o';
        }
        else {
            currentPlayerMark = 'x';
        }
    }
	
	public void playerComputer(){
		System.out.println("Tah pocitaca");
		Random random = new Random();
		int turn = 1;
		while(turn >0 ){
			int row = random.nextInt(3);
			int column = random.nextInt(3);
			if(board[row][column] == '-'){
				board[row][column] = 'o';
				turn--;
				changePlayer();
			}
		}
	}
	
	public int getPlayingSeconds() {
		return (int) ((System.currentTimeMillis() - startMillis) / 1000);
	}

}
