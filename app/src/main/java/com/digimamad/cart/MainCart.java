package com.digimamad.cart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.digimamad.DatabaseAccess;
import com.digimamad.HomePage;
import com.digimamad.Information;
import com.digimamad.Login;
import com.digimamad.MainPage;
import com.digimamad.R;
import com.digimamad.model.Cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainCart extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static List<Cart> cartList;
    private DrawerLayout drawerLayout;
    private static final String TAG = "MAinCart";
    private static List<Integer> counter;
    private Button cart_checkout;
    TextView cart_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_recycleview_layout);
        cart_checkout = findViewById(R.id.cart_checkout);
        cart_price = findViewById(R.id.cart_total_price);

        SelectDataFromDatabase();
        initRecyclerView();
        TotalPrice();
        Checkout_btn();

        drawerLayout = findViewById(R.id.drawer_layout_cart);
        Toolbar toolbar = findViewById(R.id.toolbar_cart);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_cart);

    }

    public void TotalPrice()
    {
        int sum=0;
        for (int i = 0; i < cartList.size(); i++) {
            sum+=cartList.get(i).getPrice()*cartList.get(i).getNumber();
        }
        cart_price.setText(Integer.toString(sum));
    }


    public void Checkout_btn()
    {
        Log.d("checkout","idk");
        cart_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartList.isEmpty())
                    Alert("fill Quantity field","Ops");
                else {
                    FinalizeCheckout();
                    UpdateInventory();
                    DeleteCart();
                    Alert("thank you for your purchase","purchase is completed");
                    // hesab ha munde
//                    GoToHomePage();
                }
            }
        });
    }

    public void GoToHomePage()
    {
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }

    public void Alert(String massage,String title)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(MainCart.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(massage);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }


    private int Counter()
    {
        Log.d("counter","check");
        DatabaseAccess db = new DatabaseAccess(this);

        String sql = "select MAX(order_id) from orders";
        Cursor c = db.getDb().rawQuery(sql,null);
        c.moveToFirst();
        int max = c.getInt(0);

        return(max+1);
    }

    private void UpdateInventory()
    {
        DatabaseAccess db = new DatabaseAccess(this);
        int count = cartList.size();
        for (int i = 0; i < HomePage.products.size() ; i++) {
            for (int j = 0; j <cartList.size() ; j++) {
                if(HomePage.products.get(i).getId() == cartList.get(j).getId())
                {
                    int new_inventory = HomePage.products.get(i).getNumber() -  cartList.get(j).getNumber();
                    count--;
                    String sql = "Update mahsulat set number = '"+new_inventory+"' where id = '"+HomePage.products.get(i).getId()+"'";
                    db.getDb().execSQL(sql);

                    if(count == 0)
                        break;
                }
            }
        }
    }

    private void DeleteCart()
    {
        DatabaseAccess db = new DatabaseAccess(this);
        String sql = "delete from cart where username ='"+Login.u_info.get(Login.list_number).getUsername()+"'";
        db.getDb().execSQL(sql);
        cartList.clear();
    }


    private void FinalizeCheckout()
    {
        Log.d("Finalize","check");
        DatabaseAccess db = new DatabaseAccess(this);
        int max = Counter();

        for (int i = 0; i < cartList.size(); i++) {

            String username = Login.u_info.get(Login.list_number).getUsername();
            String title = cartList.get(i).getTitle();
            String first_name = Login.u_info.get(Login.list_number).getFirst_name();
            String last_name = Login.u_info.get(Login.list_number).getLast_name();
            int product_id = cartList.get(i).getId();
            int number = cartList.get(i).getNumber();
            int price = cartList.get(i).getPrice();
            int order_id = max;

            String sql = "Insert into orders(username,product_id,number,price,first_name,last_name,title,order_id) values('"+username+"','"+product_id+"','"+number+"','"+price+"','"+first_name+"','"+last_name+"','"+title+"','"+order_id+"')";
            db.getDb().execSQL(sql);
        }
    }


    public void SelectDataFromDatabase()
    {
        cartList = new ArrayList<>();
        DatabaseAccess dbAccess = new DatabaseAccess(this);

        Cursor c = dbAccess.getDb().rawQuery("SELECT * FROM cart where username = '"+ Login.u_info.get(Login.list_number).getUsername()+"'", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {

            String username = c.getString(8);
            int discount = c.getInt(7);
            int number = c.getInt(6);
            String image = c.getString(5);
            int id = c.getInt(4);
            String color = c.getString(3);
            String details = c.getString(2);
            int price = c.getInt(1);
            String title = c.getString(0);
            Cart cart = new Cart(title, details, color, price, id, number, image,discount,username);
            cartList.add(cart);
            c.moveToNext();
        }
    }
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private void initRecyclerView(){
        recyclerView = findViewById(R.id.cart_recycle_view);
        adapter = new CartAdapter(this);
        recyclerView.setAdapter(adapter);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT)
    {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            Log.d(TAG,"Swipe action");
            cart_price.setText( Integer.toString(Integer.valueOf(cart_price.getText().toString()) - cartList.get(viewHolder.getAdapterPosition()).getNumber() * cartList.get(viewHolder.getAdapterPosition()).getPrice()));
            Delete(cartList.get(viewHolder.getAdapterPosition()).getId());
            cartList.remove(viewHolder.getAdapterPosition());
        }
    };
    public void Delete(int position)
    {
        DatabaseAccess db = new DatabaseAccess(this);
        String sql = "delete from cart where id ='"+position+"' ";
        db.getDb().execSQL(sql);
    }
    public void GoToHome()
    {
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }

    public void GoToInformation()
    {
        Intent intent = new Intent(this, Information.class);
        startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_home:
                GoToHome();
                break;
            case R.id.nav_information:
                GoToInformation();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
