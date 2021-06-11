package com.nwu.web;

import com.nwu.service.PictureListenerService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

        System.out.println("init .....");

//        try {
//            new PictureListenerService().init();
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }
    }
}
