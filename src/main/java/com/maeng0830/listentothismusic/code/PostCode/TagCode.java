package com.maeng0830.listentothismusic.code.PostCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 게시글 태그 코드

public class TagCode {

    @Getter
    @RequiredArgsConstructor
    public enum Genre {
        HIPHOP("힙합"),
        ballad("발라드"),
        ROCK("락"),
        JAZZ("재즈");

        private final String genre;

        public String getValue() {
            return genre;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum Mood {
        EXCITING("신나는"),
        SAD("슬픈"),
        CALM("잔잔한"),
        WARM("따뜻한"),
        AERIAL("몽환적인");

        private final String mood;

        public String getValue() {
            return mood;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum Weather {
        CLEAR("맑음"), // api 코드: 800
        CLOUDS("흐림"), // api 코드: 801 ~ 804
        RAIN("비"), // api 코드: 300 ~ 321, 500 ~ 531
        THUNDERSTORM("천둥 번개"), // api 코드: 200 ~ 232
        SNOW("눈"), // api 코드: 600 ~ 622
        MIST("안개"), // api 코드: 701 ~ 771
        TORNADO("폭풍"); // api 코드: 781

        private final String weather;

        public String getValue() {
            return weather;
        }
    }
}
