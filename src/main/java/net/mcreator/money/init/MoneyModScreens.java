/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.money.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.money.client.gui.*;

@EventBusSubscriber(Dist.CLIENT)
public class MoneyModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(MoneyModMenus.CARDMAKERGUI.get(), CardmakerguiScreen::new);
		event.register(MoneyModMenus.ATMGUI.get(), AtmguiScreen::new);
		event.register(MoneyModMenus.EXCHANGEGUI.get(), ExchangeguiScreen::new);
		event.register(MoneyModMenus.CARDUPGRADERGUI.get(), CardupgraderguiScreen::new);
		event.register(MoneyModMenus.PAYMENTMACHINEOWNERGUI.get(), PaymentmachineownerguiScreen::new);
		event.register(MoneyModMenus.PAYMENTMACHINEPAYERGUI.get(), PaymentmachinepayerguiScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}