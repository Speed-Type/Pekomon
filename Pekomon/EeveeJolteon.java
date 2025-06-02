//Ian Jang
//Last updated 4/9
//File description: Eevee pekomon (jolteon variety)

//Evolution: Eevee/Jolteon

public class EeveeJolteon extends Pekomon
{
    //Constructor

    public EeveeJolteon()
    {
        super("Eevee", 55, 55, 50, 45, 65, 55, 3, 3, 2, 2, 0, "Normal", 0, "EeveeFront.png", "EeveeBack.png");
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

    //Flash Strike
    public void specialMove(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Flash Strike!\nZoom!!!\n";

        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        //damge calculation
        int damageToDeal = super.getSpeed() - enemyPekomon.getSpeed(); //base damage is flat difference in speed
        
        if(damageToDeal < 0)
        {
            damageToDeal = 0;
        }

        //deal actual damage
        enemyPekomon.changeCurrentHP(damageToDeal * -1);

        //update output text
        PekomonRunner.battleOutput += enemyPekomon.getName() + " is struck for " + damageToDeal + " damage!";

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
            super.setName("Jolteon");
            super.setFrontImage("JolteonFront.png");
            super.setBackImage("JolteonBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(10);
            changeAttack(10);
            changeDefense(10);
            changeSAttack(65);
            changeSDefense(30);
            changeSpeed(75);
            changeEvasion(3);
            changeAccuracy(3);

            //change type
            super.setType("Electric");

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
            return "Flash Strike";
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
        return "Strikes with blinding speed, dealing \ndamage based on speed difference";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "";
    }
}
