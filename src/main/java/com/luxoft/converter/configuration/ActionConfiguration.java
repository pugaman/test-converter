package com.luxoft.converter.configuration;

import com.luxoft.converter.ui.component.action.StartConvertingAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

/**
 * Created by Pavel on 10.11.2016.
 */
@Configuration
public class ActionConfiguration {

    @Bean(name = "startConvertingAction")
    public Action getStartConvertingAction(){
        return new StartConvertingAction();
    }
}
