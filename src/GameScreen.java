import javax.swing.JFrame;

public class GameScreen extends JFrame {

	public GameScreen(String string) {

	}

	public static void main(String[] args) {

		GameScreen screen = new GameScreen("SpaceGame");
		screen.setResizable(false);
		screen.setFocusable(false);
		screen.setSize(800, 600);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Game spacegame = new Game();

		spacegame.requestFocus();

		spacegame.addKeyListener(spacegame);

		spacegame.setFocusable(true);
		spacegame.setFocusTraversalKeysEnabled(false);

		screen.add(spacegame);

		screen.setVisible(true);

	}

}
