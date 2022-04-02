package cn.baobao.server.service;

import cn.baobao.server.pojo.Examlog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Iris
 * @since 2022-03-29
 */
public interface IExamlogService extends IService<Examlog> {

    /**
     * @see 根据uid和当前日期获取Examlog信息
     * @param uid
     * @param now
     * @return
     */
    Examlog getExamlogByUid(String uid, LocalDate now);
}
