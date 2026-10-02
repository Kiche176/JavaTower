public class PlayerJobs {
    public static final Player SWORDSMAN = new Player(
        Job.SWORDSMAN, WeaponTypes.WOODEN_SWORD,
        20, 0, 10, 10, 5, 7.5, 2.5, 4
    );
    
    public static final Player ASSASSIN = new Player(
        Job.ASSASSIN, WeaponTypes.RUSTY_DAGGER,
        15, 5, 30, 5, 5, 6.25, 2.5, 1.5
    );

    public static final Player TANK = new Player(
        Job.TANK, WeaponTypes.WOODEN_SHIELD,
        15, 0, 5, 15, 15, 5, 1.25, 10
    );

    public static final Player MAGE = new Player(
        Job.MAGE, WeaponTypes.SPELLBOOK,
        5, 25, 10, 5, 15, 2.5, 7.5, 0
    );
}