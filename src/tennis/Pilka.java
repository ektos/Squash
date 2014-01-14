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

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

public class Pilka {
	private static final int DIAMETER = 25;
	
	double x = 10;
	double y = 20;
	double a = -1.015;
        double v = 2.00;
        double spin = (Math.random() % 90) / 5.00;
        double angle = 50;
	private Tennis game;

	public Pilka(Tennis game) {
		this.game = game;
	}

	void move() throws IOException, Przegrana {
                double xa = (Math.sin(Math.toRadians(angle)) * v);
                double ya = (Math.cos(Math.toRadians(angle)) * v);
                
                boolean stop = false;
                
		if (x + xa < 0)
			angle = 360 - angle;
		else if (x + xa > game.getWidth() - DIAMETER)
			angle = 360 - angle;
		else if (y + ya < 0){
                        v *= a;
                        angle = 360 - angle; 
                }
		else if (y + ya > game.getHeight() - DIAMETER)
			throw new Przegrana();
                        //game.gameOver();
		else if (collision()){
			angle = 360 - angle; 
                        stop = true;
                        game.score++;
                        v *= a;              
			y = game.racquet.getTopY() - DIAMETER;
                        spin = (Math.random() % 90) / 4.50;
		} else 
			
		angle += spin;
                    
		x = x + xa;
                if(!stop) y = y + ya;
                
                stop = false;
	}

	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.fillOval((int)x, (int)y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, DIAMETER, DIAMETER);
	}
}


