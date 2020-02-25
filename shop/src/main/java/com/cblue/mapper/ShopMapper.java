package com.cblue.mapper;

import com.cblue.domain.Custom;
import com.cblue.domain.Shop;
import com.cblue.domain.Type;

import java.util.List;

public interface ShopMapper {
    List<Shop> getAll(Custom custom);

    void add(Shop shop);

    void update(Shop shop);

    Shop getById(int id);

    List<Type> getType();

}
