package cn.baobao.server.controller;


import cn.baobao.server.pojo.Exam;
import cn.baobao.server.pojo.Examlog;
import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.service.IExamService;
import cn.baobao.server.service.IExamlogService;
import cn.baobao.server.service.IUserService;
import cn.baobao.server.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Iris
 * @since 2022-03-27
 */
@Controller
@ResponseBody
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private IExamService examService;

    @ApiOperation(value = "上传题目")
    @PostMapping("/new")
    public RespBean add1Prob(@RequestBody Exam exam) {
        if (ObjectUtils.isEmpty(exam)) {
            RespBean.error("数据有误，上传失败");
        }
        if (ObjectUtils.isEmpty(exam.getAns())) {
            RespBean.error("未设置答案，上传失败");
        }
        String only1Id = commonUtils.getOnly1Id();
        // 将答案转为大写
        exam.setAns(exam.getAns().toUpperCase());
        exam.setId(only1Id);

        return (examService.save(exam)) ? RespBean.success("上传成功", only1Id) : RespBean.error("服务器出错，上传失败，请联系管理员");
    }

    @ApiOperation(value = "随机出题 | 简单：5题 | 中等：10题 | 较难：20题")
    @GetMapping("/{cnt}")
    public List<Exam> getRandomCntProbs(@PathVariable("cnt") int cnt) {
        // 对应三个难度
        if (cnt == 5 || cnt == 10 || cnt == 20) {
            return examService.getRandomCntProbs(cnt);
        } else {
            return null;
        }
    }

    @ApiOperation(value = "审核答案")
    @PostMapping("/check")
    public RespBean checkAns(@RequestParam("ids") List<String> ids, @RequestParam("ans") List<String> ans, @RequestParam("uid") String uid) {
        if (ObjectUtils.isEmpty(ids) || ObjectUtils.isEmpty(ans) || ids.size() != ans.size()) {
            return RespBean.error("数据有误");
        }
        return examService.checkAns(ids, ans, uid);
    }

}
