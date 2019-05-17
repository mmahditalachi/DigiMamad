package com.digimamad;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.digimamad.cart.MainCart;
import com.digimamad.model.Products;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends Activity {
    public static List<Products> products;
    private Button goto_ntn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_layout);
        SelectDataFromDatabase();
        initImageBitmaps();

        goto_ntn = findViewById(R.id.gotocart);
        goto_ntn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToCart();
            }
        });
    }
    private void GoToCart()
    {
        Intent intent = new Intent(this, MainCart.class);
        startActivity(intent);
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
