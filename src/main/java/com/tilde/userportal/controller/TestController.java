package com.tilde.userportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tilde.userportal.dao.TestDao;

@Controller
public class TestController {
    private final TestDao testDao;

    @Autowired
    public TestController(TestDao testDao) {
        this.testDao = testDao;
    }

    @GetMapping("/dbtest")
    public String testDatabaseConnection(Model model) {
        String versionInfo = testDao.getDatabaseVersion();
        model.addAttribute("versionInfo", versionInfo);
        return "dbtest";
    }
}
