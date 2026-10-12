package Weapons;

import java.util.Set;

import DamageSystems.Elements.*;

public class Weapons {

    // Beginner weapons

    public static final Weapon UNARMED = new Weapon(
        "Unarmed", WeaponType.UNARMED,
        null, null,
        null, null, 
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon WOODEN_SWORD = new Weapon(
        "Wooden Sword", WeaponType.SWORD,
        null, Set.of(DamageType.SLASH),
        null, null, 
        3, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon WOODEN_SHIELD = new Weapon(
        "Wooden Shield", WeaponType.SHIELD,
        null, Set.of(DamageType.BLUNT),
        null, null, 
        0, 0, 0, 4, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon RUSTY_DAGGER = new Weapon(
        "Rusty Dagger", WeaponType.KNIFE,
        null, Set.of(DamageType.PIERCE),
        null, null, 
        2, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon SPELLBOOK = new Weapon(
        "Spellbook", WeaponType.CATALYST,
        null, null, 
        null, null, 
        0, 3, 0, 0, 0,
        0, 0, 0, 0, 0
    );


    // Advanced weapons

    public static final Weapon IRON_SWORD = new Weapon(
        "Iron Sword", WeaponType.SWORD,
        null, Set.of(DamageType.SLASH),
        null, null, 
        8, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon IRON_SHIELD = new Weapon(
        "Iron Shield", WeaponType.SHIELD,
        null, Set.of(DamageType.BLUNT),
        null, null,
        0, 0, 0, 10, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon NEEDLE = new Weapon(
        "Needle", WeaponType.KNIFE,
        null, Set.of(DamageType.PIERCE),
        null, null, 
        7, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon STAFF = new Weapon(
        "Staff", WeaponType.CATALYST,
        null, null, 
        null, null, 
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );


    // Boss weapons

    public static final Weapon CLUB = new Weapon(
        "Club", WeaponType.HAMMER,
        null, Set.of(DamageType.BLUNT),
        null, null, 
        5, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    );

    public static final Weapon GRAND_STAFF = new Weapon(
        "Grand Staff", WeaponType.CATALYST,
        null, null, 
        null, null, 
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