package com.eliot.wildyteamsafety;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.Perspective;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import javax.inject.Singleton;

@Singleton
public class WildyTeamSafetyOverlay extends Overlay

{
    private final Client client;
    private final WildyTeamSafetyPlugin plugin;

    @Inject
    public WildyTeamSafetyOverlay(Client client, WildyTeamSafetyPlugin plugin)
    {
        this.client = client;
        this.plugin = plugin;

        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        graphics.setColor(Color.WHITE);
        graphics.drawString("Wildy Team Safety overlay active", 20, 20);


        for (Player player : client.getPlayers())
        {
            if (player == null || player == client.getLocalPlayer())
            {
                continue;
            }

            if (!plugin.isProtected(player))
            {
                continue;
            }

            Polygon tile = Perspective.getCanvasTilePoly(client, player.getLocalLocation());

            if (tile != null)
            {
                graphics.setColor(new Color(0, 255, 0, 60));
                graphics.fill(tile);

                graphics.setColor(new Color(0, 255, 0, 180));
                graphics.draw(tile);
            }
        }

        return null;
    }
}