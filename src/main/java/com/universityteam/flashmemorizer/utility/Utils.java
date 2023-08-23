package com.universityteam.flashmemorizer.utility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static <T> List<T> getElementsInAWithoutB(List<T> a, List<T> b) {
        List<T> result = new ArrayList<>(a);
        result.removeAll(b);
        return result;
    }

    public static String htmlToPlainText(String html) {
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));

        return document.text();
    }
}
