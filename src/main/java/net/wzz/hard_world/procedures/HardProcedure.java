package net.wzz.hard_world.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.wzz.hard_world.SetProperties;
import net.wzz.hard_world.init.HardWorldModItems;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;

@Mod.EventBusSubscriber
public class HardProcedure {
	public static Set<String> entityMashHealth = new HashSet<>();
	public static Set<String> entityMashSpeed = new HashSet<>();
	public static Set<String> entityMashArmor = new HashSet<>();
	public static float health = 0;
	@SubscribeEvent
	public static void setProperties(EntityJoinLevelEvent e) throws IOException {
		if (e.getEntity() instanceof Player) {
			String configFileContent = new String(Files.readAllBytes(Paths.get(SetProperties.properties)));
			JSONObject configObject = new JSONObject(configFileContent);
			SetProperties.revive = configObject.getBoolean("revive");
			SetProperties.disarm = configObject.getBoolean("disarm");
			SetProperties.VanishingWeapon = configObject.getBoolean("Vanishing Weapon");
			SetProperties.Death_plus_life = configObject.getBoolean("Death plus life");
			SetProperties.Death_increases_speed = configObject.getBoolean("Death increases speed");
			SetProperties.Random_attack_damage = configObject.getBoolean("Random attack damage");
			SetProperties.attackDamage = configObject.getFloat("attack damage");
			SetProperties.giveBook = configObject.getBoolean("give book");
			SetProperties.spawnTNT = configObject.getBoolean("Break Block Spawn TNT");
			SetProperties.breakBlockLightning = configObject.getBoolean("break Block Lightning");
			SetProperties.damageEntityLightning = configObject.getBoolean("damage Entity Lightning");
			SetProperties.reduceMaxHealth = configObject.getBoolean("reduce MaxHealth");
			SetProperties.RandomAttackKNOCKBACK = configObject.getBoolean("Random AttackKNOCKBACK");
			SetProperties.RandomKNOCKBACK_RESISTANCE = configObject.getBoolean("Random KNOCKBACK_RESISTANCE");
			SetProperties.RandomARMOR = configObject.getBoolean("Random ARMOR");
			if (e.getEntity().getCommandSenderWorld() instanceof ServerLevel _level && SetProperties.giveBook) {
				ItemEntity entityToSpawn = new ItemEntity(_level, e.getEntity().getX(), e.getEntity().getY() + 1,
						e.getEntity().getZ(), new ItemStack(HardWorldModItems.OPEN_GUI.get()));
				entityToSpawn.setPickUpDelay(0);
				_level.addFreshEntity(entityToSpawn);
			}
			if (SetProperties.reduceMaxHealth) {
				if (health >= ((Player) e.getEntity()).getMaxHealth())
				   health = 0;
				else
					Objects.requireNonNull(((Player) e.getEntity()).getAttributes().getInstance(MAX_HEALTH)).setBaseValue(((Player)e.getEntity()).getMaxHealth() - health);
			}
		} else {
			if (entityMashHealth.contains(e.getEntity().getClass().getName()) && SetProperties.Death_plus_life) {
				if (e.getEntity() instanceof LivingEntity) {
					Objects.requireNonNull(((LivingEntity) e.getEntity()).getAttributes().getInstance(MAX_HEALTH)).setBaseValue(
							((LivingEntity) e.getEntity()).getMaxHealth() +
									new Random().nextDouble(((LivingEntity) e.getEntity()).getMaxHealth()) * 0.6
					);
					((LivingEntity) e.getEntity()).setHealth(((LivingEntity) e.getEntity()).getMaxHealth());
				}
			}
			if (entityMashSpeed.contains(e.getEntity().getClass().getName()) && SetProperties.Death_increases_speed) {
				if (e.getEntity() instanceof LivingEntity) {
					if (((LivingEntity) e.getEntity()).getAttribute(MOVEMENT_SPEED) != null) {
						Objects.requireNonNull(((LivingEntity) e.getEntity()).getAttributes().getInstance(MOVEMENT_SPEED)).setBaseValue(
								((LivingEntity) e.getEntity()).getSpeed()
								 + new Random().nextDouble(11) * 0.2D
						);
					}
				}
			}
			if (entityMashArmor.contains(e.getEntity().getClass().getName()) && SetProperties.RandomARMOR) {
				if (e.getEntity() instanceof LivingEntity) {
					if (((LivingEntity) e.getEntity()).getAttribute(ARMOR) != null) {
						double i = ((LivingEntity) e.getEntity()).getArmorValue();
						Objects.requireNonNull(((LivingEntity) e.getEntity()).getAttributes().getInstance(ARMOR)).setBaseValue(
								((LivingEntity) e.getEntity()).getArmorValue() +
										new Random().nextDouble(i) * 0.2D
						);
					}
				}
			}
		}
	}
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (SetProperties.Death_plus_life && !(event.getEntity() instanceof Player))
			entityMashHealth.add(event.getEntity().getClass().getName());
		if (SetProperties.Death_increases_speed && !(event.getEntity() instanceof Player))
			entityMashSpeed.add(event.getEntity().getClass().getName());
		if (SetProperties.RandomARMOR && !(event.getEntity() instanceof Player))
			entityMashArmor.add(event.getEntity().getClass().getName());
		if (event.getEntity() != null && event.getSource().getEntity() instanceof Player && SetProperties.revive && !(event.getEntity() instanceof Player)) {
			int i = new Random().nextInt(3);
			if (i == 1) {
				event.setCanceled(true);
				event.getEntity().setHealth(event.getEntity().getMaxHealth());
				((Player) Objects.requireNonNull(event.getSource().getEntity())).displayClientMessage(Component.literal("你的运气不怎么好。。。"), false);
			}
		}
		if (SetProperties.reduceMaxHealth && event.getEntity() instanceof Player) {
			health += 2;
		}
	}
	@SubscribeEvent
	public static void onEntityHurt(LivingHurtEvent event) {
		if (SetProperties.RandomAttackKNOCKBACK) {
			if (!(event.getEntity() instanceof Player) && event.getSource().getEntity() instanceof Player)
				Objects.requireNonNull(event.getEntity().getAttributes().getInstance(ATTACK_KNOCKBACK))
						.setBaseValue(event.getEntity().getAttributes().getBaseValue(ATTACK_KNOCKBACK)
								+ new Random().nextDouble(event.getEntity().getAttributes().getBaseValue(ATTACK_KNOCKBACK)+1D) * 0.2f);
		}
		if (SetProperties.RandomKNOCKBACK_RESISTANCE) {
			if (event.getSource().getEntity() instanceof Player) {
				Objects.requireNonNull(event.getEntity().getAttributes().getInstance(KNOCKBACK_RESISTANCE))
						.setBaseValue(event.getEntity().getAttributes().getBaseValue(KNOCKBACK_RESISTANCE)
								+ new Random().nextDouble(event.getEntity().getAttributes().getBaseValue(KNOCKBACK_RESISTANCE) + 1D) * 0.2f);
			}
		}
		if (event.getSource().getEntity() instanceof Player && SetProperties.disarm) {
            System.out.println("[Hard World] disarm item has BUG");
//			int i = new Random().nextInt(8);
//			if (i == 2) {
//				Level _level = event.getSource().getEntity().level();
//				ItemEntity entityToSpawn = new ItemEntity(_level, event.getSource().getEntity().getX(), event.getSource().getEntity().getY() + 1,
//						event.getSource().getEntity().getZ(), ((Player)event.getSource().getEntity()).getMainHandItem());
//				if (!entityToSpawn.getItem().isEmpty()) {
//					entityToSpawn.setPickUpDelay(50);
//					_level.addFreshEntity(entityToSpawn);
//					(event.getSource().getEntity() instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
//					((Player) Objects.requireNonNull(event.getSource().getEntity())).displayClientMessage(Component.literal("你的武器掉落了"), false);
//				}
//			}
		}
		if (event.getSource().getEntity() instanceof Player && SetProperties.VanishingWeapon) {
			int i = new Random().nextInt(26);
			if (i == 2) {
				(event.getSource().getEntity() instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
				((Player) Objects.requireNonNull(event.getSource().getEntity())).displayClientMessage(Component.literal("你的武器似乎消失了"), false);
			}
		}
		if (SetProperties.Random_attack_damage) {
			if (!(event.getSource().getEntity() instanceof Player))
				event.setAmount(new Random().nextFloat(SetProperties.attackDamage));
		}
		if (SetProperties.damageEntityLightning) {
			if (event.getSource().getEntity() instanceof Player) {
				int i = new Random().nextInt(10);
				if (i == 1) {
					LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create((Level) event.getSource().getEntity().level());
					entityToSpawn.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(event.getSource().getEntity().getX(), event.getSource().getEntity().getY(), event.getSource().getEntity().getZ())));
					event.getSource().getEntity().level().addFreshEntity(entityToSpawn);
				}
			}
		}
	}
	@SubscribeEvent
	public static void breakBLock(BlockEvent.BreakEvent e) {
		if (SetProperties.spawnTNT) {
			int i = new Random().nextInt(11);
			if (i == 1) {
				PrimedTnt tnt = new PrimedTnt((Level) e.getLevel(), e.getPlayer().getX(), e.getPlayer().getY() + 2, e.getPlayer().getZ(), e.getPlayer());
				e.getLevel().addFreshEntity(tnt);
			}
		}
		if (SetProperties.breakBlockLightning) {
			int r = new Random().nextInt(10);
			if (r == 1) {
				LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create((Level) e.getLevel());
				entityToSpawn.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(e.getPlayer().getX(), e.getPlayer().getY(), e.getPlayer().getZ())));
				e.getLevel().addFreshEntity(entityToSpawn);
			}
		}
	}
}
