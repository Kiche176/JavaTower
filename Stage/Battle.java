import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.StringBuilder;

public class Battle {
    private Scanner scanner;

    public Battle() {
        this.scanner = new Scanner(System.in);
    }

    private void speechLine(String s) {
        System.out.print(s + " [PRESS ENTER]");
        this.scanner.nextLine();
    }

    private void battleIntro(Player player, List<Enemy> enemies) {
        if (enemies.size() == 1) {
            speechLine("There is 1 enemy.");
        } else {
            speechLine("There are " + enemies.size() + " enemies.");
        }

        for (Enemy e : enemies) {
            speechLine(
                "Lv." + enemy.getLVL() + enemy.getName() +
                " has " + enemy.getCurrentHealth() + " HP remaining."
            );
        }
    }

    private void updateDoT(Player player, List<Enemy> enemies) {
        List<Integer> toRemove = new ArrayList<>();
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            if (enemy.getTicks() > 0) {
                int damageDealt = (int) DamageCalculator.calculateTickDamage(player, enemy);
                enemy.decreaseHealth(damageDealt);
                speechLine(enemy);

                if (enemy.getCurrentHealth() == 0) {
                    // THEY DIE
                    speechLine(enemy.getName().toUpperCase() + " has been defeated.");
                    toRemove.add(i);
                }

                // check if they have no ticks left
                enemy.decrementTicks();
                if (enemy.getTicks() == 0) enemy.setTickAffliction(null);
            }
        }
        for (int i = toRemove.size() - 1; i >= 0; i--) {
            enemies.remove(toRemove.get(i));
        }
    }

    private void applyUtility(Combatant c, Utility u) {
        switch (u) {
            case UtilityTypes.MEDITATE : c.restoreMana(c.getART() * u.getMultiplier());
            case UtilityTypes.HEAL : c.restoreHealth(c.getART() * u.getMultiplier());
            case UtilityTypes.GREATHEAL : c.restoreHealth(c.getART() * u.getMultiplier());
        }
    }

    private Ability selectPlayerAbility(Player p) {
        Ability ability = null;
        while (ability = null) {
            System.out.println("Enter ability");
            String s = this.stream.nextLine();
            
            for (Ability a : p.getAbilities()) {
                if (a.getName().toLowerCase().equals(s.toLowerCase())) {
                    ablilty = a;
                }
            }
        }
        return ability;
    }

    private Combatant selectPlayerTarget(List<Combatant> enemies) {
        Combatant target = null;
        StringBuilder enemyList = new StringBuilder("The enemies are: ");
        for (int i = 0; i < enemies.size(); i++) {
            enemyList.append(
                "[" + (i+1) + "] " + enemies.get(i).getName() + 
                " with " + enemies.get(i).getCurrentHP() + " HP remaining. "
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
     *  @param combatants - In this case the list of combatants is a duplicate list missing the primary target as 
     *                      they take the full force of the attack rather than the AoE.
     */
    private List<Combatant> selectAreaTargets(Player p, List<Combatant> combatants, IAbility ability) {
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

    public void battle(Player player, List<Enemy> enemies) {
        boolean playerTurn = true;
        battleIntro();

        while (enemies.size() > 0 && player.getCurrentHealth() > 0) {
            
            // PLAYER'S TURN
            if (playerTurn) {

                // CHECK ENEMY DOT TICKS
                updateDoT(player, enemies);
                if (enemies.size() == 0) continue;


                // Select an ability (extra stuff if aoe)
                Ability abilitySelected = selectPlayerAbility(player);


                // Select a target
                if (ability instanceof Utility) {
                    applyUtility(player, ability);
                } else {
                    selectPlayerTarget(enemies);
                    // Apply formula

                    

                }



            } 
            else {
                continue;
            }
        }

        speechLine("CONGRATULATIONS!");
    }
}