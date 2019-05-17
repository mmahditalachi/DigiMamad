package com.digimamad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.digimamad.cart.MainCart;
import com.digimamad.comment.MainComment;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.InputStream;
import java.net.URL;

public class ProductHomePage extends Activity {
    private static final String TAG = "ProductHomePage";
    private Button addtocart,send_comment,Show_Comments;
    private ImageView image;
    private EditText comment,quantity;
    private TextView title,detail,price,inventory;
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
        Show_Comments = findViewById(R.id.comment_btn_product_page);
        inventory = findViewById(R.id.inventory_product_page);
        quantity = findViewById(R.id.quantity_product_page);


        SelectData();
        AddToCart_btn();
        Send_Comment();
        ShowComments();

    }


    public void ShowComments()
    {
        Show_Comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToComments();
            }
        });
    }

    public void GoToComments()
    {
        Intent intent = new Intent(this, MainComment.class);
        startActivity(intent);
    }

    public void Send_Comment()
    {
        send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comment.getText().toString().equals(""))
                    Alert();
                else {
                    Comment();
                    Successful();
                    GoToCart();
                }
            }
        });
    }
    public void Successful()
    {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                ProductHomePage.this).create();

        alertDialog1.setTitle("Successful");
        alertDialog1.setMessage("your massage is send +\n+ thank you");
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog1.show();
    }

    public void Alert()
    {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                ProductHomePage.this).create();

        alertDialog1.setTitle("Error");
        alertDialog1.setMessage("write something !!");
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog1.show();
    }

    public void Comment()
    {

        DatabaseAccess db = new DatabaseAccess(this);
        //correct id of our product
        int product_id = HomePage.products.get(MainPage.number-1).getId();
        String comment_ = comment.getText().toString();
        String sql = "Insert into comments(comment,username,product_id) values('"+comment_+"','"+Login.u_info.get(Login.list_number).getUsername()+"','"+product_id+"')";
        db.getDb().execSQL(sql);
    }

    private void AddToCart_btn()
    {
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity.getText().toString().equals(""))
                    QuantityIsEmpty();
                else {
                    InsertToDatabase();
                    GoToCart();
                }
            }
        });
    }
    public void QuantityIsEmpty()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(ProductHomePage.this).create();
        alertDialog.setTitle("Quantity field");
        alertDialog.setMessage("fill quantity field");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void InsertToDatabase()
    {
        DatabaseAccess db = new DatabaseAccess(this);
        // number is id and is started from 1 and our list is started from 0
        String username = Login.u_info.get(Login.list_number).getUsername();
        String title_= HomePage.products.get(MainPage.number-1).getTitle();
        String detail_= HomePage.products.get(MainPage.number-1).getDetails();
        String color_ = HomePage.products.get(MainPage.number-1).getColor();
        String img_ = HomePage.products.get(MainPage.number-1).getImage();
//        int number_ = HomePage.products.get(MainPage.number-1).getNumber();
        int number_ = Integer.valueOf(quantity.getText().toString());
        int id = MainPage.number;
        int price_ = HomePage.products.get(MainPage.number-1).getPrice();
        int discount_ = HomePage.products.get(MainPage.number-1).getDiscount();

        String sql = "Insert into cart(title,price,details,color,img,number,discount,username,id) values('"+title_+"','"+price_+"','"+detail_+"','"+color_+"','"+img_+"','"+number_+"','"+discount_+"','"+username+"','"+id+"')";
        db.getDb().execSQL(sql);
    }

    private void GoToCart()
    {
        Intent intent = new Intent(this, MainCart.class);
        startActivity(intent);
    }
    public void NotFound404(String massage)
    {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                ProductHomePage.this).create();

        alertDialog1.setTitle("404 Not found");
        alertDialog1.setMessage(massage);
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog1.show();
    }

    public void SelectData()
    {
        Log.d(TAG,"receive data from product list");
        for (int i = 0; i < HomePage.products.size(); i++) {
            if(MainPage.number == HomePage.products.get(i).getId())
            {
                title.setText(HomePage.products.get(i).getTitle());
                Bitmap bmp;
                try {
                    InputStream in = new java.net.URL(HomePage.products.get(i).getImage()).openStream();
                    bmp = BitmapFactory.decodeStream(in);
                    image.setImageBitmap(bmp);
//                    URL url = new URL(HomePage.products.get(i).getImage());
//                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                    image.setImageBitmap(bmp);
                }catch (Exception e){
//                    Log.e("Error Message", e.getMessage());
                    NotFound404(e.getMessage());
                }
                inventory.setText(Integer.toString(HomePage.products.get(i).getNumber()));
                price.setText(Integer.toString(HomePage.products.get(i).getPrice()));
                detail.setText(HomePage.products.get(i).getDetails());
            }
        }
    }
}

