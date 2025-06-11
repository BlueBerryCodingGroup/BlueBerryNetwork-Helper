package de.blueberry.utils.color;

import java.util.*;

public class GradientLogger {

    /**
     * Logs the given text with a color gradient from one color to another.
     * Example usage:
     * GradientLogger.logGradient("Hello World", "red", "blue");
     */

    private static final Map<String, int[]> COLORS = Map.ofEntries(
            Map.entry("red", new int[]{255, 0, 0}),
            Map.entry("green", new int[]{0, 255, 0}),
            Map.entry("blue", new int[]{0, 0, 255}),
            Map.entry("yellow", new int[]{255, 255, 0}),
            Map.entry("orange", new int[]{255, 165, 0}),
            Map.entry("turquoise", new int[]{64, 224, 208}),
            Map.entry("cyan", new int[]{0, 255, 255}),
            Map.entry("purple", new int[]{128, 0, 128}),
            Map.entry("pink", new int[]{255, 105, 180}),
            Map.entry("white", new int[]{255, 255, 255}),
            Map.entry("black", new int[]{0, 0, 0}),
            Map.entry("gray", new int[]{128, 128, 128}),
            Map.entry("maroon", new int[]{128, 0, 0}),
            Map.entry("olive", new int[]{128, 128, 0}),
            Map.entry("lime", new int[]{0, 255, 0}),
            Map.entry("teal", new int[]{0, 128, 128}),
            Map.entry("navy", new int[]{0, 0, 128}),
            Map.entry("silver", new int[]{192, 192, 192}),
            Map.entry("gold", new int[]{255, 215, 0}),
            Map.entry("brown", new int[]{165, 42, 42}),
            Map.entry("beige", new int[]{245, 245, 220}),
            Map.entry("coral", new int[]{255, 127, 80}),
            Map.entry("crimson", new int[]{220, 20, 60}),
            Map.entry("indigo", new int[]{75, 0, 130}),
            Map.entry("violet", new int[]{238, 130, 238}),
            Map.entry("magenta", new int[]{255, 0, 255}),
            Map.entry("salmon", new int[]{250, 128, 114}),
            Map.entry("khaki", new int[]{240, 230, 140}),
            Map.entry("orchid", new int[]{218, 112, 214}),
            Map.entry("plum", new int[]{221, 160, 221}),
            Map.entry("tan", new int[]{210, 180, 140}),
            Map.entry("azure", new int[]{240, 255, 255}),
            Map.entry("lavender", new int[]{230, 230, 250}),
            Map.entry("mint", new int[]{189, 252, 201}),
            Map.entry("peach", new int[]{255, 218, 185}),
            Map.entry("chocolate", new int[]{210, 105, 30}),
            Map.entry("skyblue", new int[]{135, 206, 235}),
            Map.entry("slate", new int[]{112, 128, 144}),
            Map.entry("aqua", new int[]{0, 255, 255}),
            Map.entry("forest", new int[]{34, 139, 34}),
            Map.entry("deepblue", new int[]{0, 0, 139}),
            Map.entry("lightgray", new int[]{211, 211, 211}),
            Map.entry("darkgray", new int[]{169, 169, 169}),
            Map.entry("lightgreen", new int[]{144, 238, 144}),
            Map.entry("darkgreen", new int[]{0, 100, 0}),
            Map.entry("lightblue", new int[]{173, 216, 230}),
            Map.entry("darkblue", new int[]{0, 0, 139}),
            Map.entry("lightpink", new int[]{255, 182, 193}),
            Map.entry("darkred", new int[]{139, 0, 0}),
            Map.entry("lightyellow", new int[]{255, 255, 224}),
            Map.entry("darkorange", new int[]{255, 140, 0}),
            Map.entry("midnight", new int[]{25, 25, 112}),
            Map.entry("springgreen", new int[]{0, 255, 127}),
            Map.entry("royalblue", new int[]{65, 105, 225}),
            Map.entry("hotpink", new int[]{255, 105, 180}),
            Map.entry("steelblue", new int[]{70, 130, 180}),
            Map.entry("tomato", new int[]{255, 99, 71}),
            Map.entry("sienna", new int[]{160, 82, 45}),
            Map.entry("peru", new int[]{205, 133, 63}),
            Map.entry("wheat", new int[]{245, 222, 179}),
            Map.entry("limegreen", new int[]{50, 205, 50}),
            Map.entry("seagreen", new int[]{46, 139, 87}),
            Map.entry("dodgerblue", new int[]{30, 144, 255}),
            Map.entry("firebrick", new int[]{178, 34, 34}),
            Map.entry("indianred", new int[]{205, 92, 92}),
            Map.entry("mediumvioletred", new int[]{199, 21, 133}),
            Map.entry("mediumorchid", new int[]{186, 85, 211}),
            Map.entry("mediumslateblue", new int[]{123, 104, 238}),
            Map.entry("mediumturquoise", new int[]{72, 209, 204}),
            Map.entry("mediumseagreen", new int[]{60, 179, 113}),
            Map.entry("mediumspringgreen", new int[]{0, 250, 154}),
            Map.entry("mediumaquamarine", new int[]{102, 205, 170}),
            Map.entry("mediumblue", new int[]{0, 0, 205})
    );

    public static void logGradient(String text, String fromColorName, String toColorName) {
        int[] start = COLORS.get(fromColorName.toLowerCase());
        int[] end = COLORS.get(toColorName.toLowerCase());

        if (start == null || end == null) {
            System.err.println("Color not found. Available colors: " + COLORS.keySet());
            return;
        }

        for (int i = 0; i < text.length(); i++) {
            double ratio = (double) i / (text.length() - 1);
            int r = (int) (start[0] + ratio * (end[0] - start[0]));
            int g = (int) (start[1] + ratio * (end[1] - start[1]));
            int b = (int) (start[2] + ratio * (end[2] - start[2]));
            System.out.print(String.format("\u001B[38;2;%d;%d;%dm%c", r, g, b, text.charAt(i)));
        }
        System.out.print("\u001B[0m\n");
    }
}
