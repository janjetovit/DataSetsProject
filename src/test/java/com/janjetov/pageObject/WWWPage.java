package com.janjetov.pageObject;

import com.janjetov.enums.enums;
import com.janjetov.helpers.JsoupHelper;
import org.jsoup.nodes.Document;
import org.testng.asserts.SoftAssert;

public class WWWPage {

    //substitute this method with real values from WebElement from page
    public String getFirstName(enums.Language lang){
        switch (lang){
            case ENGLISH:
                return "FirstName";
            case SRPSKI:
                return "Ime";
            default:
                return null;
        }
    }

    //substitute this method with real values from WebElement from page
    public String getLastName(enums.Language lang){
        switch (lang){
            case ENGLISH:
                return "LastName";
            case SRPSKI:
                return "Prezime";
            default:
                return null;
        }
    }

    //substitute this method with real values from WebElement from page
    public String getPhone(enums.Language lang){
        switch (lang){
            case ENGLISH:
                return "phone";
            case SRPSKI:
                return "telefon";
            default:
                return null;
        }
    }

    //substitute this method with real values from WebElement from page
    public String getCity(enums.Language lang){
        switch (lang){
            case ENGLISH:
                return "city";
            case SRPSKI:
                return "grad";
            default:
                return null;
        }
    }

    public SoftAssert checkOneLanguage(JsoupHelper jh, Document doc, String id) throws Exception {
        SoftAssert soft = new SoftAssert();

        System.out.println("\nValidating '" + enums.Language.strToEnum(id) + "' language...");

        String firstNameDS = jh.getStringXPath(doc, "//datasets/dataset[@id='" + id + "']/firstname");  //must be lowerCase !
        String lastNameDS = jh.getStringXPath(doc, "//datasets/dataset[@id='" + id + "']/lastname");
        String phoneDS = jh.getStringXPath(doc, "//datasets/dataset[@id='" +id + "']/phone");
        String cityDS = jh.getStringXPath(doc, "//datasets/dataset[@id='" + id + "']/city");

        //values from WebElement from page:
        String firstName = getFirstName(enums.Language.strToEnum(id));
        String lastName = getLastName(enums.Language.strToEnum(id));
        String phone = getPhone(enums.Language.strToEnum(id));
        String city = getCity(enums.Language.strToEnum(id));

        soft.assertTrue(firstNameDS.equalsIgnoreCase(firstName), "Assert not passed: " + firstNameDS + "<>" + firstName);
        System.out.println("passed: " + firstNameDS + "=" + firstName);
        soft.assertTrue(lastNameDS.equalsIgnoreCase(lastName), "Assert not passed: " + lastNameDS + "<>" + lastName);
        System.out.println("passed: " + lastNameDS + "=" + lastName);
        soft.assertTrue(phoneDS.equalsIgnoreCase(phone), "Assert not passed: " + phoneDS + "<>" + phone);
        System.out.println("passed: " + phoneDS + "=" + phone);
        soft.assertTrue(cityDS.equalsIgnoreCase(city), "Assert not passed: " + cityDS + "<>" + city);
        System.out.println("passed: " + cityDS + "=" + city);

        return soft;
    }
}
