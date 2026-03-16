package net.mcreator.money.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

import net.mcreator.money.network.MoneyModVariables;
import net.mcreator.money.init.MoneyModMenus;
import net.mcreator.money.init.MoneyModItems;

public class ATMGUIBalanceLabelProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		ItemStack creditCard = ItemStack.EMPTY;
		creditCard = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof MoneyModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(0).getItem() : ItemStack.EMPTY).copy();
		if ((MoneyModItems.CREDIT_CARD.get() == creditCard.getItem() || MoneyModItems.GOLD_CREDIT_CARD.get() == creditCard.getItem()) && MoneyModVariables.CardVerified) {
			return "Balance: $" + creditCard.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("credit", 0);
		}
		return "Balance: $xxxx.xx";
	}
}