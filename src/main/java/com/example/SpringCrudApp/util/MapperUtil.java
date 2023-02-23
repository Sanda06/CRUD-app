package com.example.SpringCrudApp.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.Conditions;

public class MapperUtil {
    private static final org.modelmapper.ModelMapper modelMapper = new org.modelmapper.ModelMapper();

    static {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }
    public static <T, D> D map(T object, Class<D> targetClass) {
        return modelMapper.map(object, targetClass);
    }

    public static <T, D> List<D> map(Collection<T> objects, Class<D> targetClass) {
        return objects.stream().map(o -> map(o, targetClass)).collect(Collectors.toList());
    }
}
