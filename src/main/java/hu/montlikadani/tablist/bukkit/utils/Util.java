package hu.montlikadani.tablist.bukkit.utils;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import hu.montlikadani.tablist.bukkit.TabList;

public class Util {

	public static void logConsole(String msg) {
		logConsole(msg, true);
	}

	public static void logConsole(String msg, boolean loaded) {
		logConsole(Level.INFO, msg, loaded);
	}

	public static void logConsole(Level level, String msg) {
		logConsole(level, msg, true);
	}

	public static void logConsole(Level level, String msg, boolean loaded) {
		if ((!loaded || TabList.getInstance().getC().getBoolean("logconsole", true)) && msg != null
				&& !msg.trim().isEmpty()) {
			Bukkit.getLogger().log(level != null ? level : Level.INFO, "[TabList] " + msg);
		}
	}

	public static String colorMsg(String msg) {
		return msg == null ? "" : ChatColor.translateAlternateColorCodes('&', msg);
	}

	public static void sendMsg(CommandSender sender, String s) {
		if (s != null && !s.isEmpty()) {
			if (s.contains("\n")) {
				for (String msg : s.split("\n")) {
					sender.sendMessage(msg);
				}
			} else {
				sender.sendMessage(s);
			}
		}
	}

	public static String stripColor(String str) {
		if (str.contains("&a"))
			str = str.replace("&a", "");

		if (str.contains("&b"))
			str = str.replace("&b", "");

		if (str.contains("&c"))
			str = str.replace("&c", "");

		if (str.contains("&d"))
			str = str.replace("&d", "");

		if (str.contains("&e"))
			str = str.replace("&e", "");

		if (str.contains("&f"))
			str = str.replace("&f", "");

		if (str.contains("&1"))
			str = str.replace("&1", "");

		if (str.contains("&2"))
			str = str.replace("&2", "");

		if (str.contains("&3"))
			str = str.replace("&3", "");

		if (str.contains("&4"))
			str = str.replace("&4", "");

		if (str.contains("&5"))
			str = str.replace("&5", "");

		if (str.contains("&6"))
			str = str.replace("&6", "");

		if (str.contains("&7"))
			str = str.replace("&7", "");

		if (str.contains("&8"))
			str = str.replace("&8", "");

		if (str.contains("&9"))
			str = str.replace("&9", "");

		if (str.contains("&0"))
			str = str.replace("&0", "");

		if (str.contains("&n"))
			str = str.replace("&n", "");

		if (str.contains("&o"))
			str = str.replace("&o", "");

		if (str.contains("&m"))
			str = str.replace("&m", "");

		if (str.contains("&k"))
			str = str.replace("&k", "");

		if (str.contains("&l"))
			str = str.replace("&l", "");

		str = ChatColor.stripColor(str);
		return str;
	}
}
