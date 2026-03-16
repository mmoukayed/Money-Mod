package net.mcreator.money.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class FiveCashItem extends Item {
	public FiveCashItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}
}