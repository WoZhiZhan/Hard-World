package net.wzz.hard_world.client.gui;

import net.wzz.hard_world.SetProperties;
import net.wzz.hard_world.network.HardsButtonMessage;
import net.wzz.hard_world.world.inventory.Hards2Menu;
import net.wzz.hard_world.network.Hards2ButtonMessage;
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

public class Hards2Screen extends AbstractContainerScreen<Hards2Menu> {
	private final static HashMap<String, Object> guistate = Hards2Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_empty;
	Button button_empty1;
	Button button_wan_cheng;
	Button button_geng_gai;
	Button button_geng_gai1;
	Button button_geng_gai2;
	Button button_geng_gai3;

	public Hards2Screen(Hards2Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("hard_world:textures/screens/hards_2.png");

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
		guiGraphics.drawString(this.font, Component.literal("随机伤害范围：0.0~"+ SetProperties.attackDamage), 6, 6, -16777216, false);
		guiGraphics.drawString(this.font, Component.literal("挖方块随机生成TNT："+ SetProperties.spawnTNT), 6, 54, -16777216, false);
		guiGraphics.drawString(this.font, Component.literal("挖方块随机生成闪电："+ SetProperties.breakBlockLightning), 6, 72, -16777216, false);
		guiGraphics.drawString(this.font, Component.literal("攻击实体随机生成闪电："+ SetProperties.damageEntityLightning), 6, 110, -16777216, false);
		guiGraphics.drawString(this.font, Component.literal("玩家死亡减少2点最大生命值："+ SetProperties.reduceMaxHealth), 6, 128, -16777216, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		button_empty = Button.builder(Component.translatable("gui.hard_world.hards_2.button_empty"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new Hards2ButtonMessage(0, x, y, z));
				try {
					Hards2ButtonMessage.handleButtonAction(entity, 0, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 28, this.topPos + 30, 30, 20).build();
		guistate.put("button:button_empty", button_empty);
		this.addRenderableWidget(button_empty);
		button_empty1 = Button.builder(Component.translatable("gui.hard_world.hards_2.button_empty1"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new Hards2ButtonMessage(1, x, y, z));
				try {
					Hards2ButtonMessage.handleButtonAction(entity, 1, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 116, this.topPos + 30, 30, 20).build();
		guistate.put("button:button_empty1", button_empty1);
		this.addRenderableWidget(button_empty1);
		button_wan_cheng = Button.builder(Component.literal("下一页"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new Hards2ButtonMessage(2, x, y, z));
				try {
					Hards2ButtonMessage.handleButtonAction(entity, 2, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 179, this.topPos + 142, 35, 20).build();
		guistate.put("button:button_wan_cheng", button_wan_cheng);
		this.addRenderableWidget(button_wan_cheng);
		button_geng_gai = Button.builder(Component.translatable("gui.hard_world.hards.button_geng_gai"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new Hards2ButtonMessage(3, x, y, z));
				try {
					Hards2ButtonMessage.handleButtonAction(entity, 3, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 133, this.topPos + 52, 35, 20).build();
		guistate.put("button:button_geng_gai", button_geng_gai);
		this.addRenderableWidget(button_geng_gai);
		button_geng_gai1 = Button.builder(Component.translatable("gui.hard_world.hards.button_geng_gai"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new Hards2ButtonMessage(4, x, y, z));
				try {
					Hards2ButtonMessage.handleButtonAction(entity, 4, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 133, this.topPos + 70, 35, 20).build();
		guistate.put("button:button_geng_gai1", button_geng_gai1);
		this.addRenderableWidget(button_geng_gai1);
		button_geng_gai2 = Button.builder(Component.translatable("gui.hard_world.hards.button_geng_gai"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new Hards2ButtonMessage(5, x, y, z));
				try {
					Hards2ButtonMessage.handleButtonAction(entity, 5, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 133, this.topPos + 104, 45, 20).build();
		guistate.put("button:button_geng_gai2", button_geng_gai2);
		this.addRenderableWidget(button_geng_gai2);
		button_geng_gai3 = Button.builder(Component.translatable("gui.hard_world.hards.button_geng_gai"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new Hards2ButtonMessage(6, x, y, z));
				try {
					Hards2ButtonMessage.handleButtonAction(entity, 6, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 133, this.topPos + 134, 35, 20).build();
		guistate.put("button:button_geng_gai3", button_geng_gai3);
		this.addRenderableWidget(button_geng_gai3);
	}
}
