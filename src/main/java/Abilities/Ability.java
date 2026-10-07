public interface Ability {
    public String getName();
    public Element getElement();
    public double getMultiplier();

    public int getTargetCount();
    public DamageType getDamageType();
    public int getStaminaCost();
    public int getManaCost();
}