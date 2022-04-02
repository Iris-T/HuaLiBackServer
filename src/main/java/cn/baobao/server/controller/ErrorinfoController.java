package cn.baobao.server.controller;


import cn.baobao.server.pojo.Errorinfo;
import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.service.IErrorinfoService;
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
import java.time.LocalDateTime;
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
@ResponseBody
@RequestMapping("/errinfo")
public class ErrorinfoController {

    @Autowired
    private IErrorinfoService errorinfoService;

    @ApiOperation(value = "用户问题反馈")
    @PostMapping("/feedback")
    public RespBean feedback(@RequestPart("images") MultipartFile[] images, @RequestParam("errinfo") String errinfo) {
        if (ObjectUtils.isEmpty(errinfo) || errinfo.length()>200) {
            return RespBean.error("错误的反馈信息!");
        }
        return errorinfoService.feedback(images, errinfo);
    }

    @ApiOperation(value = "获取所有反馈信息（不分页）")
    @GetMapping("/check")
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
        if (ObjectUtils.isEmpty(id) || ObjectUtils.isEmpty(isValid)) {
            return RespBean.error("提交失败,数据有误");
        }
        return errorinfoService.checkErrInfo(id, isValid);
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
