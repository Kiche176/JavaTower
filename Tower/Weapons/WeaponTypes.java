import java.util.Set;

public class WeaponTypes {

    // Beginner weapons

    public static final Weapon UNARMED = new Weapon(
        "Unarmed",
        null,
        null,
        Set.of(
            punch,
            kick,
            bonk,
            heal
        ),
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon WOODEN_SWORD = new Weapon(
        "Wooden Sword",
        null,
        DamageType.SLASH,
        Set.of(
            slash,
            stab,
            bonk,
            kick,
            heal
        ),
        3, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon WOODEN_SHIELD = new Weapon(
        "Wooden Shield",
        null,
        DamageType.BLUNT,
        Set.of(
            bash,
            bonk,
            clobber,
            heal
        ),
        0, 0, 0, 4, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon RUSTY_DAGGER = new Weapon(
        "Rusty Dagger",
        null,
        DamageType.PIERCE,
        Set.of(
            slash,
            stab,
            heal
        ),
        2, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon SPELLBOOK = new Weapon(
        "Spellbook",
        null,
        null,
        Set.of(
            bonk,
            ember,
            splash,
            icicle,
            stone,
            gale,
            bolt,
            heal
        ),
        0, 3, 0, 0, 0,
        0, 0, 0, 0, 0
    );


    // Advanced weapons

    public static final Weapon IRON_SWORD = new Weapon(
        "Iron Sword",
        null,
        DamageType.SLASH,
        Set.of(
            slash,
            stab,
            bonk,
            slice,
            dice,
            kick,
            decimate,
            annihilation,
            heal
        ),
        8, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon IRON_SHIELD = new Weapon(
        "Iron Shield",
        null,
        DamageType.BLUNT,
        Set.of(
            bash,
            bonk,
            clobber,
            shieldCharge,
            heal
        ),
        0, 0, 0, 10, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon NEEDLE = new Weapon(
        "Needle",
        null,
        DamageType.PIERCE,
        Set.of(
            slash,
            stab,
            dice,
            needleRain,
            annihilation,
            heal
        ),
        7, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon STAFF = new Weapon(
        "Staff",
        null,
        null,
        Set.of(
            bonk,
            ember,
            splash,
            icicle,
            stone,
            gale,
            bolt,
            heal,
            eruption,
            ignite,
            tsunami,
            waterfall,
            iceLance,
            hailstorm,
            rockslide,
            planetaryDevastation,
            windScythe,
            tornado,
            kamui,
            thunderstorm,
            greatHeal
        ),
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );


    // Boss weapons

    public static final Weapon CLUB = new Weapon(
        "Club",
        null,
        DamageType.BLUNT,
        Set.of(
            punch,
            kick,
            bonk,
            heal
        ),
        5, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon GRAND_STAFF = new Weapon(
        "Grand Staff",
        null,
        null,
        Set.of(
            bonk,
            ember,
            splash,
            icicle,
            stone,
            gale,
            bolt,
            heal,
            eruption,
            ignite,
            tsunami,
            waterfall,
            iceLance,
            hailstorm,
            rockslide,
            planetaryDevastation,
            windScythe,
            tornado,
            kamui,
            thunderstorm,
            greatHeal
        ),
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );


    // Weapon groups

    public static final Set<Weapon> BEGINNER_WEAPONS = Set.of(
        WOODEN_SWORD,
        WOODEN_SHIELD,
        RUSTY_DAGGER,
        SPELLBOOK
    );

    public static final Set<Weapon> ADVANCED_WEAPONS = Set.of(
        IRON_SWORD,
        IRON_SHIELD,
        NEEDLE,
        STAFF
    );
}