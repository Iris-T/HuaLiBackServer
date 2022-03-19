package cn.baobao.server.controller;


import cn.baobao.server.pojo.Errorinfo;
import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.service.IErrorinfoService;
import cn.baobao.server.utils.FileUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Iris
 * @since 2022-02-28
 */
@Controller
@RequestMapping("/ei")
public class ErrorinfoController {

    @Autowired
    private IErrorinfoService errorinfoService;
    @Autowired
    private FileUtils fileUtils;

    @ApiOperation(value = "用户问题反馈")
    @PostMapping("/feedback")
    @ResponseBody
    public RespBean feedback(@RequestPart("images") MultipartFile[] images, @RequestParam("errinfo") String errinfo) {
        if (ObjectUtils.isEmpty(errinfo) || errinfo.length()>200) {
            return RespBean.error("错误的反馈信息!");
        }

        Errorinfo errorinfo = new Errorinfo();
        errorinfo.setId(fileUtils.getFileName());
        errorinfo.setInfo(errinfo);
        errorinfo.setCreated(LocalDateTime.now().toLocalDate());
        errorinfo.setIschecked(false);

        if (images == null || images.length < 1) {
            errorinfoService.save(errorinfo);
            return RespBean.success("反馈成功!");
        }

        String imgAddr = "";
        for (MultipartFile image : images) {
            String fileName = fileUtils.getFileName();
            try {
                image.transferTo(new File("E:/serverImgs/".concat(fileName).concat(".jpg")));
                imgAddr = imgAddr.concat(fileName).concat(",");
                errorinfo.setImgaddr(imgAddr);
                errorinfoService.save(errorinfo);
                return RespBean.success("反馈成功!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return RespBean.error("反馈失败，未知错误。请联系管理员!");
    }

    @ApiOperation(value = "获取所有反馈信息（不分页）")
    @GetMapping("/check")
    @ResponseBody
    public List<Errorinfo> checkErrorInfo() {
        List<Errorinfo> errorInfos = errorinfoService.list(new QueryWrapper<Errorinfo>()
                .eq("ischecked", false)
        );
        return (ObjectUtils.isEmpty(errorInfos)) ? null : errorInfos;
    }

    @ApiOperation(value = "提交审核")
    @PostMapping("/up/{id}/{isValid}")
    @ResponseBody
    public RespBean update(@PathVariable("id") String id, @PathVariable("isValid") boolean isValid) {
        // 审核结果有效
        Errorinfo errorinfo = errorinfoService.getById(id);
        if (ObjectUtils.isEmpty(errorinfo)) {
            return RespBean.error("提交失败,错误信息有误");
        }
        if (isValid) {
            // 图片继续存放，更新审核状态和更新时间
            errorinfo.setChecked(LocalDateTime.now().toLocalDate());
            errorinfo.setIschecked(true);
            errorinfoService.updateById(errorinfo);
        } else {
            // 删除图片
            String[] imgAddrList = errorinfo.getImgaddr().split(",");
            for (String imgName : imgAddrList) {
                fileUtils.deleteFile(imgName);
            }
            // 删除数据库记录
            errorinfoService.removeById(id);
        }
        return RespBean.success("审核结果提交成功!");
    }

    @ApiOperation(value = "获取错误信息详细信息")
    @GetMapping("/{id}")
    @ResponseBody
    public Errorinfo getById(@PathVariable("id") String id) {
        Errorinfo errorinfo = errorinfoService.getById(id);
        if (ObjectUtils.isEmpty(errorinfo)) {
            return null;
        } else {
            return errorinfo;
        }
    }
}
