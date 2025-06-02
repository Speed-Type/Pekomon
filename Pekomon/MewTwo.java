//Ian Jang
//Last updated 4/11
//File description: MewTwo pekomon 

//Evolution: MewTwo (no evolution)

public class MewTwo extends Pekomon
{
    //private variables
    int adaptLevel = 0;

    //Constructor

    public MewTwo()
    {
        super("MewTwo", 106, 110, 90, 154, 90, 130, 12, 12, 10, 2, 1, "Psychic", 2, "MewTwoFront.png", "MewTwoBack.png");
        
        //stop from having evolutions
        super.setMaxXP(9999);
    }

    //Abilities

    //Adapt
    public void basicAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Adapt!\n";
        
        //damge calculation
        int damageToDeal = super.getAttack() * 3; //base damage is attack * 3
        damageToDeal /= enemyPekomon.getDefense(); //damage divided by enemy defense

        //add damage for adapt level
        damageToDeal += adaptLevel;

        //check for crit
        boolean isCrit = false;
        if((Math.random() * 101) <= super.getAccuracy())
        {
            isCrit = true;
            damageToDeal *= 2;
        }

        //check for dodge
        boolean isDodge = false;
        if((Math.random() * 101) <= enemyPekomon.getEvasion())
        {
            isDodge = true;
        }

        //do type calculation
        double effectiveness = typeAdvantage(enemyPekomon);
        damageToDeal = (int)(damageToDeal * effectiveness);
        
        //print out the results
        if((isCrit || !isDodge) && effectiveness != 0)
        {
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

            //deal actual damage
            enemyPekomon.changeCurrentHP(damageToDeal * -1);

            //update output text
            PekomonRunner.battleOutput += enemyPekomon.getName() + " took " + damageToDeal + " damage!";
        }
        else
        {
            //dodged
            PekomonRunner.battleOutput += "It missed!";
        }

        //update adapt level
        adaptLevel++;

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Purify
    public void heavyAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Purify!";

        //decrease heavy attack charges
        super.changeCurrentHeavyAttackCharges(-1);

        //reset status effects
        resetStatusEffects();

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Life Drain
    public void specialMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Life Drain!\n";

        //just do a heavy attack
        super.heavyAttack(enemyPekomon);
        super.changeCurrentHeavyAttackCharges(1);

        //steal charges
        if(enemyPekomon.getCurrentHeavyAttackCharges() > 0)
        {
            enemyPekomon.changeCurrentHeavyAttackCharges(-1);
            super.changeCurrentHeavyAttackCharges(1);

            PekomonRunner.battleOutput += "\nHeavy charge stolen!";
        }
        if(enemyPekomon.getCurrentSpecialAttackCharges() > 0 && enemyPekomon.getEvo() > 0)
        {
            enemyPekomon.changeCurrentSpecialAttackCharges(-1);
            super.changeCurrentSpecialAttackCharges(1);

            PekomonRunner.battleOutput += "\nSpecial charge stolen!";
        }
        if(enemyPekomon.getCurrentUltimateAttackCharges() > 0 && enemyPekomon.getEvo() > 1)
        {
            enemyPekomon.changeCurrentUltimateAttackCharges(-1);
            super.changeCurrentUltimateAttackCharges(1);

            PekomonRunner.battleOutput += "\nUltimate charge stolen!";
        }

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Psystrike ult
    public void ultimateMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentUltimateAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Psystrike!\n";

        int damageToDeal = super.getSAttack(); //base damage is special attack
        damageToDeal -= enemyPekomon.getSDefense(); //damage reduced by enemy physical defense

        //check for crit
        boolean isCrit = false;
        if((Math.random() * 81) <= super.getAccuracy())
        {
            isCrit = true;
            damageToDeal *= 1.5;
        }

        //check for dodge
        boolean isDodge = false;
        if((Math.random() * 151) <= enemyPekomon.getEvasion())
        {
            isDodge = true;
        }

        //do type calculation
        double effectiveness = typeAdvantage(enemyPekomon);
        damageToDeal = (int)(damageToDeal * effectiveness);
        
        //print out the results
        if((isCrit || !isDodge) && effectiveness != 0)
        {
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

            //deal actual damage
            enemyPekomon.changeCurrentHP(damageToDeal * -1);

            //output
            PekomonRunner.battleOutput += enemyPekomon.getName() + " took " + damageToDeal + " damage!";
        }
        else
        {
            //dodged
            PekomonRunner.battleOutput += "It missed!";
        }

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Method for evolving Pekomon
    public void evolve()
    {
        //no evolution
    }

    //Method that returns this Pekomon's light attack description; overrides parent class
    public String getLightAttackName()
    {
        return "Adapt";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Purify";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Life Drain";
        }
        return "None";
    }

    //Method that returns this Pekomon's ultimate attack description; overrides parent class
    public String getUltimateAttackName()
    {
        if(super.getEvo() > 1)
        {
            return "Psystrike";
        }
        return "None";
    }

    //Method that returns this Pekomon's light attack description; overridden completely by polymorphism
    public String getLightAttackDescription()
    {
        return "An attack that gains more damage the \nmore times it is used in a battle";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "Removes all status effects, including \nAdapt bonus damage";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "Deals damage and steals one of each of \nthe enemy's ability charges";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "Materializes an odd psychic wave to \nattack the target, dealing physical damage";
    }

    //Method to reset status, along with adapt level
    public void resetStatusEffects()
    {
        super.resetStatusEffects();
        adaptLevel = 0;
    }
}