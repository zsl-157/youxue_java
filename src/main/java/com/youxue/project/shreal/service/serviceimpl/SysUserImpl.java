package com.youxue.project.shreal.service.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youxue.project.shreal.common.utils.HttpUtils;
import com.youxue.project.shreal.common.utils.PasswordUtils;
import com.youxue.project.shreal.entity.Result;
import com.youxue.project.shreal.entity.Role;
import com.youxue.project.shreal.entity.User;
import com.youxue.project.shreal.entity.UserAndRole;
import com.youxue.project.shreal.mapper.SysUserMapper;
import com.youxue.project.shreal.service.RedisService;
import com.youxue.project.shreal.service.RoleService;
import com.youxue.project.shreal.service.SysUserService;
import com.youxue.project.shreal.service.UserAndRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class SysUserImpl extends ServiceImpl<SysUserMapper,User> implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    //@Resource UserAndRoleServiceImpl userAndRoleService;
    //@Resource RoleServiceImpl roleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserAndRoleService userAndRoleService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Result<User> register(User userEntity) {
        Result<User> result = new Result<>();
        User user = sysUserMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName,userEntity.getUserName()));
        if (user != null){
            System.out.println(user.getUserName());
            result.setCm(0,"用户已存在!");
            log.error("用户已存在,注册失败!");
            return result;
        }
        if(!StringUtils.isEmpty(userEntity.getPhoneNumber())){
            userEntity.setPhoneNumber(userEntity.getPhoneNumber());
        }
        String uid = UUID.randomUUID().toString();
        userEntity.setUserId(uid);
        userEntity.setUserName(userEntity.getUserName());
        userEntity.setPassword(userEntity.getPassword());
        String encryptPassword = PasswordUtils.md5Password(userEntity.getPassword());
        //userEntity.setPassword();
        userEntity.setEncryptedPassword(encryptPassword);
        UserAndRole userAndRole = new UserAndRole();
        Role role = new Role();
        role.setRname("admin");
        //实际不推介使用uuid，重复的可能性较大
        String roleId = UUID.randomUUID().toString();
        role.setRid(roleId);
        roleService.addRole(role);
        userAndRole.setRid(roleId);
        userAndRole.setUid(uid);
        userAndRoleService.add(userAndRole);
        try{
            sysUserMapper.insert(userEntity);
        }catch (Exception e){
            e.printStackTrace();
            log.error("注册用户失败");
            result.setCm(0,"注册失败");
            return result;
        }
        result.setCmd(1,"注册成功",userEntity);
        return result;
    }

    //登录成功后请求接口获取用户信息
    @Override
    public Result getOneUserByUserName(String username){
        Result result = new Result();
        try{
            Map<String,Object> userInfo = jdbcTemplate.queryForMap("SELECT u.*,r.rname from user u left JOIN user_and_role ur ON u.user_id=ur.uid LEFT JOIN role r ON r.rid=ur.rid where u.user_name=?;",new Object[]{username});
            if (!ObjectUtils.isEmpty(userInfo)){
                result.setCmd(1,"seccess",userInfo);
                return result;
            }
            else{
                result.setCm(0,"error,用户不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }

    //分页查询
    @Override
    public Result getAllUsers(int pageNum,int rows,int offset) {
        Result result = new Result();
        List<User> userList= null;
        try{
            Page<User> page = new Page<>(pageNum,rows);
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("deleted","0");
            IPage<User> userIPage = sysUserMapper.selectPage(page,queryWrapper);
            userList = userIPage.getRecords();
        }catch (Exception e){
            e.printStackTrace();
            result.setCm(0,"查询失败！");
            return result;
        }
        result.setCmd(1,"成功！",userList);
        return result;

    }



    @Override
    public Result login(User userEntity,HttpServletRequest request){
        Result<Object> result = new Result<>();
        User user = sysUserMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName,userEntity.getUserName()));
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userEntity.getUserName(),userEntity.getPassword());
        try{
            subject.login(usernamePasswordToken);
            request.getSession().setAttribute("l",user.getUserName());
            String sessionId = request.getSession().getId();
            String jsonUserObj =  JSONObject.toJSONString(user);
            long expTime = 3 * 24 * 60 * 60;
            redisService.setKeyExpired(sessionId,jsonUserObj,expTime);
        }catch (UnknownAccountException e){
            e.printStackTrace();
            result.setCm(0,e.getMessage());
            return result;
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            result.setCm(0,e.getMessage());
            return result;
        }
        result.setCmd(1,"登录成功",HttpUtils.getRandomToken()+"#"+userEntity.getUserId());
        return result;
    }
    @Override
    public Map<String,Object> getallTest() {

        Map<String,Object> userInfo = jdbcTemplate.queryForMap("SELECT u.user_name,u.user_id,r.rname " +
                "from user u " +
                "left JOIN user_and_role ur ON " +
                "u.user_id=ur.uid LEFT JOIN role r ON" +
                " r.rid=ur.rid where u.user_name='liu';");
        return userInfo;
    }
}
