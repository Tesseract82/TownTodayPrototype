package com.example.redbaron.towntoday;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Constants {

    public static float dpHeight, dpWidth;
    public static float dpPadding = 3;
    public static float dpEventPadding = 15;
    public static Set<String> selected= new HashSet<>();
    public static ArrayList<String> categories;
    public static ArrayList<EventThumbView> conventions, charity, concerts, art, festivals;
    public static Map<String, ArrayList<EventThumbView>> eventThumbs;


    static{
        categories = new ArrayList<>(Arrays.asList("premium", "a21", "after5pm", "art", "casinotiles", "charity", "comedytiles", "concerts",
                "conventions", "dance", "environmental", "farmmarket", "festivals", "foodtruck", "sports", "dog", "kids", "museums", "political", "religious"));
        eventThumbs = new HashMap<String, ArrayList<EventThumbView>>();
    }

    public static void initializeEventThumbs(Context c){
        //Future use internet server to populate hashmap, for now just hard-coded

        conventions = new ArrayList<>(Arrays.asList(new EventThumbView(c, "one"), new EventThumbView(c, "two"),
                new EventThumbView(c, "three"), new EventThumbView(c, "four"), new EventThumbView(c, "five")));
        charity = new ArrayList<>(Arrays.asList(new EventThumbView(c, "six"), new EventThumbView(c, "seven"),
                new EventThumbView(c, "eight"), new EventThumbView(c, "nine"), new EventThumbView(c, "ten")));
        concerts = new ArrayList<>(Arrays.asList(new EventThumbView(c, "eleven"), new EventThumbView(c, "twelve"),
                new EventThumbView(c, "thirteen"), new EventThumbView(c, "fourteen"), new EventThumbView(c, "fifteen")));
        art = new ArrayList<>(Arrays.asList(new EventThumbView(c, "sixteen"), new EventThumbView(c, "seventeen"),
                new EventThumbView(c, "eighteen"), new EventThumbView(c, "nineteen"), new EventThumbView(c, "twenty")));
        festivals = new ArrayList<>(Arrays.asList(new EventThumbView(c, "twentytwo"),
                new EventThumbView(c, "twentythree")));

        eventThumbs.put("conventions", conventions);
        eventThumbs.put("charity", charity);
        eventThumbs.put("concerts", concerts);
        eventThumbs.put("art", art);
        eventThumbs.put("festivals", festivals);
    }
}
