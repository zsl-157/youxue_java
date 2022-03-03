package com.youxue.project.shreal.service.serviceimpl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youxue.project.shreal.entity.Course;
import com.youxue.project.shreal.entity.Result;
import com.youxue.project.shreal.entity.User;
import com.youxue.project.shreal.mapper.CourseMapper;
import com.youxue.project.shreal.mapper.SysUserMapper;
import com.youxue.project.shreal.service.CourseService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.UUID;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private CourseMapper courseMapper;

    @Override
    public Result add(Course course) {
        Result result = new Result();
        String userName =(String) SecurityUtils.getSubject().getPrincipal();
        User user = sysUserMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName,userName));
        if(!ObjectUtils.isEmpty(user)){
            if("1".equals(user.getVip())){
                String cid = UUID.randomUUID().toString();
                course.setCourseAuthor(userName);
                course.setUserId(user.getUserId());
                course.setCourseId(cid);
                try{
                    courseMapper.insert(course);
                    result.setCm(1,"发布成功！");
                    return result;
                }catch (Exception e){
                    e.printStackTrace();
                    result.setCm(0,"发布失败，请联系管理员");
                    return result;
                }
            }else {
                result.setCm(0,"您还不是作者，请联系管理员成为作者");
                return result;
            }
        }else {
            result.setCm(1,"您还未登录，请登录后操作");
            return result;
        }

    }

    @Override
    public Result del() {

        return null;
    }

    @Override
    public Result modify() {
        return null;
    }

    @Override
    public Result lists() {
        return null;

    }
}
