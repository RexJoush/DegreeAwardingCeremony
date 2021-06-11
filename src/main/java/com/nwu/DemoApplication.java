package com.nwu;

import com.nwu.service.ConnectDeviceService;
import com.nwu.util.AnalyzerDataCB;
import com.nwu.util.HaveReConnect;
import com.nwu.util.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true) // 配置多线程
public class DemoApplication {



    public static void main(String[] args) {


        SpringApplication.run(DemoApplication.class, args);

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


    }

}
