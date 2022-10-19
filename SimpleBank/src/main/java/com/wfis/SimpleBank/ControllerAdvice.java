package com.wfis.SimpleBank;

import com.wfis.SimpleBank.account.MoneyNotAvailable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MoneyNotAvailable.class)
    public ModelAndView handleCityNotFoundException() {
        return new ModelAndView("redirect:" + "/moreMoney");
    }
}
