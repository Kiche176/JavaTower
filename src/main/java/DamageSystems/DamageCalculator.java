package DamageSystems;

import Abilities.Ability;
import Combatants.Combatant;
import Combatants.Job;
import DamageSystems.Elements.*;
import DamageSystems.Reactions.*;
import Weapons.*;

public class DamageCalculator {
    // NORMAL DAMAGE
    private static double damageFormula(
        Combatant attacker, Weapon attackingWeapon, double attackerStat, double weaponAtk, 
        Combatant defender, Weapon defensiveWeapon, double defenderStat, double weaponDef,
        Ability ability
    ) {
        // OFFENSIVE CALCULATIONS
        double atkStat = attackerStat + weaponAtk;
        
        double atkLVLMultiplier = attacker.getLVL() / 10.0;
        double atkMultiplier = ability.getMultiplier() + atkLVLMultiplier;

        if (attackingWeapon.getElementalAffinities().contains(ability.getElement())) atkMultiplier += 0.5;
        if (attackingWeapon.getDamageAffinities().contains(ability.getDamageType())) atkMultiplier += 0.5;

        // REACTIONS
        Reaction rct = ReactionData.returnReaction(defender.getAffliction(), ability.getElement().getAffliction());
        if (rct != null) atkMultiplier *= rct.getMultiplier();


        // DEFENSIVE CALCULATIONS
        double defStat = defenderStat + weaponDef;

        double defLVLMultiplier = defender.getLVL() / 10.0;
        double defMultiplier = 1.0 + defLVLMultiplier; 

        if (defensiveWeapon.getElementalResistances().contains(ability.getElement())) defMultiplier += 0.5;
        if (defensiveWeapon.getDamageResistances().contains(ability.getDamageType())) defMultiplier += 0.5;

        // FINAL CALCULATIONS
        double finalDamage = (atkStat * atkMultiplier) - (defStat * defMultiplier);
        double minDamage = (atkStat * 0.2);

        if (defender.getJob() == Job.TANK) {
            return (finalDamage > 0) ? finalDamage : 0;
        } else {
            return (finalDamage > minDamage) ? finalDamage : minDamage;
        }
    }

    private static double physDamage(Combatant att, Combatant def, Ability ability) {
        return damageFormula(
            att, att.getWeapon(), 
            att.getSTR(), att.getWeapon().getSTR(), 
            def, def.getWeapon(),
            def.getDEF(), def.getWeapon().getDEF(),
            ability);
    }

    private static double artsDamage(Combatant att, Combatant def, Ability ability) {
        return damageFormula(
            att, att.getWeapon(), 
            att.getART(), att.getWeapon().getART(), 
            def, def.getWeapon(),
            def.getRES(), def.getWeapon().getRES(),
            ability);
    }

    public static double calculateDamage(Combatant att, Combatant def, Ability ability) {
        if (ability.getElement() == Element.PHYSICAL) return physDamage(att, def, ability);
        else return artsDamage(att, def, ability);
    }

    // TICK DAMAGE
    private static double tickDamageFormula(
        Combatant attacker, Weapon attackingWeapon, double attackerStat, double weaponAtk, 
        Combatant defender, Weapon defensiveWeapon, double defenderStat, double weaponDef,
        Element element
    ) {
        // OFFENSIVE CALCULATIONS
        double atkStat = attackerStat + weaponAtk / 2;
        
        double atkLVLMultiplier = attacker.getLVL() / 10.0;
        double atkMultiplier = defender.getAffliction().getTickMultiplier() + atkLVLMultiplier;
        if (attackingWeapon.getElementalAffinities().contains(element)) atkMultiplier += 0.5;

        // DAMAGE TICKS CANNOT APPLY REACTIONS INHERENTLY

        // DEFENSIVE CALCULATIONS
        double defStat = defenderStat + weaponDef;

        double defLVLMultiplier = defender.getLVL() / 10.0;
        double defMultiplier = 1.0 + defLVLMultiplier;
        if (defensiveWeapon.getElementalResistances().contains(element)) defMultiplier += 0.5;

        // FINAL CALCULATIONS
        double finalDamage = (atkStat * atkMultiplier) - (defStat * defMultiplier);
        double minDamage = (atkStat * 0.2);

        if (defender.getJob() == Job.TANK) {
            return (finalDamage > 0) ? finalDamage : 0;
        } else {
            return (finalDamage > minDamage) ? finalDamage : minDamage;
        }
    }

