package com.janjetov.helpers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsoupHelper {

    public Document createDocumentFromFile(File file) throws IOException {
        return Jsoup.parse(file);
    }

    public Elements getElementsCSS(Document doc, String cssQuery){
        return doc.select(cssQuery);
    }

    public Elements getElementsXPath(Document doc, String xPath){
        return doc.selectXpath(xPath);
    }

    public String getStringCSS(Document doc, String cssQuery){
        return doc.select(cssQuery).text();
    }

    public String getStringXPath(Document doc, String xPath){
        return doc.selectXpath(xPath).text();
    }


    public static void main(String[] args) throws IOException {
        JsoupHelper jh = new JsoupHelper();
        File f = new File("src/test/java/com/janjetov/data/dataSets.xml");
        Document doc = jh.createDocumentFromFile(f);

        String xpath = "//datasets/dataset[@id='srpski']/lastname";
        String lastName = jh.getStringXPath(doc,xpath);
        System.out.println("srpski lastName: " + lastName);

        String cssQuery = "dataset#srpski city";
        String city = jh.getStringCSS(doc,cssQuery);
        System.out.println("srpski city: " + city);

        Elements dataSetElem = jh.getElementsXPath(doc, "//datasets/dataset");
        ArrayList<String> idList = new ArrayList<String>();
        for (Element elem : dataSetElem) {
            idList.add(elem.attr("id"));
        }

        System.out.println("id list: " + idList.toString());
    }
}
