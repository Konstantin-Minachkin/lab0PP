package com.example.lab2ppp;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class RecycleFragment extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String ARGUMENT_PICTURE = "arg_picture";
    int pageNumber;
    Peredach tech;

    static RecycleFragment newInstance(int page, Peredach techList) {
        RecycleFragment pageFragment = new RecycleFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        arguments.putParcelable(ARGUMENT_PICTURE, techList);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        tech = getArguments().getParcelable(ARGUMENT_PICTURE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_list, null);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView help = (TextView) view.findViewById(R.id.help);
        final ImageView pict = (ImageView) view.findViewById(R.id.pict);
        name.setText(tech.name);
        help.setText(tech.help);
        Picasso.get().load("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/"
                + tech.image).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                pict.setImageBitmap(bitmap);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                //Here you should place a loading gif in the ImageView to
                //while image is being obtained.
                pict.setImageResource(R.drawable.loading); //тут другую картинку оставить
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                pict.setImageResource(R.drawable.ic_launcher); //картинка с ошибкой
            }
        });
        return view;
    }
}