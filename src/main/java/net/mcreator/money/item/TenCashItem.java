package net.mcreator.money.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class TenCashItem extends Item {
	public TenCashItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}
}