package com.vincle.appitems.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vincle.appitems.MainActivity;
import com.vincle.appitems.R;
import com.vincle.appitems.adapters.ItemsAdapter;
import com.vincle.appitems.interfaces.CRUDInterface;
import com.vincle.appitems.model.Item;
import com.vincle.appitems.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class DetailActivity extends AppCompatActivity {
    TextView idText;
    TextView nameText;
    TextView coolingText;
    TextView clientText;
    Button editButton;
    Button deleteButton;

    Integer id;

    Item item;

    CRUDInterface crudInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        idText = findViewById(R.id.idText);
        nameText = findViewById(R.id.nameText);
        coolingText = findViewById(R.id.coolingText);
        clientText = findViewById(R.id.clientText);
        id = getIntent().getExtras().getInt("id");
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callEdit();
            }
        });
        getOne(id);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDelete();
            }
        });


    }

    private void getOne(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        crudInterface = retrofit.create(CRUDInterface.class);
        Call<Item> call = crudInterface.getOne(id);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response Error:" , response.message());
                    return;
                }
                item = response.body();
                idText.setText(String.valueOf(item.getId()));
                nameText.setText(item.getName());
                if(item.getCooling() != null){
                    coolingText.setText(item.getCooling().toString());
                }else {
                    coolingText.setText("");
                }
                clientText.setText(item.getClient_id());
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw error aqui: ", t.getMessage());
            }
        });

    }
    private  void callEdit(){
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
        intent.putExtra("item", item );
        startActivity(intent);
    }

    private  void callDelete(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        crudInterface = retrofit.create(CRUDInterface.class);
        Call<String> call = crudInterface.deleteOne(new Long(id));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response Error:" , response.message());
                    return;
                }
                //String responseDelete = response.body();
                //                Toast toast = Toast.makeText(getApplicationContext(), responseDelete, Toast.LENGTH_LONG);
                //                toast.show();

                callMain();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw error aqui: ", t.getMessage());
                callMain();
            }
        });
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}