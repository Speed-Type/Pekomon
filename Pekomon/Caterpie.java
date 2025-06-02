//Ian Jang
//Last updated 4/10
//File description: Caterpie pekomon 

//Evolution: Caterpie/Metapod/Butterfree

public class Caterpie extends Pekomon
{
    //Constructor

    public Caterpie()
    {
        super("Caterpie", 45, 30, 35, 20, 20, 45, 3, 1, 5, 5, 3, "Bug", 0, "CaterpieFront.png", "CaterpieBack.png");
    }

    //Abilities

    //Bug Bite
    public void basicAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Bug Bite!\n";
        super.basicAttack(enemyPekomon);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Lunge
    public void heavyAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Lunge!\n";
        super.heavyAttack(enemyPekomon);

        //do enemy weaken
        enemyPekomon.giveAttack(-5);
        PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is weakened!\n";

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Quiver Dance special
    public void specialMove(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Quiver Dance!\nSp. Atk, Sp. Def, and Speed UP!";

        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        //give stat boosts
        super.giveSDef(5);
        super.giveSAtk(5);
        super.giveSpeed(10);
        

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Tailwind ult
    public void ultimateMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentUltimateAttackCharges(-1);

        PekomonRunner.battleOutput = super.getName() + " used Tailwind!\nSpeed and Evasion UP!";

        //give stat boosts
        super.giveSpeed(50);
        super.giveEvasion(15);
        

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Method for evolving Pekomon
    public void evolve()
    {
        super.evolve();

        if(super.getEvo() == 0)
        {
            //evolve to Metapod
            super.setName("Metapod");
            super.setFrontImage("MetapodFront.png");
            super.setBackImage("MetapodBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(5);
            changeAttack(-10);
            changeDefense(20);
            changeSAttack(5);
            changeSDefense(5);
            changeSpeed(-15);
            changeEvasion(1);
            changeAccuracy(1);

            //update next benchmark for level up
            super.setMaxXP(20);
        }
        else if(super.getEvo() == 1)
        {
            //evolve to Butterfree
            super.setName("Butterfree");
            super.setFrontImage("ButterfreeFront.png");
            super.setBackImage("ButterfreeBack.png");
            super.setEvo(2);

            //update stats
            changeMaxHP(10);
            changeAttack(25);
            changeDefense(-5);
            changeSAttack(65);
            changeSDefense(55);
            changeSpeed(40);
            changeEvasion(2);
            changeAccuracy(1);

            super.setType("Flying");

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
        return "Bug Bite";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Lunge";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Quiver Dance";
        }
        return "None";
    }

    //Method that returns this Pekomon's ultimate attack description; overrides parent class
    public String getUltimateAttackName()
    {
        if(super.getEvo() > 1)
        {
            return "Tailwind";
        }
        return "None";
    }

    //Method that returns this Pekomon's light attack description; overridden completely by polymorphism
    public String getLightAttackDescription()
    {
        return "Bites the foe";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "Lunges at the target, attacking \nwith full force; may lower the target's ATTACK.";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "Lightly performs a beautiful, mystic \ndance; boosts the user's Sp. Atk, Sp. Def, and Speed";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "Whips up a turbulent whirlwind that \nincreases Speed and Evasion sharply";
    }
}
