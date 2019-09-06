package com.example.kate.nh_proejct;

import java.util.Date;

public class Funding {
    String title;

    public Funding(String title, String image, String description, int situation, int goal, Date date) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.situation = situation;
        this.goal = goal;
        this.date = date;
    }

    String image;
    String description;
    int situation;
    int goal;
    Date date;

}
