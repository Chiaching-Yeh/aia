package com.systex.demo.controller;


import com.systex.demo.support.FunctionLogSupport;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index(final HttpServletRequest request, final ModelMap model) {

        String ipAddress = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        System.out.println("Login Time: " + formatter.format(date));
        System.out.println("Login IP: " + ipAddress);


        model.addAttribute("localTime",formatter.format(date));
        model.addAttribute("ip",ipAddress);

        return "index";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(final HttpServletRequest request, final ModelMap model) {
        FunctionLogSupport.start("indexController.root");
        FunctionLogSupport.end("indexController.root");

        return "redirect:/index";
    }

}
