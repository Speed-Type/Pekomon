//Ian Jang
//Last updated 4/11
//File description: Driver file for Pekomon game

import java.util.Scanner;

import javax.swing.SwingUtilities;

import java.util.ArrayList;

public class PekomonRunner
{
    //static globally accessible variables
    public static Pekomon playerPekomon; //list of player's Pekomon
    public static int numCurrentlyEquippedPekomon = 0; //number of currently equipped Pekomon

    //variables for random Pekomon generation and selection
    public static Pekomon randomPekomon1;
    public static Pekomon randomPekomon2;
    public static Pekomon randomPekomon3;
    public static int pekomonSelected = 0;

    //Display things
    public static GameDisplay gameDisplay;
    public static int currentDisplay = 0; //current scene the player is on

    //setup scanner
    public static Scanner scan = new Scanner(System.in);

    //Variables for control of game flow
    public static boolean isInBattle = false;
    public static boolean gameOver = false;
    public static boolean didFlee = false;

    //Variables for control of battle
    public static Pekomon enemyPekomon;
    public static boolean isPlayerTurn = true;
    public static int numPotions = 3; //number of heal potions; player starts with three
    public static String battleOutput = "...";
    public static int timeBetweenTurns = 1;
    public static boolean firstStrike = true;

    //Variables for random events
    public static String[] randomEventsList = {"None", "Reselect", "Gain Heal Potion (Small)", "Gain Heal Potion (Medium)", "Gain Heal Potion (Large)", "Gain Heal Potion (XLarge)", "Heavy Attack Recharge", "Special Move Recharge", "Ultimate Move Recharge", "Small Full Recharge", "Medium Full Recharge", "Max Recharge", "Raise Attack", "Raise Attack (Greater)", "Raise Defense", "Raise Defense (Greater)", "Raise Special Attack", "Raise Special Attack (Greater)", "Raise Special Defense", "Raise Special Defense (Greater)", "Raise Speed", "Raise Speed (Greater)", "Raise Evasion", "Raise Evasion (Greater)", "Raise Accuracy", "Raise Accuracy (Greater)", "Raise Max HP", "Raise Max HP (Greater)", "Insta-Heal (Small)", "Insta-Heal (Medium)", "Insta-Heal (Large)", "Full Heal", "Reselect", "XP Boost (Small)", "XP Boost (Medium)", "XP Boost (Large)", "XP Boost (Massive)"};
    public static String[] randomEventsDescriptions = {"Unlucky...", "Pick a new Pekomon", "Gain 1 heal potion", "Gain 3 heal potions", "Gain 5 heal potions", "Gain 10 heal potions", "Regain all heavy attack charges", "Regain all special move charges", "Regain all ultimate move charges", "Regain one charge of each move", "Regain two charges of each move", "Regain all charges of all moves", "Increase attack stat", "Increase attack stat significantly", "Increase defense stat", "Increase defense stat significantly", "Increase special attack stat", "Increase special attack stat significantly", "Increase special defense stat", "Increase special defense stat significantly", "Increase speed stat", "Increase speed stat significantly", "Increase evasion stat", "Increase evasion stat significantly", "Increase accuracy stat", "Increase accuracy stat significantly", "Increase max HP stat", "Increase max HP stat significantly", "Heal for 25%", "Heal for 50%", "Heal for 75%", "Heal for 100%", "Pick a new Pekomon", "Gain 2 XP", "Gain 4 XP", "Gain 6 XP", "Gain 8 XP"};
    public static int event1;
    public static int event2;
    public static int event3;
    public static boolean hasChosenEvent = false;
    public static int eventChoice = 0;

