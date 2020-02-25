package com.cblue.service;

import com.cblue.domain.Custom;
import com.cblue.domain.Shop;
import com.cblue.domain.Type;

import java.util.List;

public interface IShopService {
    List<Shop> getAll(Custom custom);

    void add(Shop shop);

    void update(Shop shop);

    List<Type> getType();

    Shop getById(int id);
}
