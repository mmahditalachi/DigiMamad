package com.digimamad.cart;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.digimamad.DatabaseAccess;
import com.digimamad.Login;
import com.digimamad.MainPage;
import com.digimamad.R;
import com.digimamad.model.Cart;
import com.digimamad.model.Products;

import java.util.ArrayList;
import java.util.List;

public class MainCart extends Activity {
    public static List<Cart> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_recycleview_layout);

        SelectDataFromDatabase();
        initRecyclerView();
    }

    public void SelectDataFromDatabase()
    {
        cartList = new ArrayList<>();
        DatabaseAccess dbAccess = new DatabaseAccess(this);

        Cursor c = dbAccess.getDb().rawQuery("SELECT * FROM cart where username = '"+ Login.u_info.get(Login.list_number).getUsername()+"'", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {


            String username = c.getString(8);
            int discount = c.getInt(7);
            int number = c.getInt(6);
            String image = c.getString(5);
            int id = c.getInt(4);
            String color = c.getString(3);
            String details = c.getString(2);
            int price = c.getInt(1);
            String title = c.getString(0);
            Cart cart = new Cart(title, details, color, price, id, number, image,discount,username);
            cartList.add(cart);
            c.moveToNext();

        }
    }
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.cart_recycle_view);
        CartAdapter adapter = new CartAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