    //variables for player stats
    public static int killCount = 0; //total kills
    public static int fleeCount = 0; //total times the player fled
    public static int totalDamageDealt = 0; //total damage the player pekomon dealt to enemies
    public static int totalDamageTaken = 0; //total damage the player pekomon took
    public static int totalDamageHealed = 0; //total damage the player pekomon healed
    public static int totalHealPotionsUsed = 0; //total heal potions used
    public static int totalTurns = 0; //total turns in battle
    public static int totalPekomonUsed = 0; //total pekomon used by player
    public static ArrayList<String> pekomonUsed = new ArrayList<String>(); //list of pekomon used by player
    public static int totalLightAttacksUsed = 0; //total light attacks
    public static int totalHeavyAttacksUsed = 0; //total heavy attacks
    public static int totalSpecialAttacksUsed = 0; //total special attacks
    public static int totalUltimateAttacksUsed = 0; //total ultimate attacks



    //method for generating a random pekomon of certain evo
    public static Pekomon randomPekomon(int evo)
    {
        if(evo == 0)
        {
            int totalEvo0Pekomon = 21;
            int randNum = (int)(Math.random() * totalEvo0Pekomon);
            
            //FOR TESTING
            // if(randNum > -1)
            // {
            //     Pekomon generatedPekomon = new MewTwo();

            //     return generatedPekomon;
            // }

            if(randNum == 0)
            {
                return new Bulbasaur();
            }
            else if(randNum == 1)
            {
                return new Pikachu();
            }
            else if(randNum == 2)
            {
                return new EeveeVaporeon();
            }
            else if(randNum == 3)
            {
                return new EeveeJolteon();
            }
            else if(randNum == 4)
            {
                return new EeveeFlareon();
            }
            else if(randNum == 5)
            {
                return new Squirtle();
            }
            else if(randNum == 6)
            {
                return new Seel();
            }
            else if(randNum == 7)
            {
                return new Magikarp();
            }
            else if(randNum == 8)
            {
                return new Krabby();
            }
            else if(randNum == 9)
            {
                return new Charmander();
            }
            else if(randNum == 10)
            {
                return new Growlithe();
            }
            else if(randNum == 11)
            {
                return new Caterpie();
            }
            else if(randNum == 12)
            {
                return new Weedle();
            }
            else if(randNum == 13)
            {
                return new Gastly();
            }
            else if(randNum == 14)
            {
                return new Pidgey();
            }
            else if(randNum == 15)
            {
                return new Meowth();
            }
            else if(randNum == 16)
            {
                return new Jigglypuff();
            }
            else if(randNum == 17)
            {
                return new Grimer();
            }
            else if(randNum == 18)
            {
                return new Machop();
            }
            else if(randNum == 19)
            {
                return new Diglett();
            }
            else if(randNum == 20)
            {
                return new Mew();
            }
            else
            {
                //default
                return new Bulbasaur();
            }
        }
        else if(evo == 1)
        {
            int totalEvo1Pekomon = 22;
            int randNum = (int)(Math.random() * totalEvo1Pekomon);
            
            if(randNum == 0)
            {
                Pekomon generatedPekomon = new Bulbasaur();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 1)
            {
                Pekomon generatedPekomon = new Pikachu();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 2)
            {
                Pekomon generatedPekomon = new EeveeVaporeon();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 3)
            {
                Pekomon generatedPekomon = new EeveeFlareon();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 4)
            {
                Pekomon generatedPekomon = new EeveeJolteon();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 5)
            {
                Pekomon generatedPekomon = new Squirtle();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 6)
            {
                Pekomon generatedPekomon = new Seel();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 7)
            {
                Pekomon generatedPekomon = new Magikarp();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 8)
            {
                Pekomon generatedPekomon = new Krabby();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 9)
            {
                Pekomon generatedPekomon = new Charmander();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 10)
            {
                Pekomon generatedPekomon = new Growlithe();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 11)
            {
                Pekomon generatedPekomon = new Caterpie();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 12)
            {
                Pekomon generatedPekomon = new Weedle();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 13)
            {
                Pekomon generatedPekomon = new Gastly();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 14)
            {
                Pekomon generatedPekomon = new Pidgey();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 15)
            {
                Pekomon generatedPekomon = new Meowth();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 16)
            {
                Pekomon generatedPekomon = new Jigglypuff();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 17)
            {
                Pekomon generatedPekomon = new Grimer();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 18)
            {
                Pekomon generatedPekomon = new Machop();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 19)
            {
                Pekomon generatedPekomon = new Diglett();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 20)
            {
                return new Mew();
            }
            else if(randNum == 21)
            {
                return new MewTwo();
            }
            else
            {
                //default
                return new Bulbasaur();
            }
        }
        else
        {
            int totalEvo2Pekomon = 22;
            int randNum = (int)(Math.random() * totalEvo2Pekomon);
            
            if(randNum == 0)
            {
                Pekomon generatedPekomon = new Bulbasaur();
                generatedPekomon.evolve();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 1)
            {
                Pekomon generatedPekomon = new EeveeVaporeon();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 2)
            {
                Pekomon generatedPekomon = new EeveeFlareon();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 3)
            {
                Pekomon generatedPekomon = new EeveeJolteon();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 4)
            {
                Pekomon generatedPekomon = new Squirtle();
                generatedPekomon.evolve();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 5)
            {
                Pekomon generatedPekomon = new Pikachu();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 6)
            {
                Pekomon generatedPekomon = new Seel();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 7)
            {
                Pekomon generatedPekomon = new Magikarp();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 8)
            {
                Pekomon generatedPekomon = new Krabby();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 9)
            {
                Pekomon generatedPekomon = new Charmander();
                generatedPekomon.evolve();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 10)
            {
                Pekomon generatedPekomon = new Growlithe();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 11)
            {
                Pekomon generatedPekomon = new Caterpie();
                generatedPekomon.evolve();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 12)
            {
                Pekomon generatedPekomon = new Weedle();
                generatedPekomon.evolve();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 13)
            {
                Pekomon generatedPekomon = new Gastly();
                generatedPekomon.evolve();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 14)
            {
                Pekomon generatedPekomon = new Pidgey();
                generatedPekomon.evolve();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 15)
            {
                Pekomon generatedPekomon = new Meowth();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 16)
            {
                Pekomon generatedPekomon = new Jigglypuff();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 17)
            {
                Pekomon generatedPekomon = new Grimer();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 18)
            {
                Pekomon generatedPekomon = new Machop();
                generatedPekomon.evolve();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 19)
            {
                Pekomon generatedPekomon = new Diglett();
                generatedPekomon.evolve();

                return generatedPekomon;
            }
            else if(randNum == 20)
            {
                return new Mew();
            }
            else if(randNum == 21)
            {
                return new MewTwo();
            }
            else
            {
                //default
                return new Bulbasaur();
            }
        }
    }

