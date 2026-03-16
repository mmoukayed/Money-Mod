package net.mcreator.money.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

import net.mcreator.money.network.MoneyModVariables;
import net.mcreator.money.init.MoneyModMenus;
import net.mcreator.money.init.MoneyModItems;

public class ATMGUIDepositButtonProcedureProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ItemStack cashInput = ItemStack.EMPTY;
		double totalDeposit = 0;
		cashInput = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof MoneyModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(1).getItem() : ItemStack.EMPTY).copy();
		totalDeposit = 0;
		if (MoneyModVariables.CardVerified) {
			if (MoneyModItems.ONE_CASH.get() == cashInput.getItem()) {
				totalDeposit = totalDeposit + cashInput.getCount();
				if (entity instanceof Player _player && _player.containerMenu instanceof MoneyModMenus.MenuAccessor _menu) {
					_menu.getSlots().get(1).set(ItemStack.EMPTY);
					_player.containerMenu.broadcastChanges();
				}
			} else if (MoneyModItems.FIVE_CASH.get() == cashInput.getItem()) {
				totalDeposit = totalDeposit + 5 * cashInput.getCount();
				if (entity instanceof Player _player && _player.containerMenu instanceof MoneyModMenus.MenuAccessor _menu) {
					_menu.getSlots().get(1).set(ItemStack.EMPTY);
					_player.containerMenu.broadcastChanges();
				}
			} else if (MoneyModItems.TEN_CASH.get() == cashInput.getItem()) {
				totalDeposit = totalDeposit + 10 * cashInput.getCount();
				if (entity instanceof Player _player && _player.containerMenu instanceof MoneyModMenus.MenuAccessor _menu) {
					_menu.getSlots().get(1).set(ItemStack.EMPTY);
					_player.containerMenu.broadcastChanges();
				}
			} else if (MoneyModItems.TWENTY_CASH.get() == cashInput.getItem()) {
				totalDeposit = totalDeposit + 20 * cashInput.getCount();
				if (entity instanceof Player _player && _player.containerMenu instanceof MoneyModMenus.MenuAccessor _menu) {
					_menu.getSlots().get(1).set(ItemStack.EMPTY);
					_player.containerMenu.broadcastChanges();
				}
			} else if (MoneyModItems.FIFTY_CASH.get() == cashInput.getItem()) {
				totalDeposit = totalDeposit + 50 * cashInput.getCount();
				if (entity instanceof Player _player && _player.containerMenu instanceof MoneyModMenus.MenuAccessor _menu) {
					_menu.getSlots().get(1).set(ItemStack.EMPTY);
					_player.containerMenu.broadcastChanges();
				}
			} else if (MoneyModItems.HUNDRED_CASH.get() == cashInput.getItem()) {
				totalDeposit = totalDeposit + 100 * cashInput.getCount();
				if (entity instanceof Player _player && _player.containerMenu instanceof MoneyModMenus.MenuAccessor _menu) {
					_menu.getSlots().get(1).set(ItemStack.EMPTY);
					_player.containerMenu.broadcastChanges();
				}
			} else if (MoneyModItems.COIN.get() == cashInput.getItem()) {
				totalDeposit = totalDeposit + 0.1 * cashInput.getCount();
				if (entity instanceof Player _player && _player.containerMenu instanceof MoneyModMenus.MenuAccessor _menu) {
					_menu.getSlots().get(1).set(ItemStack.EMPTY);
					_player.containerMenu.broadcastChanges();
				}
			}
			{
				final String _tagName = "credit";
				final double _tagValue = (totalDeposit + (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof MoneyModMenus.MenuAccessor _menu22 ? _menu22.getSlots().get(0).getItem() : ItemStack.EMPTY)
						.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("credit", 0));
				CustomData.update(DataComponents.CUSTOM_DATA, (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof MoneyModMenus.MenuAccessor _menu24 ? _menu24.getSlots().get(0).getItem() : ItemStack.EMPTY),
						tag -> tag.putDouble(_tagName, _tagValue));
			}
		}
	}
}