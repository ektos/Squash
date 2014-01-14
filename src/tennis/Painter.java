/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tennis;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakub
 */
public class Painter implements Runnable {
    
    Tennis game;
    
    public Painter(Tennis game){
        this.game = game;
    };
        
    @Override
    public void run(){
        while(true){
            game.repaint();
            try {
                Thread.sleep(7);
            } catch (InterruptedException ex) {
                Logger.getLogger(Painter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
