package cn.baobao.server.service;

import cn.baobao.server.pojo.Exam;
import cn.baobao.server.pojo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Iris
 * @since 2022-03-27
 */
public interface IExamService extends IService<Exam> {

    List<Exam> getRandomCntProbs(int cnt);

    int rightCnt(List<String> ids, List<String> ans);

    RespBean checkAns(List<String> ids, List<String> ans, String uid);
}
