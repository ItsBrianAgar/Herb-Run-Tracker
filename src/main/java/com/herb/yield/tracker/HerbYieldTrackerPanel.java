package com.herb.yield.tracker;

import net.runelite.client.ui.PluginPanel;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class HerbYieldTrackerPanel extends PluginPanel {
    private final JPanel yieldPanel = new JPanel();

    public HerbYieldTrackerPanel() {
        setLayout(new BorderLayout());
        yieldPanel.setLayout(new BoxLayout(yieldPanel, BoxLayout.Y_AXIS));
        add(yieldPanel, BorderLayout.NORTH);
    }

    public void updateYields(Map<String, Map<String, Integer>> dailyYields) {
        yieldPanel.removeAll();

        for (Map.Entry<String, Map<String, Integer>> entry : dailyYields.entrySet()) {
            String date = entry.getKey();
            Map<String, Integer> herbYields = entry.getValue();

            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
            dayPanel.setBorder(BorderFactory.createTitledBorder(date));

            for (Map.Entry<String, Integer> herbEntry : herbYields.entrySet()) {
                String herb = herbEntry.getKey();
                int yield = herbEntry.getValue();
                dayPanel.add(new JLabel(herb + ": " + yield));
            }

            yieldPanel.add(dayPanel);
        }

        yieldPanel.revalidate();
        yieldPanel.repaint();
    }
}
