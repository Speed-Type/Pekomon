//Ian Jang
//Last updated 4/10
//File description: Gastly pekomon 

//Evolution: Gastly/Haunter/Gengar

public class Gastly extends Pekomon
{
    //Constructor

    public Gastly()
    {
        super("Gastly", 30, 35, 30, 100, 35, 80, 0, 3, 3, 3, 3, "Ghost", 0, "GastlyFront.png", "GastlyBack.png");
    }

    //Abilities

    //Night Shade
    public void basicAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Night Shade!\n";
        super.basicAttack(enemyPekomon);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Lick
    public void heavyAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Lick!\n";
        super.heavyAttack(enemyPekomon);

        //do paralyze
        if((int)(Math.random() * 101) <= 30)
        {
            PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is paralyzed!";
            enemyPekomon.giveParalyze(3);
        }

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Hypnosis special
    public void specialMove(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Hypnosis!\n";

        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        //damge calculation
        int damageToDeal = super.getSAttack() * 3; //base damage is special attack * 3
        damageToDeal /= enemyPekomon.getSDefense(); //damage divided by enemy special defense

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
            PekomonRunner.battleOutput += enemyPekomon.getName() + " took " + damageToDeal + " damage!\n";

            //do sleep
            enemyPekomon.giveSleep(3);
            PekomonRunner.battleOutput += enemyPekomon.getName() + " is asleep!";
        }
        else
        {
            //dodged
            PekomonRunner.battleOutput += "It missed!";
        }

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Dream Eater ult
    public void ultimateMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentUltimateAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Dream Eater!\n";
        
        //damge calculation
        int damageToDeal = super.getSAttack() * 2; //base damage is special attack * 2
        damageToDeal += (int)(Math.random() * super.getSAttack() * 5); //add more damage randomly
        damageToDeal /= enemyPekomon.getSDefense() / 2; //damage divided by enemy special defense

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

            int oldEnemyHealth = enemyPekomon.getCurrentHP();

            //deal actual damage
            enemyPekomon.changeCurrentHP(damageToDeal * -1);

            //update output text
            PekomonRunner.battleOutput += enemyPekomon.getName() + " took " + damageToDeal + " damage!";

            //heal
            if(enemyPekomon.isAsleep() > 0)
            {
                super.changeCurrentHP((oldEnemyHealth - enemyPekomon.getCurrentHP()) / 2);
                PekomonRunner.battleOutput += "\n" + super.getName() + " healed " + ((oldEnemyHealth - enemyPekomon.getCurrentHP()) / 2) + " HP!";
            }
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
        super.evolve();

        if(super.getEvo() == 0)
        {
            //evolve to Haunter
            super.setName("Haunter");
            super.setFrontImage("HaunterFront.png");
            super.setBackImage("HaunterBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(15);
            changeAttack(15);
            changeDefense(15);
            changeSAttack(15);
            changeSDefense(20);
            changeSpeed(15);
            changeEvasion(1);
            changeAccuracy(1);

            //update next benchmark for level up
            super.setMaxXP(20);
        }
        else if(super.getEvo() == 1)
        {
            //evolve to Gengar
            super.setName("Gengar");
            super.setFrontImage("GengarFront.png");
            super.setBackImage("GengarBack.png");
            super.setEvo(2);

            //update stats
            changeMaxHP(15);
            changeAttack(15);
            changeDefense(15);
            changeSAttack(15);
            changeSDefense(20);
            changeSpeed(15);
            changeEvasion(1);
            changeAccuracy(3);

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
        return "Night Shade";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Lick";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Hypnosis";
        }
        return "None";
    }

    //Method that returns this Pekomon's ultimate attack description; overrides parent class
    public String getUltimateAttackName()
    {
        if(super.getEvo() > 1)
        {
            return "Dream Eater";
        }
        return "None";
    }

    //Method that returns this Pekomon's light attack description; overridden completely by polymorphism
    public String getLightAttackDescription()
    {
        return "An attack with a mirage";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "Licks with a long tongue to injure; \nmay also paralyze.";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "A hypnotizing move that may induce \nsleep";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "Takes one half the damage inflicted \non a sleeping foe";
    }
}
