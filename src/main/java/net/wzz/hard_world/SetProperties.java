package net.wzz.hard_world;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SetProperties {
    public static boolean revive = true;
    public static boolean disarm = true;
    public static boolean VanishingWeapon = true;
    public static boolean Death_plus_life = true;
    public static boolean Death_increases_speed = true;
    public static boolean Random_attack_damage = false;
    public static float attackDamage = 100;
    public static boolean giveBook = true;
    public static boolean spawnTNT = true;
    public static boolean breakBlockLightning = true;
    public static boolean damageEntityLightning = true;
    public static boolean reduceMaxHealth = true;
    public static boolean RandomAttackKNOCKBACK = true;
    public static boolean RandomKNOCKBACK_RESISTANCE = true;
    public static boolean RandomARMOR = true;
    public static String properties = "HardWorldProperties.json";
    public static void setRevive(boolean o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        boolean reviveIs = configObject.getBoolean("revive");
        reviveIs = o;
        configObject.put("revive", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        revive = reviveIs;
    }
    public static void setDisarm(boolean o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        boolean disarmIs = configObject.getBoolean("disarm");
        disarmIs = o;
        configObject.put("disarm", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        disarm = disarmIs;
    }
    public static void setVanishingWeapon(boolean o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        boolean VanishingWeaponIs = configObject.getBoolean("Vanishing Weapon");
        VanishingWeaponIs = o;
        configObject.put("Vanishing Weapon", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        VanishingWeapon = VanishingWeaponIs;
    }
    public static void setDeathPlusLife(boolean o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        boolean DeathPlusLifeIs = configObject.getBoolean("Death plus life");
        DeathPlusLifeIs = o;
        configObject.put("Death plus life", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        Death_plus_life = DeathPlusLifeIs;
    }
    public static void setDeathIncreasesSpeed(boolean o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        boolean DeathIncreasesSpeedIs = configObject.getBoolean("Death increases speed");
        DeathIncreasesSpeedIs = o;
        configObject.put("Death increases speed", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        Death_increases_speed = DeathIncreasesSpeedIs;
    }
    public static void setRandomAttackDamage(boolean o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        boolean RandomAttackDamageIs = configObject.getBoolean("Random attack damage");
        RandomAttackDamageIs = o;
        configObject.put("Random attack damage", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        Random_attack_damage = RandomAttackDamageIs;
    }
    public static void setAttackDamage(float o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        float AttackDamageIs = configObject.getFloat("attack damage");
        AttackDamageIs = o;
        configObject.put("attack damage", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        attackDamage = AttackDamageIs;
    }
    public static void setBreakBlockSpawnTNT(boolean o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        boolean BreakBlockSpawnTNTIs = configObject.getBoolean("Break Block Spawn TNT");
        BreakBlockSpawnTNTIs = o;
        configObject.put("Break Block Spawn TNT", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        spawnTNT = BreakBlockSpawnTNTIs;
    }
    public static void setBreakBlockLightning(boolean o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        boolean BreakBlockLightningIs = configObject.getBoolean("break Block Lightning");
        BreakBlockLightningIs = o;
        configObject.put("break Block Lightning", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        breakBlockLightning = BreakBlockLightningIs;
    }
    public static void setDamageEntityLightning(boolean o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        boolean DamageEntityLightningIs = configObject.getBoolean("damage Entity Lightning");
        DamageEntityLightningIs = o;
        configObject.put("damage Entity Lightning", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        damageEntityLightning = DamageEntityLightningIs;
    }
    public static void setReduceMaxHealth(boolean o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        boolean ReduceMaxHealthIs = configObject.getBoolean("reduce MaxHealth");
        ReduceMaxHealthIs = o;
        configObject.put("reduce MaxHealth", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        reduceMaxHealth = ReduceMaxHealthIs;
    }
    public static void setRandomAttackKNOCKBACK(boolean o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        boolean RandomAttackKNOCKBACKIs = configObject.getBoolean("Random AttackKNOCKBACK");
        RandomAttackKNOCKBACKIs = o;
        configObject.put("Random AttackKNOCKBACK", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        RandomAttackKNOCKBACK = RandomAttackKNOCKBACKIs;
    }
    public static void setRandomKNOCKBACK_RESISTANCE(boolean o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        boolean RandomKNOCKBACK_RESISTANCEIs = configObject.getBoolean("Random KNOCKBACK_RESISTANCE");
        RandomKNOCKBACK_RESISTANCEIs = o;
        configObject.put("Random KNOCKBACK_RESISTANCE", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        RandomKNOCKBACK_RESISTANCE = RandomKNOCKBACK_RESISTANCEIs;
    }
    public static void setRandomARMOR(boolean o) throws IOException {
        String configFileContent = new String(Files.readAllBytes(Paths.get(properties)));
        JSONObject configObject = new JSONObject(configFileContent);
        boolean RandomARMORIs = configObject.getBoolean("Random ARMOR");
        RandomARMORIs = o;
        configObject.put("Random ARMOR", o);
        Files.write(Paths.get(properties), configObject.toString().getBytes());
        RandomARMOR = RandomARMORIs;
    }
}
