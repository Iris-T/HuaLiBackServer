package cn.baobao.server.service;

import cn.baobao.server.pojo.Plog;
import cn.baobao.server.pojo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Iris
 * @since 2022-03-22
 */
public interface IPlogService extends IService<Plog> {

    List<Plog> falseRecommend();

    RespBean uploadPlog(Plog plog);

    RespBean uploadImg(MultipartFile[] images, String pid);

    RespBean checkPlog(String pid, int isValid);
}
