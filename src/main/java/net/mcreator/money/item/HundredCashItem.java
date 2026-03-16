package net.mcreator.money.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class HundredCashItem extends Item {
	public HundredCashItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}
}