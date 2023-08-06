package com.universityteam.flashmemorizer.event;

import com.universityteam.flashmemorizer.dto.UserDTO;
import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent{
    
    private UserDTO user;
    private String applicationUrl;

    public RegistrationCompleteEvent(UserDTO user, String applicationUrl){
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
