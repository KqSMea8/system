package com.system.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author weiyu
 * @version V1.0
 * @since 2018-10-05
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})

public class Demo {

    public static void main(String[] args) {
        String[] s = {"aa","bb","cc"};
        List<String> strlist = Arrays.asList(s);
        for(String str:strlist){
            System.out.println(str);
        }
        System.out.println("------------------------");
        //基本数据类型结果打印为一个元素
        int[] i ={11,22,33};
        List intlist = Arrays.asList(i);
        for(Object o:intlist){
            System.out.println(o.toString());
        }
        System.out.println("------------------------");
        Integer[] ob = {11,22,33};
        List<Integer> oblist = Arrays.asList(ob);
        for(int a:oblist){
            System.out.println(a);
        }
        System.out.println("------------------------");
    }


  /*  @Test
    public void jedisClient() {
        JedisPool pool = (JedisPool) applicationContext.getBean("jedisPool");
        Jedis jedis = null;
        try {
            jedis = pool.getResource();

            jedis.set("name", "lisi");
            String name = jedis.get("name");
            System.out.println(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (jedis != null) {
                // 关闭连接
                jedis.close();
            }
        }
        new Thread().start();


    }*/


}
