package Combatants.Enemies;

import Combatants.*;

public abstract class Enemy extends Combatant {
    
    protected  final void initialise(int lvl) {
        setSTR(lvl);
        setART(lvl);
        setAGI(lvl);
        setDEF(lvl);
        setRES(lvl);

        setStaminaStat(lvl);
        setManaStat(lvl);
        setHealthStat(lvl);

        initaliseStats();
    }

    public abstract void setSTR(int lvl);
    public abstract void setART(int lvl);
    public abstract void setAGI(int lvl);
    public abstract void setDEF(int lvl);
    public abstract void setRES(int lvl);

    public abstract void setStaminaStat(int lvl);
    public abstract void setManaStat(int lvl);
    public abstract void setHealthStat(int lvl);
    

    public void initialiseStamina() {
        this.maxStamina = this.staminaStat * 4;
        this.currentStamina = this.maxStamina;
    }

    public void initialiseMana() {
        this.maxMana = this.manaStat * 4;
        this.currentMana = this.maxMana;
    }
    
    public void initialiseHealth() {
        this.maxHealth = this.healthStat * 4;
        this.currentHealth = this.maxHealth;
    }

    public void initaliseStats() {
        initialiseStamina();
        initialiseMana();
        initialiseHealth();
    }
    
}