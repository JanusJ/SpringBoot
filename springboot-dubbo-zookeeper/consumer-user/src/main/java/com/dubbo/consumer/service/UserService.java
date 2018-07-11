package com.dubbo.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.provider.service.TicketService;
import org.springframework.stereotype.Service;

/**
 * Created by Janus on 2018/7/11.
 */
@Service
public class UserService {

    // 远程引用，按照全类名从注册中心寻找
    @Reference
    TicketService ticketService;

    public void hello(){
        String ticket = ticketService.getTicket();
        System.out.println("买到票了："+ticket);
    }

}
