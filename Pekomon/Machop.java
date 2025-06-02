//Ian Jang
//Last updated 4/11
//File description: Machop pekomon 

//Evolution: Machop/Machoke/Machamp

public class Machop extends Pekomon
{
    //Constructor

    public Machop()
    {
        super("Machop", 70, 80, 50, 35, 35, 35, 2, 4, 3, 3, 1, "Fighting", 0, "MachopFront.png", "MachopBack.png");
    }

    //Abilities

    //Low Kick
    public void basicAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Low Kick!\n";
        super.basicAttack(enemyPekomon);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Dynamic Punch
    public void heavyAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Dynamic Punch!\n";
        super.heavyAttack(enemyPekomon);

        //do paralyze
        PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is paralyzed!";
        enemyPekomon.giveParalyze(2);
        
        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Stretch special
    public void specialMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Stretch!\n";

        //stat boosts
        super.giveAttack(5);
        super.giveSAtk(5);
        super.giveDef(5);
        super.giveSDef(5);
        super.giveEvasion(5);
        super.giveAccuracy(5);

        if(PekomonRunner.firstStrike)
        {
            //first strike boost
            PekomonRunner.battleOutput += "It was extra effective!\n";

            super.giveAttack(5);
            super.giveSAtk(5);
            super.giveDef(5);
            super.giveSDef(5);
            super.giveEvasion(5);
            super.giveAccuracy(5);
        }

        PekomonRunner.battleOutput += "Atk, Sp. Atk, Def, S. Def, Evasion, Accuracy UP!";

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Giga Impact ult
    public void ultimateMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentUltimateAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Giga Impact!\n";

        int damageToDeal = super.getAttack() * 3; //base damage is attack * 2
        damageToDeal /= enemyPekomon.getDefense(); //damage divided by enemy defense
        
        //check for crit
        boolean isCrit = false;
        if((Math.random() * 81) <= super.getAccuracy())
        {
            isCrit = true;
            damageToDeal *= 1.5;
        }

        //do type calculation
        double effectiveness = typeAdvantage(enemyPekomon);
        damageToDeal = (int)(damageToDeal * effectiveness);
        
        //if crit
        if(isCrit)
        {
            PekomonRunner.battleOutput += "Critical hit!\n";
        }

        //type check
        if(effectiveness == 2)
        {
            PekomonRunner.battleOutput += "It was very effective!\n";
        }
        else if(effectiveness == 0.5)
        {
            PekomonRunner.battleOutput += "It wasn't very effective...\n";
        }

        //check for charge consumption
        int chargesConsumed = super.getCurrentHeavyAttackCharges() + super.getCurrentSpecialAttackCharges();
        damageToDeal *= chargesConsumed;
        PekomonRunner.battleOutput += chargesConsumed + " charges consumed!\n";
        super.changeCurrentHeavyAttackCharges(-999);
        super.changeCurrentSpecialAttackCharges(-999);


        //deal actual damage
        enemyPekomon.changeCurrentHP(damageToDeal * -1);

        //output
        PekomonRunner.battleOutput += enemyPekomon.getName() + " took " + damageToDeal + " damage!\n";

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Method for evolving Pekomon
    public void evolve()
    {
        super.evolve();

        if(super.getEvo() == 0)
        {
            //evolve to Machoke
            super.setName("Machoke");
            super.setFrontImage("MachokeFront.png");
            super.setBackImage("MachokeBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(10);
            changeAttack(20);
            changeDefense(20);
            changeSAttack(15);
            changeSDefense(25);
            changeSpeed(10);
            changeEvasion(1);
            changeAccuracy(2);

            //update next benchmark for level up
            super.setMaxXP(20);
        }
        else if(super.getEvo() == 1)
        {
            //evolve to Machamp
            super.setName("Machamp");
            super.setFrontImage("MachampFront.png");
            super.setBackImage("MachampBack.png");
            super.setEvo(2);

            //update stats
            changeMaxHP(10);
            changeAttack(30);
            changeDefense(10);
            changeSAttack(15);
            changeSDefense(25);
            changeSpeed(10);
            changeEvasion(2);
            changeAccuracy(2);

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
        return "Low Kick";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Dynamic Punch";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Stretch";
        }
        return "None";
    }

    //Method that returns this Pekomon's ultimate attack description; overrides parent class
    public String getUltimateAttackName()
    {
        if(super.getEvo() > 1)
        {
            return "Giga Impact";
        }
        return "None";
    }

    //Method that returns this Pekomon's light attack description; overridden completely by polymorphism
    public String getLightAttackDescription()
    {
        return "A low sweeping kick that deals \nlight damage";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "A powerful and discombobulating \npunch";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "Stretches the body to boost both ATTACK and \nDEFENSE; gives more buff if it is the first move of the battle";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "Charges at the foe using every bit of power; \nconsumes all charges and deals more damage for each charge consumed";
    }
}
