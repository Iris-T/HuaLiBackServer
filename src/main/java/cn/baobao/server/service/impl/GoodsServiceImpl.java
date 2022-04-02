package cn.baobao.server.service.impl;

import cn.baobao.server.pojo.Goods;
import cn.baobao.server.mapper.GoodsMapper;
import cn.baobao.server.pojo.Logisticsinfo;
import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.pojo.User;
import cn.baobao.server.service.IGoodsService;
import cn.baobao.server.service.ILogisticsinfoService;
import cn.baobao.server.service.IUserService;
import cn.baobao.server.utils.CommonUtils;
import cn.baobao.server.utils.FileUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

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

    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private IUserService userService;
    @Autowired
    private ILogisticsinfoService logisticsinfoService;
    private final Logger logger = LoggerFactory.getLogger("FileInfo");

    @Override
    public RespBean addGood(Goods good) {
        String only1Id = commonUtils.getOnly1Id();
        good.setId(only1Id);
        good.setCreated(LocalDate.now());
        good.setIsactive(true);
        return save(good) ? RespBean.success("添加商品成功",only1Id) : RespBean.error("添加失败，请联系服务器管理员");
    }

    @Override
    public RespBean updateGoodImg(Goods good, MultipartFile[] images, String gid) {
        String imgs = good.getImgs();
        String imgPaths = ObjectUtils.isEmpty(imgs) ? "" : imgs;

        for (MultipartFile image : images) {
            String fileName = fileUtils.getOnly1FileName();
            fileUtils.saveImg(fileName, image, "goods", null, gid);
            imgPaths = imgPaths.concat(fileName+",");
        }

        good.setImgs(imgPaths);
        return updateById(good) ? RespBean.success("更新商品图片成功") : RespBean.error("更新失败，请联系管理员");
    }

    @Override
    public RespBean excGood(String gid, String uid) {
        User user = userService.getOne(new QueryWrapper<User>().eq("id", uid));
        Goods good = getOne(new QueryWrapper<Goods>().eq("id", gid).eq("isactive", true));
        if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(good)) {
            return RespBean.error("数据错误");
        }
        RespBean respBean = new RespBean();
        Integer cost = good.getCost();
        Integer currentPoint = user.getCurrentPoint();
        logger.info(String.format("用户%s当前积分为%d，将要兑换%d积分的%s", uid, currentPoint, cost, gid));
        if (currentPoint < cost) {
            respBean.setCode(403);
            respBean.setMessage("用户积分不足");
        } else if (null == user.getAddress()) {
            respBean.setCode(402);
            respBean.setMessage("用户地址缺失");
        }

        if (respBean.getCode() == 403 || respBean.getCode() == 402) {
            return respBean;
        }

        // 先减积分再生成物流信息
        user.setCurrentPoint(currentPoint - cost);
        // 用户数据更新成功则
        Logisticsinfo logisticsinfo = new Logisticsinfo();
        if (userService.updateById(user)) {
            logisticsinfo.setId(commonUtils.getOnly1Id());
            logisticsinfo.setGid(gid);
            logisticsinfo.setUid(uid);
            logisticsinfo.setCreated(LocalDate.now());
        } else {
            return RespBean.error("服务器出错，请联系管理员");
        }

        boolean res = logisticsinfoService.save(logisticsinfo);
        respBean.setCode(res ? 200 : 400);
        respBean.setMessage(res ? "兑换成功" : "兑换失败，请联系管理员生成物流信息");
        return respBean;
    }

}
