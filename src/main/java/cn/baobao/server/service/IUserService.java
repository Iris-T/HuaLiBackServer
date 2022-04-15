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
     * @see 增加用户得分
     * @param points
     * @return
     */
    boolean upUserPoint(int points, String uid);

    /**
     * @see 用户登录
     * @param phone
     * @param password
     * @return
     */
    RespBean login(String phone, String password);

    /**
     * @see 用户注册信息
     * @param user
     * @return
     */
    RespBean signup(User user);

    /**
     * 用户签到-每日一次，每次1积分
     * @param uid 用户ID
     * @return 成功与否
     */
    RespBean Sign_in(String uid);
}
