package com.nwu.controller;

import com.nwu.service.ConnectDeviceService;
import com.nwu.util.Utils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.nwu.util.*;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
public class ConnectDeviceController {
    public static final String ip = "219.245.19.120";
    public static final int port = 37777;
    public static final String username = "admin";
    public static final String password = "admin123";

    //@GetMapping("/deviceinit")
    public String init(Model model){
        try {
            if (ConnectDeviceService.init(new Utils.DisConnect(), new HaveReConnect())) {
                if (ConnectDeviceService.login(ip, port, username, password)){
                    ConnectDeviceService.realLoadPic(0, new AnalyzerDataCB());
                    System.out.println(true);
                }
            } else {
                throw new RuntimeException("init fail!");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        model.addAttribute("name","hello,");
        return "hello";
    }

}
