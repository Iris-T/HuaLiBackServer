package cn.baobao.server.controller;


import cn.baobao.server.pojo.Admin;
import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.service.IAdminService;
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
 * @since 2022-02-28
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "后台管理员登录接口")
    @PostMapping("/login")
    @ResponseBody
    public RespBean login(@RequestBody Admin admin) {
        // 登录信息不存在，登录失败
        if (null==admin) {
            return RespBean.error("登录失败", null);
        }
        String username = admin.getUsername();
        String password = admin.getPassword();
        return adminService.getAdminByUnameWithPwd(username, password);
    }

    @ApiOperation(value = "获取（所有）管理员信息列表")
    @GetMapping("/all")
    @ResponseBody
    public List<Admin> getAllAdmins() {
        return adminService.list();
    }

}
