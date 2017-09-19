package com.craig.kotlin.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class IndexController {


    @RequestMapping("/index")
    fun hello(model: Model, @RequestParam(value = "name", required = false, defaultValue = "World") name: String): String {
        model.addAttribute("name", name)
        return "hello"
    }

    @GetMapping("/ticktacktoe")
    fun getTickTackToe() : String{
        return "ticktacktoe"
    }
}