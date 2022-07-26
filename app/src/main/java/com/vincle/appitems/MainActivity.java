package com.vincle.appitems;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vincle.appitems.activity.CreateActivity;
import com.vincle.appitems.adapters.ItemsAdapter;
import com.vincle.appitems.interfaces.CRUDInterface;
import com.vincle.appitems.model.Item;
import com.vincle.appitems.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Item> items;
    CRUDInterface crudInterface;

    ListView listView;
    FloatingActionButton createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =  findViewById(R.id.listItemView);
        createButton = findViewById(R.id.createItemButton);
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callCreate();
            }
        });
        getAll();
    }

    private void getAll(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    crudInterface = retrofit.create(CRUDInterface.class);
    Call<List<Item>> call = crudInterface.getAll();
    call.enqueue(new Callback<List<Item>>() {
                     @RequiresApi(api = Build.VERSION_CODES.N)
                     @Override
                     public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                         if(!response.isSuccessful()){
                             Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                             toast.show();
                             Log.e("Response Error:" , response.message());
                             return;
                         }
                         items = response.body();
                         ItemsAdapter itemsAdapter = new ItemsAdapter(items, getApplicationContext());
                         listView.setAdapter(itemsAdapter);
                         items.forEach(p -> Log.i("Items: " ,p.toString()));
                     }

                     @Override
                     public void onFailure(Call<List<Item>> call, Throwable t) {
                         Log.e("Throw error aqui: ", t.getMessage());
                     }
                 }
    );
    }
    private void callCreate() {
        Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
        startActivity(intent);
    }
}