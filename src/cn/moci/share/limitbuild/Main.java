package cn.moci.share.limitbuild;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor {

		public static String pluginName = "LimitBuild";
		public static String pluginVersion = "公开版";

		public static List<String> wolrd;
		public static String msg;
		public static String perm;
		public static String perm_cmd;

		public static boolean blockBreak;
		public static boolean blockPlace;
		public static boolean monsters;
		public static boolean others;
		public static boolean pi;
		public static boolean ei;
		public static boolean pbf;
		public static boolean pbe;

		public static String color(final String text) {
				return text.replaceAll("&", "§");
		}

		@Override
		public void onEnable() {
				pluginMessage("已启用。");
				initConfig();
				Bukkit.getPluginManager().registerEvents(new Listeners(), this);
				this.getCommand("limitbuild").setExecutor(this);
		}

		@Override
		public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
				if (cmd.getName().equalsIgnoreCase("limitbuild")) {
						if (!sender.hasPermission(Main.perm_cmd)) {
								return true;
						}
						if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
								initConfig();
								sender.sendMessage("§7配置文件重载完成。");
								return true;
						} else {
								sender.sendMessage("§7输入 §3/limitbuild reload §7重载配置文件。");
								return true;
						}

				}
				return true;
		}

		private void initConfig() {
				Main.wolrd = getConfig().getStringList("worlds");
				Main.msg = color(getConfig().getString("msg"));
				Main.perm = getConfig().getString("permission.pass");
				Main.perm_cmd = getConfig().getString("permission.command");
				Main.blockBreak = getConfig().getBoolean("Settings.BlockBreakEvent");
				Main.blockPlace = getConfig().getBoolean("Settings.BlockPlaceEvent");
				Main.monsters = getConfig().getBoolean("Settings.EntityDamageByEntityEvent.Monsters");
				Main.others = getConfig().getBoolean("Settings.EntityDamageByEntityEvent.Others");
				Main.pi = getConfig().getBoolean("Settings.PlayerInteractEvent");
				Main.ei = getConfig().getBoolean("Settings.EntityInteractEvent");
				Main.pbf = getConfig().getBoolean("Settings.PlayerBucketFillEvent");
				Main.pbe = getConfig().getBoolean("Settings.PlayerBucketEmptyEvent");
				saveDefaultConfig();
				reloadConfig();
		}

		private void pluginMessage(String s) {
				this.getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "!" + ChatColor.DARK_GRAY + "] " + ChatColor.AQUA + pluginName + " " + ChatColor.WHITE + pluginVersion + ChatColor.GRAY + s);
		}

}
