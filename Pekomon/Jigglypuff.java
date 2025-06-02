//Ian Jang
//Last updated 4/11
//File description: Jigglypuff pekomon 

//Evolution: Jigglypuff/Wigglytuff

public class Jigglypuff extends Pekomon
{
    //Constructor

    public Jigglypuff()
    {
        super("Jigglypuff", 115, 45, 20, 45, 25, 20, 0, 0, 5, 2, 0, "Fairy", 0, "JigglypuffFront.png", "JigglypuffBack.png");
    }

    //Abilities

    //Pound
    public void basicAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Pound!\n";
        super.basicAttack(enemyPekomon);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Charm
    public void heavyAttack(Pekomon enemyPekomon)
    {
        //change charges
        super.changeCurrentHeavyAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Charm!\n" + enemyPekomon.getName() + " is weakened!";

        enemyPekomon.giveAttack(-5);
        enemyPekomon.giveSAtk(-5);
        enemyPekomon.giveDef(-5);
        enemyPekomon.giveSDef(-5);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Rest
    public void specialMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Rest!\n";

        super.giveParalyze(2);
        super.giveRegen(3, 0.25);

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
            //evolve to Wigglytuff
            super.setName("Wigglytuff");
            super.setFrontImage("WigglytuffFront.png");
            super.setBackImage("WigglytuffBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(25);
            changeAttack(25);
            changeDefense(25);
            changeSAttack(40);
            changeSDefense(25);
            changeSpeed(25);
            changeEvasion(0);
            changeAccuracy(0);

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
        return "Pound";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Charm";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Rest";
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
        return "Pounds the foe";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "Lowers enemy ATTACK and DEFENSE";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "Paralyzes self for three turns, but gains \nheavy regeneration";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "";
    }
}