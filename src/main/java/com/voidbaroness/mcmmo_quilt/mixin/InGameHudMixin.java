package com.voidbaroness.mcmmo_quilt.mixin;

import com.voidbaroness.mcmmo_quilt.ui.EXPBars;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = InGameHud.class, priority = 800)
public abstract class InGameHudMixin {
	@Final
	@Shadow
	private MinecraftClient client;

	private EXPBars expBars;
	@Inject(at = @At("TAIL"), method = "render")
	public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
		expBars = new EXPBars(matrixStack);

		boolean barsVisible = !client.options.hudHidden;
		expBars.renderBars();
	}
}
