package com.janjetov.enums;

public class enums {
    public enum Language {
        ENGLISH, SRPSKI;

        public String toString(){
            switch(this){
                case ENGLISH:
                    return "english";
                case SRPSKI:
                    return "srpski";
                default:
                    return null;
            }

        }

        //needed for WebElement emulation
        public static Language strToEnum(String id) throws Exception{
            switch (id) {
                case "srpski": return SRPSKI;
                case "english":  return ENGLISH;
                default: return null;
            }
        }
    }
}
