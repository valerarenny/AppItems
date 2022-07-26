package com.vincle.appitems.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.vincle.appitems.R;
import com.vincle.appitems.activity.DetailActivity;
import com.vincle.appitems.model.Item;

import org.w3c.dom.Text;

import java.util.List;

public class ItemsAdapter extends BaseAdapter {
    List<Item> items;

    Context context;

    TextView nameText;
    Button viewButton;

    public ItemsAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        }
        nameText = convertView.findViewById(R.id.nameText);
        nameText.setText(items.get(position).getName());
        viewButton = convertView.findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener(){
              @Override
              public void onClick(View view) {
                  callDetail(items.get(position).getId());
              }
          }
        );
        return convertView;
    }
    private void callDetail (int id){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id",id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