    //method for generating the three random Pekomon for the player to choose from
    public static void obtainNewPekomon(int evo)
    {
        randomPekomon1 = randomPekomon(evo);
        randomPekomon2 = randomPekomon(evo);
        randomPekomon3 = randomPekomon(evo);
    }

    //Method for the player to actually select the random Pekomon to keep
    public static void selectNewPekomon(int numSelection)
    {
        if(totalPekomonUsed > 0)
        {
            //add this to the pile
            pekomonUsed.add(playerPekomon.getName());
        }

        //update stats
        totalPekomonUsed++;

        if(numSelection == 1)
        {
            //select Pekomon 1
            playerPekomon = randomPekomon1;
            pekomonSelected = 1;
        }
        else if(numSelection == 2)
        {
            //select Pekomon 2
            playerPekomon = randomPekomon2;
            pekomonSelected = 2;
        }
        else if(numSelection == 3)
        {
            //select Pekomon 3
            playerPekomon = randomPekomon3;
            pekomonSelected = 3;
        }
    }

    //method to run an event
    public static void runEvent()
    {
        hasChosenEvent = true;
        int event = 0;
        if(eventChoice == 1)
        {
            event = event1;
        }
        else if(eventChoice == 2)
        {
            event = event2;
        }
        else if(eventChoice == 3)
        {
            event = event3;
        }

        if(event == 0)
        {
            //do nothing
        }
        else if(event == 1)
        {
            //reselect

            pekomonSelected = 0;
            gameDisplay.toNewPekomonScene();
        }
        else if(event == 2)
        {
            //gain one heal potion
            numPotions++;
        }
        else if(event == 3)
        {
            //gain 3 heal potion
            numPotions += 3;
        }
        else if(event == 4)
        {
            //gain 5 heal potion
            numPotions += 5;
        }
        else if(event == 5)
        {
            //gain 10 heal potion
            numPotions += 10;
        }
        else if(event == 6)
        {
            //Regain all heavy attack charges
            playerPekomon.changeCurrentHeavyAttackCharges(999999);
        }
        else if(event == 7)
        {
            //Regain all special attack charges
            playerPekomon.changeCurrentSpecialAttackCharges(999999);
        }
        else if(event == 8)
        {
            //Regain all ultimate attack charges
            playerPekomon.changeCurrentUltimateAttackCharges(999999);
        }
        else if(event == 9)
        {
            //Regain one charge of each move
            playerPekomon.changeCurrentHeavyAttackCharges(1);
            playerPekomon.changeCurrentSpecialAttackCharges(1);
            playerPekomon.changeCurrentUltimateAttackCharges(1);
        }
        else if(event == 10)
        {
            //Regain two charges of each move
            playerPekomon.changeCurrentHeavyAttackCharges(2);
            playerPekomon.changeCurrentSpecialAttackCharges(2);
            playerPekomon.changeCurrentUltimateAttackCharges(2);
        }
        else if(event == 11)
        {
            //Regain all charges of all moves
            playerPekomon.changeCurrentHeavyAttackCharges(999999);
            playerPekomon.changeCurrentSpecialAttackCharges(999999);
            playerPekomon.changeCurrentUltimateAttackCharges(999999);
        }
        else if(event == 12)
        {
            //Increase attack stat
            playerPekomon.changeAttack(5);
        }
        else if(event == 13)
        {
            //Increase attack highly
            playerPekomon.changeAttack(10);
        }
        else if(event == 14)
        {
            //increase defense
            playerPekomon.changeDefense(5);
        }
        else if(event == 15)
        {
            //increase defense highly
            playerPekomon.changeDefense(10);
        }
        else if(event == 16)
        {
            //increase special attack
            playerPekomon.changeSAttack(5);
        }
        else if(event == 17)
        {
            //increase special attack highly
            playerPekomon.changeSAttack(10);
        }
        else if(event == 18)
        {
            //increase special defense
            playerPekomon.changeSDefense(5);
        }
        else if(event == 19)
        {
            //increase special defense highly
            playerPekomon.changeSDefense(10);
        }
        else if(event == 20)
        {
            //increase speed stat
            playerPekomon.changeSpeed(5);
        }
        else if(event == 21)
        {
            //increase speed stat highly
            playerPekomon.changeSpeed(10);
        }
        else if(event == 22)
        {
            //increase evasion
            playerPekomon.changeEvasion(2);
        }
        else if(event == 23)
        {
            //increase evasion highly
            playerPekomon.changeEvasion(4);
        }
        else if(event == 24)
        {
            //increase accuracy
            playerPekomon.changeAccuracy(2);
        }
        else if(event == 25)
        {
            //increase accuracy highly
            playerPekomon.changeAccuracy(4);
        }
        else if(event == 26)
        {
            //increase max HP
            playerPekomon.changeMaxHP(5);
        }
        else if(event == 27)
        {
            //increase max HP greatly
            playerPekomon.changeMaxHP(10);
        }
        else if(event == 28)
        {
            //heal for 25%
            playerPekomon.changeCurrentHP((int)(playerPekomon.getMaxHP() / 4));
        }
        else if(event == 29)
        {
            //heal for 50%
            playerPekomon.changeCurrentHP((int)(playerPekomon.getMaxHP() / 2));
        }
        else if(event == 30)
        {
            //heal for 75%
            playerPekomon.changeCurrentHP((int)(playerPekomon.getMaxHP() * 3 / 4));
        }
        else if(event == 31)
        {
            //heal for 100%
            playerPekomon.changeCurrentHP(playerPekomon.getMaxHP());
        }
        else if(event == 32)
        {
            //reselect

            pekomonSelected = 0;
            gameDisplay.toNewPekomonScene();
        }
        else if(event == 33)
        {
            //increase xp by 2
            playerPekomon.changeXP(2);
        }
        else if(event == 34)
        {
            //increase xp by 4
            playerPekomon.changeXP(4);
        }
        else if(event == 35)
        {
            //increase xp by 6
            playerPekomon.changeXP(6);
        }
        else if(event == 36)
        {
            //increase xp by 8
            playerPekomon.changeXP(8);
        }
    }

