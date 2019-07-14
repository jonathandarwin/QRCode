package com.example.qrcode.app.started;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qrcode.databinding.StartedSlider1Binding;
import com.example.qrcode.databinding.StartedSlider2Binding;
import com.example.qrcode.databinding.StartedSlider3Binding;
import com.example.qrcode.databinding.StartedSlider3BindingImpl;

import java.util.List;

public class StartedAdapter extends PagerAdapter {

    private Context context;
    private List<Integer> listLayout;

    public StartedAdapter(Context context, List<Integer> listLayout){
        this.context = context;
        this.listLayout = listLayout;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), listLayout.get(position), container, false);
        if(position == 2){
            ((StartedSlider3Binding) binding).btnStarted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((StartedActivity) context).onButtonGoListener();
                }
            });
        }
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public int getCount() {
        return listLayout.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
