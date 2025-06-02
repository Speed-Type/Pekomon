//Ian Jang
//Last updated 4/9
//File description: Eevee pekomon (flareon variety)

//Evolution: Eevee/Flareon

public class EeveeFlareon extends Pekomon
{
    //Constructor

    public EeveeFlareon()
    {
        super("Eevee", 55, 55, 50, 45, 65, 55, 3, 3, 2, 7, 0, "Normal", 0, "EeveeFront.png", "EeveeBack.png");
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

    //Bite
    public void heavyAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Bite!\n";
        super.heavyAttack(enemyPekomon);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Fire Fang
    public void specialMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Fire Fang!\n";

        int damageToDeal = super.getSAttack() * 2; //base damage is special attack * 2
        damageToDeal /= enemyPekomon.getSDefense(); //damage divided by enemy special defense

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

            //do paralyze
            if((int)(Math.random() * 101) <= 25)
            {
                PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is paralyzed!";
                enemyPekomon.giveParalyze(1);
            }

            //do burn
            if((int)(Math.random() * 101) <= 50)
            {
                PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is burned!";
                enemyPekomon.giveBurn(3, 0.05);
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
            //evolve to Jolteon
            super.setName("Flareon");
            super.setFrontImage("FlareonFront.png");
            super.setBackImage("FlareonBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(10);
            changeAttack(75);
            changeDefense(10);
            changeSAttack(50);
            changeSDefense(45);
            changeSpeed(10);
            changeEvasion(3);
            changeAccuracy(3);

            //change type
            super.setType("Fire");

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
        return "Bite";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Fire Fang";
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
        return "Bites with vicious fangs";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "Bites with flame-cloaked fangs, \npossibly inflicting burn or paralyze";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "";
    }
}
