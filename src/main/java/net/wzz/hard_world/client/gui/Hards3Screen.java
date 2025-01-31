package net.wzz.hard_world.client.gui;

import net.wzz.hard_world.SetProperties;
import net.wzz.hard_world.world.inventory.Hards3Menu;
import net.wzz.hard_world.network.Hards3ButtonMessage;
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

public class Hards3Screen extends AbstractContainerScreen<Hards3Menu> {
	private final static HashMap<String, Object> guistate = Hards3Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_geng_gai;
	Button button_geng_gai1;
	Button button_geng_gai2;
	Button button_wan_cheng;

	public Hards3Screen(Hards3Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("hard_world:textures/screens/hards_3.png");

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
		guiGraphics.drawString(this.font, Component.literal("生物随机攻击击退："+SetProperties.RandomAttackKNOCKBACK), 7, 10, -16777216, false);
		guiGraphics.drawString(this.font, Component.literal("生物随机击退抗性："+SetProperties.RandomKNOCKBACK_RESISTANCE), 7, 39, -16777216, false);
		guiGraphics.drawString(this.font, Component.literal("生物随机增加护甲值："+SetProperties.RandomARMOR), 7, 69, -16777216, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		button_geng_gai = Button.builder(Component.literal("更改"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new Hards3ButtonMessage(0, x, y, z));
				try {
					Hards3ButtonMessage.handleButtonAction(entity, 0, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 131, this.topPos + 7, 35, 20).build();
		guistate.put("button:button_geng_gai", button_geng_gai);
		this.addRenderableWidget(button_geng_gai);
		button_geng_gai1 = Button.builder(Component.literal("更改"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new Hards3ButtonMessage(1, x, y, z));
				try {
					Hards3ButtonMessage.handleButtonAction(entity, 1, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 131, this.topPos + 36, 35, 20).build();
		guistate.put("button:button_geng_gai1", button_geng_gai1);
		this.addRenderableWidget(button_geng_gai1);
		button_geng_gai2 = Button.builder(Component.literal("更改"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new Hards3ButtonMessage(2, x, y, z));
				try {
					Hards3ButtonMessage.handleButtonAction(entity, 2, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 131, this.topPos + 66, 35, 20).build();
		guistate.put("button:button_geng_gai2", button_geng_gai2);
		this.addRenderableWidget(button_geng_gai2);
		button_wan_cheng = Button.builder(Component.literal("完成"), e -> {
			if (true) {
				HardWorldMod.PACKET_HANDLER.sendToServer(new Hards3ButtonMessage(3, x, y, z));
				try {
					Hards3ButtonMessage.handleButtonAction(entity, 3, x, y, z);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).bounds(this.leftPos + 179, this.topPos + 141, 35, 20).build();
		guistate.put("button:button_wan_cheng", button_wan_cheng);
		this.addRenderableWidget(button_wan_cheng);
	}
}
