package Stage;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.StringBuilder;

import Abilities.Ability;
import Abilities.Skills.*;
import Abilities.Spells.*;
import Abilities.Utilities.*;
import Combatants.Combatant;
import Combatants.Players.Player;
import DamageSystems.Afflictions.*;
import DamageSystems.Elements.Element;
import DamageSystems.Reactions.*;
import DamageSystems.*;


public class Battle {
    private Scanner scanner;

    public Battle() {
        this.scanner = new Scanner(System.in);
    }

    private void speechLine(String statement) {
        System.out.print(statement + " [PRESS ENTER]");
        this.scanner.nextLine();
    }

    private void battleIntro(Player player, List<Combatant> enemies) {
        if (enemies.size() == 1) {
            speechLine("There is 1 enemy.");
        } else {
            speechLine("There are " + enemies.size() + " enemies.");
        }

        for (Combatant enemy : enemies) {
            speechLine(
                "Lv." + enemy.getLVL() + " " + enemy.getName() +
                " has " + enemy.getCurrentHealth() + " HP remaining."
            );
        }
    }

    private void statusStatement(Ability ability, Combatant combatant) {
        if (ability.getElement() == Element.PHYSICAL) return;

        Reaction rct = ReactionData.returnReaction(combatant.getAffliction(), ability.getElement().getAffliction());
        Affliction aff = ReactionData.returnAffliction(combatant.getAffliction(), ability.getElement().getAffliction());
        if (rct != null) {
            speechLine(combatant.getName() + " has been " + rct.getName());
        } else if (aff != null) {
            speechLine(combatant.getName() + " has been afflicted with " + aff.getName());
        }
    }

    private void statusDoTStatement(Combatant combatant) {
        Reaction rct = ReactionData.returnReaction(combatant.getAffliction(), combatant.getTickElement().getAffliction());
        Affliction aff = ReactionData.returnAffliction(combatant.getAffliction(), combatant.getTickElement().getAffliction());
        if (rct != null) {
            speechLine(combatant.getName() + " has been " + rct.getName());
        } else {
            speechLine(combatant.getName() + " has been afflicted with " + aff.getName());
        }
    }

    private void updateDoT(Player player, List<Combatant> enemies) {
        List<Integer> toRemove = new ArrayList<>();
        for (int i = 0; i < enemies.size(); i++) {
            Combatant enemy = enemies.get(i);

            if (enemy.getTicks() > 0) {
                int damageDealt = (int) DamageCalculator.calculateTickDamage(player, enemy);
                statusDoTStatement(enemy);
                enemy.reduceHealth(damageDealt);
                speechLine(enemy.getName() + " has " + enemy.getCurrentHealth() + " HP remaining.");

                if (enemy.getCurrentHealth() == 0) {
                    // THEY DIE
                    speechLine(enemy.getName().toUpperCase() + " has been defeated.");
                    toRemove.add(i);
                }

                // check if they have no ticks left
                enemy.decrementTicks();
                if (enemy.getTicks() == 0) enemy.setTickElement(null);
            }
        }
        for (int i = toRemove.size() - 1; i >= 0; i--) {
            enemies.remove(toRemove.get(i));
        }
    }


    // ABILITY SELECTION

    private Ability selectPlayerAbility(Player player) {
        Ability ability = null;
        while (ability == null) {
            System.out.println("Enter ability");
            String s = this.scanner.nextLine();
            
            for (Ability a : player.getAbilities()) {
                if (s.toLowerCase().equals(a.getName().toLowerCase())) {
                    ability = a;
                    if (player.getCurrentStamina() >= ability.getStaminaCost()) {
                        speechLine("You do not have enough stamina to use this ability");
                    } 
                    else if (player.getCurrentMana() >= ability.getManaCost()) {
                        speechLine("You do not have enough mana to use this ability");
                    } 
                    else {
                        return ability;
                    }
                }
            }
        }
        return null;
    }


    // TARGET SELECTION

    private Combatant selectPlayerTarget(List<Combatant> enemies) {

        StringBuilder enemyList = new StringBuilder("The enemies are: ");
        for (int i = 0; i < enemies.size(); i++) {
            enemyList.append(
                "[" + (i+1) + "] " + enemies.get(i).getName() + 
                " with " + enemies.get(i).getCurrentHealth() + " HP remaining. "
            );
        }

        while (true) {
            speechLine(enemyList.toString());
            System.out.print("Please enter the number of the enemy you wish to target: ");
            String ans = this.scanner.nextLine();

            int i;
            try {
                i = Integer.parseInt(ans);
            }
            catch (NumberFormatException e) {
                speechLine("Please enter a valid Integer");
                continue;
            }

            if (i < 1) {
                speechLine("Please enter a positive Integer");
                continue;
            } else if (i > enemies.size()) {
                speechLine("Please enter a valid Integer");
                continue;
            }
            return enemies.get(i - 1);
        }
    }

