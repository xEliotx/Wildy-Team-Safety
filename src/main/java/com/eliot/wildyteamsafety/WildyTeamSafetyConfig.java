package com.eliot.wildyteamsafety;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("wildyteamsafety")
public interface WildyTeamSafetyConfig extends Config
{
	@ConfigItem(
			keyName = "protectedNames",
			name = "Protected Names",
			description = "Comma-separated list of teammates to highlight"
	)
	default String protectedNames()
	{
		return "";
	}
}