package com.anime.test_client.controller;

import com.anime.api.test.Default;
import com.anime.framework.domain.test.DefaultModel;
import com.anime.framework.model.response.QueryResponseResult;
import com.anime.test_client.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestCtrl implements Default{

    @Autowired
    TestService testService;

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size, DefaultModel def) {
        return testService.findList(page,size,def);
    }
}
