package com.digimamad.cart;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.digimamad.DatabaseAccess;
import com.digimamad.HomePage;
import com.digimamad.Login;
import com.digimamad.MainPage;
import com.digimamad.R;
import com.digimamad.model.Cart;
import com.digimamad.model.Products;

import java.util.ArrayList;
import java.util.List;

public class MainCart extends Activity {
    public static List<Cart> cartList;
    private Button cart_checkout;
    TextView cart_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_recycleview_layout);
        cart_checkout = findViewById(R.id.cart_checkout);
        cart_price = findViewById(R.id.cart_total_price);

        SelectDataFromDatabase();
        initRecyclerView();
        TotalPrice();
    }

    public void TotalPrice()
    {
        int sum=0;
        for (int i = 0; i < cartList.size(); i++) {
            sum+=cartList.get(i).getPrice();
        }
        cart_price.setText(Integer.toString(sum));
    }
    public void Checkout_btn()
    {
        cart_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinalizeCheckout();
            }
        });
    }
    public void FinalizeCheckout()
    {
        DatabaseAccess db = new DatabaseAccess(this);

        for (int i = 0; i < cartList.size(); i++) {
            String username = Login.u_info.get(Login.list_number).getUsername();
            String title = cartList.get(i).getTitle();
            int id = cartList.get(i).getId();
            int number = cartList.get(i).getNumber();
//            int line_id = cartList.get(i).

            String sql = "Insert into orders(username,id,number,line_id,price) values('"+username+"','"+id+"','"+number+"',)";
            db.getDb().execSQL(sql);
//        String sql ="Delete from cart where username='"+Login.u_info.get(Login.list_number).getUsername()+"'";
//            db.getDb().execSQL(sql1);

        }
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
