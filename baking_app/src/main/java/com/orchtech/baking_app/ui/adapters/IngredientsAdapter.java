package com.orchtech.baking_app.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orchtech.baking_app.R;
import com.orchtech.baking_app.models.IngredientsModel;

import java.util.ArrayList;

/**
 * Created by ahmed yousef on 11/18/2017.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ReceipeCardHolder> {

    ArrayList<IngredientsModel>  ingredientsModelList;
    IngredientsModel ingredientsModel;
    Context context;


    public IngredientsAdapter(ArrayList<IngredientsModel> ingredientsModelList, Context context) {
        this.ingredientsModelList = ingredientsModelList;
        this.context = context;
    }

    @Override
    public ReceipeCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipe_card_model, parent, false);
        return new ReceipeCardHolder(view);
    }

    @Override
    public void onBindViewHolder(ReceipeCardHolder holder, int position) {

        ingredientsModel = ingredientsModelList.get(position);
        holder.txt_card_name.setText("Quantity: "+ingredientsModel.getQuantity()+"\n" +
                "Measure: "+ ingredientsModel.getMeasure()+"\n"+"Ingredient: "+ ingredientsModel.getIngredient());



       /* final String CardId = ingredientsModel.getId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, ReceipeCardActivity.class);
                i.putExtra("card_id", CardId);
                i.putParcelableArrayListExtra("ingredientsList", receipesModel.getIngredientsModels());
                i.putParcelableArrayListExtra("stepsList", receipesModel.getStepsModels());
                context.startActivity(i);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return ingredientsModelList.size();
    }


    public class ReceipeCardHolder extends RecyclerView.ViewHolder {

        TextView txt_card_name;

        public ReceipeCardHolder(View itemView) {
            super(itemView);

            txt_card_name = itemView.findViewById(R.id.txt_card_name);

        }
    }
}
