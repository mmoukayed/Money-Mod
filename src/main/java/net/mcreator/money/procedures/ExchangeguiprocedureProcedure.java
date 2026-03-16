package net.mcreator.money.procedures;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.money.init.MoneyModMenus;
import net.mcreator.money.init.MoneyModItems;

public class ExchangeguiprocedureProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (Items.EMERALD == (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof MoneyModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem()) {
			if (entity instanceof Player _player && _player.containerMenu instanceof MoneyModMenus.MenuAccessor _menu) {
				ItemStack _setstack3 = new ItemStack(MoneyModItems.ONE_CASH.get()).copy();
				_setstack3.setCount(10 * getAmountInGUISlot(entity, 0));
				_menu.getSlots().get(1).set(_setstack3);
				_menu.getSlots().get(0).set(ItemStack.EMPTY);
				_player.containerMenu.broadcastChanges();
			}
		}
	}

	private static int getAmountInGUISlot(Entity entity, int sltid) {
		if (entity instanceof Player player && player.containerMenu instanceof MoneyModMenus.MenuAccessor menuAccessor) {
			ItemStack stack = menuAccessor.getSlots().get(sltid).getItem();
			if (stack != null)
				return stack.getCount();
		}
		return 0;
	}
}