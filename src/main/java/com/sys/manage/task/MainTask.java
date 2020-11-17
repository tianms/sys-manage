package com.sys.manage.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 功能描述: 监听器
 * @auther: tms
 * @date: 2020/11/17 10:35
 * @return
*/
@Slf4j
@Component
public class MainTask {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void instalmentsGrantIntegral() {
        log.info("定时器执行了------------------");
    }

}
