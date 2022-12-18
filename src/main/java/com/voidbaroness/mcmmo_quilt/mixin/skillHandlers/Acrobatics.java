package com.voidbaroness.mcmmo_quilt.mixin.skillHandlers;

import com.voidbaroness.mcmmo_quilt.McMMO;
import com.voidbaroness.mcmmo_quilt.skillData.IEntityDataSaver;
import com.voidbaroness.mcmmo_quilt.skillData.SkillData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.Map;

@Mixin(PlayerEntity.class)
public class Acrobatics {
	@Inject(method = "handleFallDamage", at = @At("TAIL"))
	public void handleAcroEXP(float fallDistance, float damageMultiplier, Map<StatusEffect, StatusEffectInstance> activeStatusEffects) {
		StatusEffectInstance statusEffectInstance = activeStatusEffects.get(StatusEffects.JUMP_BOOST);
		float f = statusEffectInstance == null ? 0.0F : (float)(statusEffectInstance.getAmplifier() + 1);
		float expectedDamage = MathHelper.ceil((fallDistance - 3.0F - f) * damageMultiplier);
		McMMO.LOGGER.info(String.valueOf(((IEntityDataSaver)this).getPersistentData().getInt("acrobaticsxp")));
		SkillData.addXPToSkill((IEntityDataSaver)this, "acrobatics", (int)(120*expectedDamage));
	}
}
