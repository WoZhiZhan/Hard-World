
package net.wzz.hard_world.network;

import net.wzz.hard_world.SetProperties;
import net.wzz.hard_world.world.inventory.Hards3Menu;
import net.wzz.hard_world.procedures.OverProcedure;
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
public class Hards3ButtonMessage {
	private final int buttonID, x, y, z;

	public Hards3ButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public Hards3ButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(Hards3ButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(Hards3ButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
			SetProperties.setRandomAttackKNOCKBACK(!SetProperties.RandomAttackKNOCKBACK);
			return;
		}
		if (buttonID == 1) {
			SetProperties.setRandomKNOCKBACK_RESISTANCE(!SetProperties.RandomKNOCKBACK_RESISTANCE);
			return;
		}
		if (buttonID == 2) {
			SetProperties.setRandomARMOR(!SetProperties.RandomARMOR);
			return;
		}
		if (buttonID == 3) {
			OverProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		HardWorldMod.addNetworkMessage(Hards3ButtonMessage.class, Hards3ButtonMessage::buffer, Hards3ButtonMessage::new, Hards3ButtonMessage::handler);
	}
}
