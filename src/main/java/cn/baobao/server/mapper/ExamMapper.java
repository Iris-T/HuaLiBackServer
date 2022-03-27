package cn.baobao.server.mapper;

import cn.baobao.server.pojo.Exam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Iris
 * @since 2022-03-27
 */
public interface ExamMapper extends BaseMapper<Exam> {

    List<Exam> getRandomCntProbs(int cnt);

    List<String> getAnsById(List<String> ids);
}
