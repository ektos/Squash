/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tennis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakub
 */
public class HiScore {
    int score;
    
    public HiScore(){
        score = -1;
    }
    
    public void load(){
        File file = new File("hiscore.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HiScore.class.getName()).log(Level.SEVERE, null, ex);
        }
        String text = null;
        try {
            text = reader.readLine();
            score = Integer.parseInt(text);
            System.out.println(score);
        } catch (IOException ex) {
            Logger.getLogger(HiScore.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public int getScore(){
        return score;
    }
    
    public void setScore(int newScore){
        score = newScore;
        Writer wr;
        try {
            wr = new FileWriter("hiscore.txt");
            wr.write(String.valueOf(score));
            wr.close();
        } catch (IOException ex) {
            Logger.getLogger(HiScore.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
