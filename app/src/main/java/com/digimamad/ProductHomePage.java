package com.digimamad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductHomePage extends Activity {
    private Button addtocart;
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
        addtocart = findViewById(R.id.addtobag_btn_product_page);

        SelectData();
        AddToCart_btn();

    }

    public void AddToCart_btn()
    {
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InsertToDatabase();
            }
        });
    }

    public void InsertToDatabase()
    {

        DatabaseAccess db = new DatabaseAccess(this);
        String username = Login.u_info.get(Login.list_number).getUsername();
        String title_= HomePage.products.get(MainPage.number).getTitle();
        String detail_= HomePage.products.get(MainPage.number).getDetails();
        String color_ = HomePage.products.get(MainPage.number).getColor();
        String img_ = HomePage.products.get(MainPage.number).getImage();
        int number_ = HomePage.products.get(MainPage.number).getNumber();
        int price_ = HomePage.products.get(MainPage.number).getPrice();
        int discount_ = HomePage.products.get(MainPage.number).getDiscount();

        String sql = "Insert into cart(title,price,details,color,img,number,discount,username) values('"+title_+"','"+price_+"','"+detail_+"','"+color_+"','"+img_+"','"+number_+"','"+discount_+"','"+username+"')";
        db.getDb().execSQL(sql);

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

