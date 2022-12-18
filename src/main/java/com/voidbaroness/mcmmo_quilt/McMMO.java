package com.voidbaroness.mcmmo_quilt;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class McMMO implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("McMMO");

	@Override
	public void onInitialize(ModContainer mod) {
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
	}
}
