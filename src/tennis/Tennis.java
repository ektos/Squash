/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tennis;

/**
 *
 * @author Jakub
 */


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@SuppressWarnings("serial")
public class Tennis extends JPanel {

	Pilka ball = new Pilka(this);
	Rakietka racquet = new Rakietka(this);
        HiScore hiscore = new HiScore();
        
	int score = 1;

	private int getScore() {
		return score - 1;
	}

	public Tennis() throws IOException {
            hiscore.load();                          
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		setFocusable(true);
	}

	private void move() throws IOException {
            try {
                ball.move();
            } catch (Przegrana ex) {
                this.gameOver();
            }
		racquet.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
                g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Consolas", Font.BOLD, 100));
		g2d.drawString(String.valueOf(getScore()), 150, 200);
                
                g2d.setColor(Color.RED);
                ball.paint(g2d);
                
                g2d.setColor(Color.BLACK);
		racquet.paint(g2d);		
	}

	public void gameOver() throws IOException {
                if(hiscore.getScore() < getScore()){
                    JOptionPane.showMessageDialog(this, "Brawo, pobiłeś nowy rekord " + getScore(),
				"Game Over", JOptionPane.YES_NO_OPTION);
                    hiscore.setScore(getScore());
                }
                else
                    JOptionPane.showMessageDialog(this, "Cieniasie, twój wynik to marne " + getScore(),
				"Game Over", JOptionPane.YES_NO_OPTION);
                
		System.exit(ABORT);
	}

	public static void main(String[] args) throws InterruptedException, IOException {
                
		JFrame frame = new JFrame("Squash");
		Tennis game = new Tennis();
		frame.add(game);
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                Runnable painter = new Painter(game);
                Thread rysownik = new Thread(painter);
                rysownik.start();
                Thread.sleep(3000);
                
		while (true) {
			game.move();
			//game.repaint();
			Thread.sleep(7);
		}
	}
}