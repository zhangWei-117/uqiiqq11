package com.cblue.service;

import com.cblue.domain.Custom;
import com.cblue.domain.Shop;
import com.cblue.domain.Type;
import com.cblue.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements IShopService {
    @Autowired
    private ShopMapper mapper;

    @Override
    public List<Shop> getAll(Custom custom) {
        return mapper.getAll(custom);
    }

    @Override
    public void add(Shop shop) {
        mapper.add(shop);
    }

    @Override
    public void update(Shop shop) {
        mapper.update(shop);
    }

    @Override
    public List<Type> getType() {
        return mapper.getType();
    }

    @Override
    public Shop getById(int id) {
        return mapper.getById(id);
    }
}
