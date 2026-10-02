public class DamageCalcuator {
    // NORMAL DAMAGE
    private double damageFormula(
        Combatant attacker, Weapon attackingWeapon, double attackerStat, double attackingWeaponStat, 
        Combatant defender, Weapon defensiveWeapon, double defenderStat, double defensiveWeaponStat,
        Move move) {
        
        // OFFENSIVE CALCULATIONS
        int weaponAtk = (attackingWeapon != null) ? attackingWeaponStat : 0;
        double atkStat = attackerStat + weaponAtk;
        
        double atkLVLMultiplier = attacker.getLVL() / 10.0;
        double atkMultiplier = moveMultiplier + atkLVLMultiplier;
        if (attackingWeapon.getElementalAffinities().contains(move.getElement())) atkLVLMultiplier += 0.5;
        if (attackingWeapon.getDamageAffinities().contains(move.getDamageType())) atkLVLMultiplier += 0.5;

        // DEFENSIVE CALCULATIONS
        int weaponDef = (defensiveWeapon != null) ? defensiveWeaponStat : 0;
        double defStat = defenderStat + weaponDef;

        double defLVLMultiplier = defender.getLVL() / 10.0;
        double defMultiplier = 1.0 + defLVLMultiplier; 
        if (defensiveWeapon.getElementalResistances().contains(move.getElement())) defMultiplier += 0.5;
        if (defensiveWeapon.getDamageResistances().contains(move.getDamageType())) defMultiplier += 0.5;

        // FINAL CALCULATIONS
        double finalDamage = (atkStat * atkMultiplier) - (defStat * defMultiplier);
        double minDamage = (atkStat * 0.2);

        if (defender.getJob() == Job.TANK) {
            return (finalDamage > 0) ? finalDamage : 0;
        } else {
            return (finalDamage > minDamage) ? finalDamage : minDamage;
        }
    }

    private double physDamage(Combatant att, Combatant def, Move move) {
        return damageFormula(
            att, att.getWeapon(), 
            att.getSTR(), att.getWeapon().getSTR(), 
            def, def.getWeapon(),
            def.getDEF(), def.getWeapon().getDEF(),
            move);
    }

    private double artsDamage(Combatant att, Combatant def, Move move) {
        return damageFormula(
            att, att.getWeapon(), 
            att.getART(), att.getWeapon().getART(), 
            def, def.getWeapon(),
            def.getRES(), def.getWeapon().getRES(),
            move);
    }

    public static double calcuateDamage(Combatant att, Combatant def, Move move) {
        if (move.getElement() == Element.PHYSICAL) return physDamage(att, def, move);
        else return artsDamage(att, def, move);
    }

    // TICK DAMAGE
    private double tickDamageFormula(
        Combatant attacker, Weapon attackingWeapon, double attackerStat, double attackingWeaponStat, 
        Combatant defender, Weapon defensiveWeapon, double defenderStat, double defensiveWeaponStat,
        Affliction affliction) {

        // OFFENSIVE CALCULATIONS
        int weaponAtk = (attackingWeapon != null) ? attackingWeaponStat : 0;
        double atkStat = attackerStat + weaponAtk / 2;
        
        double atkLVLMultiplier = attacker.getLVL() / 10.0;
        double atkMultiplier = affliction.getTickMultiplier() + atkLVLMultiplier;
        if (attackingWeapon.getElementalAffinities().contains(affliction.getElement())) atkLVLMultiplier += 0.5;

        // DEFENSIVE CALCULATIONS
        int weaponDef = (defensiveWeapon != null) ? defensiveWeaponStat : 0;
        double defStat = defenderStat + weaponDef;

        double defLVLMultiplier = defender.getLVL() / 10.0;
        double defMultiplier = 1.0 + defLVLMultiplier;
        if (defensiveWeapon.getElementalResistances().contains(affliction.getElement())) defMultiplier += 0.5;

        // FINAL CALCULATIONS
        double finalDamage = (atkStat * atkMultiplier) - (defStat * defMultiplier);
        double minDamage = (atkStat * 0.2);

        if (defender.getJob() == Job.TANK) {
            return (finalDamage > 0) ? finalDamage : 0;
        } else {
            return (finalDamage > minDamage) ? finalDamage : minDamage;
        }
    }

    private double physTickDamage(Combatant att, Combatant def) {
        return tickDamageFormula(
            att, att.getWeapon(), 
            att.getSTR(), att.getWeapon().getSTR(), 
            def, def.getWeapon(),
            def.getDEF(), def.getWeapon().getDEF(),
            def.getTickAffliction());
    }

    private double artsTickDamage(Combatant att, Combatant def) {
        return tickDamageFormula(
            att, att.getWeapon(), 
            att.getART(), att.getWeapon().getART(), 
            def, def.getWeapon(),
            def.getRES(), def.getWeapon().getRES(),
            def.getTickAffliction());
    }

    public static double calcuateTickDamage(Combatant att, Combatant def) {
        if (move.getElement() == Element.PHYSICAL) return physTickDamage(att, def);
        else return artsTickDamage(att, def);
    }

    // AREA DAMAGE
    private double areaDamageFormula(
        Combatant attacker, Weapon attackingWeapon, double attackerStat, double attackingWeaponStat, 
        Combatant defender, Weapon defensiveWeapon, double defenderStat, double defensiveWeaponStat,
        Move move) {
        
        // OFFENSIVE CALCULATIONS
        int weaponAtk = (attackingWeapon != null) ? attackingWeaponStat : 0;
        double atkStat = attackerStat + weaponAtk;
        
        double atkLVLMultiplier = attacker.getLVL() / 10.0;
        double atkMultiplier = moveMultiplier / 2 + atkLVLMultiplier;
        if (attackingWeapon.getElementalAffinities().contains(move.getElement())) atkLVLMultiplier += 0.5;
        if (attackingWeapon.getDamageAffinities().contains(move.getDamageType())) atkLVLMultiplier += 0.5;

        // DEFENSIVE CALCULATIONS
        int weaponDef = (defensiveWeapon != null) ? defensiveWeaponStat : 0;
        double defStat = defenderStat + weaponDef;

        double defLVLMultiplier = defender.getLVL() / 10.0;
        double defMultiplier = 1.0 + defLVLMultiplier; 
        if (defensiveWeapon.getElementalResistances().contains(move.getElement())) defMultiplier += 0.5;
        if (defensiveWeapon.getDamageResistances().contains(move.getDamageType())) defMultiplier += 0.5;

        // FINAL CALCULATIONS
        double finalDamage = (atkStat * atkMultiplier) - (defStat * defMultiplier);
        double minDamage = (atkStat * 0.2);

        if (defender.getJob() == Job.TANK) {
            return (finalDamage > 0) ? finalDamage : 0;
        } else {
            return (finalDamage > minDamage) ? finalDamage : minDamage;
        }
    }

    private double physAreaDamage(Combatant att, Combatant def, Move move) {
        return areaDamageFormula(
            att, att.getWeapon(), 
            att.getSTR(), att.getWeapon().getSTR(), 
            def, def.getWeapon(),
            def.getDEF(), def.getWeapon().getDEF(),
            move);
    }

    private double artsAreaDamage(Combatant att, Combatant def, Move move) {
        return areaDamageFormula(
            att, att.getWeapon(), 
            att.getART(), att.getWeapon().getART(), 
            def, def.getWeapon(),
            def.getRES(), def.getWeapon().getRES(),
            move);
    }

    public static double calcuateAreaDamage(Combatant att, Combatant def, Move move) {
        if (move.getElement() == Element.PHYSICAL) return physAreaDamage(att, def, move);
        else return artsAreaDamage(att, def, move);
    }
}

