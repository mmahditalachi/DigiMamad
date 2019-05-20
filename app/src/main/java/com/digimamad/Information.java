package com.digimamad;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.digimamad.cart.MainCart;

public class Information extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView first_name,last_name,email,number_phone,home_number,address,username,card_num;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_layout);

        first_name = findViewById(R.id.first_name_information);
        last_name = findViewById(R.id.last_name_information);
        email = findViewById(R.id.email_information);
        number_phone = findViewById(R.id.phone_information);
        home_number = findViewById(R.id.home_phone_information);
        address = findViewById(R.id.address_information);
        username =findViewById(R.id.username_information);
        card_num = findViewById(R.id.card_number_information);

        FillingTheBlack();


        drawerLayout = findViewById(R.id.drawer_layout_information);

        Toolbar toolbar = findViewById(R.id.toolbar_information);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_information);

    }

    private void FillingTheBlack()
    {
        first_name.setText( Login.u_info.get(Login.list_number).getFirst_name());
        last_name.setText( Login.u_info.get(Login.list_number).getLast_name());
        email.setText( Login.u_info.get(Login.list_number).getEmail());
        number_phone.setText( Double.toString(Login.u_info.get(Login.list_number).getPhonenum()));
        home_number.setText( Double.toString(Login.u_info.get(Login.list_number).getNumber()));
        address.setText( Login.u_info.get(Login.list_number).getAddress());
        username.setText( Login.u_info.get(Login.list_number).getUsername());
        card_num.setText( Double.toString(Login.u_info.get(Login.list_number).getCardnum()));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_cart:
                GoToCart();
                break;
            case R.id.nav_home:
                GoToHome();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void GoToCart()
    {
        Intent intent = new Intent(this, MainCart.class);
        startActivity(intent);
    }
    private void GoToHome()
    {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}
