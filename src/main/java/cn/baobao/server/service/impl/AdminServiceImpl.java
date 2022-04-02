package cn.baobao.server.service.impl;

import cn.baobao.server.pojo.Admin;
import cn.baobao.server.mapper.AdminMapper;
import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.service.IAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Iris
 * @since 2022-02-28
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Override
    public RespBean getAdminByUnameWithPwd(String username, String password) {
        // 登录信息不全，登录失败
        if (ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(password)) {
            return RespBean.error("登录失败", null);
        }
        // 查找用户信息
        Admin realUser = getOne(new QueryWrapper<Admin>().eq("username", username).eq("password", password));

        // 避免信息泄露
        realUser.setPassword(null);
        return ObjectUtils.isEmpty(realUser) ? RespBean.error("登录失败", null)
                : RespBean.success("登录成功", realUser);
    }
}
