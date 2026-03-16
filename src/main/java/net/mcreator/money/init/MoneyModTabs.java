/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.money.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.money.MoneyMod;

public class MoneyModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoneyMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MONEY_MOD_ITEMS = REGISTRY.register("money_mod_items",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.money.money_mod_items")).icon(() -> new ItemStack(MoneyModItems.CREDIT_CARD.get())).displayItems((parameters, tabData) -> {
				tabData.accept(MoneyModItems.CREDIT_CARD.get());
				tabData.accept(MoneyModItems.COIN.get());
				tabData.accept(MoneyModBlocks.CARD_MAKER.get().asItem());
				tabData.accept(MoneyModBlocks.ATM.get().asItem());
				tabData.accept(MoneyModBlocks.EXCHANGE_BLOCK.get().asItem());
				tabData.accept(MoneyModItems.GOLD_CREDIT_CARD.get());
				tabData.accept(MoneyModBlocks.CARDUPGRADER.get().asItem());
				tabData.accept(MoneyModBlocks.PAYMENT_MACHINE.get().asItem());
				tabData.accept(MoneyModItems.ONE_CASH.get());
				tabData.accept(MoneyModItems.FIVE_CASH.get());
				tabData.accept(MoneyModItems.TEN_CASH.get());
				tabData.accept(MoneyModItems.TWENTY_CASH.get());
				tabData.accept(MoneyModItems.FIFTY_CASH.get());
				tabData.accept(MoneyModItems.HUNDRED_CASH.get());
			}).build());
}