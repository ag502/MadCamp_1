package com.example.week1.Data;

public class UrlInfo {
    public final static int SEARCH_AREA_CONTENT = 1;
    public final static int SEARCH_KEYWORD = 2;

    private static String keyword;
    private static int areaCode;
    private static int contentType;
    private static int currentPage;

    private static int mode = -1;

    public static int getMode() {
        return mode;
    }

    public static void setMode(int mode) {
        UrlInfo.mode = mode;
    }

    public static int getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(int currentPage) {
        UrlInfo.currentPage = currentPage;
    }

    public static String getKeyword() {
        return keyword;
    }

    public static void setKeyword(String keyword) {
        UrlInfo.keyword = keyword;
    }

    public static int getAreaCode() {
        return areaCode;
    }

    public static void setAreaCode(int areaCode) {
        UrlInfo.areaCode = areaCode;
    }

    public static int getContentType() {
        return contentType;
    }

    public static void setContentType(int contentType) {
        UrlInfo.contentType = contentType;
    }
}
