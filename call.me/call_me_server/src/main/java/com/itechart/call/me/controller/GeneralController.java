package com.itechart.call.me.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {
    private final Logger logger =  LoggerFactory.getLogger(ContactController.class);

    @RequestMapping(value="/", produces = "text/html;charset=UTF-8")
    public String root(Model model) {
        logger.info("Returning a main page of the applicationn");
        return "index";
    }
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
