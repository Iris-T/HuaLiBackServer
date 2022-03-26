package cn.baobao.server.controller;


import cn.baobao.server.pojo.Plog;
import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.service.IPlogService;
import cn.baobao.server.utils.CommonUtils;
import cn.baobao.server.utils.FileUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Iris
 * @since 2022-03-22
 */
@Controller
@ResponseBody
@RequestMapping("/plog")
public class PlogController {

    @Autowired
    private IPlogService plogService;
    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private FileUtils fileUtils;

    @ApiOperation(value = "上传plog")
    @PostMapping("/upload")
    public RespBean uploadPlog(@RequestBody Plog plog) {
        String only1Id = commonUtils.getOnly1Id();
        plog.setId(only1Id);
        plog.setCreated(LocalDate.now());
        plog.setIscheck(0);

        return (plogService.save(plog)) ? RespBean.success("发布成功", only1Id) : RespBean.error("发送失败，请联系管理员");
    }

    @ApiOperation(value = "提交plog照片")
    @PostMapping("/img/{id}")
    public RespBean uploadImg(@RequestPart("images") MultipartFile[] images, @PathVariable("id") String pid) {

        Plog plog = plogService.getOne(new QueryWrapper<Plog>().eq("id", pid));
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
        return plogService.updateById(plog) ? RespBean.success("更新plog图片成功") : RespBean.error("更新失败，请联系管理员");
    }

    @ApiOperation(value = "提交plog审核")
    @PostMapping("/check/{id}/{isValid}")
    public RespBean checkPlog(@PathVariable("id") String pid, @PathVariable("isValid") int isValid) {

        Plog plog = plogService.getOne(new QueryWrapper<Plog>().eq("id", pid));
        if (plog == null) {
            return RespBean.error("用户该plog不存在");
        }
        if (isValid == 1 || isValid == 2) {
            plog.setIscheck(isValid);
        }

        plog.setCheckdate(LocalDate.now());
        return plogService.updateById(plog) ? RespBean.success("审核提交成功") : RespBean.error("审核提交失败");
    }

    @ApiOperation(value = "通过ID获取详细plog信息")
    @GetMapping("/detail/{id}")
    public RespBean getPlogById(@PathVariable("id") String pid) {
        Plog plog = plogService.getOne(new QueryWrapper<Plog>().eq("id", pid));
        if (plog == null) {
            return RespBean.error("获取失败");
        }
        return RespBean.success("获取成功", plog);
    }

    @ApiOperation(value = "获取所有未审核Plog")
    @GetMapping("/all")
    public List<Plog> getAllUnaudited() {
        return plogService.list(new QueryWrapper<Plog>().eq("ischeck", 0));
    }

    @ApiOperation(value = "伪随机推送Plog")
    @GetMapping("/rmd")
    public List<Plog> falseRecommend() {
        return plogService.falseRecommend();
    }
}
