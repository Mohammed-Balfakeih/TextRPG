/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textrpg;

/**
 *
 * @author Mohammed
 */
public class Player{
        double level = 1;
        double attack;
        double defense;
        double HP = 15 + (level-1)*2;
        String[] moves = new String[4]; 
        String[] firstletterm = new String[4];
        String[] items = new String[1];
        String[] firstletteri = new String[1];
        boolean killed8 = false;
        boolean killed0 = false;
        public Player(){
            moves[0] = "Bite"; //5 damage
            moves[1] = "Tackle"; //4 damage
            moves[2] = "Roar"; //increase attack by 0.25
            firstletterm[0] = "b";
            firstletterm[1] = "t";
            firstletterm[2] = "r";
            
        }
    }
