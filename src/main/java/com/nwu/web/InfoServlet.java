package com.nwu.web;


import com.nwu.service.PictureListenerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getStudentInfo")
public class InfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new PictureListenerService().init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resp.getWriter().println("Hello Servlet");
    }
}
