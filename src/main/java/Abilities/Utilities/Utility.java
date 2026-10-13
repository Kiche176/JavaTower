package Abilities.Utilities;

import Abilities.*;
import DamageSystems.Elements.*;

public class Utility extends Ability { // SELF-TARGETING ABILITIES

    public Utility(String name, Element element, double multiplier, int manaCost) {
        this.name = name;
        this.element = element;
        this.multiplier = multiplier;
        this.manaCost = manaCost;
    }

    @Override 
    public boolean equals(Object obj) {
        if (!(obj instanceof Utility)) return false;
        Utility skill = (Utility) obj;
        return (
            this.name == skill.getName() &&
            this.element == skill.getElement() &&
            this.multiplier == skill.getMultiplier() &&

            this.targetCount == skill.getTargetCount() &&
            this.damageType == skill.getDamageType() &&
            this.staminaCost == skill.getStaminaCost() &&
            this.manaCost == skill.getManaCost()
        );
    }
}