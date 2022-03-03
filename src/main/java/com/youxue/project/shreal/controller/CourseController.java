package com.youxue.project.shreal.controller;


import com.youxue.project.shreal.entity.Course;
import com.youxue.project.shreal.entity.Result;
import com.youxue.project.shreal.service.CourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "课程相关操作Controller")
@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/v2/course")
    public Result add(@ModelAttribute Course course){
        Result result = courseService.add(course);
        return result;
    }
}
