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

import net.mcreator.money.world.inventory.AtmguiMenu;
import net.mcreator.money.procedures.ATMGUIPinVerifiedProcedure;
import net.mcreator.money.procedures.ATMGUIPinVerifiedNegatedProcedure;
import net.mcreator.money.procedures.ATMGUIBalanceLabelProcedure;
import net.mcreator.money.network.AtmguiButtonMessage;
import net.mcreator.money.init.MoneyModScreens;

public class AtmguiScreen extends AbstractContainerScreen<AtmguiMenu> implements MoneyModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox PIN;
	private EditBox withdrawAmount;
	private Button button_deposit;
	private Button button_withdraw;
	private Button button_verify;

	public AtmguiScreen(AtmguiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 281;
		this.imageHeight = 230;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("PIN"))
				PIN.setValue(stringState);
			else if (name.equals("withdrawAmount"))
				withdrawAmount.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("money:textures/screens/atmgui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		PIN.render(guiGraphics, mouseX, mouseY, partialTicks);
		withdrawAmount.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (withdrawAmount.isFocused())
			return withdrawAmount.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String PINValue = PIN.getValue();
		String withdrawAmountValue = withdrawAmount.getValue();
		super.resize(minecraft, width, height);
		PIN.setValue(PINValue);
		withdrawAmount.setValue(withdrawAmountValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.money.atmgui.label_atm"), 132, 7, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.money.atmgui.label_credit_card"), 10, 21, -12829636, false);
		guiGraphics.drawString(this.font, ATMGUIBalanceLabelProcedure.execute(entity), 98, 20, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.money.atmgui.label_deposit"), 40, 67, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.money.atmgui.label_cash_and_coins_only"), 13, 79, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.money.atmgui.label_withdraw"), 186, 58, -12829636, false);
		if (ATMGUIPinVerifiedNegatedProcedure.execute())
			guiGraphics.drawString(this.font, Component.translatable("gui.money.atmgui.label_not_verified"), 218, 38, -65536, false);
		if (ATMGUIPinVerifiedProcedure.execute())
			guiGraphics.drawString(this.font, Component.translatable("gui.money.atmgui.label_verified"), 218, 38, -16724992, false);
	}

	@Override
	public void init() {
		super.init();
		PIN = new EditBox(this.font, this.leftPos + 34, this.topPos + 35, 118, 18, Component.translatable("gui.money.atmgui.PIN"));
		PIN.setMaxLength(8192);
		PIN.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "PIN", content, false);
		});
		PIN.setHint(Component.translatable("gui.money.atmgui.PIN"));
		this.addWidget(this.PIN);
		withdrawAmount = new EditBox(this.font, this.leftPos + 148, this.topPos + 72, 118, 18, Component.translatable("gui.money.atmgui.withdrawAmount"));
		withdrawAmount.setMaxLength(8192);
		withdrawAmount.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "withdrawAmount", content, false);
		});
		withdrawAmount.setHint(Component.translatable("gui.money.atmgui.withdrawAmount"));
		this.addWidget(this.withdrawAmount);
		button_deposit = Button.builder(Component.translatable("gui.money.atmgui.button_deposit"), e -> {
			int x = AtmguiScreen.this.x;
			int y = AtmguiScreen.this.y;
			if (ATMGUIPinVerifiedProcedure.execute()) {
				ClientPacketDistributor.sendToServer(new AtmguiButtonMessage(0, x, y, z));
				AtmguiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 30, this.topPos + 114, 61, 20).build();
		this.addRenderableWidget(button_deposit);
		button_withdraw = Button.builder(Component.translatable("gui.money.atmgui.button_withdraw"), e -> {
			int x = AtmguiScreen.this.x;
			int y = AtmguiScreen.this.y;
			if (ATMGUIPinVerifiedProcedure.execute()) {
				ClientPacketDistributor.sendToServer(new AtmguiButtonMessage(1, x, y, z));
				AtmguiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 175, this.topPos + 114, 67, 20).build();
		this.addRenderableWidget(button_withdraw);
		button_verify = Button.builder(Component.translatable("gui.money.atmgui.button_verify"), e -> {
			int x = AtmguiScreen.this.x;
			int y = AtmguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new AtmguiButtonMessage(2, x, y, z));
				AtmguiButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 158, this.topPos + 34, 56, 20).build();
		this.addRenderableWidget(button_verify);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		this.button_deposit.visible = ATMGUIPinVerifiedProcedure.execute();
		this.button_withdraw.visible = ATMGUIPinVerifiedProcedure.execute();
	}
}