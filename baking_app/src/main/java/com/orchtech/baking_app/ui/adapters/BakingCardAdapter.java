package com.orchtech.baking_app.ui.adapters;

import com.orchtech.baking_app.R;
import com.orchtech.baking_app.models.ReceipesModel;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by ahmed yousef on 11/18/2017.
 */

public class ReceipeCardAdapter extends RecyclerView.Adapter<ReceipeCardAdapter.ReceipeCardHolder> {

    List<ReceipesModel> receipesModelList;
    ReceipesModel receipesModel;
    Context context;

    public ReceipeCardAdapter(List<ReceipesModel> receipesModelList, Context context) {
        this.receipesModelList = receipesModelList;
        this.context = context;
    }

    @Override
    public ReceipeCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.receipe_card_model,parent,false);
        return new ReceipeCardHolder(view);
    }

    @Override
    public void onBindViewHolder(ReceipeCardHolder holder, int position) {

        receipesModel=receipesModelList.get(position);
        holder.txt_card_name.setText(receipesModel.getName());
    }

    @Override
    public int getItemCount() {
        return receipesModelList.size();
    }


    public class ReceipeCardHolder extends RecyclerView.ViewHolder {

        TextView txt_card_name;
        public ReceipeCardHolder(View itemView) {
            super(itemView);

            txt_card_name=itemView.findViewById(R.id.txt_card_name);
        }
    }
}
