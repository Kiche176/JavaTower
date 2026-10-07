import java.util.HashSet;
import java.util.Set;
package Combatants;

public abstract class Combatant implements ILiveStatSource {
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
    protected Affliction tickAffliction;


    Set<Ablity> abilities;
    Set<Skill> skills;
    Set<Spell> spells;
    Affliction currentAffliction = null;
    Weapon weapon = null;

    public String getName() { return this.name; }
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
    public Set<Skill> getSkills() { return this.skills; }
    public Set<Spell> getSpells() { return this.spells; }
    
    public Affliction getAffliction() { return this.affliction; }
    public Weapon getWeapon() { return this.weapon; }

    // Stat restoration / replenishment

    public void restoreStamina(int amount) {
        this.currentStamina += amount;
        if (this.maxStamina < this.currentStamina) this.currentStamina = this.maxStamina;
    }

    public boolean reduceStamina(int amount) {
        if (this.currentStamina < amount) {
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
        if (this.currentMana < amount) {
            return false;
        } else {
            this.currentMana -= amount;
            return true;
        }
    }

    public void restoreMana(int amount) {
        this.currentMana += amount;
        if (this.maxMana < this.currentMana) this.currentMana = this.maxMana;
    }

    public void replenishMana() {
        this.currentMana = this.maxMana;
    }

    public void restoreHealth(int amount) {
        this.currentHealth += amount;
        if (this.maxHealth < this.currentHealth) this.currentHealth = this.maxHealth;
    }

    public boolean reduceHealth(int amount) {
        if (this.currentHealth < amount) {
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
    

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public void decrementTicks() {
        this.ticks--;
    }

    public int getTicks() {
        return this.ticks;
    }

    public void setTickAffliction(Affliction affliction) {
        this.tickAffliction = affliction;
    }

    public Affliction getTickAffliction() {
        return this.tickAffliction;
    }
}