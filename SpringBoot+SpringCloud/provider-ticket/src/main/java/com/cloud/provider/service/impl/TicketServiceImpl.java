package com.cloud.provider.service.impl;

import com.cloud.provider.service.TicketService;
import org.springframework.stereotype.Service;

/**
 * Created by Janus on 2018/7/11.
 */
@Service//Spring 注解
public class TicketServiceImpl implements TicketService {

    @Override
    public String getTicket() {
        return "《厉害了，我的国》";
    }
}
