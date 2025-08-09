package com.zephyria.abilities;

import org.bukkit.entity.Player;

public class AbilityManager {

    private final AbilityCache abilityCache;

    // Abilities
    private final Ability antiFall = new Ability("Anti Fall", "Voorkomt schade door vallen.");
    private final Ability autoBow = new Ability("Auto Bow", "Automatisch schieten met een boog.");
    private final Ability belchBomb = new Ability("Belch Bomb", "Een explosieve braakselbom.");
    private final Ability cocaine = new Ability("Cocaine", "Versnel je bewegingen drastisch.");
    private final Ability enderTeleport = new Ability("Ender Teleport", "Teleporteer naar een willekeurige locatie.");
    private final Ability fakePearl = new Ability("Fake Pearl", "Gooi een nepperige enderpearl.");
    private final Ability fastPearl = new Ability("Fast Pearl", "Gooi een snelle enderpearl.");
    private final Ability fireball = new Ability("Fireball", "Schiet een vuurbal.");
    private final Ability flashBang = new Ability("Flash Bang", "Bliksem die het zicht van de vijand beperkt.");
    private final Ability freezeGun = new Ability("Freeze Gun", "Bevries vijanden in plaats.");
    private final Ability grapplingHook = new Ability("Grappling Hook", "Gebruik een grijphaak om snel te verplaatsen.");
    private final Ability grenade = new Ability("Grenade", "Gooi een explosieve granaat.");
    private final Ability invisRaid = new Ability("Invis Raid", "Wordt tijdelijk onzichtbaar.");
    private final Ability magicPill = new Ability("Magic Pill", "Verhoog je gezondheid.");
    private final Ability mixer = new Ability("Mixer", "Meng twee items samen.");
    private final Ability ninjaShear = new Ability("Ninja Shear", "Verberg je identiteit als ninja.");
    private final Ability playerHook = new Ability("Player Hook", "Trek een andere speler naar je toe.");
    private final Ability pocketBard = new Ability("Pocket Bard", "Genees jezelf met magische muziek.");
    private final Ability portableBackstab = new Ability("Portable Backstab", "Verhoog je schade bij verrassing.");
    private final Ability prePearl = new Ability("Pre Pearl", "Gooi een enderpearl vooraf.");
    private final Ability rawPotion = new Ability("Raw Potion", "Maak een krachtige brew.");
    private final Ability rocket = new Ability("Rocket", "Vuur een raket af die schade doet.");
    private final Ability rottenEgg = new Ability("Rotten Egg", "Gooi een rot ei als wapen.");
    private final Ability secondChance = new Ability("Second Chance", "Herleef jezelf na de dood.");
    private final Ability shield = new Ability("Shield", "Gebruik een schild om schade te blokkeren.");
    private final Ability stormBreaker = new Ability("Storm Breaker", "Gebruik een storm om je vijanden te verslaan.");
    private final Ability strength = new Ability("Strength", "Verhoog je kracht.");
    private final Ability superAxe = new Ability("Super Axe", "Gebruik een super bijl om sneller te hakken.");
    private final Ability switchStick = new Ability("Switch Stick", "Wissel je positie met een vijand.");
    private final Ability switcher = new Ability("Switcher", "Wissel van locatie met een andere speler.");
    private final Ability trapEvading = new Ability("Trap Evading", "Vermijd vallen in vallen.");
    private final Ability witherGun = new Ability("Wither Gun", "Schiet een kogel die vijanden meterachtig maakt.");

    public AbilityManager() {
        abilityCache = new AbilityCache();
    }

    public void activateAbility(Player player, String abilityName) {
        Ability ability = getAbilityByName(abilityName);
        if (ability != null) {
            ability.activate(player);
            abilityCache.setActiveAbility(player, ability.getName());
        } else {
            player.sendMessage("Ability niet gevonden!");
        }
    }

    private Ability getAbilityByName(String abilityName) {
        switch (abilityName.toLowerCase()) {
            case "anti-fall": return antiFall;
            case "auto-bow": return autoBow;
            case "belch-bomb": return belchBomb;
            case "cocaine": return cocaine;
            case "ender-teleport": return enderTeleport;
            case "fake-pearl": return fakePearl;
            case "fast-pearl": return fastPearl;
            case "fireball": return fireball;
            case "flash-bang": return flashBang;
            case "freeze-gun": return freezeGun;
            case "grappling-hook": return grapplingHook;
            case "grenade": return grenade;
            case "invis-raid": return invisRaid;
            case "magic-pill": return magicPill;
            case "mixer": return mixer;
            case "ninja-shear": return ninjaShear;
            case "player-hook": return playerHook;
            case "pocket-bard": return pocketBard;
            case "portable-backstab": return portableBackstab;
            case "pre-pearl": return prePearl;
            case "raw-potion": return rawPotion;
            case "rocket": return rocket;
            case "rotten-egg": return rottenEgg;
            case "second-chance": return secondChance;
            case "shield": return shield;
            case "storm-breaker": return stormBreaker;
            case "strength": return strength;
            case "super-axe": return superAxe;
            case "switch-stick": return switchStick;
            case "switcher": return switcher;
            case "trap-evading": return trapEvading;
            case "wither-gun": return witherGun;
            default: return null;
        }
    }

    public void listAbilities(Player player) {
        player.sendMessage("Beschikbare abilities:");
        player.sendMessage("Anti Fall, Auto Bow, Belch Bomb, Cocaine, Ender Teleport, Fake Pearl, Fast Pearl, Fireball, Flash Bang, Freeze Gun, Grappling Hook, Grenade, Invis Raid, Magic Pill, Mixer, Ninja Shear, Player Hook, Pocket Bard, Portable Backstab, Pre Pearl, Raw Potion, Rocket, Rotten Egg, Second Chance, Shield, Storm Breaker, Strength, Super Axe, Switch Stick, Switcher, Trap Evading, Wither Gun");
    }
}
