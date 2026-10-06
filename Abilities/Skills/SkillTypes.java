public class SkillTypes {

    // Beginner Skills

    public static final Skill SLASH = new Skill(
        "Slash",
        Element.PHYSICAL,
        2.5,
        1,
        DamageType.SLASH,
        10
    );

    public static final Skill STAB = new Skill(
        "Stab",
        Element.PHYSICAL,
        2,
        1,
        DamageType.PIERCE,
        10
    );

    public static final Skill BASH = new Skill(
        "Bash",
        Element.PHYSICAL,
        1.5,
        1,
        DamageType.BLUNT,
        5
    );

    public static final Skill CLOBBER = new Skill(
        "Clobber",
        Element.PHYSICAL,
        2.5,
        1,
        DamageType.BLUNT,
        7
    );

    public static final Skill BONK = new Skill(
        "Bonk",
        Element.PHYSICAL,
        2,
        1,
        DamageType.BLUNT,
        5
    );

    public static final Skill PUNCH = new Skill(
        "Punch",
        Element.PHYSICAL,
        1,
        1,
        DamageType.BLUNT,
        5
    );

    public static final Skill KICK = new Skill(
        "Kick",
        Element.PHYSICAL,
        1,
        1,
        DamageType.BLUNT,
        5
    );


    // Advanced Skills

    public static final Skill SLICE = new Skill(
        "Slice",
        Element.PHYSICAL,
        3,
        1,
        DamageType.SLASH,
        20
    );

    public static final Skill DICE = new Skill(
        "Dice",
        Element.PHYSICAL,
        2.5,
        1,
        DamageType.PIERCE,
        15
    );

    public static final Skill DECIMATE = new Skill(
        "Decimate",
        Element.PHYSICAL,
        5,
        99,
        DamageType.SLASH,
        35
    );

    public static final Skill SHIELD_CHARGE = new Skill(
        "Shield Charge",
        Element.PHYSICAL,
        2.5,
        1,
        DamageType.BLUNT,
        10
    );

    public static final Skill NEEDLE_RAIN = new Skill(
        "Needle Rain",
        Element.PHYSICAL,
        4,
        99,
        DamageType.PIERCE,
        25
    );

    public static final Skill ANNIHILATION = new Skill(
        "Annihilation",
        Element.PHYSICAL,
        8,
        1,
        DamageType.SLASH,
        75
    );
}