package cn.baobao.server.service;

import cn.baobao.server.pojo.Admin;
import cn.baobao.server.pojo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Iris
 * @since 2022-02-28
 */
public interface IAdminService extends IService<Admin> {

    RespBean getAdminByUnameWithPwd(String username, String password);
}
