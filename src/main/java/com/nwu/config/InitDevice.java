package com.nwu.config;

import com.nwu.service.ConnectDeviceService;
import com.nwu.util.AnalyzerDataCB;
import com.nwu.util.HaveReConnect;
import com.nwu.util.Utils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
public class InitDevice {
//    public static final String ip = "219.245.19.120";
//    public static final int port = 37777;
//    public static final String username = "admin";
//    public static final String password = "admin123";
//    @PostConstruct
//    public void inits(){
//        try {
//            if (ConnectDeviceService.init(new Utils.DisConnect(), new HaveReConnect())) {
//                if (ConnectDeviceService.login(ip, port, username, password)){
//                    ConnectDeviceService.realLoadPic(0, new AnalyzerDataCB());
//                }
//            } else {
//                throw new RuntimeException("init fail!");
//            }
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
}
