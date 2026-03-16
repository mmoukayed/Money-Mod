package net.mcreator.money.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

import net.mcreator.money.network.MoneyModVariables;
import net.mcreator.money.init.MoneyModMenus;

public class ATMGUIVerifyPinProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (((entity instanceof Player _entity0 && _entity0.containerMenu instanceof MoneyModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "PIN", "") : "")
				.equals((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof MoneyModMenus.MenuAccessor _menu1 ? _menu1.getSlots().get(0).getItem() : ItemStack.EMPTY)
						.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getStringOr("PIN", ""))) {
			MoneyModVariables.CardVerified = true;
		} else {
			MoneyModVariables.CardVerified = false;
		}
	}
}