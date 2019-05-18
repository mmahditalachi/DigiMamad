package com.digimamad.orders;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.digimamad.DatabaseAccess;
import com.digimamad.R;
import com.digimamad.model.Orders;
import com.digimamad.model.Users;

import java.util.ArrayList;
import java.util.List;

public class MainOrder extends Activity {

    public static List<Orders> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_main);
        SelectFromDatabase();
        ListView l = findViewById(R.id.order_list);
        Orders_List orders_list = new Orders_List(this);
        l.setAdapter(orders_list);
    }
    private void SelectFromDatabase()
    {
        orders = new ArrayList<>();
        DatabaseAccess dbAccess = new DatabaseAccess(this);

        Cursor c = dbAccess.getDb().rawQuery("SELECT * FROM orders", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {

            String username = c.getString(0);
            int product_id = c.getInt(1);
            int number = c.getInt(2);
            int price = c.getInt(3);
            String first_name = c.getString(4);
            String last_name = c.getString(5);
            String title = c.getString(6);
            int order_id = c.getInt(7);

            Orders o = new Orders(first_name,last_name,username,title, order_id,product_id,number,price);

            orders.add(o);
            c.moveToNext();
        }
    }
}
