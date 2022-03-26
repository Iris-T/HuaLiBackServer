package cn.baobao.server.service.impl;

import cn.baobao.server.pojo.Plog;
import cn.baobao.server.mapper.PlogMapper;
import cn.baobao.server.service.IPlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Iris
 * @since 2022-03-22
 */
@Service
public class PlogServiceImpl extends ServiceImpl<PlogMapper, Plog> implements IPlogService {

    @Autowired
    private PlogMapper plogMapper;

    @Override
    public List<Plog> falseRecommend() {
        return plogMapper.getRandomPlogs();
    }
}
