package com.example.android.eaton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //intent to go on updatemenu interface
        Button toUpdate = (Button) findViewById(R.id.update_menu);
        toUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goUpdate = new Intent(MainActivity.this, UpdateMenu.class);
                startActivity(goUpdate);
            }
        });

        //intent to go on menulist interface
        Button toMenuList = (Button) findViewById(R.id.menu_list);
        toMenuList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goMenuList = new Intent(MainActivity.this, MenuList.class);
                startActivity(goMenuList);
            }
        });


    }
}
