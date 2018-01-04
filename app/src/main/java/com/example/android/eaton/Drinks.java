package com.example.android.eaton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Drinks extends AppCompatActivity {
    private RecyclerView mFoodList;
    private DatabaseReference mDatabase;
    private String drink_key = "Drinks";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mFoodList = (RecyclerView) findViewById(R.id.foodList);
        mFoodList.setHasFixedSize(true);
        mFoodList.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Drinks");

        FirebaseRecyclerAdapter<MenuItem, BurgerMenu.FoodViewHolder> FBRA = new FirebaseRecyclerAdapter<MenuItem, BurgerMenu.FoodViewHolder>(
                MenuItem.class,
                R.layout.activity_list_view,
                BurgerMenu.FoodViewHolder.class,
                mDatabase


        ) {
            @Override
            protected void populateViewHolder(BurgerMenu.FoodViewHolder viewHolder, MenuItem model, int position) {

                viewHolder.setmID(model.getmID());
                viewHolder.setmName(model.getmName());
                viewHolder.setmPrice(model.getmPrice());
                viewHolder.setmImageResourceId(getApplicationContext(),model.getmImageResourceId());

                final String food_key = getRef(position).getKey().toString();
                viewHolder.mView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent SingleFoodActivity = new Intent(Drinks.this, SingleFoodActivity.class);
                        SingleFoodActivity.putExtra("FoodID",food_key);
                        SingleFoodActivity.putExtra("category",drink_key);

                        startActivity(SingleFoodActivity);
                    }
                });

            }
        };

        mFoodList.setAdapter(FBRA);

    }
}
