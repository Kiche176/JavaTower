import java.util.Scanner;
import java.util.List;

public class Battle {
    private Scanner scanner;

    public Battle() {
        this.scanner = new Scanner(System.in);
    }

    private void battleIntro(Player player, List<Enemy> enemies) {
        if (enemies.length == 1) {
            System.out.print("There is 1 enemy. [PRESS ENTER]");
        } else {
            System.out.print("There are " + enemies.length + " enemies. [PRESS ENTER]");
        }
        String enter = this.scanner.nextLine();

        for (Enemy e : enemies) {
            System.out.print(
                "Lv." + enemy.getLVL() + enemy.getName() +
                " has " + enemy.getCurrentHealth() + " HP remaining. [PRESS ENTER]"
            );
        }
    }

    public void battle(Player player, List<Enemy> enemies) {
        boolean playerTurn = true;
        battleIntro();

        while (enemies.size() > 0 && player.getCurrentHealth() > 0) {
            
            // PLAYER'S TURN
            if (playerTurn) {
                // CHECK ENEMY DOT TICKS
                for (Enemy e : enemies) {
                    if (enemy.getTicks() > 0) {
                        int damageDealt = (int) DamageCalculator.calculateTickDamage(player, e);
                        enemy.decreaseHealth(damageDealt);
                        if (enemy.getCurrentHealth() == 0) {
                            // THEY DIE
                            continue;
                        }
                        // check if they have no ticks left
                        enemy.decrementTicks()
                        if (enemy.getTicks() == 0) enemy.setTickAffliction(null);
                    }
                }
                // Select a target


                // Select an ability (extra stuff if aoe)


                // Apply formula


            }
        }
    }
}