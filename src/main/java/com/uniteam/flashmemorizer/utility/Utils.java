package com.uniteam.flashmemorizer.utility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static String EXPIRED_TOKEN_MSG = "Token already expired";
    public static String INVALID_TOKEN_MSG = "Invalid verification token";
    public static String OLD_LINK_TOKEN_MSG = "Invalid verification link, please, check your email for new verification link.";
    public static String RESEND_TOKEN_MSG = "A new verification link has been sent to your email, please, check to active your account.";
    public static String SUCCESS_TOKEN_MSG = "Email verified successfully. Now you can login account!";

    public static String INCORRECT_PASSWORD_MSG = "Incorrect current password";
    public static String NOT_MATCH_PASSWORD_MSG = "New password and confirm password do not match";

    public static String htmlToPlainText(String html) {
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));

        return document.text();
    }
}
