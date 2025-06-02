//Ian Jang
//Last updated 4/11
//File description: Diglett pekomon 

//Evolution: Diglett/Dugtrio

public class Diglett extends Pekomon
{
    //Constructor

    public Diglett()
    {
        super("Diglett", 10, 55, 25, 35, 45, 95, 15, 10, 4, 3, 0, "Ground", 0, "DiglettFront.png", "DiglettBack.png");
    }

    //Abilities

    //Astonish
    public void basicAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Astonish!\n";
        super.basicAttack(enemyPekomon);
        
        //do paralyze
        if((int)(Math.random() * 101) <= 30)
        {
            PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is paralyzed!";
            enemyPekomon.giveParalyze(1);
        }

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Dig
    public void heavyAttack(Pekomon enemyPekomon)
    {
        //change charges
        super.changeCurrentHeavyAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Dig!\nEvasion UP!";

        super.giveEvasion(10);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Tri Attack
    public void specialMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Tri Attack!";

        //do poison
        if((int)(Math.random() * 101) <= 75)
        {
            PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is poisoned!";
            enemyPekomon.givePoison(10);
        }

        //do paralyze
        if((int)(Math.random() * 101) <= 75)
        {
            PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is paralyzed!";
            enemyPekomon.giveParalyze(3);
        }

        //do leech
        if((int)(Math.random() * 101) <= 75)
        {
            PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is leeched!";
            enemyPekomon.giveLeech(3, 0.1);
        }

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    public void ultimateMove(Pekomon enemyPekomon)
    {
        //None
    }

    //Method for evolving Pekomon
    public void evolve()
    {
        super.evolve();

        if(super.getEvo() == 0)
        {
            //evolve to Dugtrio
            super.setName("Dugtrio");
            super.setFrontImage("DugtrioFront.png");
            super.setBackImage("DugtrioBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(25);
            changeAttack(45);
            changeDefense(25);
            changeSAttack(15);
            changeSDefense(25);
            changeSpeed(25);
            changeEvasion(10);
            changeAccuracy(10);

            //stop from evolving again
            super.setMaxXP(9999);
        }
        else
        {
            //do nothing
        }
    }

    //Method that returns this Pekomon's light attack description; overrides parent class
    public String getLightAttackName()
    {
        return "Astonish";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Dig";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Tri Attack";
        }
        return "None";
    }

    //Method that returns this Pekomon's ultimate attack description; overrides parent class
    public String getUltimateAttackName()
    {
        if(super.getEvo() > 1)
        {
            return "";
        }
        return "None";
    }

    //Method that returns this Pekomon's light attack description; overridden completely by polymorphism
    public String getLightAttackDescription()
    {
        return "An attack that may shock the foe into \nflinching";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "Digs underground to sharply gain evasion";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "Fires three kinds of beams; may inflict \nthree different debuffs";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "";
    }
}