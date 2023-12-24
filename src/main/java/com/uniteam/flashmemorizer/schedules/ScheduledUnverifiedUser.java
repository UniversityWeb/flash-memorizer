package com.uniteam.flashmemorizer.schedules;

import com.uniteam.flashmemorizer.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledUnverifiedUser {
    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteUnverifiedAccount(){
        userService.deleteUnverifiedUsers();
    }
}
