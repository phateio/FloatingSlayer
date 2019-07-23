package github.phateio.floatingslayer;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FloatingSlayer extends JavaPlugin {

	@Override
	public void onEnable() {
		FloatingSlayer plugin = this;

		getServer().getPluginManager().registerEvents(new Listener() {

			@EventHandler
			public void onKick(PlayerKickEvent event) {
				Player player = event.getPlayer();
				String playerName = player.getName();
				String reason = event.getReason();

				if (!reason.equalsIgnoreCase("Flying is not enabled on this server")) {
					return;
				}

				event.setCancelled​(true);

				player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 5));
				plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
					player.kickPlayer("You are kicked for floating too long!");
				}, 20L);
			}
		}, this);
	}

	@Override
	public void onDisable() {
	}
}
