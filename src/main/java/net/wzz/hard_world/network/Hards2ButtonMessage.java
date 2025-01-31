
package net.wzz.hard_world.network;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;
import net.wzz.hard_world.HardWorldMod;
import net.wzz.hard_world.SetProperties;
import net.wzz.hard_world.procedures.OverProcedure;
import net.wzz.hard_world.world.inventory.Hards2Menu;
import net.wzz.hard_world.world.inventory.Hards3Menu;

import java.io.IOException;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Hards2ButtonMessage {
	private final int buttonID, x, y, z;

	public Hards2ButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public Hards2ButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(Hards2ButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(Hards2ButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		if (buttonID == 0) {
			SetProperties.setAttackDamage(SetProperties.attackDamage += 10);;
			return;
		}
		if (buttonID == 1) {
			SetProperties.setAttackDamage(SetProperties.attackDamage -= 10);;
			return;
		}
		if (buttonID == 2) {
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				NetworkHooks.openScreen(_ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("Hards3");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new Hards3Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
			return;
		}
		if (buttonID == 3) {
			SetProperties.setBreakBlockSpawnTNT(!SetProperties.spawnTNT);
			return;
		}
		if (buttonID == 4) {
			SetProperties.setBreakBlockLightning(!SetProperties.breakBlockLightning);
			return;
		}
		if (buttonID == 5) {
			SetProperties.setDamageEntityLightning(!SetProperties.damageEntityLightning);
			return;
		}
		if (buttonID == 6) {
			SetProperties.setReduceMaxHealth(!SetProperties.reduceMaxHealth);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		HardWorldMod.addNetworkMessage(Hards2ButtonMessage.class, Hards2ButtonMessage::buffer, Hards2ButtonMessage::new, Hards2ButtonMessage::handler);
	}
}
