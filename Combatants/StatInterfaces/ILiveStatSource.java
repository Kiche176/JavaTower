public interface ILiveStatSource extends IStatSource {
    public int getLVL();
    
    public int getStaminaStat();
    public int getManaStat();
    public int getHealthStat();

    public int getMaxStamina();
    public int getMaxMana();
    public int getMaxHealth();

    public int getCurrentStamina();
    public int getCurrentMana();
    public int getCurrentHealth();    


    public Set<Ablity> getAbilities();
    public Set<Skill> getSkills();
    public Set<Spell> getSpells();
    
    public Affliciton getAffliction();
    public Weapon getWeapon();
}