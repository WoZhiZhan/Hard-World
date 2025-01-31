
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.wzz.hard_world.init;

import net.wzz.hard_world.world.inventory.HardsMenu;
import net.wzz.hard_world.world.inventory.Hards3Menu;
import net.wzz.hard_world.world.inventory.Hards2Menu;
import net.wzz.hard_world.HardWorldMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

public class HardWorldModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, HardWorldMod.MODID);
	public static final RegistryObject<MenuType<HardsMenu>> HARDS = REGISTRY.register("hards", () -> IForgeMenuType.create(HardsMenu::new));
	public static final RegistryObject<MenuType<Hards2Menu>> HARDS_2 = REGISTRY.register("hards_2", () -> IForgeMenuType.create(Hards2Menu::new));
	public static final RegistryObject<MenuType<Hards3Menu>> HARDS_3 = REGISTRY.register("hards_3", () -> IForgeMenuType.create(Hards3Menu::new));
}
