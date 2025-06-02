//Ian Jang
//Last updated 4/11
//File description: Superclass for all pekomonsters

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pekomon 
{
    //Instance variables

    //Each pekomon has name, max HP, current HP, attack, defense, speed, evasion, accuracy, and type
    private String name;
    private int maxHP; //the cap for this Pekomon's hp
    private int currentHP; //the current HP of this Pekomon
    private int attack; //affects damage dealt
    private int defense; //affects damage taken
    private int sAttack; //affects special attack damage
    private int sDefense; //affects special attack defense
    private int speed; //affects which Pekomon gets the first action
    private int evasion; //affects dodge chance
    private int accuracy; //affects critical hit chance; crits always land
    private String type; //affects type damage bonuses
    private int evo; //evolution of the pekomon; affects what abilities are available
    private boolean isPlayer; //used for game flow
    private int currentXP; //current experience points of this pekomon
    private int maxXP; //xp required to reach next level

    //Each pekomon also has various statuses
    private boolean isAlive = true;

    //Each pekomon also has limited uses of abilities, some of which reset after battle, some not
    private int maxHeavyAttackCharges;
    private int currentHeavyAttackCharges;
    private int maxSpecialAttackCharges;
    private int currentSpecialAttackCharges;
    private int maxUltimateAttackCharges;
    private int currentUltimateAttackCharges;

    //each pekomon also has various status effects

    //leech takes health away from this pekomon and gives it to another pekomon
    private int leechLength; //length of leech status effect duration
    private double leechStrength; //strength of leech status effect; on a decimal scale of 0.00 to 1.00 of max health

    //paralyze is basically a stun
    private int paralyzeLength; //length of paralyzed status effect duration

    //sleep is somewhat a stun; it forces the pekomon to only be able to do basic attacks
    private int sleepLength;

    //regen gives heal each turn
    private int regenLength; //length of regeneration status effect duration
    private double regenStrength; //strength of regeneration effect

    //fly gives invulnerability for a bit
    private int flyLength;

    //burn deals damage over time, with unstacked strength that deals consistent damage each turn
    private int burnLength; //length of burn status effect duration
    private double burnStrength; //strength of burn

    //poison deals damage over time, with stacked length that also is the amount of damage dealt each turn
    private int poisonLength;

    //temporary stat boosts
    private int tempDef = 0;
    private int tempSDef = 0;
    private int tempSAtk = 0;
    private int tempAttack = 0;
    private int tempSpeed = 0;
    private int tempEvasion = 0;
    private int tempAccuracy = 0;

    //images of this Pekomon
    private BufferedImage frontImage;
    private BufferedImage backImage;

    //Constructors

    public Pekomon(String name, int maxHP, int attack, int defense, int sAttack, int sDefense, int speed, int evasion, int accuracy, int maxHeavyAttackCharges, int maxSpecialAttackCharges, int maxUltimateAttackCharges, String type, int evo, String frontImagePath, String backImagePath)
    {
        this.name = name;
        this.maxHP = maxHP;
        currentHP = maxHP;
        this.attack = attack;
        this.defense = defense;
        this.sAttack = sAttack;
        this.sDefense = sDefense;
        this.speed = speed;
        this.evasion = evasion;
        this.accuracy = accuracy;
        this.maxHeavyAttackCharges = maxHeavyAttackCharges;
        currentHeavyAttackCharges = maxHeavyAttackCharges;
        this.maxSpecialAttackCharges = maxSpecialAttackCharges;
        currentSpecialAttackCharges = maxSpecialAttackCharges;
        this.maxUltimateAttackCharges = maxUltimateAttackCharges;
        currentUltimateAttackCharges = maxUltimateAttackCharges;
        this.type = type;
        this.evo = evo;
        
        //default xp to 0, max to 10
        currentXP = 0;
        maxXP = 10;

        //default isPlayer to true
        isPlayer = true;

        //load images
        try 
        {
            //front facing image
            frontImage = ImageIO.read(new File(frontImagePath));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        try 
        {
            //back facing image
            backImage = ImageIO.read(new File(backImagePath));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        //reset all status effects
        resetStatusEffects();
    }



    //GET METHODS

    //Method that returns max HP
    public String getName()
    {
        return name;
    }

    //Method that returns max HP
    public int getMaxHP()
    {
        return maxHP;
    }
    
    //Method that returns current HP
    public int getCurrentHP()
    {
        return currentHP;
    }

    //Method that returns attack
    public int getAttack()
    {
        return attack;
    }

    //Method that returns defense
    public int getDefense()
    {
        return defense;
    }

    //Method that returns special attack
    public int getSAttack()
    {
        return sAttack;
    }

    //Method that returns special defense
    public int getSDefense()
    {
        return sDefense;
    }

    //Method that returns speed
    public int getSpeed()
    {
        return speed;
    }

    //Method that returns evasion
    public int getEvasion()
    {
        return evasion;
    }

    //Method that returns accuracy
    public int getAccuracy()
    {
        return accuracy;
    }

    //Method that returns type
    public String getType()
    {
        return type;
    }

    //Method that returns whether this Pekomon is alive
    public boolean isAlive()
    {
        return isAlive;
    }

    //Method that returns this Pekomon's evo
    public int getEvo()
    {
        return evo;
    }

    //Method that returns this Pekomon's light attack name; overridden completely by polymorphism
    public String getLightAttackName()
    {
        return "";
    }
    
    //Method that returns this Pekomon's heavy attack name; overridden completely by polymorphism
    public String getHeavyAttackName()
    {
        return "";
    }

    //Method that returns this Pekomon's special attack name; overridden completely by polymorphism
    public String getSpecialAttackName()
    {
        return "";
    }

    //Method that returns this Pekomon's ultimate attack name; overridden completely by polymorphism
    public String getUltimateAttackName()
    {
        return "";
    }

    //Method that returns this Pekomon's light attack description; overridden completely by polymorphism
    public String getLightAttackDescription()
    {
        return "";
    }
    
    //Method that returns this Pekomon's heavy attack description; overridden completely by polymorphism
    public String getHeavyAttackDescription()
    {
        return "";
    }

    //Method that returns this Pekomon's special attack description; overridden completely by polymorphism
    public String getSpecialAttackDescription()
    {
        return "";
    }

    //Method that returns this Pekomon's ultimate attack description; overridden completely by polymorphism
    public String getUltimateAttackDescription()
    {
        return "";
    }

    //Method that returns the current front image of this Pekomon
    public BufferedImage getFrontImage()
    {
        return frontImage;
    }

    //Method that returns the current back image of this Pekomon
    public BufferedImage getBackImage()
    {
        return backImage;
    }

    //Method that returns the current heavy attack charges of this Pekomon
    public int getCurrentHeavyAttackCharges()
    {
        return currentHeavyAttackCharges;
    }

    //Method that returns the current special attack charges of this Pekomon
    public int getCurrentSpecialAttackCharges()
    {
        return currentSpecialAttackCharges;
    }

    //Method that returns the current ultimate attack charges of this Pekomon
    public int getCurrentUltimateAttackCharges()
    {
        return currentUltimateAttackCharges;
    }

    //Method that returns the max heavy attack charges of this Pekomon
    public int getMaxHeavyAttackCharges()
    {
        return maxHeavyAttackCharges;
    }

    //Method that returns the max special attack charges of this Pekomon
    public int getMaxSpecialAttackCharges()
    {
        return maxSpecialAttackCharges;
    }

    //Method that returns the max ultimate attack charges of this Pekomon
    public int getMaxUltimateAttackCharges()
    {
        return maxUltimateAttackCharges;
    }

    //Method that returns the current XP of this pekomon
    public int getXP()
    {
        return currentXP;
    }

    //Method that returns whether this pekomon is leeched
    public int isLeeched()
    {
        return leechLength;
    }

    //Method that returns whether this pekomon is flying
    public int isFlying()
    {
        return flyLength;
    }

    //Method that returns whether this pekomon is poisoned
    public int isPoisoned()
    {
        return poisonLength;
    }

    //Method that returns whether this pekomon is burnt
    public int isBurned()
    {
        return burnLength;
    }

    //Method that returns whether this pekomon is regenerating
    public int isRegenerating()
    {
        return regenLength;
    }

    //Method that returns whether this pekomon is paralyzed
    public int isParalyzed()
    {
        return paralyzeLength;
    }

    //Method that returns whether a pekomon is asleep
    public int isAsleep()
    {
        return sleepLength;
    }

    //Method that returns whether this pekomon is debuffed at all
    public boolean isDebuffed()
    {
        return (burnLength > 0) || (leechLength > 0) || (poisonLength > 0) || (paralyzeLength > 0) || (sleepLength > 0) || (tempAttack < 0) || (tempDef < 0) || (tempSDef < 0) || (tempSAtk < 0) || (tempSpeed < 0) || (tempEvasion < 0) || (tempAccuracy < 0);
    }



    //MODIFIERS

    //Method that modifies name
    public void setName(String name)
    {
        this.name = name;
    }
    
    //Method that modifies max HP
    public void changeMaxHP(int changeValue)
    {
        maxHP += changeValue;
        if(maxHP <= 0)
        {
            maxHP = 1;
        }
        if(currentHP > maxHP)
        {
            currentHP = maxHP;
        }

        changeCurrentHP(changeValue);
    }

    //Method that modifies current HP
    public void changeCurrentHP(int changeValue)
    {
        currentHP += changeValue;
        if(currentHP > maxHP)
        {
            currentHP = maxHP;
        }
        if(currentHP <= 0)
        {
            currentHP = 0;
            isAlive = false;
        }

        //update stat
        if(isPlayer)
        {
            if(changeValue < 0)
            {
                //damage
                PekomonRunner.totalDamageTaken += changeValue * -1;
            }
        }
        else
        {
            if(changeValue < 0)
            {
                //damage dealt
                PekomonRunner.totalDamageDealt += changeValue * -1;
            }
        }
    }

    //Method that modifies attack
    public void changeAttack(int changeValue)
    {
        attack += changeValue;
        if(attack <= 0)
        {
            attack = 1;
        }
    }

    //Method that modifies defense
    public void changeDefense(int changeValue)
    {
        defense += changeValue;
        if(defense <= 0)
        {
            defense = 1;
        }
    }

    //Method that modifies special attack
    public void changeSAttack(int changeValue)
    {
        sAttack += changeValue;
        if(sAttack <= 0)
        {
            sAttack = 1;
        }
    }

    //Method that modifies special defense
    public void changeSDefense(int changeValue)
    {
        sDefense += changeValue;
        if(sDefense <= 0)
        {
            sDefense = 1;
        }
    }

    //Method that modifies speed
    public void changeSpeed(int changeValue)
    {
        speed += changeValue;
        if(speed <= 0)
        {
            speed = 1;
        }
    }

    //Method that modifies evasion
    public void changeEvasion(int changeValue)
    {
        evasion += changeValue;
        if(evasion <= 0)
        {
            evasion = 1;
        }
    }

    //Method that modifies accuracy
    public void changeAccuracy(int changeValue)
    {
        accuracy += changeValue;
        if(accuracy <= 0)
        {
            accuracy = 1;
        }
    }

    //Method that modifies type
    public void setType(String type)
    {
        this.type = type;
    }

    //Method that sets isAlive
    public void setIsAlive(boolean val)
    {
        isAlive = val;
    }

    //Method that modifies heavyAttackCharges
    public void changeCurrentHeavyAttackCharges(int changeValue)
    {
        currentHeavyAttackCharges += changeValue;
        if(currentHeavyAttackCharges <= 0)
        {
            currentHeavyAttackCharges = 0;
        }
        if(currentHeavyAttackCharges > maxHeavyAttackCharges)
        {
            currentHeavyAttackCharges = maxHeavyAttackCharges;
        }
    }

    //Method that modifies specialAttackCharges
    public void changeCurrentSpecialAttackCharges(int changeValue)
    {
        currentSpecialAttackCharges += changeValue;
        if(currentSpecialAttackCharges <= 0)
        {
            currentSpecialAttackCharges = 0;
        }
        if(currentSpecialAttackCharges > maxSpecialAttackCharges)
        {
            currentSpecialAttackCharges = maxSpecialAttackCharges;
        }
    }

    //Method that modifies ultimateAttackCharges
    public void changeCurrentUltimateAttackCharges(int changeValue)
    {
        currentUltimateAttackCharges += changeValue;
        if(currentUltimateAttackCharges <= 0)
        {
            currentUltimateAttackCharges = 0;
        }
        if(currentUltimateAttackCharges > maxUltimateAttackCharges)
        {
            currentUltimateAttackCharges = maxUltimateAttackCharges;
        }
    }

    //Method that sets front image path
    public void setFrontImage(String path)
    {
        try 
        {
            frontImage = ImageIO.read(new File(path));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    //Method that sets back image path
    public void setBackImage(String path)
    {
        try 
        {
            backImage = ImageIO.read(new File(path));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    //Method that sets evo
    public void setEvo(int evo)
    {
        this.evo = evo;
    }

    //Method that sets pekomon to enemy
    public void setEnemy()
    {
        isPlayer = false;
    }


    //Method that modifies XP
    public void changeXP(int changeValue)
    {
        currentXP += changeValue;
        if(currentXP >= maxXP)
        {
            currentXP -= maxXP;
            evolve();
        }
    }

    //Method that sets max XP
    public void setMaxXP(int value)
    {
        maxXP = value;
    }

    //Method that gives this pekomon the leech status effect
    public void giveLeech(int length, double strength)
    {
        leechLength += length; //length stacks
        leechStrength = strength; //strength does not stack
    }

    //Method that gives this pekomon the fly status effect
    public void giveFly(int length)
    {
        flyLength = length; //does not stack, just sets

        //give temp evasion
        giveEvasion(200);
    }

    //Method that gives this pekomon the poison status effect
    public void givePoison(int length)
    {
        poisonLength += length; //length stacks
    }

    //Method that gives burn status effect
    public void giveBurn(int length, double strength)
    {
        burnLength += length; //length stacks
        burnStrength = strength; //strength does not stack
    }

    //Method that gives temp def
    public void giveDef(int def)
    {
        int oldDef = defense;
        defense += def;

        if(defense <= 0)
        {
            def = 1;
            tempDef += oldDef - 1;
        }
        else
        {
            tempDef += def;
        }
    }

    //Method that gives temp attack
    public void giveAttack(int atk)
    {
        int oldAttack = attack;
        attack += atk;

        if(attack <= 0)
        {
            atk = 1;
            tempAttack += oldAttack - 1;
        }
        else
        {
            tempAttack += atk;
        }
    }

    //Method that gives temp sdef
    public void giveSDef(int def)
    {
        int oldDef = sDefense;
        sDefense += def;

        if(sDefense <= 0)
        {
            def = 1;
            tempSDef += oldDef - 1;
        }
        else
        {
            tempSDef += def;
        }
    }

    //Method that gives temp speed
    public void giveSpeed(int spd)
    {
        int oldSpd = speed;
        speed += spd;

        if(speed <= 0)
        {
            spd = 1;
            tempSpeed += oldSpd - 1;
        }
        else
        {
            tempSpeed += spd;
        }
    }

    //Method that gives temp evasion
    public void giveEvasion(int eva)
    {
        int oldEvasion = evasion;
        evasion += eva;

        if(evasion <= 0)
        {
            eva = 1;
            tempEvasion += oldEvasion - 1;
        }
        else
        {
            tempEvasion += eva;
        }
    }

    //Method that gives temp accuracy
    public void giveAccuracy(int acc)
    {
        int oldAccuracy = accuracy;
        accuracy += acc;

        if(accuracy <= 0)
        {
            acc = 1;
            tempAccuracy += oldAccuracy - 1;
        }
        else
        {
            tempAccuracy += acc;
        }
    }

    //Method that gives temp sattack
    public void giveSAtk(int atk)
    {
        int oldAtk = sAttack;
        sAttack += atk;

        if(sAttack <= 0)
        {
            atk = 1;
            tempSAtk += oldAtk - 1;
        }
        else
        {
            tempSAtk += atk;
        }
    }

    //Method that gives this pekomon the regeneration status effect
    public void giveRegen(int length, double strength)
    {
        regenLength += length; //length stacks
        regenStrength = strength; //strength does not stack
    }

    //Method that gives this pekomon the paralyzed status effect
    public void giveParalyze(int length)
    {
        paralyzeLength += length; //length stacks
    }

    //Method that gives the pekomon the sleep status effect
    public void giveSleep(int length)
    {
        sleepLength += length; //length stacks
    }



    //FIGHT-BASED METHODS

    //Method that returns a double indicating what kind of type advantage this Pekomon has compared to another Pekomon
    public double typeAdvantage(Pekomon enemyPekomon)
    {
        //do type calculation
        //Types used are Normal, Fighting, Flying, Poison, Ground, Rock, Bug, Ghost, Fire, Water, Grass, Electric, Psychic, Dragon, Fairy
        
        double effectiveness;
        int thisPekomonType = 0;
        int enemyPekomonType = 0;

        //get this pekomon's type
        if(type.equals("Normal"))
        {
            thisPekomonType = 0;
        }
        else if(type.equals("Fighting"))
        {
            thisPekomonType = 1;
        }
        else if(type.equals("Flying"))
        {
            thisPekomonType = 2;
        }
        else if(type.equals("Poison"))
        {
            thisPekomonType = 3;
        }
        else if(type.equals("Ground"))
        {
            thisPekomonType = 4;
        }
        else if(type.equals("Rock"))
        {
            thisPekomonType = 5;
        }
        else if(type.equals("Bug"))
        {
            thisPekomonType = 6;
        }
        else if(type.equals("Ghost"))
        {
            thisPekomonType = 7;
        }
        else if(type.equals("Fire"))
        {
            thisPekomonType = 8;
        }
        else if(type.equals("Water"))
        {
            thisPekomonType = 9;
        }
        else if(type.equals("Grass"))
        {
            thisPekomonType = 10;
        }
        else if(type.equals("Electric"))
        {
            thisPekomonType = 11;
        }
        else if(type.equals("Psychic"))
        {
            thisPekomonType = 12;
        }
        else if(type.equals("Dragon"))
        {
            thisPekomonType = 13;
        }
        else if(type.equals("Fairy"))
        {
            thisPekomonType = 14;
        }
        
        //get enemy pekomon type
        if(enemyPekomon.type.equals("Normal"))
        {
            enemyPekomonType = 0;
        }
        else if(enemyPekomon.type.equals("Fighting"))
        {
            enemyPekomonType = 1;
        }
        else if(enemyPekomon.type.equals("Flying"))
        {
            enemyPekomonType = 2;
        }
        else if(enemyPekomon.type.equals("Poison"))
        {
            enemyPekomonType = 3;
        }
        else if(enemyPekomon.type.equals("Ground"))
        {
            enemyPekomonType = 4;
        }
        else if(enemyPekomon.type.equals("Rock"))
        {
            enemyPekomonType = 5;
        }
        else if(enemyPekomon.type.equals("Bug"))
        {
            enemyPekomonType = 6;
        }
        else if(enemyPekomon.type.equals("Ghost"))
        {
            enemyPekomonType = 7;
        }
        else if(enemyPekomon.type.equals("Fire"))
        {
            enemyPekomonType = 8;
        }
        else if(enemyPekomon.type.equals("Water"))
        {
            enemyPekomonType = 9;
        }
        else if(enemyPekomon.type.equals("Grass"))
        {
            enemyPekomonType = 10;
        }
        else if(enemyPekomon.type.equals("Electric"))
        {
            enemyPekomonType = 11;
        }
        else if(enemyPekomon.type.equals("Psychic"))
        {
            enemyPekomonType = 12;
        }
        else if(enemyPekomon.type.equals("Dragon"))
        {
            enemyPekomonType = 13;
        }
        else if(enemyPekomon.type.equals("Fairy"))
        {
            enemyPekomonType = 14;
        }

        //use 2D array to determine effectiveness
        double[][] typeAdvantages = 
        {
            {1, 1, 1, 1, 1, 0.5, 1, 0, 1, 1, 1, 1, 1, 1, 1}, //normal
            {2, 1, 0.5, 0.5, 1, 2, 0.5, 0, 1, 1, 1, 1, 0.5, 1, 0.5}, //fighting
            {1, 2, 1, 1, 1, 0.5, 2, 1, 1, 1, 2, 0.5, 1, 1, 1}, //flying
            {1, 1, 1, 0.5, 0.5, 0.5, 1, 0.5, 1, 1, 2, 1, 1, 1, 2}, //poison
            {1, 1, 0, 2, 1, 2, 0.5, 1, 2, 1, 0.5, 2, 1, 1, 1}, //ground
            {1, 0.5, 2, 1, 0.5, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1}, //rock
            {1, 0.5, 0.5, 0.5, 1, 1, 1, 0.5, 0.5, 1, 2, 1, 2, 1, 0.5}, // bug
            {0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1}, //ghost
            {1, 1, 1, 1, 1, 0.5, 2, 1, 0.5, 0.5, 2, 1, 1, 0.5, 1}, //fire
            {1, 1, 1, 1, 2, 2, 1, 1, 2, 0.5, 0.5, 1, 1, 0.5, 1}, //water
            {1, 1, 0.5, 0.5, 2, 2, 0.5, 1, 0.5, 2, 0.5, 1, 1, 0.5, 1}, //grass
            {1, 1, 2, 1, 0, 1, 1, 1, 1, 2, 0.5, 0.5, 1, 0.5, 1}, //electric
            {1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 0.5, 1, 1}, //psychic
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0}, //dragon
            {1, 2, 1, 0.5, 1, 1, 1, 1, 0.5, 1, 1, 1, 1, 2, 1} //fairy
        };

        effectiveness = typeAdvantages[thisPekomonType][enemyPekomonType];

        return effectiveness;
    }

    //Basic attack method; overriden by polymorphism, but called within child classes
    //Deals a set amount of damage according to attack, accuracy, type, enemy type, enemy defense, enemy evasion
    public void basicAttack(Pekomon enemyPekomon)
    {
        //damge calculation
        int damageToDeal = attack * 3; //base damage is attack * 3
        damageToDeal /= enemyPekomon.defense; //damage divided by enemy defense

        //check for crit
        boolean isCrit = false;
        if((Math.random() * 101) <= accuracy)
        {
            isCrit = true;
            damageToDeal *= 2;
        }

        //check for dodge
        boolean isDodge = false;
        if((Math.random() * 101) <= enemyPekomon.evasion)
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

            //update output text
            PekomonRunner.battleOutput += enemyPekomon.name + " took " + damageToDeal + " damage!";
        }
        else
        {
            //dodged
            PekomonRunner.battleOutput += "It missed!";
        }

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Heavy Attack method; overriden by polymorphism, but called within child classes
    //Deals the basic attack damage plus extra random damage; greater chance to hit and crit, too
    public void heavyAttack(Pekomon enemyPekomon)
    {
        //decrease heavy attack charges
        currentHeavyAttackCharges--;

        //damge calculation
        int damageToDeal = sAttack * 2; //base damage is special attack * 2
        damageToDeal += (int)(Math.random() * sAttack * 5); //add more damage randomly
        damageToDeal /= (enemyPekomon.sDefense / 2); //damage divided by enemy special defense

        //check for crit
        boolean isCrit = false;
        if((Math.random() * 81) <= accuracy)
        {
            isCrit = true;
            damageToDeal *= 2;
        }

        //check for dodge
        boolean isDodge = false;
        if((Math.random() * 151) <= enemyPekomon.evasion)
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
            PekomonRunner.battleOutput += enemyPekomon.name + " took " + damageToDeal + " damage!";
        }
        else
        {
            //dodged
            PekomonRunner.battleOutput += "It missed!";
        }

        //reset first strike
        PekomonRunner.firstStrike = false;
    }

    //Special Attack method; overriden completely by polymorphism
    //Effect varies from Pekomon to Pekomon; can only be used by evo 1 and above
    public void specialMove(Pekomon enemyPekomon)
    {
        
    }

    //Ultimate Attack method; overriden completely by polymorphism
    //Effect varies from Pekomon to Pekomon; can only be used by evo 2 and above
    public void ultimateMove(Pekomon enemyPekomon)
    {

    }

    //Heal method: heals the pekomon's health by 1/3 of max health
    public void heal()
    {
        PekomonRunner.numPotions--;
        int previousHP = currentHP;
        currentHP += (int)(maxHP / 3);
        currentHP = Math.min(currentHP, maxHP);
        PekomonRunner.battleOutput = name + " healed for " + (currentHP - previousHP) + " HP!";

        //update stat
        PekomonRunner.totalDamageHealed += currentHP - previousHP;
    }



    //STATUS EFFECT METHODS

    //Method to apply leech status
    public void leech(Pekomon pekomonToReceiveHealth)
    {
        leechLength--;

        int oldHP = currentHP;
        
        changeCurrentHP((int)(maxHP * leechStrength) * -1);
        pekomonToReceiveHealth.changeCurrentHP(oldHP - currentHP);

        //change text
        PekomonRunner.battleOutput = "Leech (" + leechLength + " turns left)\n" + pekomonToReceiveHealth.name + " took " + (oldHP - currentHP) + " HP from " + name + "!";
    }

    //Method to apply fly status
    public void fly()
    {
        flyLength--;

        //change text
        PekomonRunner.battleOutput = "Fly (" + flyLength + " turns left)\n";

        if(flyLength == 0)
        {
            PekomonRunner.battleOutput += name + " is landing!";
            giveEvasion(-200);
        }
        else
        {
            PekomonRunner.battleOutput += name + " is invulnerable to attack!";
        }
    }

    //Method to apply poison status
    public void poison()
    {
        int oldHP = currentHP;

        changeCurrentHP(poisonLength * -1);

        //change text
        PekomonRunner.battleOutput = "Poison (" + poisonLength + " turns left)\n" + name + " lost " + (oldHP - currentHP) + " HP!";
    
        poisonLength--;
    }

    //Method to apply burn status
    public void burn()
    {
        burnLength--;

        int oldHP = currentHP;

        changeCurrentHP((int)(maxHP * burnStrength) * -1);

        //change text
        PekomonRunner.battleOutput = "Burn (" + burnLength + " turns left)\n" + name + " lost " + (oldHP - currentHP) + " HP!";
    }

    //Method to apply regen status
    public void regen()
    {
        regenLength--;

        int oldHP = currentHP;

        changeCurrentHP((int)(maxHP * regenStrength));

        //change text
        PekomonRunner.battleOutput = "Regen (" + regenLength + " turns left)\n" + name + " regenerated " + (currentHP - oldHP) + " HP!";
    }

    //Method to apply paralyzed status
    public void paralyze()
    {
        paralyzeLength--;

        //change text
        PekomonRunner.battleOutput = name + " is paralyzed (" + paralyzeLength + " turns left)";
    }

    //Method to apply sleep status
    public void sleep()
    {
        sleepLength--;

        //change text
        PekomonRunner.battleOutput = name + " is asleep (" + sleepLength + " turns left)\n" + name + " can only use light attacks!";
    }




    //MISC METHODS

    //Method to display stats
    public String displayStats()
    {
        String str = name + "\nEvo: " + evo + "\nType: " + type + "\nHP: " + currentHP + "/" + maxHP + "\nAttack: " + attack + "\nDefense: " + defense + "\nSpecial Attack: " + sAttack + "\nSpecial Defense: " + sDefense + "\nSpeed: " + speed + "\nEvasion: " + evasion + "\nAccuracy: " + accuracy + "\n\nXP: ";
        
        if(maxXP == 9999)
        {
            str += "Maxed out\n";
        }
        else
        {
            str += currentXP + "/" + maxXP + "\n";
        }

        str += "Heavy Charges: " + currentHeavyAttackCharges + "/" + maxHeavyAttackCharges + "\n";

        if(evo > 0)
        {
            str += "Special Charges: " + currentSpecialAttackCharges + "/" + maxSpecialAttackCharges + "\n"; 
        }
        if(evo > 1)
        {
            str += "Ultimate Charges: " + currentUltimateAttackCharges + "/" + maxUltimateAttackCharges + "\n";
        }
        
        return str;
    }

    //Method to evolve; resets charges and health; overridden partially by polymorphism
    public void evolve()
    {
        //reset charges
        changeCurrentHeavyAttackCharges(9999);
        changeCurrentSpecialAttackCharges(9999);
        changeCurrentUltimateAttackCharges(9999);

        //regen health
        changeCurrentHP(999999);
    }

    //Method to reset status effects
    public void resetStatusEffects()
    {
        leechLength = 0;
        leechStrength = 0;

        burnLength = 0;
        burnStrength = 0;

        paralyzeLength = 0;

        sleepLength = 0;

        flyLength = 0;

        regenLength = 0;
        regenLength = 0;

        poisonLength = 0;

        defense -= tempDef;
        tempDef = 0;

        sDefense -= tempSDef;
        tempSDef = 0;

        sAttack -= tempSAtk;
        tempSAtk = 0;

        speed -= tempSpeed;
        tempSpeed = 0;

        evasion -= tempEvasion;
        tempEvasion = 0;

        accuracy -= tempAccuracy;
        tempAccuracy = 0;

        attack -= tempAttack;
        tempAttack = 0;
    }
}