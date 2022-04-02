package cn.baobao.server.service.impl;

import cn.baobao.server.pojo.Errorinfo;
import cn.baobao.server.mapper.ErrorinfoMapper;
import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.service.IErrorinfoService;
import cn.baobao.server.utils.CommonUtils;
import cn.baobao.server.utils.FileUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Iris
 * @since 2022-02-28
 */
@Service
public class ErrorinfoServiceImpl extends ServiceImpl<ErrorinfoMapper, Errorinfo> implements IErrorinfoService {

    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private FileUtils fileUtils;

    @Override
    public RespBean feedback(MultipartFile[] images, String errinfo) {
        Errorinfo errorinfo = new Errorinfo();
        String only1Id = commonUtils.getOnly1Id();
        errorinfo.setId(only1Id);
        errorinfo.setInfo(errinfo);
        errorinfo.setCreated(LocalDate.now());

        String imgPaths = "";
        for (MultipartFile image : images) {
            String fileName = fileUtils.getOnly1FileName();
            boolean flag = fileUtils.saveImg(fileName, image, "errinfos", null, only1Id);
            if (!flag) return RespBean.error("添加图片失败，请联系管理员");
            imgPaths = imgPaths.concat(fileName+",");
        }
        errorinfo.setImgaddr(imgPaths);
        return (save(errorinfo))
                ? RespBean.success("反馈成功")
                : RespBean.error("反馈失败，未知错误。请联系管理员!");
    }

    @Override
    public RespBean checkErrInfo(String id, boolean isValid) {
        // 审核结果有效
        Errorinfo errorinfo = getById(id);
        if (ObjectUtils.isEmpty(errorinfo)) {
            return RespBean.error("提交失败,数据有误");
        }
        if (isValid) {
            // 图片继续存放，更新审核状态和更新时间
            errorinfo.setChecked(LocalDateTime.now().toLocalDate());
            errorinfo.setIschecked(true);
            updateById(errorinfo);
        } else {
            // 删除图片
            fileUtils.delImg(errorinfo.getId(), "errinfos");
            // 删除数据库记录
            removeById(id);
        }
        return RespBean.success("审核结果提交成功!");
    }
}
