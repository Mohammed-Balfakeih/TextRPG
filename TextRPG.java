/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textrpg;

import java.util.Random;
import java.util.Scanner;

//To do:
/**
 *
 * @author Mohammed
 */
public class TextRPG {

    /**
     * @param n The room number, which will cause certain exceptions because of the map design
     * @param player Player statistics
     * @return The next room number
     * @throws java.lang.InterruptedException
     */
    public static int Room(int n, Player player) throws InterruptedException{
        Scanner s = new Scanner(System.in);
        int e; //Enemys, 1 is a normal enemy and 2 is a boss fight
        int t; //item number, 1 is a potion increasing defense, 2 is a new move
        switch (n) {
            case 0: //Battle in Room 0
            case 8: //Battle in room 8
                if((player.killed8 == true && n == 8) || (player.killed0 == true && n == 0)){
                    System.out.println("The man is gone, it's an empty room");
                }else{
                    System.out.println("A man is in the cage, he sees you and tries to kill you");
                    e = 1; //Normal enemy
                    Battle(e, player); //Going to Battle
                    if(n == 0){
                        player.killed0 = true;
                    }else if(n == 8){
                        player.killed8 = true;
                    }
                }
                break;
            case 7: //Battle in Room 7
                System.out.println("An angry man with a club glances at you, he charges furiously at you");
                e = 2; //Boss fight
                Battle(e, player); //Going to Battle
                break;
            case 4: //Item in room 4
                if("p".equals(player.firstletteri[0])){ //If they already have the item
                    System.out.println("The potion was taken, the room is empty");
                }else{
                System.out.println("There is a small mysterious potion, maybe drinking it will help in battle. You pick it up"); //Increases defense
                player.items[0] = "Potion"; //Adding potion to their inventory
                player.firstletteri[0] = "p";
                System.out.println("You now have a " + player.items[0]);
                }
                break;
            case 6: //New move in room 6
                System.out.println("There are some notes in the cell, it teaches you how to intimdate enemies. You can now intimidate in battle"); //Decreases enemy defense
                player.moves[3] = "Intimidate"; //Adding intimidate to move list
                player.firstletterm[3] = "i";
                break;
            case 2:
                System.out.println("You are in your cell");
            default: //Other rooms are empty
                System.out.println("It's an empty room");
                break;
        }
        String option;
        switch (n) { //There are 9 rooms, arranged in a rectangle 5 wide and 2 high, rooms on the short edge of the rectangle have doors to move up or down (+5)
            case 10: //There are only 9 rooms, room 10 is only in case anything goes wrong
                System.exit(0);
            case 4:
                System.out.println("Do you want to move forward or left? (f/l)?");
                option = s.next().toLowerCase();
                if(null == option){
                    System.exit(0);
                }else switch (option) {
            case "f": 
                return (n+5);
            case "l": 
                return (n-1);
            default:
                System.exit(0);
        }
            case 9:
                System.out.println("Do you want to move backwards or left? (b/l)?");
                option = s.next().toLowerCase();
                if(null == option){
                    System.exit(0);
                }else switch (option) {
            case "b":
                return (n-5);
            case "l":
                return (n-1);
            default:
                System.exit(0);
        }
            case 5:
                System.out.println("Do you want to move backwards or right? (b/r)?");
                option = s.next().toLowerCase();
                if(null == option){
                    System.exit(0);
                }else switch (option) {
            case "b":
                return 0;
            case "r":
                return 6;
            default:
                System.exit(0);
        }
            case 0:
                System.out.println("Do you want to move forwards or right? (f/r)?");
                option = s.next().toLowerCase();
                if(null == option){
                    System.exit(0);
                }else switch (option) {
            case "f":
                return 5;
            case "r":
                return 1;
            default:
                System.exit(0);
        }
            default: //Default moving right or left
                System.out.println("Do you want to move right or left? (r/l)?");
                option = s.next().toLowerCase();
                if(null == option){
                    System.exit(0);
                }else switch (option) {
            case "r":
                return (n+1);
            case "l":
                return (n-1);
            default:
                System.exit(0);
        }
        }
        return 10; //Return statement required, the code should not reach here
        
    }
    
    
    /**
     * @param e enemy number
     * @param player The player statistics
     * @throws java.lang.InterruptedException
     */
    public static void Battle(int e, Player player) throws InterruptedException{ //Actual battling system
        Enemy enemy = new Enemy(e); //e == 1 is a normal enemy, e == 2 is a boss fight
        while(enemy.HP > 0 && player.HP > 0){ //While they are both alive
        //Printing HP and player choices
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        Thread.sleep(1000);
        System.out.println("Prisoner:");
        if(enemy.HP > 0 && enemy.HP < 1){ //As to not print that the enemy has 0HP
            enemy.HP = 1;
        }
        System.out.println("HP: " + (int) enemy.HP);
        Thread.sleep(1000);
        System.out.println("Attack: " + enemy.attack);
        System.out.println("Defense: " + enemy.defense);
        System.out.println("\n");
        System.out.println("Wolf" + ":");
        if(player.HP > 0 && player.HP < 1){ //As to not print that the player has 0HP
            player.HP = 1;
        }
        System.out.println("HP: " + (int) player.HP);
        Thread.sleep(1000);
        System.out.println("Level: " + (int) player.level);
        System.out.println("Attack: " + player.attack);
        System.out.println("Defense: " + player.defense);
        Thread.sleep(1000);
        System.out.println("Attack");
        Thread.sleep(1000);
        System.out.println("Items");
        Thread.sleep(1000);
        System.out.println("Run");
        Thread.sleep(1000);
        System.out.println("(a/i/r)?");
        Scanner s = new Scanner(System.in);
        String option = s.next().toLowerCase();
        String move = null;
        if( null != option) //Choosing between attacking, using items, or running away
            switch (option) {
                case "a":
                    for (String moves : player.moves) { //Printing moves
                        if(moves != null){
                            System.out.println(moves);
                        }
                    } 
                    System.out.print("(");
                    for(String letter : player.firstletterm){ //Printing inputs
                        if(letter != null){
                            System.out.print(letter + "/");
                        }
                    }   
                    System.out.print(")");
                    option = s.next().toLowerCase();
                    
                    if(player.firstletterm[0].equals(option)){ //Bite
                        enemy.HP = (enemy.HP - ((5 * player.attack)/enemy.defense));
                        move = player.moves[0];
                    }
                    else if(player.firstletterm[1].equals(option)){ //Tackle
                        enemy.HP = (enemy.HP - ((4 * player.attack)/enemy.defense));
                        move = player.moves[1];
                    }
                    else if(player.firstletterm[2].equals(option)){ //Roar
                        player.attack += 0.25; //Increasing User attack
                        move = player.moves[2];
                    }
                    else if(player.firstletterm[3].equals(option)){ //Intimidate
                        if(enemy.defense <= 0.25){
                            System.out.println("Enemy's defense can't be further lowered");
                        }else{
                            enemy.defense -= 0.25; //Decreasing Enemy defense
                        }
                        move = player.moves[3];
                    }   
                    break;
                
                case "i":
                    if(player.items[0] == null){
                        System.out.println("You have no items!"); //Continues to next turn
                        move = "items, but you don't have any!";
                    }else{ //If they have items
                        for(String items : player.items){
                            System.out.println(items); //printing items
                        }
                        System.out.print("(");
                        for(String letter : player.firstletteri){ //Printing inputs
                            if(letter != null){
                            System.out.print(letter + "/");
                            }       
                            System.out.print(")");
                        } 
                        
                        option = s.next().toLowerCase();
                        if(player.firstletteri[0].equals(option)){
                            System.out.println("Your defense increased!"); //Potion
                            player.defense += 0.5;
                            move = player.items[0];
                            player.items = null;
                        }
                    }   break;
                
                case "r":
                    Random rand = new Random();
                    int n = rand.nextInt(20); //5% chance of success
                    if(n == 0){
                        System.out.println("You ran away!");
                        return;
                    }else{
                        move = "Run, but it failed!";
                    }   break;
                default:
                    break;
            }
        //Choosing Enemy move randomly
        Random rand = new Random();
        int n = -1;
        if(e == 1){
            n = rand.nextInt(3);
        }else if(e == 2){ //Giving Boss Club Swing
            n = rand.nextInt(5);
        }
        String enemyMove = null;
            switch (n) {
                case 0:
                    enemyMove = enemy.moves[0]; //Punch
                    player.HP = (player.HP - ((5 * enemy.attack)/player.defense));
                    break;
                case 1:
                    enemyMove = enemy.moves[1]; //Kick
                    player.HP = (player.HP - ((4 * enemy.attack)/player.defense));
                    break;
                case 2:
                    enemyMove = enemy.moves[2]; //Intimidate
                    if(player.defense <= 0.25){
                        System.out.println("Enemy used intimidate but your defense can't be lowered!");
                    }else{
                        player.defense -= 0.25;
                    }   break;
                case 3:
                case 4:
                    enemyMove = enemy.moves[3]; //Club swing, only the boss
                    player.HP = (player.HP - ((7 * enemy.attack)/player.defense));
                    break; 
                default:
                    break;
            }
        //Printing moves
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Enemy used " + enemyMove);
        Thread.sleep(2000);
        System.out.println("You used " + move);
        }
        if(player.HP <= 0){ //Player loses
            System.out.println("You lost, better luck next time!");
            System.exit(0);
        }else if(enemy.HP <= 0 && e == 2){ //Winning the game by beating the boss fight
            System.out.println("You find a hole in the wall, you can escape! You crawl out of the jail cell, good job you won!");
            System.exit(0);
        }else if(enemy.HP <= 0){ //Beating the enemy, returning you to the rooms
            System.out.println("You won the battle! You find a bottle in the cell, you're fully healed!");
            Thread.sleep(2000);
            player.level += 1;
            player.HP = 15 + (player.level-1)*2;
            player.attack = player.level;
            player.defense = player.level;
            System.out.println("You leveled up! You are now level: " + player.level);
        }
    }
     

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner s = new Scanner(System.in);
        System.out.println("Would you like to play? (y/n)"); 
        if("y".equals(s.next().toLowerCase())){
            Thread.sleep(1000);
            System.out.println("You wake up in your small home, you live in the village of Ordon, and are a farmer. It’s time for you to check on your sheep.");
            Thread.sleep(2000);
            System.out.println("Exit or investigate (e/i)?");
            String option = s.next().toLowerCase();
            
            if("e".equals(option)){
                System.out.println("You leave the house and check on your sheep, they’re all hiding in the small shed, you hear the sound of loud footsteps, you looks behind you and see a human-like red monster with a club, before you knows what’s happening, you're knocked out");
            }else if("i".equals(option)){
                System.out.println("You looks at the shelf and see a picture of your family");
                Thread.sleep(5000);
                System.out.println("You leave the house and check on your sheep, they’re all hiding in the small shed, you hear the sound of loud footsteps, you look behind you and see a human-like red monster with a club, before you knows what’s happening, you're knocked out");
            }
            
            Thread.sleep(7000);
            System.out.println("You wake up in a small cell, there's a torn mattress and a tiny toilet.");
            Thread.sleep(5000);
            System.out.println("Hello there wolf! bellowed the monster");
            Thread.sleep(5000);
            System.out.println("You looked at your body. You're a wolf!");
            Thread.sleep(5000);
            System.out.println("Have some fun before your execution! said the monster.");
            Thread.sleep(5000);
            System.out.println("Investigate or sit around (i/s)?");
            
            option = s.next().toLowerCase();
            if("s".equals(option)){
                System.out.println("You were taken to the execution room, the monster took out his club and started hammering and hammering until you stopped screaming, you were dead.");
                Thread.sleep(5000);
                System.out.println("You lost, better luck next time");
                System.exit(0);
            }else if("i".equals(option)){
                System.out.println("You see a box, you push it away, and find an open vent, you crawl into it");
                boolean won = false;
                int n = 2;
                Player player = new Player(); //Player statistics
                player.attack = 1;
                player.defense = 1;
                while(won == false){
                   n = Room(n, player);
                }
            }
            
        
        }else{
            System.exit(0);
        }
    }
    
    
}
