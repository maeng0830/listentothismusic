package com.maeng0830.listentothismusic.util;

import com.sun.javafx.binding.StringFormatter;

public class YoutubeViewTag {
    public static String createYoutubeTag(String uniqueCode) {
        return String.format("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/%s\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>", uniqueCode);
    }
}
