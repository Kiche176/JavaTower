public enum Affliction {
    // BASIC AFFLICTIONS
    PHYSICAL(AfflictionType.BASIC, 0, 0, null, 0, 0, 0, 0, 0),
    HOT(AfflictionType.BASIC, 0, 0, null 0, 0, 0, 0, 0),
    WET(AfflictionType.BASIC, 0, 0, null, 0, 0, 0, 0, 0),
    COLD(AfflictionType.BASIC, 0, 0, null, 0, 0, 0, 0, 0),
    ROCKY(AfflictionType.BASIC, 0, 0, null, 0, 0, 0, 0, 0),
    BREEZED(AfflictionType.BASIC, 0, 0, null, 0, 0, 0, 0, 0),
    CHARGED(AfflictionType.BASIC, 0, 0, null, 0, 0, 0.2, 0, 0.15),

    // DOUBLE AFFLICTIONS
    BURNING(AfflictionType.DOUBLE, 3, 0.2, Element.FIRE, 0, 0, 0, 0.2, 0),
    DROWNING(AfflictionType.DOUBLE, 0, 0, null, 0, 0, 0.2, 0, 0),
    FROSTY(AfflictionType.DOUBLE, 3, 0.1, Element.ICE, 0, 0, 0.2, 0, 0),
    STUNNED(AfflictionType.DOUBLE, 0, 0, null, 0, 0, 0.3, 0, 0.3),

    // ADVANCED AFFLICTIONS
    FREEZING(AfflictionType.ADVANCED, 0, 0, null, 0, 0, 0.5, 0, 0),
    SWAMPED(AfflictionType.ADVANCED, 0, 0, null, 0, 0, 0.2, 0, 0.2),
    ELECTRIFIED(AfflictionType.ADVANCED, 3, 0.2, Element.THUNDER, 0, 0, 0, 0.2, 0),
    HARDENED(AfflictionType.ADVANCED, 0, 0, null, 0, 0, 0.7, -0.3, -0.3),

    // HIDDEN 
    SUSTAIN(AfflictionType.BASIC, 0, 0, null, 0, 0, 0, 0, 0);

    private final AfflictionType afflictionType;
    private final int ticks;
    private final double tickMultiplier; // MULTIPLIER FOR DAMAGE TICKS
    private final Element tickElement; // THE ELEMENT APPLIED ON EACH TICK

    

    // THE PERCENTAGE OF THE STAT THAT IS TO BE REMOVED
    private final double debuffSTR;
    private final double debuffART;
    private final double debuffAGI;
    private final double debuffDEF;
    private final double debuffRES;

    public Affliction(
        AfflictionType afflictionType; int ticks, double tickMultiplier, Element tickElement, 
        double debuffSTR, double debuffART, double debuffAGI, double debuffDEF, double debuffRES
    ) {
        this.afflictionType = afflictionType;
        this.ticks = ticks;
        this.tickDamage = tickDamage;
        this.tickMultiplier = tickMultiplier;
        this.debuffSTR = debuffSTR;
        this.debuffART = debuffART;
        this.debuffAGI = debuffAGI;
        this.debuffDEF = debuffDEF;
        this.debuffRES = debuffRES;
    }

    public AfflictionType getAfflictionType() {
        return this.afflictionType;
    }

    public int getTicks() {
        return this.ticks;
    }

    public double getTickMultiplier() {
        return this.tickMultiplier;
    }

    public Element getTickElement() {
        return this.tickElement;
    }

    public double getDebuffSTR() {
        return this.debuffSTR;
    }

    public double getDebuffART() {
        return this.debuffART;
    }

    public double getDebuffAGI() {
        return this.debuffAGI;
    }

    public double getDebuffDEF() {
        return this.debuffDEF;
    }

    public double getDebuffRES() {
        return this.debuffRES;
    }
}