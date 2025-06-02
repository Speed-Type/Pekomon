//Ian Jang
//Last updated 4/9
//File description: Eevee pekomon (vaporeon variety)

//Evolution: Eevee/Vaporeon

public class EeveeVaporeon extends Pekomon
{
    //Constructor

    public EeveeVaporeon()
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

    //Aqua Ring
    public void specialMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Aqua Ring!\n" + super.getName() + " will regenerate health!";

        super.giveRegen(7, 0.0625);

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
            //evolve to Vaporeon
            super.setName("Vaporeon");
            super.setFrontImage("VaporeonFront.png");
            super.setBackImage("VaporeonBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(75);
            changeAttack(10);
            changeDefense(10);
            changeSAttack(65);
            changeSDefense(30);
            changeSpeed(10);
            changeEvasion(3);
            changeAccuracy(3);

            //change type
            super.setType("Water");

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
            return "Aqua Ring";
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
        return "Envelops self in a veil made of water, \nregaining some HP on every turn";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "";
    }
}
