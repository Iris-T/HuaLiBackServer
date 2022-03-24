package cn.baobao.server.service.impl;

import cn.baobao.server.pojo.Goods;
import cn.baobao.server.mapper.GoodsMapper;
import cn.baobao.server.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Iris
 * @since 2022-03-24
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

}
