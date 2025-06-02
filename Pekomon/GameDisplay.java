//Ian Jang
//Last updated: 4/11
//Code description: The graphics controller for the game

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameDisplay extends JFrame
{
    //private variables
    private DrawPanel drawPanel; //panel used for drawing

    //buttons (accessed in multiple functions)

    //Footer buttons Panel
    JPanel footerButtons = new JPanel(new FlowLayout());

    //header buttons panel
    JPanel headerButtons = new JPanel(new FlowLayout());

    //Button to start playing (main menu)
    JButton goToPekomonSelectButton = new JButton("Play");

    //button to continue to next battle (in pekomon choosing scene and random event scene)
    JButton battleButton = new JButton("Battle!");

    //button to check current Pekomon (in random event scene)
    JButton checkCurrentPekomonButton = new JButton("Your Pekomon");

    //button to return from pekomon display to random event (in pekomon display scene)
    JButton toRandomEventButton = new JButton("Continue");

    //button to check current stats
    JButton checkStatsButton = new JButton("Stats");

    //Button to select Pekomon 1, 2, 3
    JButton pekomon1Button = new JButton("Pekomon 1");
    JButton pekomon2Button = new JButton("Pekomon 2");
    JButton pekomon3Button = new JButton("Pekomon 3");

    //BATTLE BUTTONS

    //light attack button
    JButton lightAttackButton = new JButton("Light Attack");

    //heavy attack button
    JButton heavyAttackButton = new JButton("Heavy Attack (0/0)");

    //special attack button
    JButton specialAttackButton = new JButton("Special Move (0/0)");

    //ultimate attack button
    JButton ultimateAttackButton = new JButton("Ultimate Move (0/0)");

    //heal button
    JButton healButton = new JButton("Heal Potion (num)");

    //flee button
    JButton fleeButton = new JButton("Flee");

    //game over button
    JButton gameOverButton = new JButton("Game Over");

    //constructor
    public GameDisplay() 
    {
        //Window settings
        setTitle("Pekomon");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //create panel
        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);
        drawPanel.setBackground(Color.BLACK);
        drawPanel.setVisible(true);

        //update style of buttons
        styleButton(goToPekomonSelectButton);
        styleButton(battleButton);
        styleButton(checkCurrentPekomonButton);
        styleButton(toRandomEventButton);
        styleButton(lightAttackButton);
        styleButton(heavyAttackButton);
        styleButton(specialAttackButton);
        styleButton(ultimateAttackButton);
        styleButton(healButton);
        styleButton(fleeButton);
        styleButton(gameOverButton);
        styleButton(checkStatsButton);

        //update style of footer button panel
        footerButtons.setBackground(Color.BLACK);

        //add footer buttons to main panel
        footerButtons.add(goToPekomonSelectButton);
        footerButtons.add(battleButton);
        footerButtons.add(checkCurrentPekomonButton);
        footerButtons.add(toRandomEventButton);
        footerButtons.add(lightAttackButton);
        footerButtons.add(heavyAttackButton);
        footerButtons.add(specialAttackButton);
        footerButtons.add(ultimateAttackButton);
        footerButtons.add(healButton);
        footerButtons.add(fleeButton);
        footerButtons.add(gameOverButton);
        footerButtons.add(checkStatsButton);

        add(footerButtons, BorderLayout.SOUTH);

        //show/hide buttons
        footerButtons.setVisible(true);
        goToPekomonSelectButton.setVisible(true);
        battleButton.setVisible(false);
        checkCurrentPekomonButton.setVisible(false);
        toRandomEventButton.setVisible(false);
        lightAttackButton.setVisible(false);
        heavyAttackButton.setVisible(false);
        specialAttackButton.setVisible(false);
        ultimateAttackButton.setVisible(false);
        healButton.setVisible(false);
        fleeButton.setVisible(false);
        gameOverButton.setVisible(false);
        checkStatsButton.setVisible(false);



        //PANEL FOR header buttons (flow organization; top)
        headerButtons.setBackground(Color.BLACK);

        //Button to select events 1, 2, 3
        JButton event1Button = new JButton("Event 1");
        JButton event2Button = new JButton("Event 2");
        JButton event3Button = new JButton("Event 3");

        //style buttons
        styleButton(pekomon1Button);
        styleButton(pekomon2Button);
        styleButton(pekomon3Button);
        styleButton(event1Button);
        styleButton(event2Button);
        styleButton(event3Button);

        //add buttons
        headerButtons.add(pekomon1Button);
        headerButtons.add(pekomon2Button);
        headerButtons.add(pekomon3Button);
        headerButtons.add(event1Button);
        headerButtons.add(event2Button);
        headerButtons.add(event3Button);
        add(headerButtons, BorderLayout.NORTH);

        //hide things
        headerButtons.setVisible(false);
        event1Button.setVisible(false);
        event2Button.setVisible(false);
        event3Button.setVisible(false);



        //Buttons
        
        //button to begin game
        goToPekomonSelectButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                toNewPekomonScene();
            }
        });

        //button to select Pekomon 1
        pekomon1Button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //add Pekomon 1
                PekomonRunner.selectNewPekomon(1);

                //hide all buttons, show continue button
                pekomon1Button.setVisible(false);
                pekomon2Button.setVisible(false);
                pekomon3Button.setVisible(false);
                toRandomEventButton.setVisible(true);

                drawPanel.revalidate();
                drawPanel.repaint();
            }
        });

        //button to select Pekomon 2
        pekomon2Button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //add Pekomon 2
                PekomonRunner.selectNewPekomon(2);

                //hide all buttons, show continue button
                pekomon1Button.setVisible(false);
                pekomon2Button.setVisible(false);
                pekomon3Button.setVisible(false);
                toRandomEventButton.setVisible(true);

                drawPanel.revalidate();
                drawPanel.repaint();
            }
        });

        //button to select Pekomon 3
        pekomon3Button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //add Pekomon 3
                PekomonRunner.selectNewPekomon(3);

                //hide all buttons, show continue button
                pekomon1Button.setVisible(false);
                pekomon2Button.setVisible(false);
                pekomon3Button.setVisible(false);
                toRandomEventButton.setVisible(true);

                drawPanel.revalidate();
                drawPanel.repaint();
            }
        });

        //button to check current Pekomon
        checkCurrentPekomonButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //go to Pekomon view scene
                PekomonRunner.currentDisplay = 4;

                //close out all other panels
                battleButton.setVisible(false);
                checkCurrentPekomonButton.setVisible(false);
                checkStatsButton.setVisible(false);
                headerButtons.setVisible(false);

                //show these
                toRandomEventButton.setVisible(true);

                drawPanel.revalidate();
                drawPanel.repaint();
            }
        });

        //button to check stats
        checkStatsButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //go to Pekomon view scene
                PekomonRunner.currentDisplay = 6;

                //close out all other panels
                battleButton.setVisible(false);
                checkCurrentPekomonButton.setVisible(false);
                checkStatsButton.setVisible(false);
                headerButtons.setVisible(false);

                //show these
                toRandomEventButton.setVisible(true);

                drawPanel.revalidate();
                drawPanel.repaint();
            }
        });

        //button to go to random event
        toRandomEventButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //go to next random event scene
                PekomonRunner.currentDisplay = 3;

                //hide other buttons
                toRandomEventButton.setVisible(false);

                //show these
                battleButton.setVisible(true);
                checkCurrentPekomonButton.setVisible(true);
                checkStatsButton.setVisible(true);
                headerButtons.setVisible(true);

                if(PekomonRunner.hasChosenEvent == false)
                {
                    event1Button.setVisible(true);
                    event2Button.setVisible(true);
                    event3Button.setVisible(true);

                    //change texts
                    event1Button.setText(PekomonRunner.randomEventsList[PekomonRunner.event1]);
                    event2Button.setText(PekomonRunner.randomEventsList[PekomonRunner.event2]);
                    event3Button.setText(PekomonRunner.randomEventsList[PekomonRunner.event3]);
                }

                drawPanel.revalidate();
                drawPanel.repaint();
            }
        });

        //button to select event 1
        event1Button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //do functionality
                PekomonRunner.eventChoice = 1;
                PekomonRunner.runEvent();

                //hide all choice buttons
                event1Button.setVisible(false);
                event2Button.setVisible(false);
                event3Button.setVisible(false);

                drawPanel.revalidate();
                drawPanel.repaint();
            }
        });

        //button to select event 2
        event2Button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //do functionality
                PekomonRunner.eventChoice = 2;
                PekomonRunner.runEvent();

                //hide all choice buttons
                event1Button.setVisible(false);
                event2Button.setVisible(false);
                event3Button.setVisible(false);

                drawPanel.revalidate();
                drawPanel.repaint();
            }
        });

        //button to select event 3
        event3Button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //do functionality
                PekomonRunner.eventChoice = 3;
                PekomonRunner.runEvent();

                //hide all choice buttons
                event1Button.setVisible(false);
                event2Button.setVisible(false);
                event3Button.setVisible(false);

                drawPanel.revalidate();
                drawPanel.repaint();
            }
        });

        //button to go to battle
        battleButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //change to battle scene
                PekomonRunner.currentDisplay = 2;

                //hide other buttons
                battleButton.setVisible(false);
                checkCurrentPekomonButton.setVisible(false);
                checkStatsButton.setVisible(false);
                headerButtons.setVisible(false);

                //do actual battle functioniality
                PekomonRunner.battle(PekomonRunner.playerPekomon.getEvo());

                //show the battle buttons
                toggleBattleButtons(true);

                drawPanel.revalidate();
                drawPanel.repaint();
            }
        });

        //button to do light attack
        lightAttackButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //do light attack functionality
                if(PekomonRunner.isPlayerTurn)
                {
                    //check paralyzed
                    if(PekomonRunner.playerPekomon.isParalyzed() > 0)
                    {
                        //paralyzed
                        PekomonRunner.playerPekomon.paralyze();
                    }
                    else
                    {
                        PekomonRunner.playerPekomon.basicAttack(PekomonRunner.enemyPekomon);
                    }

                    //update stats
                    PekomonRunner.totalLightAttacksUsed++;

                    doTurn();
                }
            }
        });

        //button to do heavy attack
        heavyAttackButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //do heavy attack functionality
                if(PekomonRunner.playerPekomon.getCurrentHeavyAttackCharges() > 0 && PekomonRunner.isPlayerTurn)
                {
                    //check paralyzed
                    if(PekomonRunner.playerPekomon.isParalyzed() > 0)
                    {
                        //paralyzed
                        PekomonRunner.playerPekomon.paralyze();

                        doTurn();
                    }
                    else if(PekomonRunner.playerPekomon.isAsleep() > 0)
                    {
                        //asleep
                        PekomonRunner.battleOutput = PekomonRunner.playerPekomon.getName() + " is asleep! Only basic attack usable.";
                    }
                    else
                    {
                        PekomonRunner.playerPekomon.heavyAttack(PekomonRunner.enemyPekomon);

                        //update stats
                        PekomonRunner.totalHeavyAttacksUsed++;

                        doTurn();
                    }
                }
            }
        });

        //button to do special attack
        specialAttackButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //do special attack functionality
                if(PekomonRunner.playerPekomon.getCurrentSpecialAttackCharges() > 0 && PekomonRunner.isPlayerTurn)
                {
                    //check paralyzed
                    if(PekomonRunner.playerPekomon.isParalyzed() > 0)
                    {
                        //paralyzed
                        PekomonRunner.playerPekomon.paralyze();

                        doTurn();
                    }
                    else if(PekomonRunner.playerPekomon.isAsleep() > 0)
                    {
                        //asleep
                        PekomonRunner.battleOutput = PekomonRunner.playerPekomon.getName() + " is asleep! Only basic attack usable.";
                    }
                    else
                    {
                        PekomonRunner.playerPekomon.specialMove(PekomonRunner.enemyPekomon);

                        //update stats
                        PekomonRunner.totalSpecialAttacksUsed++;
                        
                        doTurn();
                    }
                }
            }
        });

        //button to do ultimate attack
        ultimateAttackButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //do ultimate attack functionality
                if(PekomonRunner.playerPekomon.getCurrentUltimateAttackCharges() > 0 && PekomonRunner.isPlayerTurn)
                {
                    //check paralyzed
                    if(PekomonRunner.playerPekomon.isParalyzed() > 0)
                    {
                        //paralyzed
                        PekomonRunner.playerPekomon.paralyze();

                        doTurn();
                    }
                    else if(PekomonRunner.playerPekomon.isAsleep() > 0)
                    {
                        //asleep
                        PekomonRunner.battleOutput = PekomonRunner.playerPekomon.getName() + " is asleep! Only basic attack usable.";
                    }
                    else
                    {
                        PekomonRunner.playerPekomon.ultimateMove(PekomonRunner.enemyPekomon);

                        //update stats
                        PekomonRunner.totalUltimateAttacksUsed++;
                    
                        doTurn();
                    }
                }
            }
        });

        //button to heal
        healButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //do ultimate attack functionality
                if(PekomonRunner.numPotions > 0 && PekomonRunner.isPlayerTurn)
                {
                    //check paralyzed
                    if(PekomonRunner.playerPekomon.isParalyzed() > 0)
                    {
                        //paralyzed
                        PekomonRunner.playerPekomon.paralyze();

                        doTurn();
                    }
                    else if(PekomonRunner.playerPekomon.isAsleep() > 0)
                    {
                        //asleep
                        PekomonRunner.battleOutput = PekomonRunner.playerPekomon.getName() + " is asleep! Only basic attack usable.";
                    }
                    else
                    {
                        PekomonRunner.playerPekomon.heal();

                        //update stat
                        PekomonRunner.totalHealPotionsUsed++;
                    
                        doTurn();
                    }
                }
            }
        });

        //button to flee
        fleeButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //do fleeing functionality
                if(PekomonRunner.isPlayerTurn)
                {
                    //check paralyzed
                    if(PekomonRunner.playerPekomon.isParalyzed() > 0)
                    {
                        //paralyzed
                        PekomonRunner.playerPekomon.paralyze();

                        doTurn();
                    }
                    else if(PekomonRunner.playerPekomon.isAsleep() > 0)
                    {
                        //asleep
                        PekomonRunner.battleOutput = PekomonRunner.playerPekomon.getName() + " is asleep! Only basic attack usable.";
                    }
                    else
                    {
                        //flee

                        PekomonRunner.flee();

                        doTurn();
                    }
                }
            }
        });

        //button for game over
        gameOverButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //change scene
                PekomonRunner.currentDisplay = 5;

                //hide game over button
                gameOverButton.setVisible(false);

                drawPanel.revalidate();
                drawPanel.repaint();
            }
        });

        //try to update graphic for the first time
        drawPanel.revalidate();
        drawPanel.repaint();
    }

    //method for checking turns in battle
    private void doTurn()
    {
        //stop player actions
        PekomonRunner.isPlayerTurn = false;

        //update stat
        PekomonRunner.totalTurns++;

        try {         
            //show player action
            drawPanel.revalidate();
            drawPanel.paintImmediately(drawPanel.getBounds());

            //pause
            Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);



            //do any status effects for player pekomon

            //sleep
            if(PekomonRunner.playerPekomon.isAsleep() > 0)
            {
                //do actual functionality
                PekomonRunner.playerPekomon.sleep();

                //show changes
                drawPanel.revalidate();
                drawPanel.paintImmediately(drawPanel.getBounds());

                //pause
                Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);
            }

            //leech
            if(PekomonRunner.playerPekomon.isLeeched() > 0)
            {                
                //do actual functionality
                PekomonRunner.playerPekomon.leech(PekomonRunner.enemyPekomon);

                //show changes
                drawPanel.revalidate();
                drawPanel.paintImmediately(drawPanel.getBounds());

                //pause
                Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);
            }
            
            //poison
            if(PekomonRunner.playerPekomon.isPoisoned() > 0)
            {
                //do actual functionality
                PekomonRunner.playerPekomon.poison();

                //show changes
                drawPanel.revalidate();
                drawPanel.paintImmediately(drawPanel.getBounds());

                //pause
                Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);
            }

            //regen
            if(PekomonRunner.playerPekomon.isRegenerating() > 0)
            {
                //do actual functionality
                PekomonRunner.playerPekomon.regen();

                //show changes
                drawPanel.revalidate();
                drawPanel.paintImmediately(drawPanel.getBounds());

                //pause
                Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);
            }

            //burn
            if(PekomonRunner.playerPekomon.isBurned() > 0)
            {
                //do actual functionality
                PekomonRunner.playerPekomon.burn();

                //show changes
                drawPanel.revalidate();
                drawPanel.paintImmediately(drawPanel.getBounds());

                //pause
                Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);
            }

            //fly
            if(PekomonRunner.playerPekomon.isFlying() > 0)
            {
                //do actual functionality
                PekomonRunner.playerPekomon.fly();

                //show changes
                drawPanel.revalidate();
                drawPanel.paintImmediately(drawPanel.getBounds());

                //pause
                Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);
            }



            //check if player has won
            PekomonRunner.checkBattleOver();

            if(PekomonRunner.gameOver)
            {
                //game over

                //change text
                PekomonRunner.battleOutput = PekomonRunner.playerPekomon.getName() + " fainted!\nYou lost...";

                //update stats
                PekomonRunner.pekomonUsed.add(PekomonRunner.playerPekomon.getName());
                
                //update buttons
                gameOverButton.setVisible(true);
                toggleBattleButtons(false);
                
                drawPanel.revalidate();
                drawPanel.paintImmediately(drawPanel.getBounds());
            }
            else if(PekomonRunner.isInBattle == false)
            {
                //has won

                //change text of battle
                PekomonRunner.battleOutput = PekomonRunner.enemyPekomon.getName() + " fainted!\nYou won!";

                //check if fled
                if(PekomonRunner.didFlee)
                {
                    PekomonRunner.battleOutput = "You fled!\n" + PekomonRunner.enemyPekomon.getName() + " taunts you!";

                    //change two rewards to nothing
                    PekomonRunner.event2 = 0;
                    PekomonRunner.event3 = 0;
                }
                        
                //update buttons
                toRandomEventButton.setVisible(true);
                toggleBattleButtons(false);

                //reset status effects
                PekomonRunner.playerPekomon.resetStatusEffects();

                drawPanel.revalidate();
                drawPanel.paintImmediately(drawPanel.getBounds());
            }
            else
            {
                //Continue

                //check paralyzed
                if(PekomonRunner.enemyPekomon.isParalyzed() > 0)
                {
                    //paralyzed
                    PekomonRunner.enemyPekomon.paralyze();
                }
                else
                {
                    //keep going
                    PekomonRunner.enemyTurn();
                }
                
                drawPanel.revalidate();
                drawPanel.paintImmediately(drawPanel.getBounds());

                //do any status effects for enemy pekomon

                //sleep
                if(PekomonRunner.enemyPekomon.isAsleep() > 0)
                {
                    //pause
                    Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);

                    //do actual functionality
                    PekomonRunner.enemyPekomon.sleep();

                    //show changes
                    drawPanel.revalidate();
                    drawPanel.paintImmediately(drawPanel.getBounds());
                }
                
                //leech
                if(PekomonRunner.enemyPekomon.isLeeched() > 0)
                {
                    //pause
                    Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);

                    //do actual functionality
                    PekomonRunner.enemyPekomon.leech(PekomonRunner.playerPekomon);

                    //show changes
                    drawPanel.revalidate();
                    drawPanel.paintImmediately(drawPanel.getBounds());
                }

                //poison
                if(PekomonRunner.enemyPekomon.isPoisoned() > 0)
                {
                    //pause
                    Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);

                    //do actual functionality
                    PekomonRunner.enemyPekomon.poison();

                    //show changes
                    drawPanel.revalidate();
                    drawPanel.paintImmediately(drawPanel.getBounds());
                }

                //regen
                if(PekomonRunner.enemyPekomon.isRegenerating() > 0)
                {
                    //pause
                    Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);

                    //do actual functionality
                    PekomonRunner.enemyPekomon.regen();

                    //show changes
                    drawPanel.revalidate();
                    drawPanel.paintImmediately(drawPanel.getBounds());
                }

                //burn
                if(PekomonRunner.enemyPekomon.isBurned() > 0)
                {
                    //pause
                    Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);

                    //do actual functionality
                    PekomonRunner.enemyPekomon.burn();

                    //show changes
                    drawPanel.revalidate();
                    drawPanel.paintImmediately(drawPanel.getBounds());
                }

                //fly
                if(PekomonRunner.enemyPekomon.isFlying() > 0)
                {
                    //pause
                    Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);

                    //do actual functionality
                    PekomonRunner.enemyPekomon.fly();

                    //show changes
                    drawPanel.revalidate();
                    drawPanel.paintImmediately(drawPanel.getBounds());
                }



                //check if player has lost
                PekomonRunner.checkBattleOver();
                if(PekomonRunner.gameOver)
                {
                    //game over
                    Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);

                    //change text
                    PekomonRunner.battleOutput = PekomonRunner.playerPekomon.getName() + " fainted!\nYou lost...";

                    //update stats
                    PekomonRunner.pekomonUsed.add(PekomonRunner.playerPekomon.getName());
                    
                    //update buttons
                    gameOverButton.setVisible(true);
                    toggleBattleButtons(false);
                    
                    drawPanel.revalidate();
                    drawPanel.paintImmediately(drawPanel.getBounds());
                }
                else if(PekomonRunner.isInBattle == false)
                {
                    //has won
                    Thread.sleep(PekomonRunner.timeBetweenTurns * 1000);

                    //change text of battle
                    PekomonRunner.battleOutput = PekomonRunner.enemyPekomon.getName() + " fainted!\nYou won!";
                            
                    //update buttons
                    toRandomEventButton.setVisible(true);
                    toggleBattleButtons(false);

                    //reset status effects
                    PekomonRunner.playerPekomon.resetStatusEffects();

                    drawPanel.revalidate();
                    drawPanel.paintImmediately(drawPanel.getBounds());
                }
                else
                {
                    //still going
                    PekomonRunner.isPlayerTurn = true;
                    
                    //show battle buttons
                    toggleBattleButtons(true);
                }
            }
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }

    //method for toggling the battle buttons on/off
    private void toggleBattleButtons(boolean bool)
    {
        //show/hide buttons
        lightAttackButton.setVisible(bool);
        heavyAttackButton.setVisible(bool);
        specialAttackButton.setVisible(false);
        ultimateAttackButton.setVisible(false);
        healButton.setVisible(bool);
        fleeButton.setVisible(bool);

        if(bool)
        {
            if(PekomonRunner.playerPekomon.getEvo() > 0)
            {
                specialAttackButton.setVisible(true);
            }

            if(PekomonRunner.playerPekomon.getEvo() > 1)
            {
                ultimateAttackButton.setVisible(true);
            }
        }

        //update text of buttons
        lightAttackButton.setText(PekomonRunner.playerPekomon.getLightAttackName());

        heavyAttackButton.setText(PekomonRunner.playerPekomon.getHeavyAttackName() + " (" + PekomonRunner.playerPekomon.getCurrentHeavyAttackCharges() + "/" + PekomonRunner.playerPekomon.getMaxHeavyAttackCharges() + ")");

        if(PekomonRunner.playerPekomon.getEvo() > 0)
        {
            specialAttackButton.setText(PekomonRunner.playerPekomon.getSpecialAttackName() + " (" + PekomonRunner.playerPekomon.getCurrentSpecialAttackCharges() + "/" + PekomonRunner.playerPekomon.getMaxSpecialAttackCharges() + ")");
        }

        if(PekomonRunner.playerPekomon.getEvo() > 1)
        {
            ultimateAttackButton.setText(PekomonRunner.playerPekomon.getUltimateAttackName() + " (" + PekomonRunner.playerPekomon.getCurrentUltimateAttackCharges() + "/" + PekomonRunner.playerPekomon.getMaxUltimateAttackCharges() + ")");
        }

        healButton.setText("Heal Potion (" + PekomonRunner.numPotions + ")");

        fleeButton.setText("Flee (" + (int)(PekomonRunner.playerPekomon.getSpeed() * 50 / PekomonRunner.enemyPekomon.getSpeed()) + "%)");

        drawPanel.revalidate();
        drawPanel.repaint();
    }

    //method for setting style of a button
    public void styleButton(JButton butt)
    {
        butt.setFont(new Font("SansSerif", Font.BOLD, 17));
        butt.setBackground(Color.DARK_GRAY); //light blue
        butt.setForeground(Color.WHITE);
        butt.setFocusPainted(false);
        
        butt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        butt.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3, true));

        butt.setFocusPainted(false);
        butt.setContentAreaFilled(false);
        butt.setOpaque(true);
    }

    //method for setting to choose pekomon
    public void toNewPekomonScene()
    {
        //go to Pekomon selection scene
        PekomonRunner.currentDisplay = 1;

        //run actual logic
        if(PekomonRunner.playerPekomon == null || PekomonRunner.playerPekomon.getEvo() == 0)
        {
            PekomonRunner.obtainNewPekomon(0);
        }
        else
        {
            PekomonRunner.obtainNewPekomon(PekomonRunner.playerPekomon.getEvo() - 1);
        }

        //close out all other panels
        goToPekomonSelectButton.setVisible(false);
        battleButton.setVisible(false);
        checkCurrentPekomonButton.setVisible(false);
        checkStatsButton.setVisible(false);

        //open pekomon selection
        headerButtons.setVisible(true);
        pekomon1Button.setVisible(true);
        pekomon2Button.setVisible(true);
        pekomon3Button.setVisible(true);

        //change button texts of pekomon selection buttons
        pekomon1Button.setText(PekomonRunner.randomPekomon1.getName());
        pekomon2Button.setText(PekomonRunner.randomPekomon2.getName());
        pekomon3Button.setText(PekomonRunner.randomPekomon3.getName());

        drawPanel.revalidate();
        drawPanel.repaint();
    }
}
