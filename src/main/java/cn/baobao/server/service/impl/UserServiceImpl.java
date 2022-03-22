package cn.baobao.server.service.impl;

import cn.baobao.server.pojo.User;
import cn.baobao.server.mapper.UserMapper;
import cn.baobao.server.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