    private static double physTickDamage(Combatant att, Combatant def) {
        return tickDamageFormula(
            att, att.getWeapon(), 
            att.getSTR(), att.getWeapon().getSTR(), 
            def, def.getWeapon(),
            def.getDEF(), def.getWeapon().getDEF(),
            def.getTickElement());
    }

    private static double artsTickDamage(Combatant att, Combatant def) {
        return tickDamageFormula(
            att, att.getWeapon(), 
            att.getART(), att.getWeapon().getART(), 
            def, def.getWeapon(),
            def.getRES(), def.getWeapon().getRES(),
            def.getTickElement());
    }

    public static double calculateTickDamage(Combatant att, Combatant def) {
        if (def.getTickElement() == Element.PHYSICAL) return physTickDamage(att, def);
        else return artsTickDamage(att, def);
    }

    // AREA DAMAGE
    private static double areaDamageFormula(
        Combatant attacker, Weapon attackingWeapon, double attackerStat, double weaponAtk, 
        Combatant defender, Weapon defensiveWeapon, double defenderStat, double weaponDef,
        Ability ability
    ) {
        // OFFENSIVE CALCULATIONS
        double atkStat = attackerStat + weaponAtk;
        
        double atkLVLMultiplier = attacker.getLVL() / 10.0;
        double atkMultiplier = ability.getMultiplier() / 2 + atkLVLMultiplier;
        if (attackingWeapon.getElementalAffinities().contains(ability.getElement())) atkMultiplier += 0.5;
        if (attackingWeapon.getDamageAffinities().contains(ability.getDamageType())) atkMultiplier += 0.5;

        // REACTIONS
        Reaction rct = ReactionData.returnReaction(defender.getAffliction(), ability.getElement().getAffliction());
        if (rct != null) atkMultiplier *= rct.getMultiplier();

        // DEFENSIVE CALCULATIONS
        double defStat = defenderStat + weaponDef;

        double defLVLMultiplier = defender.getLVL() / 10.0;
        double defMultiplier = 1.0 + defLVLMultiplier; 
        if (defensiveWeapon.getElementalResistances().contains(ability.getElement())) defMultiplier += 0.5;
        if (defensiveWeapon.getDamageResistances().contains(ability.getDamageType())) defMultiplier += 0.5;

        // FINAL CALCULATIONS
        double finalDamage = (atkStat * atkMultiplier) - (defStat * defMultiplier);
        double minDamage = (atkStat * 0.2);

        if (defender.getJob() == Job.TANK) {
            return (finalDamage > 0) ? finalDamage : 0;
        } else {
            return (finalDamage > minDamage) ? finalDamage : minDamage;
        }
    }

    private static double physAreaDamage(Combatant att, Combatant def, Ability ability) {
        return areaDamageFormula(
            att, att.getWeapon(), 
            att.getSTR(), att.getWeapon().getSTR(), 
            def, def.getWeapon(),
            def.getDEF(), def.getWeapon().getDEF(),
            ability);
    }

    private static double artsAreaDamage(Combatant att, Combatant def, Ability ability) {
        return areaDamageFormula(
            att, att.getWeapon(), 
            att.getART(), att.getWeapon().getART(), 
            def, def.getWeapon(),
            def.getRES(), def.getWeapon().getRES(),
            ability);
    }

    public static double calculateAreaDamage(Combatant att, Combatant def, Ability ability) {
        if (ability.getElement() == Element.PHYSICAL) return physAreaDamage(att, def, ability);
        else return artsAreaDamage(att, def, ability);
    }
}

