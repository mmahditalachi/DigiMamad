package com.digimamad;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductHomePage extends Activity {
    private ImageView image;
    private TextView title,detail,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_page_layout);

        title = findViewById(R.id.product_name_product_page);
        image = findViewById(R.id.product_image_product_page);
        detail = findViewById(R.id.information_product_page);
        price = findViewById(R.id.price_product_page);

        SelectData();

    }
    public void SelectData()
    {
        for (int i = 0; i < HomePage.products.size(); i++) {
            if(MainPage.number == HomePage.products.get(i).getId())
            {
                title.setText(HomePage.products.get(i).getTitle());
//                image = HomePage.products.get(i).getImage();
                price.setText(Integer.toString(HomePage.products.get(i).getPrice()));
                detail.setText(HomePage.products.get(i).getDetails());
            }
        }
    }
}

