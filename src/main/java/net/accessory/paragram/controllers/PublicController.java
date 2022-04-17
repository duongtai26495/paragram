package net.accessory.paragram.controllers;

import net.accessory.paragram.configs.PublicString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class PublicController {

    @GetMapping("")
    public String index(HttpServletResponse response){
        response.setContentType("text/html");
        response.setStatus(200);
        return PublicString.IndexStrings;
    }
}
