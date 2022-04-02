package cn.baobao.server.controller;


import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.pojo.User;
import cn.baobao.server.service.IUserService;
import cn.baobao.server.utils.CommonUtils;
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
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户登录接口")
    @PostMapping("/login")
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
        return userService.login(phone, password);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/signup")
    public RespBean signup(@RequestBody User user) {
        if (ObjectUtils.isEmpty(user)) {
            return RespBean.error("注册失败，参数错误", null);
        }
        return userService.signup(user);
    }

    @ApiOperation(value = "获取所有用户")
    @GetMapping("/all")
    @ResponseBody
    public List<User> getAllUser() {
        return userService.list();
    }

}
