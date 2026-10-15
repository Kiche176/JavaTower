package Abilities.Utilities;

import Abilities.*;
import DamageSystems.Elements.*;

public class Utility extends Ability { // SELF-TARGETING ABILITIES

    public Utility(String name, Element element, double multiplier, int staminaCost, int manaCost) {
        this.name = name;
        this.element = element;
        this.multiplier = multiplier;
        this.staminaCost = staminaCost;
        this.manaCost = manaCost;
    }

    @Override 
    public boolean equals(Object obj) {
        if (!(obj instanceof Utility)) return false;
        Utility utility = (Utility) obj;
        return this.name.equals(utility.getName()) 
            && this.element == utility.getElement() 
            && this.multiplier == utility.getMultiplier()

            && this.targetCount == utility.getTargetCount()
            && this.damageType == utility.getDamageType()
            && this.staminaCost == utility.getStaminaCost()
            && this.manaCost == utility.getManaCost();
    }
}