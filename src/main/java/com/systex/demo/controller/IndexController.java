package com.systex.demo.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
public class IndexController {

    @GetMapping("/index")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> index(final HttpServletRequest request, final ModelMap model) {

        String IP = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println("Login Time: " + formatter.format(date));
        System.out.println("Login IP: " + IP);

        // 準備返回的 JSON 數據
        Map<String, Object> response = new HashMap<>();
        response.put("loginTime", formatter.format(date));
        response.put("loginIP", IP);

        // 返回 JSON 數據和 200 OK 狀態
        return ResponseEntity.ok(response);
    }

}
