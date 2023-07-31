package com.janjetov.testCases;

import com.janjetov.enums.enums;
import com.janjetov.helpers.JsoupHelper;
import com.janjetov.helpers.XMLHelper;
import com.janjetov.pageObject.WWWPage;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test1 {

    @Test
    public void test() throws Exception {
        String xmlFilePath = "src/test/java/com/janjetov/data/dataSets.xml";
        String xmlSchemaFilePath = "src/test/java/com/janjetov/data/dataSets.xsd";

        boolean validationOK = XMLHelper.validateXMLSchema(xmlFilePath, xmlSchemaFilePath);

        if (!validationOK){
            throw new Exception("Validation didn't pass!");
        }

        JsoupHelper jh = new JsoupHelper();
        File f = new File(xmlFilePath);

        Document doc = jh.createDocumentFromFile(f);

        WWWPage wwwPage = new WWWPage();

        Elements dataSetElem = jh.getElementsXPath(doc, "//datasets/dataset");
        HashMap<String,String> idHM = new HashMap<>();

        for (Element elem : dataSetElem) {
            String id = elem.attr("id");
            String sum = elem.attr("summary");

            if(idHM.containsKey(id)){
                throw new Exception("Duplicated dataset id = '" + id + "'");
            }

            idHM.put(id, sum);
        }

        for (Map.Entry<String, String> set : idHM.entrySet()) {
            // Printing all elements of a Map
            System.out.println("id: " + set.getKey() + ", summary = " + set.getValue());
        }

        SoftAssert soft;
        for (String id : idHM.keySet()) {
            soft = wwwPage.checkOneLanguage(jh, doc, id);
            soft.assertAll();
        }

    }
}
