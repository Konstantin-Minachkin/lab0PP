package com.example.lab2ppp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Objects {

    String name;
    String helptext;
    Bitmap image;
    Objects(String name, String help, String image, Context h){
        this.name = name;
        helptext = help;
        try {
            this.image = Picasso.get().load("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/"
                    + image).get();
        } catch (IOException e) {
            e.printStackTrace();
            this.image = BitmapFactory.decodeResource(h.getResources(), R.drawable.ic_launcher); //сделать эрор картнку
        }
    }
}
