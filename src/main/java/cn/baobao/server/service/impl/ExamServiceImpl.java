package cn.baobao.server.service.impl;

import cn.baobao.server.pojo.Exam;
import cn.baobao.server.mapper.ExamMapper;
import cn.baobao.server.service.IExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Iris
 * @since 2022-03-27
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements IExamService {

    @Autowired
    private ExamMapper examMapper;

    @Override
    public List<Exam> getRandomCntProbs(int cnt) {
        return examMapper.getRandomCntProbs(cnt);
    }

    @Override
    public int rightCnt(List<String> ids, List<String> ans) {
        // 将输入的ans全部转大写
        ans = ans.parallelStream().map(String::toUpperCase).collect(Collectors.toList());
        int points = 0;
        List<String> ansById = examMapper.getAnsById(ids);
        for (int i = 0; i < ans.size(); i++)
            if (ans.get(i).equals(ansById.get(i))) points++;
        return points;
    }
}
