package com.digimamad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends Activity {
    private EditText firstname,lastname,email,pass,phonenumber,homenumber,cardnum,address,username;
    private Button map,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singn_up_layout);
        Map_btn();
        Sign_up();


    }
    public void Map_btn(){
        map = findViewById(R.id.address_singup_btn);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToMap();
            }
        });
    }
    public void Sign_up()
    {
        signup = findViewById(R.id.singup_btn);
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InsertToDatabase();
            }
        });
    }

    public void InsertToDatabase()
    {

        DatabaseAccess db = new DatabaseAccess(this);

        username = findViewById(R.id.username_singup);
        firstname = findViewById(R.id.first_name_singup);
        lastname = findViewById(R.id.last_name_singup);
        email = findViewById(R.id.email_singup);
        pass = findViewById(R.id.password_singup);
        phonenumber = findViewById(R.id.phone_singup);
        homenumber = findViewById(R.id.home_phone_singup);
        cardnum = findViewById(R.id.card_number_singup);
        address = findViewById(R.id.address_singup);


        String username_ = username.getText().toString();
        String first_name = firstname.getText().toString();
        String last_name = lastname.getText().toString();
        String email_ = email.getText().toString();
        String pass_ = pass.getText().toString();
//        int phone_number  = Integer.valueOf(phonenumber.getText().toString());
//        int home_number = Integer.valueOf(homenumber.getText().toString());
//        int cardnum_ = Integer.valueOf(cardnum.getText().toString());
        String address_ = address.getText().toString();

        AddressLocation addressLocation = new AddressLocation();
        double latitude=addressLocation.location_selected.latitude;
        double longitude=addressLocation.location_selected.longitude;

        if(username_.equals("") || email_.equals("") || pass_.equals("") || latitude ==0 || longitude ==0)
            Alert();

        else {
            db.getDb().execSQL("Insert into users(firstname,lastname,email,number,phonenum,address,cardnum,pass,username,address_num1,address_num2) " +
                    "values('" + first_name + "','" + last_name + "','" + email_ + "','" + Integer.valueOf(homenumber.getText().toString()) + "','" + Integer.valueOf(phonenumber.getText().toString()) + "','" + address_ + "','" +Integer.valueOf(cardnum.getText().toString())+ "','" + pass_ + "','" + username_ + "','"+latitude+"','"+longitude+"')  ");
            GoToHomePage();
        }

    }
    public void Alert()
    {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                SignUp.this).create();

        alertDialog1.setTitle("Incorrect information");
        alertDialog1.setMessage("Sorry, your password or your username was incorrect.");
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog1.show();
    }
    public void GoToMap()
    {
        Intent intent = new Intent(this,AddressLocation.class);
        startActivity(intent);
    }

    public void GoToHomePage()
    {
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        String
//
//    }

}
