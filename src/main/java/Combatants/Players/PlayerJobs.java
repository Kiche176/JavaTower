public class PlayerJobs {
    public static final Player SWORDSMAN = new Player(
        Job.SWORDSMAN, WeaponTypes.WOODEN_SWORD,
        20, 0, 10, 10, 5, 
        8, 2, 4
    );
    
    public static final Player ASSASSIN = new Player(
        Job.ASSASSIN, WeaponTypes.RUSTY_DAGGER,
        15, 5, 30, 5, 5, 
        6, 2, 2
    );

    public static final Player TANK = new Player(
        Job.TANK, WeaponTypes.WOODEN_SHIELD,
        15, 0, 5, 15, 15, 
        5, 1, 10
    );

    public static final Player MAGE = new Player(
        Job.MAGE, WeaponTypes.SPELLBOOK,
        5, 25, 10, 5, 15, 
        2, 8, 0
    );
}