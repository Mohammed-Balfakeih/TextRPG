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
public class Enemy {
    double HP;
    double attack;
    double defense;
    String[] moves = new String[4];
    public Enemy(int e){ 
        if(e == 1){ //Regular Enemy
            HP = 12;
            attack = 1;
            defense = 1;
            moves[0] = "Punch";
            moves[1] = "Kick";
            moves[2] = "Intimidate";
        }else if(e == 2){ //Boss Fight
            HP = 20;
            attack = 2.25;
            defense = 2.25;
            moves[0] = "Punch";
            moves[1] = "Kick";
            moves[2] = "Intimidate";
            moves[3] = "Club Swing";
        }
    }
}
