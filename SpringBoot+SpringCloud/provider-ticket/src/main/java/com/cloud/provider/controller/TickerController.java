package com.cloud.provider.controller;

import com.cloud.provider.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Janus on 2018/7/11.
 */
@RestController
public class TickerController {

    @Autowired
    TicketService ticketService;

    @Value("${server.port}")
    String port;

    // SpringCloud 整合微服务时是按照轻量级的HTTP进行通信
    @GetMapping("/ticket")
    public String getTicker(){
        System.out.println("端口:"+port);
        return ticketService.getTicket();

    }
}
