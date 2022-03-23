package cn.baobao.server.service;

import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Iris
 * @since 2022-03-22
 */
public interface IUserService extends IService<User> {

    /**
     * @see 创建用户，插入用户记录
     * @param user
     * @return
     */
    RespBean insertOneUser(User user);
}
