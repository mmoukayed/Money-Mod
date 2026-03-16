package net.mcreator.money.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class OneCashItem extends Item {
	public OneCashItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}
}