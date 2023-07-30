package com.janjetov.enums;

public class enums {
    public enum Languages{
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
    }
}
