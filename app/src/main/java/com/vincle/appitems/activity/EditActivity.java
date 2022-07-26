package com.vincle.appitems.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.vincle.appitems.MainActivity;
import com.vincle.appitems.R;
import com.vincle.appitems.dto.ItemCreateDto;
import com.vincle.appitems.dto.ItemEditCapacityDto;
import com.vincle.appitems.dto.ItemEditDto;
import com.vincle.appitems.dto.ItemEditPackagingDto;
import com.vincle.appitems.interfaces.CRUDInterface;
import com.vincle.appitems.model.Item;
import com.vincle.appitems.utils.Constants;

import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class EditActivity extends AppCompatActivity {

    Item item;
    EditText nameText;
    EditText clientText;
    CheckBox checkBoxCooling;
    Spinner packagingSpinner;
    Spinner capacitySpinner;

    CRUDInterface crudInterface;

    Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent detailIntent = getIntent();
        item = (Item) detailIntent.getSerializableExtra("item");
        Log.i("item" , item.toString());

        nameText = findViewById(R.id.nameText);
        clientText = findViewById(R.id.clientText);

        nameText.setText(String.valueOf(item.getName()));
        clientText.setText(String.valueOf(item.getClient_id()));


        editButton = findViewById(R.id.editButton);

        //Listeners
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editButton.setEnabled(enableButton());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        clientText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editButton.setEnabled(enableButton());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        ArrayAdapter<String> packagingAdapter = new ArrayAdapter<String>(EditActivity.this,
//                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.packagin_name));
//        packagingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        packagingSpinner.setAdapter(packagingAdapter);
//
//        ArrayAdapter<String> capacityAdapter = new ArrayAdapter<String>(EditActivity.this,
//                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.capacity_name));
//        packagingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        capacitySpinner.setAdapter(capacityAdapter);

        editButton.setEnabled(enableButton());
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean coolingValue = new Boolean(true);
                Set<ItemEditPackagingDto> packagingSet = new HashSet<>();
                Set<ItemEditCapacityDto> capacitySet = new HashSet<>();
//                String packaging = packagingSpinner.getSelectedItem().toString();
//                String capacity = capacitySpinner.getSelectedItem().toString();
//
//                if(packaging.equals("botella")){
//                    packagingSet.add(new ItemEditPackagingDto(1L, 2L));
//                }else {
//                    packagingSet.add(new ItemEditPackagingDto(2L, 1L));
//                }
//
//                if(capacity.equals("100")){
//                    capacitySet.add(new ItemEditCapacityDto(1L, 2L));
//                }else {
//                    capacitySet.add(new ItemEditCapacityDto(1L, 2L));
//                }

//                if(checkBoxCooling.isChecked()){
//                    coolingValue = true;
//                }else{
//                    coolingValue = false;
//                }
                ItemEditDto dto = new ItemEditDto(
                        item.getId(),
                        coolingValue,
                        nameText.getText().toString(),
                        packagingSet,capacitySet,
                        clientText.getText().toString()
                );
                editOne(dto);
            }
        });
    }
    private void editOne(ItemEditDto dto){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        crudInterface = retrofit.create(CRUDInterface.class);
        Long id = new Long(item.getId());
        Call<Item> call = crudInterface.editOne(id, dto);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response Error:" , response.message());
                    return;
                }
                Item item = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), item.getName() + "item created", Toast.LENGTH_LONG);
                toast.show();
                callMain();
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Response Error:" , t.getMessage());
            }
        });

    }
    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private boolean enableButton(){
        return nameText.getText().toString().trim().length() > 0
                && clientText.getText().toString().trim().length() > 0;

    }


}