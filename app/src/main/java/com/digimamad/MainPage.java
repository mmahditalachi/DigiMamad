package com.digimamad;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainPage extends RecyclerView.Adapter<MainPage.ViewHolder>{
    private static final String TAG = "MainPage";
    public static int number;
    Context context;

    public MainPage(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder,final int i) {
        final HomePage homePage = new HomePage();
        viewHolder.imagetext.setText(homePage.products.get(i).getTitle());
        viewHolder.price.setText(Integer.toString(homePage.products.get(i).getPrice()));


        viewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = homePage.products.get(i).getId();
                GoToProduct();
            }
        });

        viewHolder.btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = homePage.products.get(i).getId();
                GoToProduct();
            }
        });


        Glide.with(context)
                .asBitmap()
                .load(homePage.products.get(i).getImage())
                .into(viewHolder.image);
    }

    public void GoToProduct()
    {
        Intent intent = new Intent(this.context,ProductHomePage.class);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        HomePage homePage = new HomePage();
        return homePage.products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        CircleImageView image;
        TextView imagetext,price;
        RelativeLayout parent_layout;
        Button btn_go;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imagetext = itemView.findViewById(R.id.image_name);
            parent_layout = itemView.findViewById(R.id.parent_layout);
            btn_go = itemView.findViewById(R.id.goBtn);
            price= itemView.findViewById(R.id.product_price);
        }
    }

}
