package com.herb.yield.tracker;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;
import javax.swing.SwingUtilities;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@PluginDescriptor(
		name = "Herb Yield Tracker"
)
public class HerbYieldTrackerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private HerbYieldTrackerConfig config;

	@Inject
	private ConfigManager configManager;

	@Inject
	private ClientToolbar clientToolbar;

	private HerbYieldTrackerPanel panel;
	private NavigationButton navButton;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Herb Yield Tracker started!");
		panel = new HerbYieldTrackerPanel();
		navButton = NavigationButton.builder()
				.tooltip("Herb Yield Tracker")
				.icon(ImageUtil.loadImageResource(getClass(), "/icon.png"))
				.priority(5)
				.panel(panel)
				.build();
		clientToolbar.addNavigation(navButton);

		// Update panel with dummy data immediately
		updatePanel();
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Herb Yield Tracker stopped!");
		clientToolbar.removeNavigation(navButton);
	}

	private Map<String, Map<String, Integer>> getDailyYields()
	{
		// Dummy data for testing
		Map<String, Map<String, Integer>> dummyData = new TreeMap<>();

		// Example data for the last few days
		Map<String, Integer> day1 = new HashMap<>();
		day1.put("Guam", 5);
		day1.put("Marrentill", 3);
		dummyData.put(LocalDate.now().toString(), day1);

		Map<String, Integer> day2 = new HashMap<>();
		day2.put("Tarromin", 2);
		day2.put("Harralander", 4);
		dummyData.put(LocalDate.now().minusDays(1).toString(), day2);

		Map<String, Integer> day3 = new HashMap<>();
		day3.put("Ranarr", 7);
		day3.put("Toadflax", 1);
		dummyData.put(LocalDate.now().minusDays(2).toString(), day3);

		return dummyData;
	}

	private void updatePanel()
	{
		SwingUtilities.invokeLater(() -> {
			panel.updateYields(getDailyYields());
		});
	}

	@Provides
	HerbYieldTrackerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(HerbYieldTrackerConfig.class);
	}
}
