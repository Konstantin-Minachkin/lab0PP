package com.example.lab2ppp;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Peredach implements Parcelable {

    String name;
    String help;
    //Bitmap image;
    String image;
    Peredach (String name, String help, String image){
        this.name = name;
        this.help = help;
        this.image = image;
    }

    Peredach (String name, String help, String image, Context h){
        this.name = name;
        this.help = help;
        this.image = image;/*
        try {
            this.image = Picasso.get().load("https://raw.githubusercontent.com/wesleywerner/ancient-tech/" +
                    "02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/"+ image).get();
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null; //BitmapFactory.decodeResource(h.getResources(), R.drawable.ic_launcher); //сделать эрор картнку
        }*/
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(help);
        dest.writeString(image);
       // if (image != null) dest.writeString(getStringFromBitmap(image));
    }

    public static final Creator<Peredach> CREATOR = new Creator<Peredach>() {

        @Override
        public Peredach createFromParcel(Parcel source) {
            String name = source.readString();
            String help = source.readString();
            String image = source.readString();
            //Bitmap image = getBitmapFromString(source.readString());
            return new Peredach(name, help, image);
        }

        @Override
        public Peredach[] newArray(int size) {
            return new Peredach[size];
        }
    };

    static private String getStringFromBitmap(Bitmap bitmapPicture) {
        final int COMPRESSION_QUALITY = 100;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        String encodedImage;

        bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY, byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encodedImage;
    }

    static private Bitmap getBitmapFromString(String imageString) {
        byte[] decodedString = Base64.decode(imageString, Base64.DEFAULT);

        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedImage;
    }
}
