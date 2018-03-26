package com.example.uju.coursetracker.objects;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.uju.coursetracker.R;
/**
 * Created by francisokoro on 2018-03-22.
 * True
 */

public class ImageAdapter extends PagerAdapter {
    private Context cntxt;
    private int type;
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
    //int i = 0;


    public ImageAdapter(Context context,int tpe){
        cntxt=context;
        type=tpe;
    }

    @Override
    public int getCount() {

        int len =0;
        if(type==1){
            len = e1ImgIds.length;
        }
        else if(type==2){
            len = e2ImgIds.length;
        }
        else if(type==3){
            len = e3ImgIds.length;
        }
        return len;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int [] id_s= new int[12];
        if(type==1){
            id_s = e1ImgIds;
        }
        else if(type==2){
            id_s = e2ImgIds;
        }
        else if(type==3){
            id_s = e3ImgIds;
        }
        ImageView imageView = new ImageView(cntxt);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageResource(id_s[position]);
        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }
}
