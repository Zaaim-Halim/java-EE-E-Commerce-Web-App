package com.midvi.eventListener;

import java.util.Date;

import javax.ejb.Local;
import javax.enterprise.event.Observes;

import com.midvi.event.OnRegistrationCompleteEvent;

@Local
public interface OnRegistrationCompleteEventListenerLocal {
	
    void catchTheEvent(@Observes OnRegistrationCompleteEvent event);
    void deleteUserVirificationToken(String token);
    void deleteUserVirificationToken(Date date);
	

}
