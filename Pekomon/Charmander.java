//Ian Jang
//Last updated 4/9
//File description: Charmander pekomon 

//Evolution: Charmander/Charmeleon/Charizard

public class Charmander extends Pekomon
{
    //Constructor

    public Charmander()
    {
        super("Charmander", 39, 52, 43, 60, 50, 65, 1, 1, 3, 2, 1, "Fire", 0, "CharmanderFront.png", "CharmanderBack.png");
    }

    //Abilities

    //Ember
    public void basicAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Ember!\n";
        super.basicAttack(enemyPekomon);

        //do burn
        if((int)(Math.random() * 101) <= 10)
        {
            PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is burned!";
            enemyPekomon.giveBurn(2, 0.05);
        }

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Dragon Breath
    public void heavyAttack(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Dragon Breath!\n";
        super.heavyAttack(enemyPekomon);

        //do paralyze
        if((int)(Math.random() * 101) <= 30)
        {
            PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is paralyzed!";
            enemyPekomon.giveParalyze(1);
        }

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Flamethrower special
    public void specialMove(Pekomon enemyPekomon)
    {
        PekomonRunner.battleOutput = super.getName() + " used Flamethrower!\n";

        //need to decrease charges
        super.changeCurrentSpecialAttackCharges(-1);

        //just do heavy attack
        super.heavyAttack(enemyPekomon);
        super.changeCurrentHeavyAttackCharges(1);

        //do burn
        PekomonRunner.battleOutput += "\n" + enemyPekomon.getName() + " is burned!";
        enemyPekomon.giveBurn((int)(Math.random() * 4) + 1, Math.random() * 0.15);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Fire Spin ult
    public void ultimateMove(Pekomon enemyPekomon)
    {
        //need to decrease charges
        super.changeCurrentUltimateAttackCharges(-1);

        int trapLength = (int)(Math.random() * 4) + 3;

        PekomonRunner.battleOutput = super.getName() + " used Fire Spin!\n" + enemyPekomon.getName() + " is trapped for " + trapLength + " turns!";

        enemyPekomon.giveParalyze(trapLength);
        enemyPekomon.giveBurn(trapLength, 0.1);

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Method for evolving Pekomon
    public void evolve()
    {
        super.evolve();

        if(super.getEvo() == 0)
        {
            //evolve to Charmeleon
            super.setName("Charmeleon");
            super.setFrontImage("CharmeleonFront.png");
            super.setBackImage("CharmeleonBack.png");
            super.setEvo(1);

            //Update stats
            changeMaxHP(19);
            changeAttack(12);
            changeDefense(15);
            changeSAttack(20);
            changeSDefense(15);
            changeSpeed(15);
            changeEvasion(1);
            changeAccuracy(1);

            //update next benchmark for level up
            super.setMaxXP(20);
        }
        else if(super.getEvo() == 1)
        {
            //evolve to Charizard
            super.setName("Charizard");
            super.setFrontImage("CharizardFront.png");
            super.setBackImage("CharizardBack.png");
            super.setEvo(2);

            //update stats
            changeMaxHP(20);
            changeAttack(20);
            changeDefense(20);
            changeSAttack(29);
            changeSDefense(20);
            changeSpeed(20);
            changeEvasion(2);
            changeAccuracy(5);

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
        return "Ember";
    }
    
    //Method that returns this Pekomon's heavy attack description; overrides parent class
    public String getHeavyAttackName()
    {
        return "Dragon Breath";
    }

    //Method that returns this Pekomon's special attack description; overrides parent class
    public String getSpecialAttackName()
    {
        if(super.getEvo() > 0)
        {
            return "Flamethrower";
        }
        return "None";
    }

    //Method that returns this Pekomon's ultimate attack description; overrides parent class
    public String getUltimateAttackName()
    {
        if(super.getEvo() > 1)
        {
            return "Fire Spin";
        }
        return "None";
    }

    //Method that returns this Pekomon's light attack description; overridden completely by polymorphism
    public String getLightAttackDescription()
    {
        return "A weak fire attack that may inflict \na burn";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "The foe is hit with an\n incredible blast of breath that may also paralyze";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "A powerful fire attack that may \ninflict a random amount of burn";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "Traps the foe in a ring of fire \nfor 3 to 6 turns";
    }
}
