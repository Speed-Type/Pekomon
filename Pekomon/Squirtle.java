//Ian Jang
//Last updated 4/9
//File description: Squirtle pekomon 

//Evolution: Squirtle/Wartortle/Blastoise

public class Squirtle extends Pekomon
{
    //Constructor

    public Squirtle()
    {
        super("Squirtle", 44, 48, 65, 50, 64, 43, 3, 3, 3, 3, 1, "Water", 0, "SquirtleFront.png", "SquirtleBack.png");
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

    //Rapid Spin
    public void heavyAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Rapid Spin!\n";
        super.heavyAttack(enemyPekomon);
        
        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Iron Defense special
    public void specialMove(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Iron Defense!\nDefense UP!";

        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        //boost own defense
        super.giveDef(10);
        super.giveSDef(10);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Wave Crash ult
    public void ultimateMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentUltimateAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Wave Crash!\n";

        int damageToDeal = super.getSDefense() + super.getDefense(); //base damage is defense + sDefense
        damageToDeal -= enemyPekomon.getSDefense() + enemyPekomon.getDefense(); //damage reduced by enemy special defense + sDefense

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
        super.evolve();

        if(super.getEvo() == 0)
        {
            //evolve to Wartortle
            super.setName("Wartortle");
            super.setFrontImage("WartortleFront.png");
            super.setBackImage("WartortleBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(15);
            changeAttack(15);
            changeDefense(15);
            changeSAttack(15);
            changeSDefense(16);
            changeSpeed(15);
            changeEvasion(2);
            changeAccuracy(2);

            //update next benchmark for level up
            super.setMaxXP(20);
        }
        else if(super.getEvo() == 1)
        {
            //evolve to Blastoise
            super.setName("Blastoise");
            super.setFrontImage("BlastoiseFront.png");
            super.setBackImage("BlastoiseBack.png");
            super.setEvo(2);

            //update stats
            changeMaxHP(20);
            changeAttack(20);
            changeDefense(20);
            changeSAttack(20);
            changeSDefense(25);
            changeSpeed(20);
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
        return "Water Gun";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Rapid Spin";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Iron Defense";
        }
        return "None";
    }

    //Method that returns this Pekomon's ultimate attack description; overrides parent class
    public String getUltimateAttackName()
    {
        if(super.getEvo() > 1)
        {
            return "Wave Crash";
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
        return "A high-speed spinning attack";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "Hardens the body's surface to \nsharply raise DEFENSE";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "The user shrouds itself in water and \nslams into the target with its whole body to inflict damage";
    }
}
