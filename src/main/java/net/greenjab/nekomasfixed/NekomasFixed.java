package net.greenjab.nekomasfixed;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NekomasFixed implements ModInitializer {
	public static final String MOD_NAME = "Nekomas' Fixed Minecraft";
	public static final String NAMESPACE = "nekomasfixed";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAMESPACE);

	@Override
	public void onInitialize() {
		// Ported one feature at a time from reference/ (see features.md). Feature
		// registration goes here as each one is re-enabled.
		LOGGER.info("[{}] loaded (1.21.1 port, scaffold)", MOD_NAME);
	}
}
