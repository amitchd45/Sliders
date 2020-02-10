package com.example.sliders.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sliders.R;
import com.example.sliders.SliderItem;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SliderAdapters extends RecyclerView.Adapter<SliderAdapters.SliderViewHolder> {

    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;
    private Select select;

    public interface Select{
        void ClickSlider(int position);
    }


    public SliderAdapters(List<SliderItem> sliderItems, ViewPager2 viewPager2, Select select) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
        this.select = select;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {

        holder.setImage(sliderItems.get(position));
        if (position== sliderItems.size()-2){
            viewPager2.post(sliderRunnable);
        }

    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView imageView;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageSlider);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    select.ClickSlider(getLayoutPosition());
                }
            });
        }

        void setImage(SliderItem sliderItem){

            imageView.setImageResource(sliderItem.getImage());

        }
    }

    private Runnable sliderRunnable=new Runnable() {
        @Override
        public void run() {

            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}
