package StatInterfaces;

import java.util.Set;

import DamageSystems.Afflictions.*;
import Abilities.*;
import Abilities.Skills.*;
import Abilities.Spells.*;
import Abilities.Utilities.*;
import Weapons.*;

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


    public Set<Ability> getAbilities();
    public Set<Skill> getSkills();
    public Set<Spell> getSpells();
    public Set<Utility> getUtilities();
    
    public Affliction getAffliction();
    public Weapon getWeapon();
}