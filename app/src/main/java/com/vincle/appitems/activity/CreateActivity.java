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
import com.vincle.appitems.interfaces.CRUDInterface;
import com.vincle.appitems.model.Capacity;
import com.vincle.appitems.model.Item;
import com.vincle.appitems.utils.Constants;

import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class CreateActivity extends AppCompatActivity {

    EditText nameText;
    EditText clientText;
    CheckBox checkBoxCooling;
    Spinner packagingSpinner;
    Spinner capacitySpinner;
    Button createButton;

    CRUDInterface crudInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create);
        nameText = findViewById(R.id.nameText);
        clientText = findViewById(R.id.clientText);
        checkBoxCooling = findViewById(R.id.checkbox_cooling);
        packagingSpinner = findViewById(R.id.packaging_spinner);
        capacitySpinner = findViewById(R.id.capacity_spinner);
        createButton = findViewById(R.id.createButton);

        //Listeners
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                createButton.setEnabled(enableButton());
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
                createButton.setEnabled(enableButton());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ArrayAdapter<String> packagingAdapter = new ArrayAdapter<String>(CreateActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.packagin_name));
        packagingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        packagingSpinner.setAdapter(packagingAdapter);

        ArrayAdapter<String> capacityAdapter = new ArrayAdapter<String>(CreateActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.capacity_name));
        packagingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        capacitySpinner.setAdapter(capacityAdapter);

        createButton.setEnabled(enableButton());

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean coolingValue = new Boolean(true);
                Set<Long> packagingSet = new HashSet<>();
                Set<Long> capacitySet = new HashSet<>();
                String packaging = packagingSpinner.getSelectedItem().toString();
                String capacity = capacitySpinner.getSelectedItem().toString();

                if(packaging.equals("botella")){
                    packagingSet.add(1L);
                }else {
                    packagingSet.add(2L);
                }

                if(capacity.equals("100")){
                    capacitySet.add(1L);
                }else {
                    capacitySet.add(2L);
                }

                if(checkBoxCooling.isChecked()){
                    coolingValue = true;
                }else{
                    coolingValue = false;
                }
                ItemCreateDto dto = new ItemCreateDto(
                        coolingValue,
                        nameText.getText().toString(), clientText.getText().toString(),
                        packagingSet,capacitySet
                        );
                create(dto);
            }
        });
    }

    private void create(ItemCreateDto dto){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        crudInterface = retrofit.create(CRUDInterface.class);
        Call<String> call = crudInterface.create(dto);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response Error:" , response.message());
                    return;
                }
                String success = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), success + "item created", Toast.LENGTH_LONG);
                toast.show();
                callMain();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Response Error:" , t.getMessage());
                return;
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