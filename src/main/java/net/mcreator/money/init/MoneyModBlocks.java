/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.money.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.money.block.PaymentMachineBlock;
import net.mcreator.money.block.ExchangeBlockBlock;
import net.mcreator.money.block.CardupgraderBlock;
import net.mcreator.money.block.CardMakerBlock;
import net.mcreator.money.block.ATMBlock;
import net.mcreator.money.MoneyMod;

import java.util.function.Function;

public class MoneyModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(MoneyMod.MODID);
	public static final DeferredBlock<Block> CARD_MAKER;
	public static final DeferredBlock<Block> ATM;
	public static final DeferredBlock<Block> EXCHANGE_BLOCK;
	public static final DeferredBlock<Block> CARDUPGRADER;
	public static final DeferredBlock<Block> PAYMENT_MACHINE;
	static {
		CARD_MAKER = register("card_maker", CardMakerBlock::new);
		ATM = register("atm", ATMBlock::new);
		EXCHANGE_BLOCK = register("exchange_block", ExchangeBlockBlock::new);
		CARDUPGRADER = register("cardupgrader", CardupgraderBlock::new);
		PAYMENT_MACHINE = register("payment_machine", PaymentMachineBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}