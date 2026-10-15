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
        Spell spell = (Spell) obj;
        return (
            this.name.equals(spell.getName()) &&
            this.element == spell.getElement() &&
            this.multiplier == spell.getMultiplier() &&

            this.targetCount == spell.getTargetCount() &&
            this.damageType == spell.getDamageType() &&
            this.staminaCost == spell.getStaminaCost() &&
            this.manaCost == spell.getManaCost()
        );
    }
}