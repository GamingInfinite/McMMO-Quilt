package com.voidbaroness.mcmmo_quilt.skillData;

import com.voidbaroness.mcmmo_quilt.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.nbt.NbtCompound;

public class SkillData {
	public static int addXPToSkill(IEntityDataSaver player, String skill, int amount) {
		NbtCompound nbt = player.getPersistentData();
		int xp = nbt.getInt(skill+"xp");
		xp += amount;

		nbt.putInt(skill+"xp", xp);
		checkSkillLevel(nbt, skill, xp);
		return xp;
	}

	public static int xpToNext(NbtCompound player, String skill) {
		ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
		double exponent = config.exponent;
		double multiplier = config.multiplier;
		int base = config.base;
		int level = player.getInt(skill+"level");
		return (int) Math.floor(multiplier * Math.pow(level, exponent) + base);
	}

	private static void checkSkillLevel(NbtCompound player, String skill, int xp) {
		int level = player.getInt(skill+"level");
		int xptonext = xpToNext(player, skill);
		if (xp > xptonext) {
			xp -= xptonext;
			player.putInt(skill+"xp", xp);
			level++;
			player.putInt(skill+"level", level);
			checkSkillLevel(player, skill, xp);
		}
	}
}
