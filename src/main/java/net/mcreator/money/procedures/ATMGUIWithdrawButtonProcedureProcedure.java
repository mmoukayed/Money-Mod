package net.mcreator.money.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.component.DataComponents;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.money.network.MoneyModVariables;
import net.mcreator.money.init.MoneyModMenus;
import net.mcreator.money.init.MoneyModItems;

public class ATMGUIWithdrawButtonProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double withdrawAmount = 0;
		double denominations100 = 0;
		double denominations50 = 0;
		double denominations20 = 0;
		double denominations10 = 0;
		double denominations5 = 0;
		double denominations1 = 0;
		withdrawAmount = new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((entity instanceof Player _entity0 && _entity0.containerMenu instanceof MoneyModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "withdrawAmount", "") : "");
		if (MoneyModVariables.CardVerified && !((entity instanceof Player _entity1 && _entity1.containerMenu instanceof MoneyModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "withdrawAmount", "") : "").isEmpty()) {
			if (withdrawAmount <= (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof MoneyModMenus.MenuAccessor _menu2 ? _menu2.getSlots().get(0).getItem() : ItemStack.EMPTY)
					.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("credit", 0)) {
				{
					final String _tagName = "credit";
					final double _tagValue = ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof MoneyModMenus.MenuAccessor _menu4 ? _menu4.getSlots().get(0).getItem() : ItemStack.EMPTY)
							.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("credit", 0) - withdrawAmount);
					CustomData.update(DataComponents.CUSTOM_DATA, (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof MoneyModMenus.MenuAccessor _menu6 ? _menu6.getSlots().get(0).getItem() : ItemStack.EMPTY),
							tag -> tag.putDouble(_tagName, _tagValue));
				}
				denominations100 = Math.floor(withdrawAmount / 100);
				withdrawAmount = withdrawAmount - denominations100 * 100;
				denominations50 = Math.floor(withdrawAmount / 50);
				withdrawAmount = withdrawAmount - denominations50 * 50;
				denominations20 = Math.floor(withdrawAmount / 20);
				withdrawAmount = withdrawAmount - denominations20 * 20;
				denominations10 = Math.floor(withdrawAmount / 10);
				withdrawAmount = withdrawAmount - denominations10 * 10;
				denominations5 = Math.floor(withdrawAmount / 5);
				withdrawAmount = withdrawAmount - denominations5 * 5;
				denominations1 = Math.floor(withdrawAmount / 1);
				withdrawAmount = withdrawAmount - denominations1 * 1;
				if (entity instanceof Player _player && _player.containerMenu instanceof MoneyModMenus.MenuAccessor _menu) {
					ItemStack _setstack8 = new ItemStack(MoneyModItems.HUNDRED_CASH.get()).copy();
					_setstack8.setCount((int) denominations100);
					_menu.getSlots().get(8).set(_setstack8);
					ItemStack _setstack9 = new ItemStack(MoneyModItems.FIFTY_CASH.get()).copy();
					_setstack9.setCount((int) denominations50);
					_menu.getSlots().get(7).set(_setstack9);
					ItemStack _setstack10 = new ItemStack(MoneyModItems.TWENTY_CASH.get()).copy();
					_setstack10.setCount((int) denominations20);
					_menu.getSlots().get(6).set(_setstack10);
					ItemStack _setstack11 = new ItemStack(MoneyModItems.TEN_CASH.get()).copy();
					_setstack11.setCount((int) denominations10);
					_menu.getSlots().get(5).set(_setstack11);
					ItemStack _setstack12 = new ItemStack(MoneyModItems.FIVE_CASH.get()).copy();
					_setstack12.setCount((int) denominations5);
					_menu.getSlots().get(4).set(_setstack12);
					ItemStack _setstack13 = new ItemStack(MoneyModItems.ONE_CASH.get()).copy();
					_setstack13.setCount((int) denominations1);
					_menu.getSlots().get(3).set(_setstack13);
					ItemStack _setstack14 = new ItemStack(MoneyModItems.COIN.get()).copy();
					_setstack14.setCount((int) (withdrawAmount * 10));
					_menu.getSlots().get(9).set(_setstack14);
					_player.containerMenu.broadcastChanges();
				}
			} else {
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("tellraw " + entity.getStringUUID() + " {\"text\":\"Insufficient Funds!\",\"color\":\"red\"}"));
			}
		}
	}
}