
package net.wzz.hard_world.command;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.wzz.hard_world.procedures.HardProcedure;
import net.wzz.hard_world.procedures.OverProcedure;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;

@Mod.EventBusSubscriber
public class ClearMashCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("clearMash")
				.executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					HardProcedure.entityMashSpeed.clear();
					HardProcedure.entityMashHealth.clear();
					HardProcedure.entityMashArmor.clear();
					((Player) entity).displayClientMessage(Component.literal("§a[Hard World] 已清除entityMashHealth,entityMashSpeed,entityMashArmor合集！"),false);
					return 0;
				}));
	}
}
