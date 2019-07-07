package com.miso.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BeanMappingService {

    private ModelMapper mm = new ModelMapper();

    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<T>();


        for (Object object : objects) {
            mappedCollection.add(mm.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public  <T> T mapTo(Object u, Class<T> mapToClass)
    {
        if (u == null || mapToClass == null) return null;
        return mm.map(u,mapToClass);
    }

    public ModelMapper getMapper(){
        return mm;
    }
}
