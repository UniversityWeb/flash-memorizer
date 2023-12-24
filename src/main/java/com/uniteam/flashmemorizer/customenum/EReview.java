package com.uniteam.flashmemorizer.customenum;

public enum EReview {
    FILL_BLANK("fill-blank-review", "Fill Blank"),
    MATCHING("matching-review", "Matching"),
    MULTI_CHOICE("multi-choice-review", "Multi Choice");

    String reviewFile;
    String displayName;

    EReview(String htmlFile, String displayName) {
        this.reviewFile = htmlFile;
        this.displayName = displayName;
    }

    public String getReviewFile() { return reviewFile; }
    public String getDisplayName() { return displayName; }
}
