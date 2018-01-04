package com.example.android.eaton;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SingleFoodActivity extends AppCompatActivity {

    private String food_key= null;
    private String category_key= null;
    private DatabaseReference mDatabase,mRef;
    private TextView singleFoodID, singleFoodName, singleFoodPrice;
    private ImageView singleFoodImage;
    private Button orderButton;

    String food_id, food_name, food_price, food_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_food);

        food_key = getIntent().getExtras().getString("FoodID");
        category_key = getIntent().getExtras().getString("category");

        mDatabase = FirebaseDatabase.getInstance().getReference().child(category_key);
        mRef = FirebaseDatabase.getInstance().getReference().child("OrderCart");


        singleFoodID = (TextView) findViewById(R.id.singleFoodID);
        singleFoodName = (TextView) findViewById(R.id.singleFoodName);
        singleFoodPrice = (TextView) findViewById(R.id.singleFoodPrice);
        singleFoodImage = (ImageView) findViewById(R.id.singleFoodImage);

        mDatabase.child(food_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 food_id = (String) dataSnapshot.child("mID").getValue();
                 food_name = (String) dataSnapshot.child("mName").getValue();
                 food_price = (String) dataSnapshot.child("mPrice").getValue();
                 food_image = (String) dataSnapshot.child("mImageResourceId").getValue();

                singleFoodID.setText(food_id);
                singleFoodName.setText(food_name);
                singleFoodPrice.setText(food_price);
                Picasso.with(SingleFoodActivity.this).load(food_image).into(singleFoodImage);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void OrderItem(View view){
        final DatabaseReference newOrder = mRef.push();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(SingleFoodActivity.this, "Item Ordered", Toast.LENGTH_SHORT).show();
                final Order itemClass = new Order(food_id, food_name, food_price);
                newOrder.setValue(itemClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        switch(category_key) {
                            case "Burger":
                                if (category_key.equals("Burger")){
                                    BurgerActivityStart();
                                }
                                break;
                            case "Pizza":
                                if (category_key.equals("Pizza")){
                                    PizzaActivityStart();
                                }
                                break;
                            case "Dessert":
                                if (category_key.equals("Dessert")){
                                    DessertActivityStart();
                                }
                                break;
                            case "Drinks":
                                if (category_key.equals("Drinks")){
                                    DrinksActivityStart();
                                }
                                break;
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void PizzaActivityStart(){
        Intent go = new Intent(SingleFoodActivity.this, PizzaMenu.class);
        go.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(go);
    }
    public void BurgerActivityStart(){
        Intent go = new Intent(SingleFoodActivity.this, BurgerMenu.class);
        go.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(go);

    }
    public void DessertActivityStart(){
        Intent go = new Intent(SingleFoodActivity.this, DessertMenu.class);
        go.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(go);
    }
    public void DrinksActivityStart(){
        Intent go = new Intent(SingleFoodActivity.this, Drinks.class);
        go.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(go);
    }
}