    //method to generate random events
    public static void generateRandomEvents()
    {
        event1 = (int)(Math.random() * randomEventsList.length);
        event2 = (int)(Math.random() * randomEventsList.length);
        event3 = (int)(Math.random() * randomEventsList.length);
        hasChosenEvent = false;
        eventChoice = 0;
    }

    //method for doing a battle
    public static void battle(int evo)
    {
        //generate enemy Pekomon
        enemyPekomon = randomPekomon(evo);
        enemyPekomon.setEnemy();

        //pre-generate random events
        generateRandomEvents();

        //set player turn
        isPlayerTurn = true;
        isInBattle = true;
        didFlee = false;
        firstStrike = true;

        //reset player status
        playerPekomon.resetStatusEffects();

        //set battle text
        if(playerPekomon.getSpeed() >= enemyPekomon.getSpeed())
        {
            //player goes first
            battleOutput = "...";
        }
        else
        {
            //enemy goes first
            enemyTurn();
        }
    }

    //method for checking if battle is over
    public static void checkBattleOver()
    {
        if(enemyPekomon.isAlive() == false)
        {
            //player wins
            isInBattle = false;
            killCount++;

            //FOR TESTING; usually just +3
            playerPekomon.changeXP(3);
        }
        else if(playerPekomon.isAlive() == false)
        {
            //game over
            gameOver = true;
            isInBattle = false;
        }
    }

