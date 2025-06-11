package xyz.blackdev.blueBerryNetworkHelper.utils.color;

import java.util.Map;

/**
 * Example usage:
 * <pre>
 *     GradientLogger logger = new GradientLogger.Builder()
 *         .setPrefix("[BBNH] ")
 *         .setGradientPrefix(true)
 *         .build();
 *     logger.log("Starting BlueBerryNetworkHelper...", "blue", "purple");
 *     logger.log("Custom hex!", "#FF00FF", "#00FFFF");
 * </pre>
 */
public class GradientLogger {

    private static final Map<String, String> COLORS = Map.ofEntries(
            Map.entry("red", "#FF0000"),
            Map.entry("green", "#00FF00"),
            Map.entry("blue", "#0000FF"),
            Map.entry("yellow", "#FFFF00"),
            Map.entry("orange", "#FFA500"),
            Map.entry("turquoise", "#40E0D0"),
            Map.entry("cyan", "#00FFFF"),
            Map.entry("purple", "#800080"),
            Map.entry("pink", "#FF69B4"),
            Map.entry("white", "#FFFFFF"),
            Map.entry("black", "#000000"),
            Map.entry("gray", "#808080"),
            Map.entry("maroon", "#800000"),
            Map.entry("olive", "#808000"),
            Map.entry("lime", "#00FF00"),
            Map.entry("teal", "#008080"),
            Map.entry("navy", "#000080"),
            Map.entry("silver", "#C0C0C0"),
            Map.entry("gold", "#FFD700"),
            Map.entry("brown", "#A52A2A"),
            Map.entry("beige", "#F5F5DC"),
            Map.entry("coral", "#FF7F50"),
            Map.entry("crimson", "#DC143C"),
            Map.entry("indigo", "#4B0082"),
            Map.entry("violet", "#EE82EE"),
            Map.entry("magenta", "#FF00FF"),
            Map.entry("salmon", "#FA8072"),
            Map.entry("khaki", "#F0E68C"),
            Map.entry("orchid", "#DA70D6"),
            Map.entry("plum", "#DDA0DD"),
            Map.entry("tan", "#D2B48C"),
            Map.entry("azure", "#F0FFFF"),
            Map.entry("lavender", "#E6E6FA"),
            Map.entry("mint", "#BDFCC9"),
            Map.entry("peach", "#FFDAB9"),
            Map.entry("chocolate", "#D2691E"),
            Map.entry("skyblue", "#87CEEB"),
            Map.entry("slate", "#708090"),
            Map.entry("aqua", "#00FFFF"),
            Map.entry("forest", "#228B22"),
            Map.entry("deepblue", "#00008B"),
            Map.entry("lightgray", "#D3D3D3"),
            Map.entry("darkgray", "#A9A9A9"),
            Map.entry("lightgreen", "#90EE90"),
            Map.entry("darkgreen", "#006400"),
            Map.entry("lightblue", "#ADD8E6"),
            Map.entry("darkblue", "#00008B"),
            Map.entry("lightpink", "#FFB6C1"),
            Map.entry("darkred", "#8B0000"),
            Map.entry("lightyellow", "#FFFFE0"),
            Map.entry("darkorange", "#FF8C00"),
            Map.entry("midnight", "#191970"),
            Map.entry("springgreen", "#00FF7F"),
            Map.entry("royalblue", "#4169E1"),
            Map.entry("hotpink", "#FF69B4"),
            Map.entry("steelblue", "#4682B4"),
            Map.entry("tomato", "#FF6347"),
            Map.entry("sienna", "#A0522D"),
            Map.entry("peru", "#CD853F"),
            Map.entry("wheat", "#F5DEB3"),
            Map.entry("limegreen", "#32CD32"),
            Map.entry("seagreen", "#2E8B57"),
            Map.entry("dodgerblue", "#1E90FF"),
            Map.entry("firebrick", "#B22222"),
            Map.entry("indianred", "#CD5C5C"),
            Map.entry("mediumvioletred", "#C71585"),
            Map.entry("mediumorchid", "#BA55D3"),
            Map.entry("mediumslateblue", "#7B68EE"),
            Map.entry("mediumturquoise", "#48D1CC"),
            Map.entry("mediumseagreen", "#3CB371"),
            Map.entry("mediumspringgreen", "#00FA9A"),
            Map.entry("mediumaquamarine", "#66CDAA"),
            Map.entry("mediumblue", "#0000CD")
    );

    private final String prefix;
    private final boolean gradientPrefix;

    private GradientLogger(Builder builder) {
        this.prefix = builder.prefix;
        this.gradientPrefix = builder.gradientPrefix;
    }

    private int[] hexToRgb(String hex) {
        hex = hex.replace("#", "");
        return new int[] {
                Integer.valueOf(hex.substring(0, 2), 16),
                Integer.valueOf(hex.substring(2, 4), 16),
                Integer.valueOf(hex.substring(4, 6), 16)
        };
    }

    private int[] resolveColor(String color) {
        if (color == null) return null;
        color = color.trim();
        if (color.startsWith("#") && color.length() == 7) {
            return hexToRgb(color);
        }
        String hex = COLORS.get(color.toLowerCase());
        return hex != null ? hexToRgb(hex) : null;
    }

    public void log(String text, String fromColor, String toColor) {
        int[] start = resolveColor(fromColor);
        int[] end = resolveColor(toColor);

        if (start == null || end == null) {
            System.err.println("Color not found. Use a name or hex (e.g. #FF00FF). Available names: " + COLORS.keySet());
            return;
        }

        StringBuilder sb = new StringBuilder();
        if (prefix != null && !prefix.isEmpty()) {
            if (gradientPrefix) {
                for (int i = 0; i < prefix.length(); i++) {
                    double ratio = (prefix.length() == 1) ? 0 : (double) i / (prefix.length() - 1);
                    int r = (int) (start[0] + ratio * (end[0] - start[0]));
                    int g = (int) (start[1] + ratio * (end[1] - start[1]));
                    int b = (int) (start[2] + ratio * (end[2] - start[2]));
                    sb.append(String.format("\u001B[38;2;%d;%d;%dm%c", r, g, b, prefix.charAt(i)));
                }
                sb.append("\u001B[0m");
            } else {
                sb.append(prefix);
            }
        }
        if (text != null && !text.isEmpty()) {
            for (int i = 0; i < text.length(); i++) {
                double ratio = (text.length() == 1) ? 0 : (double) i / (text.length() - 1);
                int r = (int) (start[0] + ratio * (end[0] - start[0]));
                int g = (int) (start[1] + ratio * (end[1] - start[1]));
                int b = (int) (start[2] + ratio * (end[2] - start[2]));
                sb.append(String.format("\u001B[38;2;%d;%d;%dm%c", r, g, b, text.charAt(i)));
            }
            sb.append("\u001B[0m");
        }
        System.out.println(sb);
    }

    public static class Builder {
        private String prefix = "";
        private boolean gradientPrefix = false;

        public Builder setPrefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        public Builder setGradientPrefix(boolean gradientPrefix) {
            this.gradientPrefix = gradientPrefix;
            return this;
        }

        public GradientLogger build() {
            return new GradientLogger(this);
        }
    }
}