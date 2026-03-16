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

import net.mcreator.money.world.inventory.PaymentmachinepayerguiMenu;
import net.mcreator.money.procedures.PaymentMachinePayerGUITitleProcedure;
import net.mcreator.money.network.PaymentmachinepayerguiButtonMessage;
import net.mcreator.money.init.MoneyModScreens;

public class PaymentmachinepayerguiScreen extends AbstractContainerScreen<PaymentmachinepayerguiMenu> implements MoneyModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox PIN;
	private Button button_pay;

	public PaymentmachinepayerguiScreen(PaymentmachinepayerguiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
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

	private static final ResourceLocation texture = ResourceLocation.parse("money:textures/screens/paymentmachinepayergui.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.money.paymentmachinepayergui.label_payment"), 67, 4, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.money.paymentmachinepayergui.label_insert_card"), 55, 48, -12829636, false);
		guiGraphics.drawString(this.font, PaymentMachinePayerGUITitleProcedure.execute(world, x, y, z), 54, 22, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		PIN = new EditBox(this.font, this.leftPos + 28, this.topPos + 85, 118, 18, Component.translatable("gui.money.paymentmachinepayergui.PIN"));
		PIN.setMaxLength(8192);
		PIN.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "PIN", content, false);
		});
		PIN.setHint(Component.translatable("gui.money.paymentmachinepayergui.PIN"));
		this.addWidget(this.PIN);
		button_pay = Button.builder(Component.translatable("gui.money.paymentmachinepayergui.button_pay"), e -> {
			int x = PaymentmachinepayerguiScreen.this.x;
			int y = PaymentmachinepayerguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PaymentmachinepayerguiButtonMessage(0, x, y, z));
				PaymentmachinepayerguiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 66, this.topPos + 111, 40, 20).build();
		this.addRenderableWidget(button_pay);
	}
}