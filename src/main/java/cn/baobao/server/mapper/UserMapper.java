package cn.baobao.server.mapper;

import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Iris
 * @since 2022-03-22
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * @see 创建用户记录
     * @param user
     * @return
     */
    int insertOneUser(@Param("user") User user);
}
