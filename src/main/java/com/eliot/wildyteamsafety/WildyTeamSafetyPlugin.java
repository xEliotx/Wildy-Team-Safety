package com.eliot.wildyteamsafety;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.callback.ClientThread;

@PluginDescriptor(
		name = "Wildy Team Safety",
		description = "Highlights friends and team members in the wilderness",
		tags = {"pvp", "wilderness", "team"}
)
public class WildyTeamSafetyPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private WildyTeamSafetyConfig config;

	@Inject
	private ClientThread clientThread;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private WildyTeamSafetyOverlay overlay;

	@Override
	protected void startUp() throws Exception
	{

		clientThread.invokeLater(() ->
		{
			overlayManager.add(overlay);
		});
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	boolean isProtected(Player player)
	{
		if (player == null || player.getName() == null)
		{
			return false;
		}

		String name = Text.standardize(player.getName());

		for (String protectedName : config.protectedNames().split(","))
		{
			if (name.equals(Text.standardize(protectedName.trim())))
			{
				return true;
			}
		}

		return false;
	}

	@Provides
	WildyTeamSafetyConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(WildyTeamSafetyConfig.class);
	}
}