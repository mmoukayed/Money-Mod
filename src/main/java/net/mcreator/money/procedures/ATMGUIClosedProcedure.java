package net.mcreator.money.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.money.network.MoneyModVariables;
import net.mcreator.money.init.MoneyModMenus;

public class ATMGUIClosedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && _player.containerMenu instanceof MoneyModMenus.MenuAccessor _menu)
			_menu.sendMenuStateUpdate(_player, 0, "PIN", "", true);
		MoneyModVariables.CardVerified = false;
	}
}