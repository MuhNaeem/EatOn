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

import com.firebase.ui.auth.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Iterator;

public class PlaceOrder extends AppCompatActivity {

    private RecyclerView mOrderList;
    private DatabaseReference mDatabase;
    private int total_amount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);

        mOrderList = (RecyclerView) findViewById(R.id.order_layout);
        mOrderList.setHasFixedSize(true);
        mOrderList.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("OrderCart");
        FirebaseRecyclerAdapter<Order, OrderViewHolder> FBRA = new FirebaseRecyclerAdapter<Order, OrderViewHolder>(
                Order.class,
                R.layout.single_order,
                OrderViewHolder.class,
                mDatabase


        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Order model, int position) {

                viewHolder.setmID(model.getmID());
                viewHolder.setmName(model.getmName());
                viewHolder.setmPrice(model.getmPrice());


            }
        };


        mOrderList.setAdapter(FBRA);

       /* mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> dataSnapshotsorder = dataSnapshot.child("OrderCart").getChildren().iterator();
                while (dataSnapshotsorder.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshotsorder.next();
                    total_amount += Integer.parseInt(String.valueOf(dataSnapshotChild.child("mPrice").getValue()));
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        TextView item_price = (TextView) findViewById(R.id.price);
        item_price.setText(total_amount);*/

    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public OrderViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setmID(String id) {
            TextView item_id = (TextView) mView.findViewById(R.id.orderItemID);
            item_id.setText(id);

        }

        public void setmName(String name) {
            TextView item_name = (TextView) mView.findViewById(R.id.orderItemName);
            item_name.setText(name);
        }

        public void setmPrice(String price) {
            TextView item_price = (TextView) mView.findViewById(R.id.orderItemPrice);
            item_price.setText(price);
        }


    }
}
