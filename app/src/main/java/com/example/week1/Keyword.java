package com.example.week1;

public class Keyword {
    private static String keyword;
    private static int currentPage;

    public static int getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(int currentPage) {
        Keyword.currentPage = currentPage;
    }

    public static String getKeyword() {
        return keyword;
    }

    public static void setKeyword(String keyword) {
        Keyword.keyword = keyword;
    }
}
