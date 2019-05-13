package com.digimamad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ControlPanel extends Activity {

    private EditText title,price,color,number,image,details,discount;
    private Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controlpanel_layout);

        title = findViewById(R.id.panel_title_2);
        price = findViewById(R.id.panel_price_2);
        number = findViewById(R.id.panel_number_2);
        color = findViewById(R.id.panel_color_2);
        image = findViewById(R.id.panel_image_2);
        details = findViewById(R.id.panel_detail_2);
        add_btn = findViewById(R.id.add_btn);
        discount = findViewById(R.id.panel_discount_2);

        Add_btn();

    }

    public void Add_btn()
    {
            add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(title.getText().toString().equals("") || price.getText().toString().equals("")||color.getText().toString().equals("")||number.getText().toString().equals("") ||image.getText().toString().equals("") ||details.getText().toString().equals("") || discount.getText().toString().equals(""))
                        Alert();
                    else
                        InsertIntoDatabase();
                }
            });
    }

    public void InsertIntoDatabase()
    {

        String title_= title.getText().toString();
        String detail_= details.getText().toString();
        String color_ = color.getText().toString();
        String img_ = image.getText().toString();
        int number_ = Integer.valueOf(number.getText().toString());
        int price_ = Integer.valueOf(price.getText().toString());
        int discount_ = Integer.valueOf(discount.getText().toString());

        DatabaseAccess db = new DatabaseAccess(this);
        String sql = "Insert into mahsulat(title,price,details,color,img,number,discount) values('"+title_+"','"+price_+"','"+detail_+"','"+color_+"','"+img_+"','"+number_+"','"+discount_+"')";
        db.getDb().execSQL(sql);
        Respawn();

    }
    public void Respawn()
    {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                ControlPanel.this).create();

        alertDialog1.setTitle("Complete");
        alertDialog1.setMessage("Product is saved");
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
                ControlPanel.this).create();

        alertDialog1.setTitle("Error");
        alertDialog1.setMessage("sorry you have to fill every blanks");
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog1.show();
    }


}
