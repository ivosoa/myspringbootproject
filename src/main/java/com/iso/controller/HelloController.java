package com.iso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ivo
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String index(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name){
        model.addAttribute("name", name);
        return "hello";
    }
}
