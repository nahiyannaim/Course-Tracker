package com.example.uju.coursetracker.objects;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.uju.coursetracker.R;
/**
 * Created by francisokoro on 2018-03-22.
 */

public class ImageAdapter extends PagerAdapter {
    private Context cntxt;
    private  int [] e1ImgIds = new int [] {R.drawable.facilities_e1eitc1,R.drawable.facilities_e1eitc2,
                                           R.drawable.facilities_e1eitc3,R.drawable.facilities_e1eitc4,
                                           R.drawable.facilities_e1eitc5,R.drawable.facilities_e1eitc6,
                                           R.drawable.facilities_e1eitc7,R.drawable.facilities_e1eitc8,
                                           R.drawable.facilities_e1eitc9,R.drawable.facilities_e1eitc10};

    private  int [] e2ImgIds = new int [] {R.drawable.facilities_e2eitc1,R.drawable.facilities_e2eitc2,
                                           R.drawable.facilities_e2eitc3,R.drawable.facilities_e2eitc4,
                                           R.drawable.facilities_e2eitc5,R.drawable.facilities_e2eitc6,
                                           R.drawable.facilities_e2eitc7,R.drawable.facilities_e2eitc8,
                                           R.drawable.facilities_e2eitc9};

    private  int [] e3ImgIds = new int [] {R.drawable.facilities_e3eitc1,R.drawable.facilities_e3eitc2,
                                           R.drawable.facilities_e3eitc3,R.drawable.facilities_e3eitc4,
                                           R.drawable.facilities_e3eitc5,R.drawable.facilities_e3eitc6,
                                           R.drawable.facilities_e3eitc7,R.drawable.facilities_e3eitc8,
                                           R.drawable.facilities_e3eitc9,R.drawable.facilities_e3eitc10,
                                           R.drawable.facilities_e3eitc11,R.drawable.facilities_e3eitc12};

    public ImageAdapter(Context context){
        cntxt=context;
    }

    @Override
    public int getCount() {
        return e1ImgIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(cntxt);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(e1ImgIds[position]);
        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }
}
