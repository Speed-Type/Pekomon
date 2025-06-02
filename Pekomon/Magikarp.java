//Ian Jang
//Last updated 4/9
//File description: Magikarp pekomon 

//Evolution: Magikarp/Gyarados

public class Magikarp extends Pekomon
{
    //Constructor

    public Magikarp()
    {
        super("Magikarp", 20, 10, 55, 15, 20, 80, 3, 7, 2, 2, 0, "Water", 0, "MagikarpFront.png", "MagikarpBack.png");
    }

    //Abilities

    //Tackle
    public void basicAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Tackle!\n";
        super.basicAttack(enemyPekomon);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Flail
    public void heavyAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Flail!\n";

        //decrease heavy attack charges
        super.changeCurrentHeavyAttackCharges(-1);

        //damge calculation
        int damageToDeal = super.getSAttack() * 2; //base damage is special attack * 2
        damageToDeal /= (enemyPekomon.getSDefense() / 2); //damage divided by enemy special defense
        damageToDeal += (super.getMaxHP() - super.getCurrentHP()) / 2; //add more damage according to missing health

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

    //Hyper Beam
    public void specialMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Hyper Beam!\n";

        int damageToDeal = super.getAttack(); //base damage is attack
        damageToDeal -= enemyPekomon.getSDefense(); //damage subtracted by enemy special defense

        if(damageToDeal < 0)
        {
            damageToDeal = 0;
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

        //stun self
        PekomonRunner.battleOutput += "\n" + super.getName() + " is paralyzed!";
        super.giveParalyze(2);

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
            //evolve to Gyarados
            super.setName("Gyarados");
            super.setFrontImage("GyaradosFront.png");
            super.setBackImage("GyaradosBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(75);
            changeAttack(115);
            changeDefense(24);
            changeSAttack(45);
            changeSDefense(80);
            changeSpeed(1);
            changeEvasion(1);
            changeAccuracy(1);
            setType("Flying");

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
        return "Tackle";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Flail";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Hyper Beam";
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
        return "A full-body charge attack";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "A desperate attack that becomes more \npowerful the less HP the user has";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "A severely damaging attack that \nmakes the user rest on the next turn";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "";
    }
}