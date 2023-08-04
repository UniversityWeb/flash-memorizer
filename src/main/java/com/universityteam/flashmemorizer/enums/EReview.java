package com.universityteam.flashmemorizer.enums;

public enum EReview {
    FILL_BLANK("fill-blank-review", "Fill Blank"),
    MATCHING("matching-review", "Matching"),
    MULTI_CHOICE("multi-choice-review", "Multi Choice");

    String htmlFile;
    String displayName;

    EReview(String htmlFile, String displayName) {
        this.htmlFile = htmlFile;
        this.displayName = displayName;
    }

    public String getHtmlFile() {
        return htmlFile;
    }
    public String getDisplayName() { return displayName; }
}
