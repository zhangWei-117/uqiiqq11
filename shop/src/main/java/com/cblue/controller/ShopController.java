package com.cblue.controller;

import com.cblue.domain.Custom;
import com.cblue.domain.Pages;
import com.cblue.domain.Shop;
import com.cblue.domain.Type;
import com.cblue.service.IShopService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class ShopController {
    @Autowired
    private IShopService service;

    @RequestMapping("goshow")
    public String goshow() {
        return "show";
    }

    @RequestMapping("page")
    @ResponseBody
    public Pages page(
            @RequestParam(defaultValue = "1", value = "page") int pageNum,
            @RequestParam(defaultValue = "1", value = "rows") int pageSize,
            @RequestParam(defaultValue = "0", value = "start") int start,
            @RequestParam(defaultValue = "10000", value = "end") int end
    ) {
        Custom c = new Custom();
        c.setStart(start);
        c.setEnd(end);
        PageHelper.startPage(pageNum, pageSize);
        List<Shop> list = service.getAll(c);
        PageInfo<Shop> info = new PageInfo<>(list);
        Pages p = new Pages();
        p.setRows(list);
        p.setTotal(info.getTotal());
        return p;
    }

    @RequestMapping("goadd")
    public String goadd() {
        return "show2";
    }
//添加
    @RequestMapping("add")
    public String add(MultipartFile imgs, Shop shop) {
        shop.setImg("/" + imgs.getOriginalFilename());
        try {
            imgs.transferTo(new File("D:\\imges\\", imgs.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        service.add(shop);
        return "show";
    }

    @RequestMapping("getType")
    @ResponseBody
    public List<Type> gettype() {
        List<Type> list = service.getType();
        System.out.println(list+"------------------");
        return list;
    }

    @RequestMapping("goupdate")
    public String goupdate(int id, Model model) {
        Shop shop = service.getById(id);
        model.addAttribute("shop",shop);
        return "show3";
    }
//修改
    @RequestMapping("update")
    public String update(MultipartFile imgs, Shop shop) {
        if (imgs.getSize()>0){
        shop.setImg("/" + imgs.getOriginalFilename());
        try {
            imgs.transferTo(new File("D:\\imges\\", imgs.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        service.update(shop);
        return "show";
    }
//补货
    @RequestMapping("buhuo")
    public String buhuo(int id){
        Shop shop = service.getById(id);
        shop.setNum(100);
        service.update(shop);
        return "show";
    }
}
