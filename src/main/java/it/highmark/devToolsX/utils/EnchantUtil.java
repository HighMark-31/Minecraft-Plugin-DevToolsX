// *************** RESPECT THE LICENSE OF PROJECT ***************
// ---------------     Code by HighMark.it        ----------------
// Link - Site : https://highmark.it | GitHub : https://github.com/HighMark-31/Minecraft-Plugin-DevToolsX/
// -------- I have lost 15 hours of my life for this -------------
// *************** RESPECT THE LICENSE OF PROJECT ***************

package it.highmark.devToolsX.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.util.*;
import java.util.stream.Collectors;

public final class EnchantUtil {
    private EnchantUtil() {}

    public static Enchantment resolve(String token) {
        if (token == null) return null;
        String normalized = token.toLowerCase(Locale.ROOT)
                .replace("minecraft:", "")
                .replace("-", "_")
                .trim();
        Enchantment byKey = Enchantment.getByKey(NamespacedKey.minecraft(normalized));
        if (byKey != null) return byKey;

        Enchantment byName = Enchantment.getByName(normalized.toUpperCase(Locale.ROOT));
        if (byName != null) return byName;

        for (Enchantment e : Enchantment.values()) {
            if (e == null) continue;
            if (e.getKey() != null && e.getKey().getKey().equalsIgnoreCase(normalized)) return e;
            if (e.getName() != null && e.getName().equalsIgnoreCase(normalized)) return e;
        }
        return null;
    }

    public static List<String> suggestions(String prefix) {
        String p = prefix == null ? "" : prefix.toLowerCase(Locale.ROOT);
        return Arrays.stream(Enchantment.values())
                .filter(Objects::nonNull)
                .map(e -> e.getKey() != null ? e.getKey().getKey() : e.getName())
                .filter(Objects::nonNull)
                .map(String::toLowerCase)
                .filter(s -> s.startsWith(p))
                .sorted()
                .collect(Collectors.toList());
    }
}
