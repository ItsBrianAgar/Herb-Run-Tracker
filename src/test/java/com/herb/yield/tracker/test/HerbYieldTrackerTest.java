package com.herb.yield.tracker.test;

import com.herb.yield.tracker.HerbYieldTrackerPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HerbYieldTrackerTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HerbYieldTrackerPlugin.class);
		RuneLite.main(args);
	}
}