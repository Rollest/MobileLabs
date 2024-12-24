package ru.mirea.vasilev.picassoapp;

public class ImageItem {
    private String id;
    private Urls urls;

    public String getId() {
        return id;
    }

    public Urls getUrls() {
        return urls;
    }

    public class Urls {
        private String small;
        private String full;

        public String getSmall() {
            return small;
        }

        public String getFull() {
            return full;
        }
    }
}

