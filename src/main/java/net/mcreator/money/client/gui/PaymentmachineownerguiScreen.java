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

import net.mcreator.money.world.inventory.PaymentmachineownerguiMenu;
import net.mcreator.money.network.PaymentmachineownerguiButtonMessage;
import net.mcreator.money.init.MoneyModScreens;

public class PaymentmachineownerguiScreen extends AbstractContainerScreen<PaymentmachineownerguiMenu> implements MoneyModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox price;
	private EditBox PIN;
	private Button button_start_payment;
	private Button button_collect_money_if_any;

	public PaymentmachineownerguiScreen(PaymentmachineownerguiMenu container, Inventory inventory, Component text) {
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
			if (name.equals("price"))
				price.setValue(stringState);
			else if (name.equals("PIN"))
				PIN.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("money:textures/screens/paymentmachineownergui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		price.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (price.isFocused())
			return price.keyPressed(key, b, c);
		if (PIN.isFocused())
			return PIN.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String priceValue = price.getValue();
		String PINValue = PIN.getValue();
		super.resize(minecraft, width, height);
		price.setValue(priceValue);
		PIN.setValue(PINValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.money.paymentmachineownergui.label_modify_payment"), 46, 5, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.money.paymentmachineownergui.label_type_price"), 58, 19, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		price = new EditBox(this.font, this.leftPos + 26, this.topPos + 35, 118, 18, Component.translatable("gui.money.paymentmachineownergui.price"));
		price.setMaxLength(8192);
		price.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "price", content, false);
		});
		this.addWidget(this.price);
		PIN = new EditBox(this.font, this.leftPos + 42, this.topPos + 91, 118, 18, Component.translatable("gui.money.paymentmachineownergui.PIN"));
		PIN.setMaxLength(8192);
		PIN.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "PIN", content, false);
		});
		PIN.setHint(Component.translatable("gui.money.paymentmachineownergui.PIN"));
		this.addWidget(this.PIN);
		button_start_payment = Button.builder(Component.translatable("gui.money.paymentmachineownergui.button_start_payment"), e -> {
			int x = PaymentmachineownerguiScreen.this.x;
			int y = PaymentmachineownerguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PaymentmachineownerguiButtonMessage(0, x, y, z));
				PaymentmachineownerguiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 39, this.topPos + 59, 93, 20).build();
		this.addRenderableWidget(button_start_payment);
		button_collect_money_if_any = Button.builder(Component.translatable("gui.money.paymentmachineownergui.button_collect_money_if_any"), e -> {
			int x = PaymentmachineownerguiScreen.this.x;
			int y = PaymentmachineownerguiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PaymentmachineownerguiButtonMessage(1, x, y, z));
				PaymentmachineownerguiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 16, this.topPos + 115, 139, 20).build();
		this.addRenderableWidget(button_collect_money_if_any);
	}
}