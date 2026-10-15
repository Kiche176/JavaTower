import java.util.List;

import Combatants.Players.*;
import Combatants.Enemies.*;
import Stage.*;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        Enemy enemy = new TestDummy(5);
        Battle battle = new Battle();
        battle.battle(player, List.of(enemy));
    }
}
