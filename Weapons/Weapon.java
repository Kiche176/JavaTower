import java.util.HashSet;
import java.util.Set;

public class Weapon implements IStatSource {
    private String name;
    private Set<Element> elementalAffinities;
    private Set<DamageType> damageAffinities;
    private Set<Element> elementalResistances;
    private Set<DamageType> damageResistances;

    private int STR;
    private int ART;
    private int AGI;
    private int DEF;
    private int RES;

    private int requiredSTR;
    private int requiredART;
    private int requiredAGI;
    private int requiredDEF;
    private int requiredRES;

    public Weapon(
        String name,
        Set<Element> elementalAffinities, Set<DamageType> damageAffinities,
        Set<Element> elementalResistances, Set<DamageType> damageResistances,
        int STR, int ART, int AGI, int DEF, int RES,
        int requiredSTR, int requiredART, int requiredAGI, int requiredDEF, int requiredRES
    ) {
        this.name = name;
        this.elementalAffinities = elementalAffinities;
        this.damageAffinities = damageAffinities;
        this.elementalResistances = elementalResistances;
        this.damageResistances = damageResistances;
        this.abilities = abilities;

        this.STR = STR;
        this.ART = ART;
        this.AGI = AGI;
        this.DEF = DEF;
        this.RES = RES;

        this.requiredSTR = requiredSTR;
        this.requiredART = requiredART;
        this.requiredAGI = requiredAGI;
        this.requiredDEF = requiredDEF;
        this.requiredRES = requiredRES;
    }

    public String getName() { return this.name; }
    public Set<Element> getElementalAffinities() { return this.elementalAffinities; }
    public Set<DamageType> getDamageAffinities() { return this.damageAffinities; }
    public Set<Element> getElementalResistances() { return this.elementalResistances; }
    public Set<DamageType> getDamageResistances() { return this.damageResistances; }

    public int getSTR() { return this.STR; }
    public int getART() { return this.ART; }
    public int getAGI() { return this.AGI; }
    public int getDEF() { return this.DEF; }
    public int getRES() { return this.RES; }

    public int getRequiredSTR() { return this.requiredSTR; }
    public int getRequiredART() { return this.requiredART; }
    public int getRequiredAGI() { return this.requiredAGI; }
    public int getRequiredDEF() { return this.requiredDEF; }
    public int getRequiredRES() { return this.requiredRES; }

    @Override
    public boolean equals(Object obj) {
        if (!obj instanceof Weapon) return false;
        Weapon w = (Weapon) obj;
        return w.getName().equals(this.name);
    }
}