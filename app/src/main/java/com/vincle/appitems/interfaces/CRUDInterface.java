package com.vincle.appitems.interfaces;

import com.vincle.appitems.dto.ItemCreateDto;
import com.vincle.appitems.dto.ItemEditDto;
import com.vincle.appitems.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CRUDInterface {

    @GET("item/")
    Call<List<Item>> getAll();

    @GET("item/{id}")
    Call<Item> getOne(@Path("id") int id);

    @POST("item/")
    Call<String> create(@Body ItemCreateDto itemCreateDto);

    @PUT("item/{id}")
    Call<Item> editOne(@Path("id") Long id, @Body ItemEditDto editDto);

    @DELETE("item/{id}")
    Call<String> deleteOne(@Path("id") Long id);

}
