package Stage;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.StringBuilder;

import Abilities.Ability;
import Abilities.Utilities.*;
import Combatants.Combatant;
import Combatants.Players.Player;
import DamageSystems.Afflictions.*;
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

    private void enemyIntro(List<Combatant> enemies) {
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

    private void statusStatement(Combatant target, Affliction trigger) {
        
        Reaction rct = ReactionData.returnReaction(target.getAffliction(), trigger);
        Affliction aff = ReactionData.returnAffliction(target.getAffliction(), trigger);

        if (rct != null) {
            speechLine(target.getName() + " has been " + rct.getName());
        } else if (aff != null) {
            if (aff == Affliction.SUSTAIN) {
                speechLine(target.getName() + " is still afflicted with " + target.getAffliction());
            } else {
                speechLine(target.getName() + " has been afflicted with " + aff.getName());
            }
        }
    }

    private void updateDoT(Player player, List<Combatant> enemies) {
        List<Integer> toRemove = new ArrayList<>();
        for (int i = 0; i < enemies.size(); i++) {
            Combatant enemy = enemies.get(i);

            if (enemy.getTicks() > 0) {
                int damageDealt = (int) DamageCalculator.calculateTickDamage(player, enemy);
                statusStatement(enemy, enemy.getTickElement().getAffliction());
                enemy.reduceHealth(damageDealt);
                speechLine(enemy.getName() + " has " + enemy.getCurrentHealth() + " HP remaining.");

                if (enemy.getCurrentHealth() == 0) {
                    // THEY DIE
                    speechLine(enemy.getName().toUpperCase() + " has been defeated.");
                    toRemove.add(i);
                }

                // check if they have no ticks left
                enemy.decrementTicks();
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
                    if (player.getCurrentStamina() < ability.getStaminaCost()) {
                        speechLine("You do not have enough stamina to use this ability");
                    } 
                    else if (player.getCurrentMana() < ability.getManaCost()) {
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

    private void applySingleTargetAbility(Player player, Combatant target, List<Combatant> enemies, Ability ability) {
        int dmg = (int) DamageCalculator.calculateDamage(player, target, ability);
        
        // APPLY DAMAGE
        boolean targetAlive = target.reduceHealth(dmg);
        speechLine(target.getName() + " has " + target.getCurrentHealth() + " health left.");

        if (!targetAlive) {
            speechLine(target.getName() + " has been defeated."); 
            enemies.remove(target);
        }
        
        // APPLY AFFLICTION

        Affliction aff = ReactionData.returnAffliction(target.getAffliction(), ability.getElement().getAffliction());
        statusStatement(target, ability.getElement().getAffliction());
        target.setAffliction(aff);
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
        statusStatement(target, ability.getElement().getAffliction());
        target.setAffliction(aff);
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
        if (u.equals(Utilities.SKIP)) { } // NOTHING HAPPENS
        else if (u.equals(Utilities.MEDITATE)) { c.restoreMana((int) (c.getART() * u.getMultiplier())); }
        else if (u.equals(Utilities.HEAL)) { c.restoreHealth((int) (c.getART() * u.getMultiplier())); }
        else if (u.equals(Utilities.GREAT_HEAL)) { c.restoreHealth((int) (c.getART() * u.getMultiplier())); }
    }
    
    private void applyAbility(Player player, Combatant target, List<Combatant> opponents, Ability ability) {
        applySingleTargetAbility(player, target, opponents, ability);
        if (ability.getTargetCount() > 1) applyAreaAbility(player, target, opponents, ability);
    }


    // BATTLE

    public void battle(Player player, List<Combatant> enemies) {
        boolean playerTurn = true;
        enemyIntro(enemies);

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
                    applyUtility(player, (Utility) ability);
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