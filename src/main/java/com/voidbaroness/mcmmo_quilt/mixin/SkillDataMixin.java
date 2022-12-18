package com.voidbaroness.mcmmo_quilt.mixin;

import com.voidbaroness.mcmmo_quilt.skillData.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Entity.class)
public abstract class SkillDataMixin implements IEntityDataSaver {
	private NbtCompound persistentData;

	@Override
	public NbtCompound getPersistentData() {
		if (this.persistentData == null) {
			this.persistentData = new NbtCompound();
		}

		return persistentData;
	}

	@Inject(at= @At("HEAD"), method = "writeNBT")
	protected void writeNBT(NbtCompound nbt, CallbackInfoReturnable ci) {
		if (persistentData != null) {
			nbt.put("mcmmo_quilt.skilldata", persistentData);
		}
	}

	@Inject(at = @At("HEAD"),method = "readNBT")
	protected void readNBT(NbtCompound nbt, CallbackInfoReturnable ci) {
		if (nbt.contains("mcmmo_quilt.skilldata", 10)) {
			persistentData = nbt.getCompound("mcmmo_quilt.skilldata");
		}
	}
}