    /**
     * @param combatants - In this case the list of combatants is a duplicate list missing the primary target as 
     *                      they take the full force of the attack rather than the AoE.
     * @param ability    - The AoE ability used.
     * 
     * @return The list of selected 
     */
    private List<Combatant> selectAreaTargets(List<Combatant> combatants, Ability ability) {
        if (ability.getTargetCount() - 1 >= combatants.size()) {
            return combatants;
        }

        List<Combatant> ret = new ArrayList<>();
        Random rand = new Random();

        for (int i = 1; i < ability.getTargetCount(); i++) {
            int j = rand.nextInt(combatants.size());
            ret.add(combatants.get(j));
            combatants.remove(j);
        }
        return ret;
    }


    // ABILITY APPLICATION

    private boolean applySingleTargetAbility(Player player, Combatant target, Ability ability) {
        int dmg = (int) DamageCalculator.calculateDamage(player, target, ability);
        
        // apply damage
        boolean targetAlive = target.reduceHealth(dmg);
        speechLine(target.getName() + " has " + target.getCurrentHealth() + " health left.");
        if (!targetAlive) {
            speechLine(target.getName() + " has been defeated."); 
            return false;
        }
        
        // apply affliction
        Affliction aff = ReactionData.returnAffliction(target.getAffliction(), ability.getElement().getAffliction());
        target.setAffliction(aff);
        if (!(aff == null)) speechLine(target.getName() + " has been afflicted with " + aff.getName());
        return true;
    }

    private boolean applyAoE(Player player, Combatant target, Ability ability) {
        int dmg = (int) DamageCalculator.calculateAreaDamage(player, target, ability);
        
        // apply damage
        boolean targetAlive = target.reduceHealth(dmg);
        speechLine(target.getName() + " has " + target.getCurrentHealth() + " health left.");
        if (!targetAlive) {
            speechLine(target.getName() + " + has been defeated."); 
            return false;
        }
        
        // apply affliction
        Affliction aff = ReactionData.returnAffliction(target.getAffliction(), ability.getElement().getAffliction());
        target.setAffliction(aff);
        if (!(aff == null)) speechLine(target.getName() + " has been afflicted with " + aff.getName());
        return true;
    }

    private void applyAreaAbility(Player player, Combatant mainTarget, List<Combatant> targets, Ability ability) {
        List<Combatant> excluded = new ArrayList<>(targets);
        excluded.remove(mainTarget);
        List<Combatant> areaTargets = selectAreaTargets(excluded, ability);
        List<Integer> toRemove = new ArrayList<>();

        for (int i = 0; i < areaTargets.size(); i++) {
            Combatant enemy = areaTargets.get(i);
            boolean enemyAlive = applyAoE(player, enemy, ability);
            if (!enemyAlive) toRemove.add(i); // the enemy died and needs to be removed from the list of combatants
        }

        for (int i = toRemove.size() - 1; i >= 0; i--) {
            Combatant defeated = areaTargets.get(toRemove.get(i));
            targets.remove(defeated);
        }
    }

    private void applyUtility(Combatant c, Utility u) {
        if (u.equals(UtilityTypes.MEDITATE)) { c.restoreMana((int) (c.getART() * u.getMultiplier())); }
        else if (u.equals(UtilityTypes.HEAL)) { c.restoreHealth((int) (c.getART() * u.getMultiplier())); }
        else if (u.equals(UtilityTypes.GREAT_HEAL)) { c.restoreHealth((int) (c.getART() * u.getMultiplier())); }
    }
    
    private void applyAbility(Player player, Combatant target, List<Combatant> opponents, Ability ability) {
        applySingleTargetAbility(player, target, ability);
        if (ability.getTargetCount() > 1) applyAreaAbility(player, target, opponents, ability);
    }


    // BATTLE

    public void battle(Player player, List<Combatant> enemies) {
        boolean playerTurn = true;
        battleIntro(player, enemies);

        while (enemies.size() > 0 && player.getCurrentHealth() > 0) {
            
            // PLAYER'S TURN
            if (playerTurn) {

                // CHECK ENEMY DOT TICKS
                updateDoT(player, enemies);
                if (enemies.size() == 0) continue;


                // Select an ability (extra stuff if aoe)
                Ability ability = selectPlayerAbility(player);


                // Select a target
                if (ability instanceof Utility) {
                    applyUtility(player, ability);
                } else {
                    Combatant target = selectPlayerTarget(enemies);
                    applyAbility(player, target, enemies, ability);
                }



            } 
            else {
                continue;
            }
            playerTurn = !playerTurn;
        }

        speechLine("CONGRATULATIONS!");
    }
}