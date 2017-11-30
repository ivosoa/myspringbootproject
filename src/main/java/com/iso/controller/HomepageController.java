/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ivo
 */
@Controller
public class HomepageController {
    @RequestMapping("/homepage")
    public ModelAndView index(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name){
        Map<String, Object> params = new HashMap<>();
        
        params.put("name", name);
        return new ModelAndView("app.homepage", params);
    }
}
