
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.wzz.hard_world.init;

import net.wzz.hard_world.client.gui.HardsScreen;
import net.wzz.hard_world.client.gui.Hards3Screen;
import net.wzz.hard_world.client.gui.Hards2Screen;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HardWorldModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(HardWorldModMenus.HARDS.get(), HardsScreen::new);
			MenuScreens.register(HardWorldModMenus.HARDS_2.get(), Hards2Screen::new);
			MenuScreens.register(HardWorldModMenus.HARDS_3.get(), Hards3Screen::new);
		});
	}
}
