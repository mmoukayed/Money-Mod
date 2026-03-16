package net.mcreator.money.procedures;

import net.mcreator.money.network.MoneyModVariables;

public class ATMGUIPinVerifiedNegatedProcedure {
	public static boolean execute() {
		return !MoneyModVariables.CardVerified;
	}
}