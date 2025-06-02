//Ian Jang
//Last updated 4/10
//File description: Pidgey pekomon 

//Evolution: Pidgey/Pidgeotto/Pidgeot

public class Pidgey extends Pekomon
{
    //Constructor

    public Pidgey()
    {
        super("Pidgey", 40, 45, 40, 35, 35, 56, 5, 2, 2, 2, 3, "Normal", 0, "PidgeyFront.png", "PidgeyBack.png");
    }

    //Abilities

    //Gust
    public void basicAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Gust!\n";
        super.basicAttack(enemyPekomon);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Aerial Ace
    public void heavyAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Aerial Ace!\n";

        //need to decrease charges
        super.changeCurrentHeavyAttackCharges(-1);

        
        //damge calculation
        int damageToDeal = super.getSAttack() * 2; //base damage is special attack * 2
        damageToDeal += (int)(Math.random() * super.getSAttack() * 5); //add more damage randomly
        damageToDeal /= (enemyPekomon.getSDefense() / 2); //damage divided by enemy special defense

        //check for crit
        boolean isCrit = false;
        if((Math.random() * 81) <= super.getAccuracy())
        {
            isCrit = true;
            damageToDeal *= 2;
        }

        //do type calculation
        double effectiveness = typeAdvantage(enemyPekomon);
        damageToDeal = (int)(damageToDeal * effectiveness);
        
        //print out the results
        if(effectiveness != 0)
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
            PekomonRunner.battleOutput += "It does not work on the enemy type though!";
        }
        
        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Fly special
    public void specialMove(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Fly!\n" + super.getName() + " will be invulnerable to attack!";

        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        //boost own defense
        super.giveFly(3);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Supersonic Skystrike ult
    public void ultimateMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentUltimateAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Supersonic Skystrike!\n";

        int damageToDeal = super.getAttack() + super.getSAttack(); //base damage is attack + special attack
        damageToDeal -= enemyPekomon.getDefense(); //damage reduced by enemy defense
        
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

        //deal actual damage
        enemyPekomon.changeCurrentHP(damageToDeal * -1);

        //output
        PekomonRunner.battleOutput += enemyPekomon.getName() + " took " + damageToDeal + " damage!\n";

        //damage self
        super.changeCurrentHP(damageToDeal * -1 / 3);
        PekomonRunner.battleOutput += super.getName() + " also took " + (damageToDeal / 3) + " damage!\n";

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Method for evolving Pekomon
    public void evolve()
    {
        super.evolve();

        if(super.getEvo() == 0)
        {
            //evolve to Pidgeotto
            super.setName("Pidgeotto");
            super.setFrontImage("PidgeottoFront.png");
            super.setBackImage("PidgeottoBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(23);
            changeAttack(15);
            changeDefense(15);
            changeSAttack(15);
            changeSDefense(15);
            changeSpeed(15);
            changeEvasion(2);
            changeAccuracy(1);

            //update next benchmark for level up
            super.setMaxXP(20);
        }
        else if(super.getEvo() == 1)
        {
            //evolve to Pidgeot
            super.setName("Pidgeot");
            super.setFrontImage("PidgeotFront.png");
            super.setBackImage("PidgeotBack.png");
            super.setEvo(2);

            //update stats
            changeMaxHP(20);
            changeAttack(20);
            changeDefense(20);
            changeSAttack(20);
            changeSDefense(20);
            changeSpeed(30);
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
        return "Gust";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Aerial Ace";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Fly";
        }
        return "None";
    }

    //Method that returns this Pekomon's ultimate attack description; overrides parent class
    public String getUltimateAttackName()
    {
        if(super.getEvo() > 1)
        {
            return "Supersonic Skystrike";
        }
        return "None";
    }

    //Method that returns this Pekomon's light attack description; overridden completely by polymorphism
    public String getLightAttackDescription()
    {
        return "Strikes the foe with a gust of \nwind whipped up by wings";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "An extremely speedy and \nunavoidable attack";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "Flies up, becoming invulnerable temporarily";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "Tucks in wings and charges from a \nlow altitude; the user also takes serious damage";
    }
}
