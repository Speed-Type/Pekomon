//Ian Jang
//Last updated 4/10
//File description: Meowth pekomon 

//Evolution: Meowth/Persian

public class Meowth extends Pekomon
{
    //Constructor

    public Meowth()
    {
        super("Meowth", 40, 45, 35, 40, 40, 90, 10, 10, 4, 4, 0, "Normal", 0, "MeowthFront.png", "MeowthBack.png");
    }

    //Abilities

    //Scratch
    public void basicAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Scratch!\n";
        super.basicAttack(enemyPekomon);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Fake Out
    public void heavyAttack(Pekomon enemyPekomon)
    {
        //change charges
        super.changeCurrentHeavyAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Fake Out!\n";

        int damageToDeal = super.getSAttack() * 3; //base damage is special attack * 3
        damageToDeal += (int)(Math.random() * super.getSAttack() * 5); //add more damage randomly
        damageToDeal /= enemyPekomon.getSDefense() / 2; //damage divided by enemy special defense
        
        //check if is coming in hot
        if(PekomonRunner.firstStrike)
        {
            //paralyze
            enemyPekomon.giveParalyze(3);

            PekomonRunner.battleOutput += enemyPekomon.getName() + " was caught off guard!\n";
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
        if(!isDodge && effectiveness != 0)
        {
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

    //Power Gem
    public void specialMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Power Gem!\n";

        int damageToDeal = super.getSAttack() * 2; //base damage is special attack * 2
        damageToDeal += (int)(Math.random() * super.getSAttack() * 5); //add more damage randomly
        damageToDeal /= enemyPekomon.getSDefense() / 2; //damage divided by enemy special defense
        
        //check if enemy is debuffed
        if(enemyPekomon.isDebuffed())
        {
            //double damage
            damageToDeal *= 2;
            PekomonRunner.battleOutput += "It was extra powerful!\n";
        }

        //check for crit
        boolean isCrit = false;
        if((Math.random() * 81) <= super.getAccuracy())
        {
            isCrit = true;
            damageToDeal *= 2;
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
            //evolve to Persian
            super.setName("Persian");
            super.setFrontImage("PersianFront.png");
            super.setBackImage("PersianBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(25);
            changeAttack(25);
            changeDefense(25);
            changeSAttack(25);
            changeSDefense(25);
            changeSpeed(25);
            changeEvasion(5);
            changeAccuracy(5);

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
        return "Scratch";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Fake Out";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Power Gem";
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
        return "Scratches with sharp claws";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "An attack that causes paralysis \nif it is the first move of the battle";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "Attacks with a ray of light that \nsparkles as if it were made of gemstones; deals more \ndamage if the enemy is debuffed";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "";
    }
}