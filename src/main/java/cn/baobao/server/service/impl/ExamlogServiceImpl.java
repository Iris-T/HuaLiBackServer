package cn.baobao.server.service.impl;

import cn.baobao.server.pojo.Examlog;
import cn.baobao.server.mapper.ExamlogMapper;
import cn.baobao.server.service.IExamlogService;
import cn.baobao.server.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Iris
 * @since 2022-03-29
 */
@Service
public class ExamlogServiceImpl extends ServiceImpl<ExamlogMapper, Examlog> implements IExamlogService {

    @Autowired
    private ExamlogMapper examlogMapper;
    @Autowired
    private CommonUtils commonUtils;

    @Override
    public Examlog getExamlogByUid(String uid, LocalDate now) {
        return examlogMapper.selectOne(new QueryWrapper<Examlog>().eq("uid", uid).eq("date", now));
    }
}
