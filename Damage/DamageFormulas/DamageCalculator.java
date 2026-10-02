public class DamageCalcuator {
    private double damageFormula(
        Combatant attacker, Weapon attackingWeapon, double attackerStat, double attackingWeaponStat, 
        Combatant defender, Weapon defensiveWeapon, double defenderStat, double defensiveWeaponStat,
        Move move) {

        int weaponAtk = (attackingWeapon != null) ? attackingWeaponStat : 0;
        double atkStat = attackerStat + weaponAtk;
        
        double atkLVLMultiplier = attacker.getLVL() / 10.0;
        double atkMultiplier = moveMultiplier + lvlMultiplier;
        if (attackingWeapon.getElementalAffinity() == move.getElement()) atkLVLMultiplier += 0.5;
        if (attackingWeapon.getDamageAffinity() == move.getDamageType()) atkLVLMultiplier += 0.5;


        int weaponDef = (defensiveWeapon != null) ? defensiveWeaponStat : 0;
        double defStat = defenderStat + weaponDef;

        double defLVLMultiplier = defender.getLVL() / 10.0;
        double defMultiplier = 1.0 + defLVLMultiplier;
        
        if (defensiveWeapon.getDamageTypeResistances().contains(move.getDamageType())) defMultiplier += 0.5;
        else if (move.getDamageType() = DamageType.PIERCE) defMultiplier -= 0.5;

        double defMultiplier = 1 + defender.getLVL() / 10.0;
        double finalDamage = (atkStat * atkMultiplier) - (defStat * defMultiplier);
        double minDamage = (atkStat * 0.2);

        if (defender.getJob() == Job.TANK) {
            return (finalDamage > 0) ? finalDamage : 0;
        } else {
            return (finalDamage > minDamage) ? finalDamage : minDamage;
        }

    }

    private double physDamage(Combatant att, Combatant def, Move move) {
        return calcuateDamage(
            att, att.getWeapon(), 
            att.getSTR(), att.getWeapon().getSTR(), 
            def, def.getWeapon(),
            def.getDEF(), def.getWeapon().getDEF(),
            move);
    }

    private double artsDamage(Combatant att, Combatant def, Move move) {
        return calcuateDamage(
            att, att.getWeapon(), 
            att.getART(), att.getWeapon().getART(), 
            def, def.getWeapon(),
            def.getRES(), def.getWeapon().getRES(),
            move);
    }

    public double calcuateDamage(Combatant att, Combatant def, Move move) {
        if (move.getElement() == Element.PHYSICAL) return physDamage(att, def, move);
        else return artsDamage(att, def, move);
    }
}

