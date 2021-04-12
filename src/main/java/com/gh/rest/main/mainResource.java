package com.gh.rest.main;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/api/v1/main")
public class mainResource {

    @GetMapping(value="/test")
    @PreAuthorize("hasRole('USER')")
    public String main(HttpSession session){
        session.setAttribute("hello","gwangho");

        return "SESSION ID : " + session.getId();
    }
}
