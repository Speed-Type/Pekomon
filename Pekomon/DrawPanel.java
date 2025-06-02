//Ian Jang
//Last updated: 4/11
//File description: panel used to draw backgrounds

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DrawPanel extends JPanel
{
    //private variables

    //all image files
    private BufferedImage mainMenuBGImage;
    private BufferedImage intermediateSceneBGImage;
    private BufferedImage battleSceneBGImage;
    private BufferedImage pekomonDisplayBGImage;
    private BufferedImage gameOverBGImage;

    //fonts
    Font titleFont = new Font("Serif", Font.BOLD, 150);
    Font generalFont = new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 24);
    Font emphasizedFont = new Font("Monospaced", Font.BOLD, 32);
    Font battleFont = new Font("SansSerif", Font.LAYOUT_LEFT_TO_RIGHT, 22);
    Font smallDetailFont = new Font("SansSerif", Font.LAYOUT_LEFT_TO_RIGHT, 18);

    //constructor
    public DrawPanel()
    {
        //load all images in

        //load main menu bg image
        try 
        {
            // Load the image
            mainMenuBGImage = ImageIO.read(new File("mainMenuBGImage.jpg"));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        //load intermediate scene bg image
        try 
        {
            // Load the image
            intermediateSceneBGImage = ImageIO.read(new File("intermediateSceneBGImage.jpeg"));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        //load battle scene bg image
        try 
        {
            // Load the image
            battleSceneBGImage = ImageIO.read(new File("battleSceneBGImage.png"));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        //load pekomon display scene bg image
        try 
        {
            // Load the image
            pekomonDisplayBGImage = ImageIO.read(new File("pekomonDisplayBGImage.jpeg"));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        //load game over scene bg image
        try 
        {
            // Load the image
            gameOverBGImage = ImageIO.read(new File("gameOverBGImage.jpg"));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        //report successful
        System.out.println("Loaded all images");
    }



    //main method that controls what is painted on the scene
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        //depending on current graphic, render a certain scene
        if(PekomonRunner.currentDisplay == 0)
        {
            //render main menu
            if(intermediateSceneBGImage != null)
            {
                int imageWidth = mainMenuBGImage.getWidth();
                int imageHeight = mainMenuBGImage.getHeight();

                double aspectRatio = (double) imageWidth / imageHeight;
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Calculate the new width and height to preserve the aspect ratio
                int newWidth = (int) (panelHeight * aspectRatio * 1.35);
                int newHeight = (int)(panelHeight * 1.35);

                // Calculate the x and y position to center the image
                int x = (panelWidth - newWidth) / 2 - 20;
                int y = (panelHeight - newHeight) / 2;

                g.drawImage(mainMenuBGImage, x, y, newWidth, newHeight, this);
            }

            //draw main menu text
            g.setFont(titleFont);
            g.setColor(Color.RED);
            //center it
            int stringLen = (int)g.getFontMetrics().getStringBounds("PEKOMON", g).getWidth();
            int start = getWidth()/2 - stringLen/2;
            g.drawString("PEKOMON", start, 200);
        }
        else if(PekomonRunner.currentDisplay == 1)
        {
            //render Pekomon selection scene
            if(intermediateSceneBGImage != null)
            {
                int imageWidth = intermediateSceneBGImage.getWidth();
                int imageHeight = intermediateSceneBGImage.getHeight();

                double aspectRatio = (double) imageWidth / imageHeight;
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Calculate the new width and height to preserve the aspect ratio
                int newWidth = (int) (panelHeight * aspectRatio);
                int newHeight = panelHeight;

                // Calculate the x and y position to center the image
                int x = (panelWidth - newWidth) / 2;
                int y = (panelHeight - newHeight) / 2;

                g.drawImage(intermediateSceneBGImage, x, y, newWidth, newHeight, this);
            }
        
            g.setFont(emphasizedFont);
            g.setColor(Color.BLACK);
            if(PekomonRunner.pekomonSelected != 0)
            {
                //draw text to notify player choice
                int stringLen = (int)g.getFontMetrics().getStringBounds(PekomonRunner.playerPekomon.getName() + " has been selected!", g).getWidth();
                int start = getWidth()/2 - stringLen/2;
                g.drawString(PekomonRunner.playerPekomon.getName() + " has been selected!", start, 700);
            }
            else
            {
                //draw text to prompt player to pick pekomon
                int stringLen = (int)g.getFontMetrics().getStringBounds(" Select a Pekomon!", g).getWidth();
                int start = getWidth()/2 - stringLen/2;
                g.drawString(" Select a Pekomon!", start, 700);
            }

            //draw pekomon icons
            if(PekomonRunner.pekomonSelected == 0 || PekomonRunner.pekomonSelected == 1)
            {
                g.drawImage(PekomonRunner.randomPekomon1.getFrontImage(), 0, 200, (int)(PekomonRunner.randomPekomon1.getFrontImage().getWidth() * 0.8), (int)(PekomonRunner.randomPekomon1.getFrontImage().getHeight() * 0.8), this);
            }

            if(PekomonRunner.pekomonSelected == 0 || PekomonRunner.pekomonSelected == 2)
            {
                g.drawImage(PekomonRunner.randomPekomon2.getFrontImage(), 300, 200, (int)(PekomonRunner.randomPekomon2.getFrontImage().getWidth() * 0.8), (int)(PekomonRunner.randomPekomon2.getFrontImage().getHeight() * 0.8), this);
            }

            if(PekomonRunner.pekomonSelected == 0 || PekomonRunner.pekomonSelected == 3)
            {
                g.drawImage(PekomonRunner.randomPekomon3.getFrontImage(), 600, 200, (int)(PekomonRunner.randomPekomon3.getFrontImage().getWidth() * 0.8), (int)(PekomonRunner.randomPekomon3.getFrontImage().getHeight() * 0.8), this);
            }
        }
        else if(PekomonRunner.currentDisplay == 2)
        {
            //render battle scene
            if(battleSceneBGImage != null)
            {
                int imageWidth = battleSceneBGImage.getWidth();
                int imageHeight = battleSceneBGImage.getHeight();

                double aspectRatio = (double) imageWidth / imageHeight;
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Calculate the new width and height to preserve the aspect ratio
                int newWidth = (int) (panelHeight * aspectRatio);
                int newHeight = panelHeight;

                // Calculate the x and y position to center the image
                int x = (panelWidth - newWidth) / 2;
                int y = (panelHeight - newHeight) / 2;

                g.drawImage(battleSceneBGImage, x, y, newWidth, newHeight, this);
            }

            //draw player and enemy pekomon
            g.drawImage(PekomonRunner.playerPekomon.getBackImage(), 80, 210, this);
            g.drawImage(PekomonRunner.enemyPekomon.getFrontImage(), 490, 150, (int)(PekomonRunner.enemyPekomon.getFrontImage().getWidth() * 0.7), (int)(PekomonRunner.enemyPekomon.getFrontImage().getHeight() * 0.7), this);

            //draw health bars
            g.setFont(emphasizedFont);
            g.setColor(Color.BLACK);
            g.drawString("HP: " + PekomonRunner.playerPekomon.getCurrentHP() + "/" + PekomonRunner.playerPekomon.getMaxHP(),175, 220);
            g.drawString("HP: " + PekomonRunner.enemyPekomon.getCurrentHP() + "/" + PekomonRunner.enemyPekomon.getMaxHP(),560, 130);

            //draw attack output
            g.setFont(battleFont);
            g.setColor(Color.BLACK);
            drawMultilineString(g, PekomonRunner.battleOutput, 100, 620);

            //draw turn indicator
            g.setFont(smallDetailFont);
            if(PekomonRunner.isPlayerTurn)
            {
                g.drawString("Player's turn", 650, 580);
            }
            else
            {
                g.drawString("Opponent's turn", 650, 580);
            }
        }
        else if(PekomonRunner.currentDisplay == 3)
        {
            //render next random event
            if(pekomonDisplayBGImage != null)
            {
                int imageWidth = pekomonDisplayBGImage.getWidth();
                int imageHeight = pekomonDisplayBGImage.getHeight();

                double aspectRatio = (double) imageWidth / imageHeight;
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Calculate the new width and height to preserve the aspect ratio
                int newWidth = (int) (panelHeight * aspectRatio);
                int newHeight = panelHeight;

                // Calculate the x and y position to center the image
                int x = (panelWidth - newWidth) / 2;
                int y = (panelHeight - newHeight) / 2;

                g.drawImage(pekomonDisplayBGImage, x, y, newWidth, newHeight, this);
            }

            //render text
            g.setColor(Color.WHITE);

            if(PekomonRunner.hasChosenEvent == false || PekomonRunner.eventChoice == 1)
            {
                g.setFont(emphasizedFont);
                g.drawString(PekomonRunner.randomEventsList[PekomonRunner.event1], 20, 100);
                g.setFont(generalFont);
                g.drawString(PekomonRunner.randomEventsDescriptions[PekomonRunner.event1], 20, 150);
            }

            if(PekomonRunner.hasChosenEvent == false || PekomonRunner.eventChoice == 2)
            {
                g.setFont(emphasizedFont);
                g.drawString(PekomonRunner.randomEventsList[PekomonRunner.event2], 20, 300);
                g.setFont(generalFont);
                g.drawString(PekomonRunner.randomEventsDescriptions[PekomonRunner.event2], 20, 350);
            }

            if(PekomonRunner.hasChosenEvent == false || PekomonRunner.eventChoice == 3)
            {
                g.setFont(emphasizedFont);
                g.drawString(PekomonRunner.randomEventsList[PekomonRunner.event3], 20, 500);
                g.setFont(generalFont);
                g.drawString(PekomonRunner.randomEventsDescriptions[PekomonRunner.event3], 20, 550);
            }
            
        }
        else if(PekomonRunner.currentDisplay == 4)
        {
            //render current Pekomon view
            if(pekomonDisplayBGImage != null)
            {
                int imageWidth = pekomonDisplayBGImage.getWidth();
                int imageHeight = pekomonDisplayBGImage.getHeight();

                double aspectRatio = (double) imageWidth / imageHeight;
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Calculate the new width and height to preserve the aspect ratio
                int newWidth = (int) (panelHeight * aspectRatio);
                int newHeight = panelHeight;

                // Calculate the x and y position to center the image
                int x = (panelWidth - newWidth) / 2;
                int y = (panelHeight - newHeight) / 2;

                g.drawImage(pekomonDisplayBGImage, x, y, newWidth, newHeight, this);
            }

            //draw stats
            g.setFont(generalFont);
            g.setColor(Color.WHITE);
            drawMultilineString(g, PekomonRunner.playerPekomon.displayStats(), 550, 30);

            //draw pekomon icon
            g.drawImage(PekomonRunner.playerPekomon.getFrontImage(), 0, 0, (int)(PekomonRunner.playerPekomon.getFrontImage().getWidth() * 1.4), (int)(PekomonRunner.playerPekomon.getFrontImage().getHeight() * 1.4), this);

            //draw moveset
            String movesetString = "Light Attack: " + PekomonRunner.playerPekomon.getLightAttackName() + " - " + PekomonRunner.playerPekomon.getLightAttackDescription() + "\n";
            movesetString += "Heavy Attack: " + PekomonRunner.playerPekomon.getHeavyAttackName() + " - " + PekomonRunner.playerPekomon.getHeavyAttackDescription() + "\n";
            if(PekomonRunner.playerPekomon.getEvo() > 0)
            {
                movesetString += "Special: " + PekomonRunner.playerPekomon.getSpecialAttackName() + " - " + PekomonRunner.playerPekomon.getSpecialAttackDescription() + "\n";
            }
            if(PekomonRunner.playerPekomon.getEvo() > 1)
            {
                movesetString += "Ultimate: " + PekomonRunner.playerPekomon.getUltimateAttackName() + " - " + PekomonRunner.playerPekomon.getUltimateAttackDescription() + "\n";            
            }

            drawMultilineString(g, movesetString, 20, 540);
        }
        else if(PekomonRunner.currentDisplay == 5)
        {
            //draw game over scene

            //draw background
            if(gameOverBGImage != null)
            {
                int imageWidth = gameOverBGImage.getWidth();
                int imageHeight = gameOverBGImage.getHeight();

                double aspectRatio = (double) imageWidth / imageHeight;
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Calculate the new width and height to preserve the aspect ratio
                int newWidth = (int) (panelHeight * aspectRatio);
                int newHeight = panelHeight;

                // Calculate the x and y position to center the image
                int x = (panelWidth - newWidth) / 2;
                int y = (panelHeight - newHeight) / 2;

                g.drawImage(gameOverBGImage, x, y, newWidth, newHeight, this);

                //generate stats and print
                g.setFont(emphasizedFont);
                g.setColor(Color.RED);
                drawMultilineString(g, PekomonRunner.displayGlobalStats(), 20, 20);
            }
        }
        else if(PekomonRunner.currentDisplay == 6)
        {
            //show stats

            //display background image
            if(pekomonDisplayBGImage != null)
            {
                int imageWidth = pekomonDisplayBGImage.getWidth();
                int imageHeight = pekomonDisplayBGImage.getHeight();

                double aspectRatio = (double) imageWidth / imageHeight;
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Calculate the new width and height to preserve the aspect ratio
                int newWidth = (int) (panelHeight * aspectRatio);
                int newHeight = panelHeight;

                // Calculate the x and y position to center the image
                int x = (panelWidth - newWidth) / 2;
                int y = (panelHeight - newHeight) / 2;

                g.drawImage(pekomonDisplayBGImage, x, y, newWidth, newHeight, this);
            }

            //generate stats and print
            g.setFont(generalFont);
            g.setColor(Color.WHITE);
            drawMultilineString(g, PekomonRunner.displayGlobalStats(), 50, 50);
        }
    }

    //Method that allows for drawing of multiline strings
    public static void drawMultilineString(Graphics g, String text, int x, int y) 
    {
        //use a regex to find all new lines, then draw up until that spot and then add some space
        for (String line : text.split("\n"))
        {
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
        }
    }
}
