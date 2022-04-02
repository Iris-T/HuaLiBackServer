package cn.baobao.server.service;

import cn.baobao.server.pojo.Errorinfo;
import cn.baobao.server.pojo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Iris
 * @since 2022-02-28
 */
public interface IErrorinfoService extends IService<Errorinfo> {

    RespBean feedback(MultipartFile[] images, String errinfo);

    RespBean checkErrInfo(String id, boolean isValid);
}
