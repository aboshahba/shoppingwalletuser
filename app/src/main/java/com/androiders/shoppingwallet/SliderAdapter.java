package com.androiders.shoppingwallet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {
    Context context ;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }


    public  int[] slidingImages ={
            R.drawable.goods,
            R.drawable.bag,
            R.drawable.wallet };

    public  String[] headerarray  ={
            "Easy Shopping",
            "Get your favorite items",
            "Discounts for every purchase"};

    public  String[] slideDesc={
            "We enable you navigate and searching our products and items,\n" +
                    "    making shopping easier and fast. Also you can compare prices\n" +
                    "    and get the best price in market.",
            "You can add your favorite items/products from different caregories\n" +
                    "    or stores to your favorite for easy access.",
            "For every puraches you made from our stores you get a discount in\n" +
                    "    your wallet that will be applied in next purchase, So more purachase\n" +
                    "    you mad more disounts you get."};

    @Override
    public int getCount() {
        return slideDesc.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView imageView = (ImageView) view.findViewById(R.id.slider_image);
        TextView  header = view.findViewById(R.id.header);
        TextView descreption = view.findViewById(R.id.descreption);

        imageView.setImageResource(slidingImages[position]);
        header.setText(headerarray[position]);
        descreption.setText(slideDesc[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((LinearLayout)object);
    }
}
