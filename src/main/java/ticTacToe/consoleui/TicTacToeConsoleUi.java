package ticTacToe.consoleui;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import GameStudio.score.HallOfFame;
import ticTacToe.core.TicTacToe;

public class TicTacToeConsoleUi {
	TicTacToe t = new TicTacToe();
	private HallOfFame hallOfFame;
	private static final Pattern INPUT_PATTERN = Pattern.compile("([O])([A-I])([0-8])");

	public void play() {
		t.printBoard();
		while (!t.isBoardFull()) {
			if(!t.checkForWin()){
			pocess();
			t.printBoard();
			t.playerComputer();
			t.printBoard();
			}
			// System.out.println(t.checkForWin());
			else if (t.checkForWin()) {
				System.out.println("Vyhral si!");
				String name = System.getProperty("user.name");
				System.out.println(name + " tvoj cas je : " + t.getPlayingSeconds() + " s");
				try {
					hallOfFame.addScore(name, t.getPlayingSeconds());
					hallOfFame.loadScore();

					System.out.println("Vloz komentar pre hru: " + t.getGame() + "-> ");
					String com = new Scanner(System.in).nextLine();
					hallOfFame.setComment(name, t.getGame(), com);

					System.out.println("Vloz rating pre hru: " + t.getGame() + "-> ");
					int ra = new Scanner(System.in).nextInt();
					hallOfFame.setRating(name, t.getGame(), ra);

				} catch (Exception e) {
					System.err.println("Nepodarilo sa ulozit score");
					e.printStackTrace();
				}

			}
			System.out.println("Remiza");
		}
	}

	public void pocess() {
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
				t.placeMark(row, column);
				t.changePlayer();
			}
		} else {
			System.out.println("Zrob ine, ne toto");
		}

		System.out.println("Vykreslenie herneho pola");
	}

}