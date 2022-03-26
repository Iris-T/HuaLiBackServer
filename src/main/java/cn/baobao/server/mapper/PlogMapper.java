package cn.baobao.server.mapper;

import cn.baobao.server.pojo.Plog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Iris
 * @since 2022-03-22
 */
public interface PlogMapper extends BaseMapper<Plog> {

    List<Plog> getRandomPlogs();
}
