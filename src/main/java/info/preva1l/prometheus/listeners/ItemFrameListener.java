package info.preva1l.prometheus.listeners;

import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ItemFrameListener implements Listener {
    @EventHandler
    public void onItemFrameClick(PlayerItemFrameChangeEvent event) {
        if (event.getAction() != PlayerItemFrameChangeEvent.ItemFrameChangeAction.ROTATE) return;
        if (!event.getPlayer().isSneaking()) return;

        event.setCancelled(true);
        event.getItemFrame().setVisible(!event.getItemFrame().isVisible());
    }
}
