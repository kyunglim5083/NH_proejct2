package com.example.kate.nh_proejct;

import java.util.Date;

public class Funding {
    String title;
    String progress;

    public Funding(String title, String image, String description, int situation, int goal, Date date,String progress) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.situation = situation;
        this.goal = goal;
        this.date = date;
        this.progress=progress;
    }

    String image;
    String description;
    int situation;
    int goal;
    Date date;

}
