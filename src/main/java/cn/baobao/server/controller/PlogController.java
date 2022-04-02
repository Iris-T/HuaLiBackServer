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

    @ApiOperation(value = "上传plog")
    @PostMapping("/upload")
    public RespBean uploadPlog(@RequestBody Plog plog) {
        if (ObjectUtils.isEmpty(plog)) {
            return RespBean.error("数据有误，发布失败");
        }
        return plogService.uploadPlog(plog);
    }

    @ApiOperation(value = "提交plog照片")
    @PostMapping("/img/{id}")
    public RespBean uploadImg(@RequestPart("images") MultipartFile[] images, @PathVariable("id") String pid) {
        if (null == pid || ObjectUtils.isEmpty(images)) {
            return RespBean.error("提交失败，数据有误");
        }
        return plogService.uploadImg(images, pid);
    }

    @ApiOperation(value = "提交plog审核")
    @PostMapping("/check/{id}/{isValid}")
    public RespBean checkPlog(@PathVariable("id") String pid, @PathVariable("isValid") int isValid) {
        if (null == pid || ObjectUtils.isEmpty(isValid)) {
            return RespBean.error("提交失败，数据有误");
        }
        return plogService.checkPlog(pid, isValid);
    }

    @ApiOperation(value = "通过ID获取详细plog信息")
    @GetMapping("/detail/{id}")
    public RespBean getPlogById(@PathVariable("id") String pid) {
        if (ObjectUtils.isEmpty(pid)) {
            return RespBean.error("获取信息失败，数据有误");
        }
        Plog plog = plogService.getOne(new QueryWrapper<Plog>().eq("id", pid));
        return ObjectUtils.isEmpty(plog) ? RespBean.error("获取失败") : RespBean.success("获取成功", plog);
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
