package GameStudio.main;

import java.util.Scanner;

import GameStudio.score.HallOfFame;
import fifteenPuzle.consoleui.PuzzleConsoleUI;
import minesweeper.consoleui.MineConsoleUI;
import ticTacToe.consoleui.TicTacToeConsoleUi;
import ticTacToe.core.TicTacToe;

public class GameStudio {

	private MineConsoleUI mines;
	private PuzzleConsoleUI puzzle;
	private TicTacToeConsoleUi ticTacToeConsoleUi;

	private HallOfFame hallOfFame;

	public MineConsoleUI getMines() {
		return mines;
	}

	public void setMines(MineConsoleUI mines) {
		this.mines = mines;
	}

	public PuzzleConsoleUI getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(PuzzleConsoleUI puzzle) {
		this.puzzle = puzzle;
	}
	
	public TicTacToeConsoleUi getTicTacToeConsoleUi() {
		return ticTacToeConsoleUi;
	}
	
	public void setTicTacToeConsoleUi(TicTacToeConsoleUi ticTacToeConsoleUi) {
		this.ticTacToeConsoleUi = ticTacToeConsoleUi;
	}

	static final Scanner sc = new Scanner(System.in);

	public void play() {
		System.out.println("********Game Studio********");
		System.out.println("***Hra pre jedneho hraca***");
		System.out.println("###########################");
		System.out.println("**Meno hry*********stlac***");
		System.out.println("**MINESWEEPER********M*****");
		System.out.println("**PUZZLE*************P*****");
		System.out.println("###########################");
		System.out.println("**Hra pre viacerych hracov*");
		System.out.println("###########################");
		System.out.println("**Meno hry*********stlac***");
		System.out.println("**TicTacToe**********T*****");
		System.out.println("###########################");

		String selection = sc.next().toUpperCase();
		switch (selection) {
		case "M":
			mines.play();
			break;
		case "P":
			puzzle.play();
			break;
		case "T":
			ticTacToeConsoleUi.play();
			break;
		default:
			break;
		}
	}

}
