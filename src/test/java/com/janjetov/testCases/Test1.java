package com.janjetov.testCases;

import com.janjetov.enums.enums;
import com.janjetov.helpers.JsoupHelper;
import com.janjetov.helpers.XMLHelper;
import com.janjetov.pageObject.WWWPage;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

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

        enums.Languages[] languages = enums.Languages.values();

        SoftAssert soft;
        for (enums.Languages lang : languages) {
            soft = wwwPage.checkOneLanguage(jh, doc, lang);
            soft.assertAll();
        }

    }
}
