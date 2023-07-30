package com.janjetov.pageObject;

import com.janjetov.enums.enums;
import com.janjetov.helpers.JsoupHelper;
import org.jsoup.nodes.Document;
import org.testng.asserts.SoftAssert;

public class WWWPage {

    //substitute this method with real values from WebElement from page
    public String getFirstName(enums.Languages lang){
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
    public String getLastName(enums.Languages lang){
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
    public String getPhone(enums.Languages lang){
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
    public String getCity(enums.Languages lang){
        switch (lang){
            case ENGLISH:
                return "city";
            case SRPSKI:
                return "grad";
            default:
                return null;
        }
    }


    public SoftAssert checkOneLanguage(JsoupHelper jh, Document doc, enums.Languages lang){
        SoftAssert soft = new SoftAssert();

        System.out.println("\nValidating '" + lang.toString() + "' language...");

        String firstNameDS = jh.getStringXPath(doc, "//datasets/dataset[@id='" + lang.toString() + "']/firstname");  //must be lowerCase !
        String lastNameDS = jh.getStringXPath(doc, "//datasets/dataset[@id='" + lang.toString() + "']/lastname");
        String phoneDS = jh.getStringXPath(doc, "//datasets/dataset[@id='" + lang.toString() + "']/phone");
        String cityDS = jh.getStringXPath(doc, "//datasets/dataset[@id='" + lang.toString() + "']/city");

        //values from WebElement from page:
        String firstName = getFirstName(lang);
        String lastName = getLastName(lang);
        String phone = getPhone(lang);
        String city = getCity(lang);

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
