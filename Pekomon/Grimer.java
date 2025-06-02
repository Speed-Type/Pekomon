//Ian Jang
//Last updated 4/11
//File description: Grimer pekomon 

//Evolution: Grimer/Muk

public class Grimer extends Pekomon
{
    //Constructor

    public Grimer()
    {
        super("Grimer", 80, 80, 50, 40, 50, 25, 3, 1, 5, 3, 0, "Poison", 0, "GrimerFront.png", "GrimerBack.png");
    }

    //Abilities

    //Poison Gas
    public void basicAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Poison Gas!\n" + enemyPekomon.getName() + " is poisoned!";
        
        //give poison
        enemyPekomon.givePoison(3);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Sludge Bomb
    public void heavyAttack(Pekomon enemyPekomon)
    {
        //change charges
        super.changeCurrentHeavyAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Sludge Bomb!\n";

        super.heavyAttack(enemyPekomon);

        //do poison
        if((int)(Math.random() * 101) <= 30)
        {
            PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is poisoned!";
            enemyPekomon.givePoison(7);
        }

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Belch
    public void specialMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Belch!\n" + enemyPekomon.getName() + "'s poison is doubled!";

        enemyPekomon.givePoison(enemyPekomon.isPoisoned());

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
            //evolve to Muk
            super.setName("Muk");
            super.setFrontImage("MukFront.png");
            super.setBackImage("MukBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(25);
            changeAttack(25);
            changeDefense(25);
            changeSAttack(25);
            changeSDefense(50);
            changeSpeed(25);
            changeEvasion(3);
            changeAccuracy(1);

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
        return "Poison Gas";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Sludge Bomb";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Belch";
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
        return "Envelops the foe in a toxic gas \nthat poisons";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "Sludge is hurled to inflict damage; \nmay also poison";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "The user lets out a damaging belch \nat the target";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "";
    }
}