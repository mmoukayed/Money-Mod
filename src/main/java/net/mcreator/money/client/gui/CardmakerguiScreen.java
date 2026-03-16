package net.mcreator.money.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.money.world.inventory.CardmakerguiMenu;
import net.mcreator.money.network.CardmakerguiButtonMessage;
import net.mcreator.money.init.MoneyModScreens;

public class CardmakerguiScreen extends AbstractContainerScreen<CardmakerguiMenu> implements MoneyModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox PIN;
	private Button button_create;

	public CardmakerguiScreen(CardmakerguiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 181;
		this.imageHeight = 230;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("PIN"))
				PIN.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("money:textures/screens/cardmakergui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		PIN.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (PIN.isFocused())
			return PIN.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String PINValue = PIN.getValue();
		super.resize(minecraft, width, height);
		PIN.setValue(PINValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.money.cardmakergui.label_card_maker"), 64, 14, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.money.cardmakergui.label_enter_a_pin"), 60, 45, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		PIN = new EditBox(this.font, this.leftPos + 31, this.topPos + 62, 118, 18, Component.translatable("gui.money.cardmakergui.PIN"));
		PIN.setMaxLength(8192);
		PIN.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "PIN", content, false);
		});
		PIN.setHint(Component.translatable("gui.money.cardmakergui.PIN"));
		this.addWidget(this.PIN);
		button_create = Button.builder(Component.translatable("gui.money.cardmakergui.button_create"), e -> {
			int x = CardmakerguiScreen.this.x;
			int y = CardmakerguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new CardmakerguiButtonMessage(0, x, y, z));
				CardmakerguiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 62, this.topPos + 119, 56, 20).build();
		this.addRenderableWidget(button_create);
	}
}