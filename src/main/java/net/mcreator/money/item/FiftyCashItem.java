package net.mcreator.money.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class FiftyCashItem extends Item {
	public FiftyCashItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}
}