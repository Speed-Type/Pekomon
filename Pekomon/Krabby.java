//Ian Jang
//Last updated 4/10
//File description: Krabby pekomon 

//Evolution: Krabby/Kingler

public class Krabby extends Pekomon
{
    //Constructor

    public Krabby()
    {
        super("Krabby", 30, 105, 90, 25, 25, 50, 5, 4, 1, 100, 0, "Water", 0, "KrabbyFront.png", "KrabbyBack.png");
    }

    //Abilities

    //Water Gun
    public void basicAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Water Gun!\n";
        super.basicAttack(enemyPekomon);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Crabhammer
    public void heavyAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Crabhammer!\n";

        //decrease heavy attack charges
        super.changeCurrentHeavyAttackCharges(-1);

        //damge calculation
        int damageToDeal = super.getAttack() * 2; //base damage is normal attack * 2
        damageToDeal += (int)(Math.random() * super.getAttack() * 4); //add more damage randomly, based on normal attack
        damageToDeal /= (enemyPekomon.getDefense() / 2); //damage divided by enemy defense

        //check for crit
        boolean isCrit = false;
        if((Math.random() * 81) <= super.getAccuracy() * 2)
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

    //Guillotine
    public void specialMove(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Guillotine!\n";

        //decrease heavy attack charges
        super.changeCurrentSpecialAttackCharges(-1);

        //check for hit
        if((Math.random() * 101) <= 5)
        {
            //hit

            //deal actual damage
            enemyPekomon.changeCurrentHP(-999999);

            //output
            PekomonRunner.battleOutput += "It landed!!!\n" + enemyPekomon.getName() + " is instantly knocked out!";
        }
        else
        {
            //missed
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
            //evolve to Gyarados
            super.setName("Kingler");
            super.setFrontImage("KinglerFront.png");
            super.setBackImage("KinglerBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(25);
            changeAttack(25);
            changeDefense(25);
            changeSAttack(25);
            changeSDefense(25);
            changeSpeed(25);
            changeEvasion(5);
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
        return "Water Gun";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Crabhammer";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Guillotine";
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
        return "Squirts water to attack";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "Hammers with a pincer; has a \nhigh critical-hit ratio";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "A one-hit KO, pincer attack that is \nwildly inaccurate";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "";
    }
}