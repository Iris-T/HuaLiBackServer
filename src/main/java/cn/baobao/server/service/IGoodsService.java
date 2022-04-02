package cn.baobao.server.service;

import cn.baobao.server.pojo.Goods;
import cn.baobao.server.pojo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Iris
 * @since 2022-03-24
 */
public interface IGoodsService extends IService<Goods> {

    RespBean addGood(Goods good);

    RespBean updateGoodImg(Goods good, MultipartFile[] images, String gid);

    RespBean excGood(String gid, String uid);
}
