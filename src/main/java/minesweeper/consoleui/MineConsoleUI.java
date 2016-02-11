package minesweeper.consoleui;

import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import GameStudio.score.GameState;
import GameStudio.score.HallOfFame;

import minesweeper.core.Clue;
import minesweeper.core.MineField;
import minesweeper.core.Tile;

public class MineConsoleUI {
	private MineField field;

	private HallOfFame hallOfFame;

	private static final Pattern INPUT_PATTERN = Pattern.compile("([O|M])([A-I])([0-8])");

	public MineConsoleUI(MineField field) {
		this.field = field;
	}

	public void setHallOfFame(HallOfFame hallOfFame) {
		this.hallOfFame = hallOfFame;
	}

	public void play() {
		show();
		while (field.getState() == GameState.PLAYING) {
			processInput();
			show();
		}

		if (field.getState() == GameState.SOLVED) {
			String name = System.getProperty("user.name");
			try {
				
				/*System.out.println("************Priemerne score***************");
				hallOfFame.returnAverage(field.getGame());
				System.out.println("******************************************");*/
				
				
				hallOfFame.addScore(name, field.getPlayingSeconds());
				hallOfFame.loadScore();
				
				System.out.println("Vloz komentar pre hru: " + field.getGame()+ "-> ");
				String com = new Scanner(System.in).nextLine();
				hallOfFame.setComment(name, field.getGame(), com);
				
				System.out.println("Vloz rating pre hru: " + field.getGame()+ "-> ");
				int ra = new Scanner(System.in).nextInt();
				hallOfFame.setRating(name, field.getGame(), ra);
				
				//hallOfFame.returnAverage(field.getGame());
				
				
		
			} catch (Exception e) {
				System.err.println("Nepodarilo sa ulozit score");
				e.printStackTrace();
			}
			

			System.out.println("Vyhral si!");
			System.out.println(hallOfFame);
		} else {
			System.out.println("Prehral si!");
		}
	}

	private void processInput() {
		System.out.println("Zrob daco");
		String line = new Scanner(System.in).nextLine().toUpperCase();
		if ("X".equals(line)) {
			System.exit(0);
		}
		Matcher matcher = INPUT_PATTERN.matcher(line);
		if (matcher.matches()) {
			int row = matcher.group(2).charAt(0) - 'A';
			int column = Integer.parseInt(matcher.group(3));
			if ("O".equals(matcher.group(1))) {
				field.openTile(row, column);
			} else {
				field.markTile(row, column);
			}
		} else {
			System.out.println("Zrob ine, ne toto");
		}
	}

	public void show() {
		showHeader();
		showField();
	}

	private void showField() {
		for (int row = 0; row < field.getRowCount(); row++) {
			System.out.print((char) (row + 'A'));

			for (int column = 0; column < field.getColumnCount(); column++) {
				System.out.print(" ");

				Tile tile = field.getTile(row, column);
				switch (tile.getState()) {
				case CLOSED:
					System.out.print("-");
					break;
				case MARKED:
					System.out.print("M");
					break;
				case OPEN:
					if (tile instanceof Clue) {
						System.out.print(((Clue) tile).getValue());
					} else {
						System.out.print("X");
					}
					break;
				default:
					break;
				}
			}

			System.out.println();
		}
	}

	private void showHeader() {
		System.out.println("Tvoj cas: " + field.getPlayingSeconds() + " s");

		System.out.print(" ");
		for (int column = 0; column < field.getColumnCount(); column++) {
			System.out.print(" " + column);
		}
		System.out.println();
	}
}
