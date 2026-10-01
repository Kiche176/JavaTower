public class Skill implements IAbility{
    protected String name;
    protected Element element;
    protected double multiplier;

    protected int targetCount;
    protected DamageType damageType;
    protected int staminaCost;
    protected int manaCost = 0;

    public Skill(String name, Element element, double multiplier, int targetCount, DamageType damageType, int staminaCost) {
        this.name = name;
        this.element = element;
        this.multiplier = multiplier;
        this.targetCount = targetCount;
        this.damageType = damageType;
        this.staminaCost = staminaCost;
    }

    public String getName() { return this.name; }
    public Element getElement() { return this.element; }
    public double getMultiplier() { return this.multiplier; }

    public int getTargetCount() { return this.targetCount; }
    public DamageType getDamageType() { return this.damageType; }
    public int getStaminaCost() { return this.staminaCost; }
    public int getManaCost() { return this.manaCost; }
}
