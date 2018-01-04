package com.example.android.eaton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        Button toStart = (Button) findViewById(R.id.back_start);
        toStart.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent goStart = new Intent(MenuList.this, MainActivity.class);
                                           startActivity(goStart);
                                       }
                                   }

        );

        Button toBurger = (Button) findViewById(R.id.burger_menu);
        toBurger.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent goBurger = new Intent(MenuList.this, BurgerMenu.class);
                                            startActivity(goBurger);
                                        }
                                    }

        );

        Button toPizza = (Button) findViewById(R.id.pizza_menu);
        toPizza.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent goPizza = new Intent(MenuList.this, PizzaMenu.class);
                                           startActivity(goPizza);
                                       }
                                   }

        );

        Button toDessert = (Button) findViewById(R.id.sweet_menu);
        toDessert.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent goDessert = new Intent(MenuList.this, DessertMenu.class);
                                           startActivity(goDessert);
                                       }
                                   }

        );

        Button toDrinks = (Button) findViewById(R.id.drinks_menu);
        toDrinks.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent goDrinks = new Intent(MenuList.this, Drinks.class);
                                             startActivity(goDrinks);
                                         }
                                     }

        );

        Button toOrder = (Button) findViewById(R.id.placeOrder);
        toOrder.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent goOrder = new Intent(MenuList.this, PlaceOrder.class);
                                           startActivity(goOrder);
                                       }
                                   }

        );


    }
}
