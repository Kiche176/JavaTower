package Abilities.Utilities;

import Abilities.*;
import DamageSystems.Elements.*;

public class Utility implements Ability { // SELF-TARGETING ABILITIES
    protected String name;
    protected Element element;
    protected double multiplier;

    protected int targetCount = 0;
    protected DamageType damageType = null;
    protected int staminaCost;
    protected int manaCost;

    public Utility(String name, Element element, double multiplier, int manaCost) {
        this.name = name;
        this.element = element;
        this.multiplier = multiplier;
        this.manaCost = manaCost;
    }

    public String getName() { return this.name; }
    public Element getElement() { return this.element; }
    public double getMultiplier() { return this.multiplier; }

    public int getTargetCount() { return this.targetCount; }
    public DamageType getDamageType() { return this.damageType; }
    public int getStaminaCost() { return this.staminaCost; }
    public int getManaCost() { return this.manaCost; }

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