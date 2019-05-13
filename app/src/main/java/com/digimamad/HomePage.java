package com.digimamad;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.digimamad.model.Products;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends Activity {
    public static List<Products> products;
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

            int discount = c.getInt(7);
            int number = c.getInt(6);
            String image = c.getString(5);
            int id = c.getInt(4);
            String color = c.getString(3);
            String details = c.getString(2);
            int price = c.getInt(1);
            String title = c.getString(0);
            Products p = new Products(title, details, color, price, id, number, image,discount);
            products.add(p);
            c.moveToNext();
        }
    }

    private void initImageBitmaps(){

        initRecyclerView();
    }
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        MainPage adapter = new MainPage(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
