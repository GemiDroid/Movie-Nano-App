package com.orchtech.baking_app.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orchtech.baking_app.R;
import com.orchtech.baking_app.models.BakingModel;
import com.orchtech.baking_app.models.IngredientsModel;
import com.orchtech.baking_app.models.StepsModel;
import com.orchtech.baking_app.ui.activities.ReceipeCardActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed yousef on 11/18/2017.
 */

public class BakingCardAdapter extends RecyclerView.Adapter<BakingCardAdapter.ReceipeCardHolder> {

    List<BakingModel> receipesModelList;
    BakingModel receipesModel;
    Context context;


    public BakingCardAdapter(List<BakingModel> receipesModelList, Context context) {
        this.receipesModelList = receipesModelList;
        this.context = context;
    }

    @Override
    public ReceipeCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipe_card_model, parent, false);
        return new ReceipeCardHolder(view);
    }

    @Override
    public void onBindViewHolder(ReceipeCardHolder holder, int position) {

        receipesModel = receipesModelList.get(position);
        holder.txt_card_name.setText(receipesModel.getName());
        if (receipesModel.getImage() != null) {
            Glide.with(context).load(receipesModel.getImage()).into(holder.baking_img);
        } else {

        }
        final String CardId = receipesModel.getId();
        final ArrayList<StepsModel> StepsList = receipesModel.getStepsModels();
        final ArrayList<IngredientsModel> IngredientsList1 = receipesModel.getIngredientsModels();
        final String Name = receipesModel.getName();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ReceipeCardActivity.class);
                i.putExtra("card_id", CardId);
                i.putExtra("baking_name", Name);
                i.putParcelableArrayListExtra("ingredientsList", IngredientsList1);
                i.putParcelableArrayListExtra("stepsList", StepsList);
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return receipesModelList.size();
    }


    public class ReceipeCardHolder extends RecyclerView.ViewHolder {

        TextView txt_card_name;
        ImageView baking_img;

        public ReceipeCardHolder(View itemView) {
            super(itemView);

            txt_card_name = itemView.findViewById(R.id.txt_card_name);
            baking_img = itemView.findViewById(R.id.baking_img);
        }
    }
}
