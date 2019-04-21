package com.example.lab2ppp;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;

public class Splashscreen extends AppCompatActivity {

    //ArrayList<Objects> techList = new ArrayList<Objects>();
    ArrayList<Peredach> techList;
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        techList = getIntent().getParcelableArrayListExtra("mas");
        Context h = getApplicationContext();
        list = (RecyclerView) findViewById(R.id.list);
        list.setAdapter(new CustomAdapter(this, R.layout.list_item, techList.size()));
    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        private LayoutInflater inflater;
        Context context;
        private int number;

        CustomAdapter(Context context, int textRes, int number) {
            this.number = number;
            this.inflater = LayoutInflater.from(context);
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            final View view = inflater.inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.nameView.setText(techList.get(position).name); //строка задаёт текст
            try {
                Picasso.get().load("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/"
                        + techList.get(position).image).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        holder.imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        //Here you should place a loading gif in the ImageView to
                        //while image is being obtained.
                        holder.imageView.setImageResource(R.drawable.loading); //тут другую картинку оставить
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        holder.imageView.setImageResource(R.drawable.ic_launcher); //картинка с ошибкой
                    }

                });
            } catch(NullPointerException e){
                holder.imageView.setImageResource(R.drawable.ic_launcher);
            }
        }

        @Override
        public int getItemCount() {
            //return numbers.size();
            return number;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            View view;
            final ImageView imageView;
            final TextView nameView;

            ViewHolder(final View view) {
                super(view);
                this.view = view;
                imageView = (ImageView) view.findViewById(R.id.icon);
                nameView = (TextView) view.findViewById(R.id.label);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Splashscreen.this, Recycle.class);
                        int itemPosition = list.getChildLayoutPosition(view);
                        intent.putExtra("mas1", techList);
                        intent.putExtra("pos", itemPosition);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}