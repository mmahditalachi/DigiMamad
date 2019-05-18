package com.digimamad.orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.digimamad.R;

public class Orders_List extends BaseAdapter {

    Context context;

    public Orders_List(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return MainOrder.orders.size();
    }

    @Override
    public Object getItem(int position) {
        return MainOrder.orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.order_list_layout,parent,false);
            TextView username = convertView.findViewById(R.id.username_order);
            TextView title = convertView.findViewById(R.id.title_order);
            TextView number = convertView.findViewById(R.id.number_order);
            TextView id_order = convertView.findViewById(R.id.id_order);
            TextView price = convertView.findViewById(R.id.price_order);


            username.setText(MainOrder.orders.get(position).getUsername());
            title.setText(MainOrder.orders.get(position).getTitle());
            number.setText(Integer.toString(MainOrder.orders.get(position).getNumber()));
            id_order.setText(Integer.toString(MainOrder.orders.get(position).getId()));
            price.setText(Integer.toString(MainOrder.orders.get(position).getPrice()));
        }
        return convertView;
    }
}