    //method for doing enemy action
    public static void enemyTurn()
    {
        int randNum = (int)(Math.random() * 101);
        //depending on evo
        if(enemyPekomon.getEvo() == 0)
        {
            //80% light attack, 20% heavy attack
            if(randNum <= 80)
            {
                //light attack
                enemyPekomon.basicAttack(playerPekomon);
            }
            else
            {
                //heavy attack
                if(enemyPekomon.getCurrentHeavyAttackCharges() > 0 && enemyPekomon.isAsleep() <= 0)
                {
                    enemyPekomon.heavyAttack(playerPekomon);
                }
                else
                {
                    enemyPekomon.basicAttack(playerPekomon);
                }
            }
        }
        else if(enemyPekomon.getEvo() == 1)
        {
            //75% light attack, 15% heavy attack, 10% special attack
            if(randNum <= 75)
            {
                //light attack
                enemyPekomon.basicAttack(playerPekomon);
            }
            else if(randNum <= 90)
            {
                //heavy attack
                if(enemyPekomon.getCurrentHeavyAttackCharges() > 0 && enemyPekomon.isAsleep() <= 0)
                {
                    enemyPekomon.heavyAttack(playerPekomon);
                }
                else
                {
                    enemyPekomon.basicAttack(playerPekomon);
                }
            }
            else
            {
                //special attack
                if(enemyPekomon.getCurrentSpecialAttackCharges() > 0 && enemyPekomon.isAsleep() <= 0)
                {
                    enemyPekomon.specialMove(playerPekomon);
                }
                else
                {
                    enemyPekomon.basicAttack(playerPekomon);
                }
            }
        }
        else if(enemyPekomon.getEvo() == 2)
        {
            //70% light attack, 15% heavy attack, 10% special attack, 5% ultimate attack
            if(randNum <= 70)
            {
                //light attack
                enemyPekomon.basicAttack(playerPekomon);
            }
            else if(randNum <= 85)
            {
                //heavy attack
                if(enemyPekomon.getCurrentHeavyAttackCharges() > 0 && enemyPekomon.isAsleep() <= 0)
                {
                    enemyPekomon.heavyAttack(playerPekomon);
                }
                else
                {
                    enemyPekomon.basicAttack(playerPekomon);
                }
            }
            else if(randNum <= 95)
            {
                //special attack
                if(enemyPekomon.getCurrentSpecialAttackCharges() > 0 && enemyPekomon.isAsleep() <= 0)
                {
                    enemyPekomon.specialMove(playerPekomon);
                }
                else
                {
                    enemyPekomon.basicAttack(playerPekomon);
                }
            }
            else
            {
                //ultimate attack
                if(enemyPekomon.getCurrentUltimateAttackCharges() > 0 && enemyPekomon.isAsleep() <= 0)
                {
                    enemyPekomon.ultimateMove(playerPekomon);
                }
                else
                {
                    enemyPekomon.basicAttack(playerPekomon);
                }
            }
        }
    }

