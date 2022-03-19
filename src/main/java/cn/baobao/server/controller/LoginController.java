package cn.baobao.server.controller;

import cn.baobao.server.pojo.Admin;
import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.service.IAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Iris 2022/2/28
 */
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录操作")
    @PostMapping("/login")
    public RespBean login(@RequestBody Admin admin) {
        if (null==admin) {
            return RespBean.error("登录失败");
        }
        String username = admin.getUsername();
        String password = admin.getPassword();
        Admin realUser = adminService.getOne(new QueryWrapper<Admin>().eq("username", username));
        if (null==realUser) {
            return RespBean.error("登录失败");
        }
        return (realUser.getPassword().equals(password)) ? RespBean.success("登录成功") : RespBean.error("登录失败");
    }
}
