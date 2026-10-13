package Abilities.Spells;

import Abilities.*;
import DamageSystems.Elements.*;

public class Spell extends Ability {

    public Spell(String name, Element element, double multiplier, int targetCount, DamageType damageType, int manaCost) {
        this.name = name;
        this.element = element;
        this.multiplier = multiplier;
        this.targetCount = targetCount;
        this.damageType = damageType;
        this.manaCost = manaCost;
    }

    @Override 
    public boolean equals(Object obj) {
        if (!(obj instanceof Spell)) return false;
        Spell skill = (Spell) obj;
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