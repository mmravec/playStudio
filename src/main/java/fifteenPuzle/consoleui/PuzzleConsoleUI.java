package fifteenPuzle.consoleui;

import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import GameStudio.score.GameState;
import GameStudio.score.HallOfFame;
import fifteenPuzle.core.PuzzleField;

public class PuzzleConsoleUI {
	private PuzzleField field;
	private static final Pattern INPUT_PATTERN = Pattern.compile("([WSAD])");
	private HallOfFame hallOfFame;

	public PuzzleConsoleUI(PuzzleField field) {
		this.field = field;
	}

	public void setHallOfFame(HallOfFame hallOfFame) {
		this.hallOfFame = hallOfFame;
	}

	public void play() {
		System.out.println("*********Pre ukoncenie programu stlac x**************");
		System.out.println("*********************Ovladanie***********************");
		System.out.println("*****W - hore   S - dole a - vlavo  d - vpravo*******");
		System.out.println("*****************************************************");
		show();
		while (field.getState() == GameState.PLAYING) {
			processInput();
			show();
		}

		if (field.getState() == GameState.SOLVED) {
			String name = System.getProperty("user.name");
			try {
				hallOfFame.addScore(name, field.getPlayingSeconds());
				hallOfFame.loadScore();

				System.out.println("Vloz komentar pre hru: " + field.getGame()+ "-> ");
				String com = new Scanner(System.in).nextLine();
				hallOfFame.setComment(name, field.getGame(), com);

				System.out.println("Vloz rating pre hru: " + field.getGame()+ "-> ");
				int ra = new Scanner(System.in).nextInt();
				hallOfFame.setRating(name, field.getGame(), ra);

			} catch (Exception e) {
				System.err.println("Nepodarilo sa ulozit score");
				e.printStackTrace();
			}

			System.out.println("**meno hraca*******score*******typ hry****");
			System.out.println(hallOfFame);
			System.out.println("******************************************");
		} else {
			System.err.println("Prehral si!");
		}

	}

	private void processInput() {
		@SuppressWarnings("resource")
		String line = new Scanner(System.in).nextLine().toUpperCase();

		if ("X".equals(line)) {
			System.err.println("****Koniec hry******");
			System.exit(1);
		}

		Matcher matcher = INPUT_PATTERN.matcher(line);
		if (matcher.matches()) {
			if ("W".equals(matcher.group(1))) {
				field.moveUp();
			}
			if ("D".equals(matcher.group(1))) {
				field.moveRight();
			}
			if ("S".equals(matcher.group(1))) {
				field.moveDown();
			}
			if ("A".equals(matcher.group(1))) {
				field.moveLeft();
			}
			if (field.isSolved())
				field.setState(GameState.SOLVED);
		} else {
			System.out.println("Skus jedno stohto {W/S/A/D}");
		}
	}

	public void show() {
		for (int row = 0; row < field.getRowCount(); row++) {

			for (int column = 0; column < field.getColumnCount(); column++) {

				if (field.getTile(row, column) == 0)
					System.out.print("O" + "\t");
				if (field.getTile(row, column) != 0)
					System.out.print(field.getTile(row, column) + "\t");

			}
			System.out.println("\n");

		}

	}
}
