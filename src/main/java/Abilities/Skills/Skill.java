package Abilities.Skills;

import Abilities.*;
import DamageSystems.Elements.*;

public class Skill extends Ability {

    public Skill(String name, Element element, double multiplier, int targetCount, DamageType damageType, int staminaCost) {
        this.name = name;
        this.element = element;
        this.multiplier = multiplier;
        this.targetCount = targetCount;
        this.damageType = damageType;
        this.staminaCost = staminaCost;
    }

    @Override 
    public boolean equals(Object obj) {
        if (!(obj instanceof Skill)) return false;
        Skill skill = (Skill) obj;
        return this.name.equals(skill.getName()) 
            && this.element == skill.getElement() 
            && this.multiplier == skill.getMultiplier()

            && this.targetCount == skill.getTargetCount()
            && this.damageType == skill.getDamageType()
            && this.staminaCost == skill.getStaminaCost()
            && this.manaCost == skill.getManaCost();
    }
}
