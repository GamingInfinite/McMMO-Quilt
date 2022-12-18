package com.voidbaroness.mcmmo_quilt.util;

import net.minecraft.client.MinecraftClient;

public class ClientProperties {
	public final int baseStartW;
	public final int baseEndW;
	public final int baseStartH;
	public final int baseEndH;

	public ClientProperties() {
		MinecraftClient client = MinecraftClient.getInstance();
		int scaledWidth = client.getWindow().getScaledWidth();
		int scaledHeight = client.getWindow().getScaledHeight();

		baseStartW = scaledWidth / 2 - 91;
		baseEndW = baseStartW + 182;
		baseStartH = 20;
		baseEndH = baseStartH + 9;
	}

	public int baseRelativeEndW(int value, int total){
		return Calculations.relativeW(baseStartW, baseEndW, value, total);
	}

	public int baseRelativeStartW(int value, int total){
		return Calculations.relativeW(baseEndW, baseStartW, value, total);
	}

	public int baseRelativeEndW(float value, float total){
		return baseRelativeEndW(Calculations.getPreciseInt(value), Calculations.getPreciseInt(total));
	}

	public int baseRelativeStartW(float value, float total){
		return baseRelativeStartW(Calculations.getPreciseInt(value), Calculations.getPreciseInt(total));
	}
}
