package com.digimamad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductHomePage extends Activity {
    private Button addtocart,send_comment;
    private ImageView image;
    private EditText comment;
    private TextView title,detail,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_page_layout);

        title = findViewById(R.id.product_name_product_page);
        image = findViewById(R.id.product_image_product_page);
        detail = findViewById(R.id.information_product_page);
        price = findViewById(R.id.price_product_page);
        send_comment = findViewById(R.id.send_comment);
        comment = findViewById(R.id.comment_ed);
        addtocart = findViewById(R.id.addtobag_btn_product_page);

        SelectData();
        AddToCart_btn();
        Send_Comment();

    }

    public void Send_Comment()
    {
        send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment();
            }
        });
    }
    public void Comment()
    {
        DatabaseAccess db = new DatabaseAccess(this);
        int product_id = HomePage.products.get(MainPage.number-1).getDiscount();
        String comment_ = comment.getText().toString();
        String sql = "Insert into comments(comment,username,product_id) values('"+comment_+"','"+Login.u_info.get(Login.list_number).getUsername()+"','"+product_id+"')";
        db.getDb().execSQL(sql);
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
        // number = id and id is started from 1 and our list is started from 0
        String username = Login.u_info.get(Login.list_number).getUsername();
        String title_= HomePage.products.get(MainPage.number-1).getTitle();
        String detail_= HomePage.products.get(MainPage.number-1).getDetails();
        String color_ = HomePage.products.get(MainPage.number-1).getColor();
        String img_ = HomePage.products.get(MainPage.number-1).getImage();
        int number_ = HomePage.products.get(MainPage.number-1).getNumber();
        int price_ = HomePage.products.get(MainPage.number-1).getPrice();
        int discount_ = HomePage.products.get(MainPage.number-1).getDiscount();

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

