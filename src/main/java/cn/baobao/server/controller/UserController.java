package cn.baobao.server.controller;


import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.pojo.User;
import cn.baobao.server.service.IUserService;
import cn.baobao.server.utils.FileUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Iris
 * @since 2022-03-22
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private FileUtils fileUtils;

    @ApiOperation(value = "用户登录接口")
    @PostMapping("/login")
    @ResponseBody
    public RespBean login(@RequestBody User user) {
        if (ObjectUtils.isEmpty(user)) {
            return RespBean.error("登录失败",null);
        }

        // 登录手机号 - 登录账号
        String phone = user.getPhone();
        String password = user.getPassword();

        if (ObjectUtils.isEmpty(phone) || ObjectUtils.isEmpty(password)) {
            return RespBean.error("登录失败",null);
        }

        User realUser = userService.getOne(new QueryWrapper<User>()
                .eq("phone", phone)
                .eq("password", password));
        realUser.setPassword(null);

        return (ObjectUtils.isEmpty(realUser)) ? RespBean.error("登录失败", null)
                : RespBean.success("登录成功", realUser);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/signup")
    @ResponseBody
    public RespBean signup(@RequestBody User user) {
        if (ObjectUtils.isEmpty(user)) {
            return RespBean.error("注册失败，参数错误", null);
        }
        String uid = fileUtils.getFileName();
        user.setId(uid);
        return userService.insertOneUser(user);
    }

    @ApiOperation(value = "获取所有用户")
    @GetMapping("/all")
    @ResponseBody
    public List<User> getAllUser() {
        return userService.list();
    }



}
