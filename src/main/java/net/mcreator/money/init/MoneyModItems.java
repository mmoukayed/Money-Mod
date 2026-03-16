/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.money.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.money.item.*;
import net.mcreator.money.block.PaymentMachineBlock;
import net.mcreator.money.MoneyMod;

import java.util.function.Function;

public class MoneyModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(MoneyMod.MODID);
	public static final DeferredItem<Item> CREDIT_CARD;
	public static final DeferredItem<Item> COIN;
	public static final DeferredItem<Item> CARD_MAKER;
	public static final DeferredItem<Item> ATM;
	public static final DeferredItem<Item> EXCHANGE_BLOCK;
	public static final DeferredItem<Item> GOLD_CREDIT_CARD;
	public static final DeferredItem<Item> CARDUPGRADER;
	public static final DeferredItem<Item> PAYMENT_MACHINE;
	public static final DeferredItem<Item> ONE_CASH;
	public static final DeferredItem<Item> FIVE_CASH;
	public static final DeferredItem<Item> TEN_CASH;
	public static final DeferredItem<Item> TWENTY_CASH;
	public static final DeferredItem<Item> FIFTY_CASH;
	public static final DeferredItem<Item> HUNDRED_CASH;
	static {
		CREDIT_CARD = register("credit_card", CreditCardItem::new);
		COIN = register("coin", CoinItem::new);
		CARD_MAKER = block(MoneyModBlocks.CARD_MAKER);
		ATM = block(MoneyModBlocks.ATM);
		EXCHANGE_BLOCK = block(MoneyModBlocks.EXCHANGE_BLOCK);
		GOLD_CREDIT_CARD = register("gold_credit_card", GoldCreditCardItem::new);
		CARDUPGRADER = block(MoneyModBlocks.CARDUPGRADER);
		PAYMENT_MACHINE = register("payment_machine", properties -> new PaymentMachineBlock.Item(properties.fireResistant()));
		ONE_CASH = register("one_cash", OneCashItem::new);
		FIVE_CASH = register("five_cash", FiveCashItem::new);
		TEN_CASH = register("ten_cash", TenCashItem::new);
		TWENTY_CASH = register("twenty_cash", TwentyCashItem::new);
		FIFTY_CASH = register("fifty_cash", FiftyCashItem::new);
		HUNDRED_CASH = register("hundred_cash", HundredCashItem::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), properties);
	}
}