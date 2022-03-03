package com.youxue.project.shreal.service;

import com.youxue.project.shreal.entity.Course;
import com.youxue.project.shreal.entity.Result;
import org.springframework.stereotype.Service;


public interface CourseService {
    Result add(Course course);

    Result del();

    Result modify();

    Result lists();

}
