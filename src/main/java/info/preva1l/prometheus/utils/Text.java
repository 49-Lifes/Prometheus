package info.preva1l.prometheus.utils;

import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Text {
    private final Pattern HEX_PATTERN = Pattern.compile("&#(\\w{5}[0-9a-fA-F])");

    /**
     * Colorize a list. (Useful for lore)
     *
     * @param list List typeof String
     * @return Colorized List typeof String
     */
    public List<String> colorizeList(List<String> list) {
        if (list == null) return null;
        if (list.isEmpty()) return null;
        List<String> ret = new ArrayList<>();
        for (String line : list) ret.add(colorize(line));
        return ret;
    }

    /**
     * Converts MiniMessage to legacy colour codes.
     *
     * @param message message with mini message formatting
     * @return string with legacy formatting (not colorized)
     */
    public String miniMessageToLegacy(String message) {
        message = message.replace("<dark_red>", "&4");
        message = message.replace("<red>", "&c");
        message = message.replace("<gold>", "&6");
        message = message.replace("<yellow>", "&e");
        message = message.replace("<dark_green>", "&2");
        message = message.replace("<green>", "&a");
        message = message.replace("<aqua>", "&b");
        message = message.replace("<dark_aqua>", "&3");
        message = message.replace("<dark_blue>", "&1");
        message = message.replace("<blue>", "&9");
        message = message.replace("<light_purple>", "&d");
        message = message.replace("<dark_purple>", "&5");
        message = message.replace("<white>", "&f");
        message = message.replace("<gray>", "&7");
        message = message.replace("<dark_gray>", "&8");
        message = message.replace("<black>", "&0");
        message = message.replace("<b>", "&l");
        message = message.replace("<bold>", "&l");
        message = message.replace("<obf>", "&k");
        message = message.replace("<obfuscated>", "&k");
        message = message.replace("<st>", "&m");
        message = message.replace("<strikethrough>", "&m");
        message = message.replace("<u>", "&n");
        message = message.replace("<underline>", "&n");
        message = message.replace("<i>", "&o");
        message = message.replace("<italic>", "&o");
        message = message.replace("<reset>", "&r");
        message = message.replace("<r>", "&r");


        Pattern pattern = Pattern.compile("<#[a-fA-F0-9]{6}>");
        Matcher match = pattern.matcher(message);
        String code = message;
        while (match.find()) {
            code = message.substring(match.start(), match.end());
            code = code.replace("<", "&");
            code = code.replace(">", "");
        }
        return message.replaceAll("<#[a-fA-F0-9]{6}>", code);
    }

    /**
     * Colorize  a string.
     *
     * @param text String with color codes or hex codes.
     * @return Colorized String
     */
    public String colorize(String text) {
        text = miniMessageToLegacy(text);
        Matcher matcher = HEX_PATTERN.matcher(text);
        StringBuilder buffer = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(buffer, ChatColor.of("#" + matcher.group(1)).toString());
        }

        return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
    }

    /**
     * Strip color codes from a string. (Doesn't remove hex codes)
     *
     * @param str String with color codes.
     * @return String without color codes.
     */
    public String removeColorCodes(String str) {
        str = str.replaceAll(HEX_PATTERN.pattern(), "");
        return str.replaceAll("&(?! ).", "");
    }
}