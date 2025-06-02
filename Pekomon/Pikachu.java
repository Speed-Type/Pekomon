//Ian Jang
//Last updated 4/11
//File description: Pikachu pekomon 

//Evolution: Pikachu/Raichu

public class Pikachu extends Pekomon
{
    //Constructor

    public Pikachu()
    {
        super("Pikachu", 35, 55, 30, 50, 40, 90, 7, 6, 2, 3, 0, "Electric", 0, "PikachuFront.png", "PikachuBack.png");
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

    //Volt Tackle
    public void heavyAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Volt Tackle!\n";
        super.heavyAttack(enemyPekomon);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Discharge
    public void specialMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Discharge!\n";

        int damageToDeal = super.getSAttack() * 3; //base damage is special attack * 3
        damageToDeal /= enemyPekomon.getSDefense(); //damage divided by enemy special defense

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
        
            //do paralyze
            if((int)(Math.random() * 101) <= 60)
            {
                PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is paralyzed!";
                enemyPekomon.giveParalyze(4);
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
            //evolve to Raichu
            super.setName("Raichu");
            super.setFrontImage("RaichuFront.png");
            super.setBackImage("RaichuBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(25);
            changeAttack(35);
            changeDefense(15);
            changeSAttack(40);
            changeSDefense(30);
            changeSpeed(20);
            changeEvasion(7);
            changeAccuracy(6);

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
        return "Volt Tackle";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Discharge";
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
        return "Throws an electrified tackle";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "A flare of electricity is loosed to \nstrike the area around the user, sometimes causing paralysis";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "";
    }
}
