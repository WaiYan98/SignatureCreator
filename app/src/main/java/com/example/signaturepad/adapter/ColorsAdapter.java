package com.example.signaturepad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signaturepad.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ViewHolder> {

    private Context context;
    private List<Integer> colorList;

    public ColorsAdapter(Context context, List<Integer> colorList) {

        this.context = context;
        this.colorList = colorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_colors, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.imgColorIcon.setColorFilter(context.getResources().getColor(colorList.get(position)));

    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_color_icon)
        ImageView imgColorIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
