package cn.baobao.server.service.impl;

import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.pojo.User;
import cn.baobao.server.mapper.UserMapper;
import cn.baobao.server.service.IUserService;
import cn.baobao.server.utils.CommonUtils;
import cn.baobao.server.utils.FileUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Iris
 * @since 2022-03-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private FileUtils fileUtils;

    /**
     * @see 增加用户积分
     * @param points
     * @param uid
     * @return
     */
    @Override
    public boolean upUserPoint(int points, String uid) {
        // 分数异常
        if (points < 0 || points > 20) {
            return false;
        }
        User user = getOne(new QueryWrapper<User>().eq("id", uid));
        // 用户不存在，返回false
        if (ObjectUtils.isEmpty(user)) {
            return false;
        } else {
            int currentPoint = user.getCurrentPoint() + points;
            int totalPoint = user.getTotalPoint() + points;
            user.setCurrentPoint(currentPoint);
            user.setTotalPoint(totalPoint);
            return updateById(user);
        }
    }

    @Override
    public RespBean login(String phone, String password) {
        User realUser = getOne(new QueryWrapper<User>()
                .eq("phone", phone)
                .eq("password", password));
        if (ObjectUtils.isEmpty(realUser)) {
            return RespBean.error("登录失败", null);
        }
        realUser.setPassword(null);
        return RespBean.success("登录成功", realUser);
    }

    @Override
    public RespBean signup(User user) {
        String uid = commonUtils.getOnly1Id();
        user.setId(uid);
        RespBean respBean = insertOneUser(user);
        fileUtils.createDir("plogs", uid, null);
        return respBean;
    }

    @Override
    public RespBean Sign_in(String uid) {
        User user = getOne(new QueryWrapper<User>().eq("id", uid));
        if (ObjectUtils.isEmpty(user)) {
            return RespBean.error("用户不存在");
        }
        LocalDate now = LocalDateTime.now().toLocalDate();
        RespBean respBean = new RespBean();

        if (userMapper.isTodaySign(uid, now) == 1) {
            respBean.setCode(403);
            respBean.setMessage("用户今日已签到");
        } else {
            int i = userMapper.UserPointIncrOne(uid);
            int res = userMapper.updateBySign(uid, now);
            if (i > 0 && res > 0) {
                respBean.setCode(200);
                respBean.setMessage("签到成功");
            } else {
                respBean.setCode(401);
                respBean.setMessage("签到失败，请重试");
            }
        }
        return respBean;
    }

    /**
     * @see 创建用户记录
     * @param user
     * @return
     */
    private RespBean insertOneUser(User user) {
        return userMapper.insertOneUser(user) > 0 ? RespBean.success("注册成功") : RespBean.error("服务器错误，请联系管理员");
    }
}
