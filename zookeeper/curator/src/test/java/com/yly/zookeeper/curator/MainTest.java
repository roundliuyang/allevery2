package com.yly.zookeeper.curator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZookeeperApplication.class)
public class MainTest {


    @Autowired
    private ZookeeperService zookeeperService;


    @Test
    public void test() throws InterruptedException {

        Integer number = zookeeperService.getNumber();

        System.out.println(number);

    }

}