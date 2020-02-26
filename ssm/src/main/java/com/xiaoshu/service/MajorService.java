package com.xiaoshu.service;

import com.xiaoshu.dao.MajorMapper;
import com.xiaoshu.entity.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {
    @Autowired
    private MajorMapper mapper;

    public List<Major> getAll(){
        return mapper.selectAll();
    }
}
