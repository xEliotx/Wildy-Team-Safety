package com.eliot.wildyteamsafety;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamplePluginTest
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("Loading Wildy Team Safety plugin...");
		System.setProperty("runelite.developerMode", "true");

		ExternalPluginManager.loadBuiltin(WildyTeamSafetyPlugin.class);

		RuneLite.main(args);
	}
}