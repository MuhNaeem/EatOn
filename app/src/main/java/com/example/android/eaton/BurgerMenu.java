package com.example.android.eaton;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class BurgerMenu extends AppCompatActivity {
    private RecyclerView mFoodList;
    private DatabaseReference mDatabase;
    private String burger_key = "Burger";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mFoodList = (RecyclerView) findViewById(R.id.foodList);
        mFoodList.setHasFixedSize(true);
        mFoodList.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Burger");

        FirebaseRecyclerAdapter <MenuItem, FoodViewHolder> FBRA = new FirebaseRecyclerAdapter<MenuItem, FoodViewHolder>(
                MenuItem.class,
                R.layout.activity_list_view,
                FoodViewHolder.class,
                mDatabase


        ) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, MenuItem model, int position) {

                viewHolder.setmID(model.getmID());
                viewHolder.setmName(model.getmName());
                viewHolder.setmPrice(model.getmPrice());
                viewHolder.setmImageResourceId(getApplicationContext(),model.getmImageResourceId());

                final String food_key = getRef(position).getKey().toString();
                viewHolder.mView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent SingleFoodActivity = new Intent(BurgerMenu.this, SingleFoodActivity.class);
                        SingleFoodActivity.putExtra("FoodID",food_key);
                        SingleFoodActivity.putExtra("category",burger_key);

                        startActivity(SingleFoodActivity);
                    }
                });

            }
        };

        mFoodList.setAdapter(FBRA);

    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public FoodViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setmID(String id) {
            TextView item_id = (TextView) mView.findViewById(R.id.item_id);
            item_id.setText(id);

        }

        public void setmName(String name) {
            TextView item_name = (TextView) mView.findViewById(R.id.item_name);
            item_name.setText(name);
        }

        public void setmPrice(String price) {
            TextView item_price = (TextView) mView.findViewById(R.id.item_price);
            item_price.setText(price);
        }

        public void setmImageResourceId(Context ctx, String image) {
            ImageView item_image = (ImageView) mView.findViewById(R.id.list_item_icon);
            Picasso.with(ctx).load(image).into(item_image);}

    }
}
