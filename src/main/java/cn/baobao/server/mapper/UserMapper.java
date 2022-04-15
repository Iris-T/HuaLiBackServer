package cn.baobao.server.mapper;

import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    /**
     * @see 用户今日是否签到
     * @param uid
     * @param now
     * @return
     */
    int isTodaySign(@Param("uid") String uid, @Param("now") LocalDate now);

    /**
     * @see 插入用户签到数据
     * @param uid
     * @param now
     * @return
     */
    int updateBySign(@Param("uid") String uid, @Param("now") LocalDate now);

    /**
     * @see 更新用户积分
     * @param uid
     * @return
     */
    int UserPointIncrOne(@Param("uid") String uid);
}
