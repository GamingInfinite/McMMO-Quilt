package com.voidbaroness.mcmmo_quilt.util;

import net.minecraft.util.math.MathHelper;

public class Calculations {
	public static int relativeW(int start, int end, int value, int total){
		return value < total ? MathHelper.ceil(start + ((float) (end - start) / total * value)) : end;
	}

	public static int getPreciseInt(float number){
		return MathHelper.ceil(number * 10000.0F);
	}
}
