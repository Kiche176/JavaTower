package Combatants.Enemies;

import Combatants.Job;

public class TestDummy extends Enemy{
    public TestDummy(int lvl) {
        this.name = "Dummy";
        this.job = Job.TANK;
        initialise(lvl);
    }

    public void setSTR(int lvl) {
        this.STR = 5 * lvl;
    }

    public void setART(int lvl) {
        this.ART = 5 * lvl;
    }

    public void setAGI(int lvl) {
        this.AGI = 5 * lvl;
    }

    public void setDEF(int lvl) {
        this.DEF = 5 * lvl;
    }
    
    public void setRES(int lvl) {
        this.RES = 5 * lvl;
    }

    public void setStaminaStat(int lvl) {
        this.staminaStat = 5 * lvl;
    }

    public void setManaStat(int lvl) {
        this.manaStat = 5 * lvl;
    }

    public void setHealthStat(int lvl) {
        this.healthStat = 5 * lvl;
    }
}
