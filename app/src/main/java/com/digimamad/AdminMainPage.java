package com.digimamad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.digimamad.orders.MainOrder;

public class AdminMainPage extends Activity {

    private Button add,orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main_page_layout);
        add = findViewById(R.id.add_button);
        orders = findViewById(R.id.orders_button);
        Orders_btn();
        Add_btn();

    }
    private void Add_btn()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToADD();
            }
        });
    }
    private void Orders_btn()
    {
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToOrders();
            }
        });
    }

    private void GoToOrders()
    {
        Intent intent = new Intent(this, MainOrder.class);
        startActivity(intent);
    }
    private void GoToADD()
    {
        Intent intent = new Intent(this,ControlPanel.class);
        startActivity(intent);
    }

}
