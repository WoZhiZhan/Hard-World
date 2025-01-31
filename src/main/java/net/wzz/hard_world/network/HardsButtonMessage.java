
package net.wzz.hard_world.network;

import io.netty.buffer.Unpooled;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkHooks;
import net.wzz.hard_world.SetProperties;
import net.wzz.hard_world.world.inventory.Hards2Menu;
import net.wzz.hard_world.world.inventory.HardsMenu;
import net.wzz.hard_world.procedures.HardProcedure;
import net.wzz.hard_world.HardWorldMod;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HardsButtonMessage {
	private final int buttonID, x, y, z;

	public HardsButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public HardsButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(HardsButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(HardsButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			try {
				handleButtonAction(entity, buttonID, x, y, z);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) throws IOException {
		Level world = entity.level();
		HashMap guistate = HardsMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {
			SetProperties.setRevive(!SetProperties.revive);
			return;
		}
		if (buttonID == 1) {
			SetProperties.setDisarm(!SetProperties.disarm);
			return;
		}
		if (buttonID == 2) {
			SetProperties.setVanishingWeapon(!SetProperties.VanishingWeapon);
			return;
		}
		if (buttonID == 3) {
			SetProperties.setDeathPlusLife(!SetProperties.Death_plus_life);
			return;
		}
		if (buttonID == 4) {
			SetProperties.setDeathIncreasesSpeed(!SetProperties.Death_increases_speed);
			return;
		}
		if (buttonID == 5) {
			SetProperties.setRandomAttackDamage(!SetProperties.Random_attack_damage);
			return;
		}
		if (buttonID == 6) {
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				NetworkHooks.openScreen(_ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("Hards2");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new Hards2Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		HardWorldMod.addNetworkMessage(HardsButtonMessage.class, HardsButtonMessage::buffer, HardsButtonMessage::new, HardsButtonMessage::handler);
	}
}
