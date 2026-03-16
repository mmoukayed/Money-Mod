package net.mcreator.money.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.money.world.inventory.ExchangeguiMenu;
import net.mcreator.money.network.ExchangeguiButtonMessage;
import net.mcreator.money.init.MoneyModScreens;

public class ExchangeguiScreen extends AbstractContainerScreen<ExchangeguiMenu> implements MoneyModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_convert;

	public ExchangeguiScreen(ExchangeguiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("money:textures/screens/exchangegui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
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
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.money.exchangegui.label_exchange"), 65, 7, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.money.exchangegui.label_emeralds_in"), 22, 27, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.money.exchangegui.label_cash_out"), 103, 26, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_convert = Button.builder(Component.translatable("gui.money.exchangegui.button_convert"), e -> {
			int x = ExchangeguiScreen.this.x;
			int y = ExchangeguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new ExchangeguiButtonMessage(0, x, y, z));
				ExchangeguiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 54, this.topPos + 61, 61, 20).build();
		this.addRenderableWidget(button_convert);
	}
}