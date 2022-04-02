package cn.baobao.server.service.impl;

import cn.baobao.server.pojo.Plog;
import cn.baobao.server.mapper.PlogMapper;
import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.service.IPlogService;
import cn.baobao.server.utils.CommonUtils;
import cn.baobao.server.utils.FileUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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
    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private FileUtils fileUtils;

    @Override
    public List<Plog> falseRecommend() {
        return plogMapper.getRandomPlogs();
    }

    @Override
    public RespBean uploadPlog(Plog plog) {
        String only1Id = commonUtils.getOnly1Id();
        plog.setId(only1Id);
        plog.setCreated(LocalDate.now());
        plog.setIscheck(0);

        return (save(plog)) ? RespBean.success("发布成功", only1Id) : RespBean.error("发送失败，请联系管理员");
    }

    @Override
    public RespBean uploadImg(MultipartFile[] images, String pid) {
        Plog plog = getOne(new QueryWrapper<Plog>().eq("id", pid));
        if (plog == null) {
            return RespBean.error("提交图片失败,plog或不存在");
        }

        String imgs = plog.getImgs();
        String imgPaths = ObjectUtils.isEmpty(imgs) ? "" : imgs;

        for (MultipartFile image : images) {
            String imgName = fileUtils.getOnly1FileName();
            fileUtils.saveImg(imgName, image, "plogs", plog.getUid(), pid);
            imgPaths = imgPaths.concat(imgName+",");
        }

        plog.setImgs(imgPaths);
        return updateById(plog) ? RespBean.success("更新plog图片成功") : RespBean.error("更新失败，请联系管理员");
    }

    @Override
    public RespBean checkPlog(String pid, int isValid) {
        Plog plog = getOne(new QueryWrapper<Plog>().eq("id", pid));
        if (plog == null) {
            return RespBean.error("用户该plog不存在");
        }
        if (isValid == 1 || isValid == 2) {
            plog.setIscheck(isValid);
        }

        plog.setCheckdate(LocalDate.now());
        return updateById(plog) ? RespBean.success("审核提交成功") : RespBean.error("审核提交失败");
    }


}
