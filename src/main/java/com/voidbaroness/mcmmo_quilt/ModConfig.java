package com.voidbaroness.mcmmo_quilt;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "mcmmo_quilt")
public class ModConfig implements ConfigData {
	@ConfigEntry.Gui.PrefixText
	@ConfigEntry.ColorPicker(allowAlpha = true)
	public int backgroundColor = 0xFF000000;
	@ConfigEntry.Gui.Tooltip
    public double exponent = 1.8;
	@ConfigEntry.Gui.Tooltip
	public double multiplier = 0.1;
	@ConfigEntry.Gui.Tooltip
	public int base = 2000;

	public static class TextSettings {
		@ConfigEntry.Gui.Tooltip
		public boolean showText = false;
		@ConfigEntry.ColorPicker(allowAlpha = true)
		public int textColor = 0xB3FFFFFF;
	}

	public static class GoodThings {
		@ConfigEntry.ColorPicker(allowAlpha = true)
		public int healthColor = 0xFFD32F2F;
	}

	@ConfigEntry.Category("goodThings")
	@ConfigEntry.Gui.TransitiveObject
	public GoodThings goodThings = new GoodThings();
	@ConfigEntry.Category("textSettings")
	@ConfigEntry.Gui.TransitiveObject
	public TextSettings textSettings = new TextSettings();
}
