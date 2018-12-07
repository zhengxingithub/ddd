package com.tecode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/11/9.
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"","/","/index"})
    public String index(){
        System.out.println("进入index");
        return "forward:/index.jsp";
    }
}
