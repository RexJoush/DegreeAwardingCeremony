package com.nwu.util;

import com.nwu.controller.ConnectDeviceController;
import com.nwu.service.TestThread;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ReadPictureThread implements CommandLineRunner {

    @Resource
    private TestThread testThread;


    @Override
    public void run(String... args) throws Exception {
        testThread.init();
        //testThread.getPicture();
    }


}
