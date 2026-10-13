package Abilities;

import DamageSystems.Elements.*;

public abstract class Ability {
    protected String name;
    protected Element element;
    protected double multiplier;

    protected int targetCount;
    protected DamageType damageType;
    protected int staminaCost;
    protected int manaCost;


    public String getName() { return this.name; }
    public Element getElement() { return this.element; }
    public double getMultiplier() { return this.multiplier; }

    public int getTargetCount() { return this.targetCount; }
    public DamageType getDamageType() { return this.damageType; }
    public int getStaminaCost() { return this.staminaCost; }
    public int getManaCost() { return this.manaCost; }

}