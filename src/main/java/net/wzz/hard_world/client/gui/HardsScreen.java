package net.wzz.hard_world.client.gui;

import net.wzz.hard_world.SetProperties;
import net.wzz.hard_world.world.inventory.HardsMenu;
import net.wzz.hard_world.network.HardsButtonMessage;
import net.wzz.hard_world.HardWorldMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.io.IOException;
import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class HardsScreen extends AbstractContainerScreen<HardsMenu> {
	private final static HashMap<String, Object> guistate = HardsMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_geng_gai;
	Button button_geng_gai1;
	Button button_geng_gai2;
	Button button_geng_gai3;
	Button button_geng_gai4;
	Button button_geng_gai5;
	Button button_next;

	public HardsScreen(HardsMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("hard_world:textures/screens/hards.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.literal("生物被杀死有概率复活："+ SetProperties.revive), 5, 5, -16777216, false);
		guiGraphics.drawString(this.font, Component.literal("攻击生物有几率被缴械："+ SetProperties.disarm), 5, 32, -16777216, false);
		guiGraphics.drawString(this.font, Component.literal("攻击生物有几率武器直接消失："+ SetProperties.VanishingWeapon), 5, 60, -16777216, false);
		guiGraphics.drawString(this.font, Component.literal("生物死亡随机加血："+ SetProperties.Death_plus_life), 5, 88, -16777216, false);
		guiGraphics.drawString(this.font, Component.literal("生物死亡随机增加移速："+ SetProperties.Death_increases_speed), 5, 115, -16777216, false);
		guiGraphics.drawString(this.font, Component.literal("随机攻击伤害："+ SetProperties.Random_attack_damage), 5, 142, -16777216, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		button_geng_gai = Button.builder(Component.translatable("gui.hard_world.hards.button_geng_gai"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new HardsButtonMessage(0, x, y, z));
				try {
					HardsButtonMessage.handleButtonAction(entity, 0, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 133, this.topPos + 4, 35, 20).build();
		guistate.put("button:button_geng_gai", button_geng_gai);
		this.addRenderableWidget(button_geng_gai);
		button_geng_gai1 = Button.builder(Component.translatable("gui.hard_world.hards.button_geng_gai1"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new HardsButtonMessage(1, x, y, z));
				try {
					HardsButtonMessage.handleButtonAction(entity, 1, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 133, this.topPos + 29, 35, 20).build();
		guistate.put("button:button_geng_gai1", button_geng_gai1);
		this.addRenderableWidget(button_geng_gai1);
		button_geng_gai2 = Button.builder(Component.translatable("gui.hard_world.hards.button_geng_gai2"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new HardsButtonMessage(2, x, y, z));
				try {
					HardsButtonMessage.handleButtonAction(entity, 2, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 133, this.topPos + 57, 35, 20).build();
		guistate.put("button:button_geng_gai2", button_geng_gai2);
		this.addRenderableWidget(button_geng_gai2);
		button_geng_gai3 = Button.builder(Component.translatable("gui.hard_world.hards.button_geng_gai3"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new HardsButtonMessage(3, x, y, z));
				try {
					HardsButtonMessage.handleButtonAction(entity, 3, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 132, this.topPos + 84, 35, 20).build();
		guistate.put("button:button_geng_gai3", button_geng_gai3);
		this.addRenderableWidget(button_geng_gai3);
		button_geng_gai4 = Button.builder(Component.translatable("gui.hard_world.hards.button_geng_gai4"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new HardsButtonMessage(4, x, y, z));
				try {
					HardsButtonMessage.handleButtonAction(entity, 4, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 132, this.topPos + 111, 35, 20).build();
		guistate.put("button:button_geng_gai4", button_geng_gai4);
		this.addRenderableWidget(button_geng_gai4);
		button_geng_gai5 = Button.builder(Component.translatable("gui.hard_world.hards.button_geng_gai5"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new HardsButtonMessage(5, x, y, z));
				try {
					HardsButtonMessage.handleButtonAction(entity, 5, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 132, this.topPos + 138, 35, 20).build();
		guistate.put("button:button_geng_gai5", button_geng_gai5);
		this.addRenderableWidget(button_geng_gai5);
		button_next = Button.builder(Component.literal("下一页"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new HardsButtonMessage(6, x, y, z));
				try {
					HardsButtonMessage.handleButtonAction(entity, 6, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 179, this.topPos + 142, 46, 20).build();
		guistate.put("button:button_next", button_next);
		this.addRenderableWidget(button_next);
	}
}
