//Ian Jang
//Last updated 4/9
//File description: Bulbasaur pekomon 

//Evolution: Bulbasaur/Ivysaur/Venusaur

public class Bulbasaur extends Pekomon
{
    //Constructor

    public Bulbasaur()
    {
        super("Bulbasaur", 45, 49, 49, 65, 65, 45, 3, 6, 4, 2, 1, "Grass", 0, "BulbasaurFront.png", "BulbasaurBack.png");
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

    //Vine Whip
    public void heavyAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Vine Whip!\n";
        super.heavyAttack(enemyPekomon);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //solar beam special
    public void specialMove(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Solar Beam!\n";

        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        //damge calculation
        int damageToDeal = (int)(super.getSAttack() / 3); //base damage is flat special attack / 3

        //check for crit
        boolean isCrit = false;
        if((Math.random() * 101) <= super.getAccuracy())
        {
            isCrit = true;
            damageToDeal = (int)(damageToDeal * 1.5);
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

        //update output text
        PekomonRunner.battleOutput += "The beam pierces " + enemyPekomon.getName() + " for " + damageToDeal + " damage!";

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //leech seed ult
    public void ultimateMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentUltimateAttackCharges(-1);

        //add leech status effect to enemy
        enemyPekomon.giveLeech(10, 0.125);

        //set battle output text
        PekomonRunner.battleOutput = super.getName() + " applied a leech seed to " + enemyPekomon.getName() + "!";

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Method for evolving Pekomon
    public void evolve()
    {
        super.evolve();

        if(super.getEvo() == 0)
        {
            //evolve to Ivysaur
            super.setName("Ivysaur");
            super.setFrontImage("IvysaurFront.png");
            super.setBackImage("IvysaurBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(15);
            changeAttack(13);
            changeDefense(14);
            changeSAttack(15);
            changeSDefense(15);
            changeSpeed(15);
            changeEvasion(2);
            changeAccuracy(3);

            //update next benchmark for level up
            super.setMaxXP(20);
        }
        else if(super.getEvo() == 1)
        {
            //evolve to Venusaur
            super.setName("Venusaur");
            super.setFrontImage("VenusaurFront.png");
            super.setBackImage("VenusaurBack.png");
            super.setEvo(2);

            //update stats
            changeMaxHP(20);
            changeAttack(20);
            changeDefense(20);
            changeSAttack(20);
            changeSDefense(20);
            changeSpeed(20);
            changeEvasion(2);
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
        return "Tackle";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Vine Whip";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Solar Beam";
        }
        return "None";
    }

    //Method that returns this Pekomon's ultimate attack description; overrides parent class
    public String getUltimateAttackName()
    {
        if(super.getEvo() > 1)
        {
            return "Leech Seed";
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
        return "Whips the foe with slender vines";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "Blasts a bundled beam of light that \npierces enemy defense with a guaranteed hit";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "Plants a seed on the foe to steal HP \nevery turn";
    }
}
