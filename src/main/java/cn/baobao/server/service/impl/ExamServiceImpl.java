package cn.baobao.server.service.impl;

import cn.baobao.server.pojo.Exam;
import cn.baobao.server.mapper.ExamMapper;
import cn.baobao.server.pojo.Examlog;
import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.service.IExamService;
import cn.baobao.server.service.IExamlogService;
import cn.baobao.server.service.IUserService;
import cn.baobao.server.utils.CommonUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
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
    @Autowired
    private IUserService userService;
    @Autowired
    private IExamlogService examlogService;
    @Autowired
    private CommonUtils commonUtils;

    @Override
    public List<Exam> getRandomCntProbs(int cnt) {
        List<Exam> exams = examMapper.getRandomCntProbs(cnt);
        for (Exam prob : exams) {
            prob.setAns(null);
        }
        return exams;
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

    @Override
    public RespBean checkAns(List<String> ids, List<String> ans, String uid) {

        RespBean respBean = new RespBean();
        // 过滤提交数据
        if (ids.size() == 5 || ids.size() == 10 || ids.size() == 20) {
            boolean res = false;
            boolean flag = true;

            int points = rightCnt(ids, ans);
            LocalDate now = LocalDate.now();
            Examlog examlog = examlogService.getExamlogByUid(uid, now);

            // 数据库不存在当日用户答题数据
            if (ObjectUtils.isEmpty(examlog)) {
                examlog = new Examlog(commonUtils.getOnly1Id(), uid, 1, now);
                res = examlogService.save(examlog);
                flag = false;
            }

            int cnt = examlog.getCnt();
            // 每日答题三次，超过则限制答题
            if (cnt == 3) {
                respBean.setCode(403);
                respBean.setMessage("已达每日答题次数上限");
                return respBean;
            }
            if (flag) {
                examlog.setCnt(cnt+1);
                res = examlogService.updateById(examlog);
            }
            // 用于表示提交记录成功，但未成功更新用户得分
            respBean.setCode(402);

            // 答题记录添加成功则添加分数
            if (res) {
                res = userService.upUserPoint(points, uid);
                // 存储成功则设为200，否则设为400
                respBean.setCode(res ? 200 : 400);
            }

            long code = respBean.getCode();
            if (code == 200) {
                respBean.setMessage("答案提交成功");
                respBean.setObj(points);
            } else {
                respBean.setMessage("得分数据更新出错，但是答题记录已被录入，请联系管理员进行恢复");
            }
            return respBean;
        }
        return RespBean.error("数量有误");
    }
}
