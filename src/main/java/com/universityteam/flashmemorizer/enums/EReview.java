package com.universityteam.flashmemorizer.enums;

public enum EReview {
    FILL_BLANK("fill-blank-review"),
    MATCHING("matching-review"),
    MULTI_CHOICE("multi-choice-review");

    String htmlFile;

    EReview(String htmlFile) {
        this.htmlFile = htmlFile;
    }

    public String getHtmlFile() {
        return htmlFile;
    }
}
