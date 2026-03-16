package net.mcreator.money.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

import net.mcreator.money.init.MoneyModMenus;
import net.mcreator.money.init.MoneyModItems;

public class CardmakerguibuttonProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && _player.containerMenu instanceof MoneyModMenus.MenuAccessor _menu) {
			ItemStack _setstack0 = new ItemStack(MoneyModItems.CREDIT_CARD.get()).copy();
			_setstack0.setCount(1);
			_menu.getSlots().get(0).set(_setstack0);
			_player.containerMenu.broadcastChanges();
		}
		{
			final String _tagName = "PIN";
			final String _tagValue = ((entity instanceof Player _entity1 && _entity1.containerMenu instanceof MoneyModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "PIN", "") : "");
			CustomData.update(DataComponents.CUSTOM_DATA, (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof MoneyModMenus.MenuAccessor _menu2 ? _menu2.getSlots().get(0).getItem() : ItemStack.EMPTY),
					tag -> tag.putString(_tagName, _tagValue));
		}
		{
			final String _tagName = "credit";
			final double _tagValue = 0;
			CustomData.update(DataComponents.CUSTOM_DATA, (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof MoneyModMenus.MenuAccessor _menu4 ? _menu4.getSlots().get(0).getItem() : ItemStack.EMPTY),
					tag -> tag.putDouble(_tagName, _tagValue));
		}
	}
}