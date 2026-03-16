package net.mcreator.money.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class CoinItem extends Item {
	public CoinItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}
}