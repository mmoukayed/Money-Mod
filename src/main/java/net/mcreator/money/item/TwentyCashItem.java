package net.mcreator.money.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class TwentyCashItem extends Item {
	public TwentyCashItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}
}