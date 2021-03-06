package utils;

import games.player.WPCPlayer;
import games.scenario.ALPGameScenario;
import games.scenario.GameScenario;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

import cecj.app.go.GoGame;

public class HeuristicPerformanceMeter {

	double[] wpc = { 1.00f, -0.25f, 0.10f, 0.05f, 0.05f, 0.10f, -0.25f, 1.00f, -0.25f, -0.25f,
			0.01f, 0.01f, 0.01f, 0.01f, -0.25f, -0.25f, 0.10f, 0.01f, 0.05f, 0.02f, 0.02f, 0.05f,
			0.01f, 0.10f, 0.05f, 0.01f, 0.02f, 0.01f, 0.01f, 0.02f, 0.01f, 0.05f, 0.05f, 0.01f,
			0.02f, 0.01f, 0.01f, 0.02f, 0.01f, 0.05f, 0.10f, 0.01f, 0.05f, 0.02f, 0.02f, 0.05f,
			0.01f, 0.10f, -0.25f, -0.25f, 0.01f, 0.01f, 0.01f, 0.01f, -0.25f, -0.25f, 1.00f,
			-0.25f, 0.10f, 0.05f, 0.05f, 0.10f, -0.25f, 1.00f };

	private WPCPlayer player;

	public void readPlayer(InputStream input) {
		Scanner s = new Scanner(input).useLocale(Locale.ENGLISH);
		double[] playerWpc = new double[25];
		for (int i = 0; i < 25; i++) {
			playerWpc[i] = s.nextDouble();
		}
		player = new WPCPlayer(playerWpc);

		System.out.println("Player = " + player);
	}

	private void testPlayer(GoGame game) {
//		WPCPlayer heuristic = new WPCPlayer(wpc);
		
		GameScenario scenario1 = new ALPGameScenario(player, 0, new double[] {0, 0 }); 
		GameScenario scenario2 = new ALPGameScenario(player, 1, new double[] {0, 0 });
		
//			new RandomizedTwoPlayersGameScenario(
//				new MersenneTwisterFast(1987), new Player[] { player, heuristic }, new double[] {
//						0, 0 });
//		GameScenario scenario2 = new RandomizedTwoPlayersGameScenario(
//				new MersenneTwisterFast(1987), new Player[] { heuristic, player }, new double[] {
//						0, 0 });

		game.reset();
		System.out.println("Playing as black, the result is " + scenario1.play(game));
		System.out.println(game.getBoard());

		game.reset();
		System.out.println("Playing as white, the result is " + scenario2.play(game));
		System.out.println(game.getBoard());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HeuristicPerformanceMeter hpm = new HeuristicPerformanceMeter();
		hpm.readPlayer(System.in);
		hpm.testPlayer(new GoGame());
	}
}