    //method for attempting to flee
    public static void flee()
    {
        battleOutput = "You tried to flee...";

        //calculate fleeing
        if((int)(Math.random() * 101) <= (int)(playerPekomon.getSpeed() * 50 / enemyPekomon.getSpeed()))
        {
            didFlee = true;
            isInBattle = false;
            fleeCount++;

            //one xp gain
            //TESTING; usually just +2
            playerPekomon.changeXP(2);
        }
    }

    //method for showing global stats
    public static String displayGlobalStats()
    {
        String str = "";

        //total pekomon used + pekomon used list
        str += "Pekomon Used (" + totalPekomonUsed + "): ";
        for(int i = 0; i < pekomonUsed.size(); i++)
        {
            if(i % 5 == 0)
            {
                str += "\n";
            }
            str += pekomonUsed.get(i) + " ";
        }
        str += "\n";

        //total kills
        str += "Battles Won: " + killCount + "\n";

        //total flees
        str += "Battles Fled: " + fleeCount + "\n";

        //total turns
        str += "Total Turns: " + totalTurns + "\n";

        //total damage dealt
        str += "Damage Dealt: " + totalDamageDealt + "\n";

        //total damage taken
        str += "Damage Taken: " + totalDamageTaken + "\n";

        //total heal potions used
        str += "Total Heal Potions Used: " + totalHealPotionsUsed + "\n";

        //total damage healed
        str += "Total Health Healed: " + totalDamageHealed + "\n";

        //total light attacks used
        str += "Total Light Attacks: " + totalLightAttacksUsed + "\n";
        
        //total heavy attacks used
        str += "Total Heavy Attacks: " + totalHeavyAttacksUsed + "\n";

        //total special attacks used
        str += "Total Special Moves: " + totalSpecialAttacksUsed + "\n";

        //total ultimate attacks used
        str += "Total Ultimate Moves: " + totalUltimateAttacksUsed + "\n";

        return str;
    }
    
    //main method
    public static void main(String[] args) 
    {
        //Run graphics
        SwingUtilities.invokeLater(() -> {
            gameDisplay = new GameDisplay();
            gameDisplay.setVisible(true);
        });

        //pregenerate random events
        generateRandomEvents();
    }
}
