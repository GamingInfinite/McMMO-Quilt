package com.voidbaroness.mcmmo_quilt.ui;

import com.voidbaroness.mcmmo_quilt.skillData.IEntityDataSaver;
import com.voidbaroness.mcmmo_quilt.skillData.SkillData;
import com.voidbaroness.mcmmo_quilt.util.ClientProperties;
import com.voidbaroness.mcmmo_quilt.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;

public class EXPBars{
	private final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
	private final MatrixStack stack;
	private final ClientProperties clientProperties = new ClientProperties();
	private final MinecraftClient client = MinecraftClient.getInstance();

	public EXPBars(MatrixStack stack) {
		this.stack = stack;
	}

	public void renderBars() {
		PlayerEntity playerEntity = MinecraftClient.getInstance().player;
		if (playerEntity != null) {
			barBackground();
			acroBar();
		}
	}

	private void renderBar(int x1, int y1, int x2, int y2, int color){
//		if(!config.enableGradient)
			DrawableHelper.fill(stack, x1, y1, x2, y2, color);
//		else
//			DrawableHelperAccessor.callFillGradient(stack, x1, y1, x2, y2, color, Calculations.manipulateColor(color, config.gradientShift), 0);
	}

	private void barBackground(){
		renderBar(clientProperties.baseStartW, clientProperties.baseStartH, clientProperties.baseEndW, clientProperties.baseEndH, config.backgroundColor);
	}

	private void acroBar(){
		IEntityDataSaver player = (IEntityDataSaver) MinecraftClient.getInstance().player;
		int acroXP = player.getPersistentData().getInt("acrobaticsxp");
		int acroLevelXP = SkillData.xpToNext(player.getPersistentData(), "acrobatics");
		renderBar(clientProperties.baseStartW, clientProperties.baseStartH, clientProperties.baseRelativeEndW(acroXP, acroLevelXP), clientProperties.baseEndH, config.goodThings.healthColor);
	}

	private void barText() {
		String value = "";

		if (config.textSettings.showText) {
			int textX = clientProperties.baseEndW - client.textRenderer.getWidth(value);
			int textY = clientProperties.baseStartH + 1;

			client.textRenderer.draw(stack, value, textX, textY, config.textSettings.textColor);
		}
	}
}
