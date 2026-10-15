package Combatants;

import java.util.Set;

import StatInterfaces.*;
import DamageSystems.Afflictions.*;
import DamageSystems.Elements.*;
import Abilities.*;
import Abilities.Skills.*;
import Abilities.Spells.*;
import Abilities.Utilities.*;
import Weapons.*;

public abstract class Combatant implements IStatSource {
    protected String name;
    protected Job job;
    protected int LVL = 1;

    protected int STR = 5;
    protected int ART = 5;
    protected int AGI = 5;
    protected int DEF = 5;
    protected int RES = 5;


    protected int staminaStat = 5;
    protected int manaStat = 5;
    protected int healthStat = 10;

    // NEED TO CREATE THE FORMULAS TO CONVERT STATS INTO QUANTITIES
    protected int maxStamina;
    protected int maxMana;
    protected int maxHealth;

    protected int currentStamina = this.maxStamina;
    protected int currentMana= this.maxMana;
    protected int currentHealth = this.maxHealth;    

    protected int ticksLeft;
    protected Element tickElement;


    protected Set<Ability> abilities = Set.of(Utilities.SKIP, Skills.PUNCH, Skills.KICK);
    protected Affliction affliction = null;
    protected Weapon weapon = Weapons.UNARMED;

    public String getName() { return this.name; }
    public Job getJob() {return this.job; }
    public int getLVL() { return this.LVL; }

    public int getSTR() { return this.STR; }
    public int getART() { return this.ART; }
    public int getAGI() { return this.AGI; }
    public int getDEF() { return this.DEF; }
    public int getRES() { return this.RES; }

    public int getStaminaStat() { return this.staminaStat; }
    public int getManaStat() { return this.manaStat; }
    public int getHealthStat() {return this.healthStat; }

    public int getMaxStamina() { return this.maxStamina; }
    public int getMaxMana() { return this.maxMana; }
    public int getMaxHealth() { return this.maxHealth; }

    public int getCurrentStamina() { return this.currentStamina; }
    public int getCurrentMana() { return this.currentMana; }
    public int getCurrentHealth() { return this.currentHealth; }    


    public Set<Ability> getAbilities() { return this.abilities; }
    
    public Affliction getAffliction() { return this.affliction; }
    public void setAffliction(Affliction affliction) { this.affliction = affliction; }
    public Weapon getWeapon() { return this.weapon; }

    // Stat restoration / replenishment

    public boolean restoreStamina(int amount) {
        if (amount < 0) {
            return false;
        } else {
            this.currentStamina += amount;
            if (this.maxStamina < this.currentStamina) this.currentStamina = this.maxStamina;
            return true;
        }
    }

    public boolean reduceStamina(int amount) {
        if (amount < 0) {
            return false;
        } else if (this.currentStamina < amount) {
            return false;
        } else {
            this.currentStamina -= amount;
            return true;
        }
    }

    public void replenishStamina() {
        this.currentStamina = this.maxStamina;
    }

    public boolean reduceMana(int amount) {
        if (amount < 0) {
            return false;
        } else if (this.currentMana < amount) {
            return false;
        } else {
            this.currentMana -= amount;
            return true;
        }
    }

    public boolean restoreMana(int amount) {
        if (amount < 0) {
            return false;
        } else {
            this.currentMana += amount;
            if (this.maxMana < this.currentMana) this.currentMana = this.maxMana;
            return true;
        }
    }

    public void replenishMana() {
        this.currentMana = this.maxMana;
    }

    public boolean restoreHealth(int amount) {
        if (amount < 0) {
            return false;
        } else {
            this.currentHealth += amount;
            if (this.maxHealth < this.currentHealth) this.currentHealth = this.maxHealth;
            return true;
        }  
    }

    public boolean reduceHealth(int amount) {
        if (amount < 0) {
            return false;
        } else if (this.currentHealth < amount) {
            this.currentHealth = 0;
            return false;
        } else {
            this.currentHealth -= amount;
            return true;
        }
    }

    public void replenishHealth() {
        this.currentHealth = this.maxHealth;
    }

    public void replenishStats() {
        replenishStamina();
        replenishMana();
        replenishHealth();
    }
    

    public void setTicks(int ticks, Element tickElement) {
        this.ticksLeft = ticks;
    }

    public void decrementTicks() {
        if (this.ticksLeft > 0) this.ticksLeft--;
        if (this.ticksLeft == 0) this.tickElement = null;
    }

    public int getTicks() {
        return this.ticksLeft;
    }

    public Element getTickElement() {
        return this.tickElement;
    }
}