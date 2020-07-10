package com.study.utils;

import org.junit.Test;

import java.util.UUID;

@SuppressWarnings("all")
public class IDutils {
    public static String getId(){
        return UUID.randomUUID().toString().replace("-","");
    }
    @Test
    public void test(){
        System.out.println(IDutils.getId());
        System.out.println(IDutils.getId());
        System.out.println(IDutils.getId());
        System.out.println(IDutils.getId());
    }
}
