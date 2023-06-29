package com.ks.adventure.shared;

public class WordMother {
    public static String random() {
        return MotherCreator.random().lorem().word();
    }
}
