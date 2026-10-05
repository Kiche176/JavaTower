import java.util.Scanner;
import java.util.List;

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



                // Select a target


                // Apply formula


            } 
            else {
                continue;
            }
        }

        speechLine("CONGRATULATIONS!");
    }
}