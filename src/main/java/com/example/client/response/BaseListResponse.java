package com.example.client.response;

import com.example.client.Entity.BookEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BaseListResponse extends BaseResponse {
    public List<BookEntity> data;
    public BaseListResponse(List<BookEntity> data){
        super (true,"Список книг");
        this.data=data;
    }
}
