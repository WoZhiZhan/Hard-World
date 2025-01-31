
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.wzz.hard_world.init;

import net.wzz.hard_world.item.OpenGuiItem;
import net.wzz.hard_world.HardWorldMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

public class HardWorldModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, HardWorldMod.MODID);
	public static final RegistryObject<Item> OPEN_GUI = REGISTRY.register("open_gui", () -> new OpenGuiItem());
}
