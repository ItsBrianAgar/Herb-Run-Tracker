package com.herb.yield.tracker;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("herbYieldTracker")
public interface HerbYieldTrackerConfig extends Config
{
	@ConfigItem(
			keyName = "daysToShow",
			name = "Days to Show",
			description = "Number of days to display in the daily yields summary"
	)
	default int daysToShow()
	{
		return 7;
	}
}
