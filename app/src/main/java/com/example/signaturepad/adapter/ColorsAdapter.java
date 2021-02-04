package com.example.signaturepad.adapter;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signaturepad.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ViewHolder> {

    public static final String BACKGROUND_COLOR_BTN = "BACKGROUND_COLOR_BTN";
    public static final String PEN_COLOR_BTN = "PEN_COLOR_BTN";


    private Context context;
    private List<Integer> colorList;
    private CallBack callBack;
    private String btnName;

    public ColorsAdapter(Context context, List<Integer> colorList, CallBack callBack) {

        this.context = context;
        this.colorList = colorList;
        this.callBack = callBack;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
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

        holder.imgColorIcon.setOnClickListener(v -> {

            if (btnName.equals(PEN_COLOR_BTN)) {

                callBack.onClickPenColorIcon(colorList.get(position));

            } else {

                callBack.onClickBackgroundColorIcon(colorList.get(position));

            }

        });

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

    public interface CallBack {

        void onClickPenColorIcon(@ColorRes int color);

        void onClickBackgroundColorIcon(@ColorRes int color);
    }
}
