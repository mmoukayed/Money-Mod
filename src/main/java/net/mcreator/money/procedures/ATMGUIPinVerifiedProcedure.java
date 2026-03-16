package net.mcreator.money.procedures;

import net.mcreator.money.network.MoneyModVariables;

public class ATMGUIPinVerifiedProcedure {
	public static boolean execute() {
		return MoneyModVariables.CardVerified;
	}
}