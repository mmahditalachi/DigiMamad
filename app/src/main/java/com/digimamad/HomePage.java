package com.digimamad;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.digimamad.model.Products;
import com.digimamad.model.Users;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Activity {
    public static List<Products> products;
    private ArrayList<String> mImageUrls = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_layout);
        SelectDataFromDatabase();
        initImageBitmaps();
    }

    public void SelectDataFromDatabase()
    {
        products = new ArrayList<>();
        DatabaseAccess dbAccess = new DatabaseAccess(this);

        Cursor c = dbAccess.getDb().rawQuery("SELECT * FROM mahsulat", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {

            int number = c.getInt(6);
            int image = c.getInt(5);
            int id = c.getInt(4);
            String color = c.getString(3);
            String details = c.getString(2);
            int price = c.getInt(1);
            String title = c.getString(0);
            Products p = new Products(title, details, color, price, id, number, image);
            products.add(p);
            c.moveToNext();
        }
    }

    private void initImageBitmaps(){

        mImageUrls.add("https://images-na.ssl-images-amazon.com/images/I/7140-XVajnL._SL1500_.jpg");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");

        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");

        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");

        initRecyclerView();
    }
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        MainPage adapter = new MainPage(this,mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void GoToProduct()
    {
        Intent intent = new Intent(this,ProductHomePage.class);
        startActivity(intent);
    }

}
