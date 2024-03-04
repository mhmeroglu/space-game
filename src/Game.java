import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements KeyListener, ActionListener {

	Timer timer = new Timer(40, this);

	Random generator = new Random();

	ImageIcon icon;
	JButton button;
	JLabel label;

	private BufferedImage image;
	private BufferedImage image1;
	private BufferedImage image2;

	private int playbutton = 0;

	private int spaceShipX = 365;

	private int meteorX = 0;
	private int meteorY = 0;

	private int meteor1X = 740;
	private int meteor1Y = 0;

	private int SpaceX = 20;
	private int SpaceY = 20;

	public boolean ControlOfGame() {

		if (new Rectangle(meteor1X, meteor1Y, image2.getWidth() / 4, image2.getHeight() / 4).intersects(spaceShipX, 490,
				image.getWidth() / 12, image.getHeight() / 12)
				|| new Rectangle(meteorX, meteorY, image1.getWidth() / 5, image1.getHeight() / 5).intersects(spaceShipX,
						490, image.getWidth() / 12, image.getHeight() / 12)) {
			return true;

		}
		return false;

	}

	public Game() {

		try {
			image = ImageIO.read(new FileImageInputStream(new File("SpaceShip.png")));
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {
			image1 = ImageIO.read(new FileImageInputStream(new File("ufo.png")));
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {
			image2 = ImageIO.read(new FileImageInputStream(new File("meteor.png")));
		} catch (IOException e) {

			e.printStackTrace();
		}

		setBackground(Color.black);

		icon = new ImageIcon("play.png");

		button = new JButton();

		label = new JLabel();

		button.setBounds(350, 150, 80, 80);

		button.addActionListener(this);

		button.setFocusable(false);

		button.setIcon(icon);

		button.setBackground(Color.white);

		button.setBorder(BorderFactory.createEtchedBorder());

		label.setFont(new Font("Arial Black", 2, 30));

		label.setText("Endless Fly");

		label.setBounds(290, 240, 200, 50);

		label.setForeground(Color.white);

		this.add(label);

		label.setVisible(true);

		this.setLayout(null);

		this.add(button);

		this.setVisible(true);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.drawImage(image2, meteor1X, meteor1Y, image2.getWidth() / 4, image2.getHeight() / 4, this);
		g.drawImage(image1, meteorX, meteorY, image1.getWidth() / 5, image1.getHeight() / 5, this);
		g.drawImage(image, spaceShipX, 500, image.getWidth() / 12, image.getHeight() / 12, this);

		if (ControlOfGame()) {
			timer.stop();

			String message = "GAME OVER " + " IF YOU WANT TO PLAY AGAIN" + " YOU HAVE TO OPEN THE GAME AGAIN";

			JOptionPane.showMessageDialog(this, message);
			System.exit(0);

		}

	}

	@Override
	public void repaint() {
		super.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();

		if (c == KeyEvent.VK_ENTER) {
			button.setEnabled(false);
			button.setVisible(false);
			label.setVisible(false);

			timer.start();

		}

		if (c == KeyEvent.VK_LEFT) {
			if (spaceShipX <= 0) {

				spaceShipX = 0;

			} else {
				spaceShipX = spaceShipX - SpaceX;
			}

		} else if (c == KeyEvent.VK_RIGHT) {
			if (spaceShipX >= 720) {

				spaceShipX = 720;
			} else {
				spaceShipX = spaceShipX + SpaceX;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		meteorY += SpaceY;
		meteor1Y += 25;

		if (meteorY == 600) {
			meteorX = generator.nextInt(740);

			meteorY = 0;

			if (new Rectangle(meteorX, meteorY, image1.getWidth() / 5, image1.getHeight() / 5).intersects(meteor1X,
					meteor1Y, image2.getWidth() / 4, image2.getHeight() / 4)) {
				meteorX = 100;
				meteor1X = 300;
			}

		}
		if (meteor1Y == 600) {
			meteor1X = generator.nextInt(740);

			meteor1Y = 0;

			if (new Rectangle(meteorX, meteorY, image1.getWidth() / 5, image1.getHeight() / 5).intersects(meteor1X,
					meteor1Y, image2.getWidth() / 4, image2.getHeight() / 4)) {
				meteorX = 100;
				meteor1X = 300;
			}

		}

		repaint();
	}

}
