package com.cccis.oa.wechat.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ccc on 2017/9/20.
 */
@Controller
@RequestMapping("/hello")
public class HelloAction {

    public HelloAction() {
        System.err.println("Init Hello Action");
    }

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public @ResponseBody
    String sayHello() {
        return "Hello Action";
    }
}
