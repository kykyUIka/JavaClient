package com.example.client.utils;


import com.example.client.response.BaseListResponse;
import com.example.client.response.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;

public class HTTPUtils {
    OkHttpClient client = new OkHttpClient();
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    public BaseResponse post(String url, String book) throws IOException {
        System.out.println(url);
        RequestBody body = RequestBody.create(gson.toJson(book), MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try(Response response = client.newCall(request).execute()){
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return  gson.fromJson(response.body().string(),BaseResponse.class);
        }
    }
    public BaseListResponse get(String url,String args) throws IOException {
        Request req = new Request
                .Builder()
                .url(url + args)
                .build();
        try (Response response = client.newCall(req).execute()){
            BaseListResponse book=new BaseListResponse();
            book=gson.fromJson(response.body().string(),BaseListResponse.class);
            return book;
        }
    }
    public String put(String url, String json) throws  IOException {
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
        public class HTTPUils{
            OkHttpClient client = new OkHttpClient();

            public String post(String url, String json)throws IOException{
                RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf=8"));
                Request request = new Request.Builder().url(url).post(body).build();
                try (Response response = client.newCall(request).execute()) {
                        return response.body().string();
                }
            }
            public String get(String url, String args) throws  IOException{
                Request req = new Request.Builder().url(url + args).build();
                try (Response response = client.newCall(req).execute()) {
                    return response.body().string();
                }
            }
            public String put(String url, String json)throws IOException{
                RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf=8"));
                Request request = new Request.Builder().url(url).post(body).build();
                try (Response response = client.newCall(request).execute()) {
                    return response.body().string();
                }
            }
        }
}
