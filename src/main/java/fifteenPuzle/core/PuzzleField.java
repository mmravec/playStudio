package fifteenPuzle.core;

import java.util.Random;

import GameStudio.score.GameState;

public class PuzzleField {

	private final int rowCount;
	private final int columnCount;
	private final int[][] field;
	private final int Gap = 0;
	private GameState state = GameState.PLAYING;
	private final String game = "Puzzle";
	private long startMillis;
	private int actualZeroRow;
	private int actualZeroColumn;

	public PuzzleField(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;

		if (!(rowCount > 0 && columnCount > 0)) {
			throw new IllegalArgumentException("Cele zle");
		}

		field = new int[rowCount][columnCount];
		generate();
		shuffle();
		startMillis = System.currentTimeMillis();
	}

	private void generate() {

		int value = 1;
		for (int row = 0; row < rowCount; row++)
			for (int column = 0; column < columnCount; column++) {

				field[row][column] = value;
				value++;

			}

		field[rowCount - 1][columnCount - 1] = Gap;
	}

	private void shuffle() {
		Random random = new Random();
	
		//hard mod
		//for (int shuffleCount = 0; shuffleCount < (rowCount * rowCount) * (columnCount * columnCount); shuffleCount++) {
		//easy mod
		for (int shuffleCount = 0; shuffleCount < 3; shuffleCount++) {
		int index = random.nextInt(4);

			switch (index) {
			case 0:
				moveUp();
				break;
			case 1:
				moveDown();
				break;
			case 2:
				moveLeft();
				break;
			case 3:
				moveRight();
				break;

			default:
				break;
			}
		}

	}

	public int getTile(int row, int column) {
		return field[row][column];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public String getGame() {
		return game;
	}

	private void getActualPosition() {
		for (int row = 0; row < rowCount; row++)
			for (int column = 0; column < columnCount; column++)
				if (field[row][column] == Gap) {
					actualZeroRow = row;
					actualZeroColumn = column;
				}
	}

	public void moveUp() {
		getActualPosition();
		if (actualZeroRow >= 1) {
			field[actualZeroRow][actualZeroColumn] = field[actualZeroRow - 1][actualZeroColumn];
			field[actualZeroRow - 1][actualZeroColumn] = Gap;
		}
	}

	public void moveDown() {
		getActualPosition();
		if (actualZeroRow < rowCount - 1) {
			field[actualZeroRow][actualZeroColumn] = field[actualZeroRow + 1][actualZeroColumn];
			field[actualZeroRow + 1][actualZeroColumn] = Gap;
		}

	}

	public void moveLeft() {
		getActualPosition();
		if (actualZeroColumn >= 1) {
			field[actualZeroRow][actualZeroColumn] = field[actualZeroRow][actualZeroColumn - 1];
			field[actualZeroRow][actualZeroColumn - 1] = Gap;
		}

	}

	public void moveRight() {
		getActualPosition();
		if (actualZeroColumn < columnCount - 1) {
			field[actualZeroRow][actualZeroColumn] = field[actualZeroRow][actualZeroColumn + 1];
			field[actualZeroRow][actualZeroColumn + 1] = Gap;
		}
	}

	public int getPlayingSeconds() {
		return (int) ((System.currentTimeMillis() - startMillis) / 1000);
	}

	public boolean isSolved() {
		int value = 1;

		if (field[rowCount - 1][columnCount - 1] != Gap) {
			return false;
		}

		for (int row = 0; row < rowCount; row++)
			for (int column = 0; column < columnCount; column++) {
				if (!(row == rowCount - 1 && column == columnCount - 1)) {
					if (field[row][column] != value) {
						return false;
					}
					value++;
				}

			}
		return true;
	}

}
