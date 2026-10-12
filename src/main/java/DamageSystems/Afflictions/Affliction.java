package DamageSystems.Afflictions;

import DamageSystems.Elements.*;

public enum Affliction {
    // BASIC AFFLICTIONS
    PHYSICAL(null, AfflictionType.BASIC, 0, 0, null, 0, 0, 0, 0, 0),
    HOT("HOT", AfflictionType.BASIC, 0, 0, null, 0, 0, 0, 0, 0),
    WET("WET", AfflictionType.BASIC, 0, 0, null, 0, 0, 0, 0, 0),
    COLD("COLD", AfflictionType.BASIC, 0, 0, null, 0, 0, 0, 0, 0),
    ROCKY("ROCKY", AfflictionType.BASIC, 0, 0, null, 0, 0, 0, 0, 0),
    BREEZED(null, AfflictionType.BASIC, 0, 0, null, 0, 0, 0, 0, 0),
    CHARGED("CHARGED", AfflictionType.BASIC, 0, 0, null, 0, 0, 0.2, 0, 0.15),

    // DOUBLE AFFLICTIONS
    BURNING("BURN", AfflictionType.DOUBLE, 3, 0.2, Element.FIRE, 0, 0, 0, 0.2, 0),
    DROWNING("DROWNING", AfflictionType.DOUBLE, 0, 0, null, 0, 0, 0.2, 0, 0),
    FROSTY("FROST", AfflictionType.DOUBLE, 3, 0.1, Element.ICE, 0, 0, 0.2, 0, 0),
    STUNNED("STUN", AfflictionType.DOUBLE, 0, 0, null, 0, 0, 0.3, 0, 0.3),

    // ADVANCED AFFLICTIONS
    FREEZING("FROZEN", AfflictionType.ADVANCED, 0, 0, null, 0, 0, 0.5, 0, 0),
    SWAMPED("SWAMPED", AfflictionType.ADVANCED, 0, 0, null, 0, 0, 0.2, 0, 0.2),
    ELECTRIFIED("ELECTRIFY", AfflictionType.ADVANCED, 3, 0.2, Element.THUNDER, 0, 0, 0, 0.2, 0),
    HARDENED("HARDEN", AfflictionType.ADVANCED, 0, 0, null, 0, 0, 0.7, -0.3, -0.3),

    // HIDDEN 
    SUSTAIN(null, AfflictionType.BASIC, 0, 0, null, 0, 0, 0, 0, 0);

    private final String name;
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

    private Affliction(
        String name,
        AfflictionType afflictionType, int ticks, double tickMultiplier, Element tickElement, 
        double debuffSTR, double debuffART, double debuffAGI, double debuffDEF, double debuffRES
    ) {
        this.name = name;
        this.afflictionType = afflictionType;
        this.ticks = ticks;
        this.tickMultiplier = tickMultiplier;
        this.tickElement = tickElement;
        
        this.debuffSTR = debuffSTR;
        this.debuffART = debuffART;
        this.debuffAGI = debuffAGI;
        this.debuffDEF = debuffDEF;
        this.debuffRES = debuffRES;
    }

    public String getName() {
        return this.name;
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