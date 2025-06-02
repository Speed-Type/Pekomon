//Ian Jang
//Last updated 4/9
//File description: Growlithe pekomon 

//Evolution: Growlithe/Arcanine

public class Growlithe extends Pekomon
{
    //Constructor

    public Growlithe()
    {
        super("Growlithe", 55, 70, 45, 70, 50, 60, 5, 5, 3, 3, 0, "Fire", 0, "GrowlitheFront.png", "GrowlitheBack.png");
    }

    //Abilities

    //Ember
    public void basicAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Ember!\n";
        super.basicAttack(enemyPekomon);

        //do burn
        if((int)(Math.random() * 101) <= 10)
        {
            PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is burned!";
            enemyPekomon.giveBurn(2, 0.05);
        }

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Temper Flare
    public void heavyAttack(Pekomon enemyPekomon)
    {
        //change charges
        super.changeCurrentHeavyAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Temper Flare!\n";

        int damageToDeal = super.getSAttack() * 3; //base damage is special attack * 3
        damageToDeal += (int)(Math.random() * super.getSAttack() * 5); //add more damage randomly
        damageToDeal /= enemyPekomon.getSDefense() / 2; //damage divided by enemy special defense
        
        //check if debuffed
        if(isDebuffed())
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

    //Wild Charge
    public void specialMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Wild Charge!\n";

        int damageToDeal = super.getSAttack() * 3; //base damage is special attack * 3
        damageToDeal += (int)(Math.random() * super.getSAttack() * 5); //add more damage randomly
        damageToDeal /= enemyPekomon.getSDefense() / 2; //damage divided by enemy special defense
        
        //check if is coming in hot
        if(PekomonRunner.firstStrike)
        {
            //double damage
            damageToDeal *= 2;

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
            //evolve to Arcanine
            super.setName("Arcanine");
            super.setFrontImage("ArcanineFront.png");
            super.setBackImage("ArcanineBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(35);
            changeAttack(40);
            changeDefense(35);
            changeSAttack(30);
            changeSDefense(30);
            changeSpeed(35);
            changeEvasion(7);
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
        return "Ember";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Temper Flare";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Wild Charge";
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
        return "A weak fire attack that may inflict \na burn";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "Spurred by desperation, the user attacks \nthe target; deals more damage if user is debuffed";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "The user springs into action;\ndeals more damage and paralyzes if it is the first move \nof the battle";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "";
    }
}