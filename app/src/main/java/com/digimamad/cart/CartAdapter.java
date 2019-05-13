package com.digimamad.cart;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.digimamad.ControlPanel;
import com.digimamad.HomePage;
import com.digimamad.MainPage;
import com.digimamad.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;

    public CartAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_list_layout,viewGroup,false);
        CartAdapter.ViewHolder viewHolder = new CartAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        final HomePage homePage = new HomePage();
        viewHolder.imagetext.setText(MainCart.cartList.get(i).getTitle());
        viewHolder.price.setText(Integer.toString(MainCart.cartList.get(i).getPrice()));

        Glide.with(context)
                .asBitmap()
                .load(MainCart.cartList.get(i).getImage())
                .into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return MainCart.cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView imagetext,price;
        RelativeLayout parent_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imagetext = itemView.findViewById(R.id.image_name);
            parent_layout = itemView.findViewById(R.id.parent_layout);
            price= itemView.findViewById(R.id.product_price);
        }
    }
}
