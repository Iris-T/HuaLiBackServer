package cn.baobao.server.service;

import cn.baobao.server.pojo.Plog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Iris
 * @since 2022-03-22
 */
public interface IPlogService extends IService<Plog> {

    List<Plog> falseRecommend();
}
