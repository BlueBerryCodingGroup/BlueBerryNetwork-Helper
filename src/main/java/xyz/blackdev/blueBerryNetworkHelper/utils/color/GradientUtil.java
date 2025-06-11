package xyz.blackdev.blueBerryNetworkHelper.utils.color;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for creating gradient text with optional styles using MiniMessage.
 */
public final class GradientUtil {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    private GradientUtil() {
        // Prevent instantiation
    }

    /**
     * Creates a gradient text component with the specified colors and styles.
     *
     * @param startColor       the starting color of the gradient
     * @param endColor         the ending color of the gradient
     * @param text             the text to style
     * @param isBold           true to make text bold
     * @param isItalic         true to make text italic
     * @param isUnderline      true to underline text
     * @param isStrikethrough  true to strikethrough text
     * @return a Component with the gradient and styles applied
     */
    public static @NotNull Component createGradientText(
            @NotNull NamedTextColor startColor,
            @NotNull NamedTextColor endColor,
            @NotNull String text,
            boolean isBold,
            boolean isItalic,
            boolean isUnderline,
            boolean isStrikethrough
    ) {
        String styleTags = buildStyleTags(isBold, isItalic, isUnderline, isStrikethrough);
        String miniMessageText = String.format(
                "<gradient:%s:%s>%s%s</gradient>",
                startColor.asHexString(),
                endColor.asHexString(),
                styleTags,
                text
        );
        return MINI_MESSAGE.deserialize(miniMessageText);
    }

    /**
     * Builds a string of MiniMessage style tags.
     */
    private static @NotNull String buildStyleTags(boolean bold, boolean italic, boolean underline, boolean strikethrough) {
        StringBuilder tags = new StringBuilder();
        if (bold) tags.append("<b>");
        if (italic) tags.append("<i>");
        if (underline) tags.append("<u>");
        if (strikethrough) tags.append("<st>");
        return tags.toString();
    }
}
