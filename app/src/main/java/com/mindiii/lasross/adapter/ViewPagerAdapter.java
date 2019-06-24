package com.mindiii.lasross.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mindiii.lasross.R;
import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {

    private  int[] layouts;
    private int[] images;
    private LayoutInflater layoutInflater;
    private Context context;

    public ViewPagerAdapter(int[] images, Context context) {
        this.images = images;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = layoutInflater.inflate(R.layout.swipe_layout_1,container,false);

        final ImageView img =  view.findViewById(R.id.ivImage);

        Picasso.with(context)
                .load(images[position])
                .resize(600, 830)
                .into(img);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }
}
