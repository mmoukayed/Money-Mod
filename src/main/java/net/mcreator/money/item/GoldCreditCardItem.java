package net.mcreator.money.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class GoldCreditCardItem extends Item {
	public GoldCreditCardItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC).stacksTo(1));
	}

	@Override
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}
}