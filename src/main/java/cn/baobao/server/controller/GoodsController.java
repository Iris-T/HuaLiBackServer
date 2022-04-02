package cn.baobao.server.controller;


import cn.baobao.server.pojo.Goods;
import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.service.IGoodsService;
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
 * @since 2022-03-24
 */
@Controller
@ResponseBody
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;

    @ApiOperation(value = "上架商品")
    @PostMapping("/new")
    public RespBean add1Good(@RequestBody Goods good) {
        if (ObjectUtils.isEmpty(good)) return RespBean.error("数据有误,请重新添加");
        return goodsService.addGood(good);
    }

    @ApiOperation(value = "上传商品图片")
    @PostMapping("/img/{id}")
    public RespBean updateGoodImg(@RequestPart("images") MultipartFile[] images, @PathVariable("id") String gid) {
        Goods good = goodsService.getOne(new QueryWrapper<Goods>().eq("id", gid));
        if (good == null) {
            return RespBean.error("更新图片失败，商品信息或不存在");
        }
        return goodsService.updateGoodImg(good, images, gid);
    }

    @ApiOperation(value = "获取所有全部/过滤状态商品")
    @GetMapping("/{state}")
    public List<Goods> getAllGoods(@PathVariable("state") int filter) {
        switch (filter) {
            // 不启用过滤
            case 0: return goodsService.list();
            // 启用过滤(仅启用商品)
            case 1: return goodsService.list(new QueryWrapper<Goods>().eq("isactive", true));
            // 启用过滤(仅弃用商品)
            case 2: return goodsService.list(new QueryWrapper<Goods>().eq("isactive", false));
            default: return null;
        }
    }

    @ApiOperation(value = "启用商品")
    @PostMapping("/active/{id}")
    public RespBean activeGood(@PathVariable("id") String gid) {
        Goods good = goodsService.getOne(new QueryWrapper<Goods>().eq("id", gid));
        if (good == null || good.isIsactive()) {
            return RespBean.error("启用失败，商品不存在或已被启用");
        }
        good.setIsactive(true);
        return goodsService.updateById(good)
                ? RespBean.success("启用商品成功")
                : RespBean.error("服务器错误，启用商品失败,请联系管理员");
    }

    @ApiOperation(value = "下架商品")
    @PostMapping("/disable/{id}")
    public RespBean disableGood(@PathVariable("id") String gid) {
        Goods good = goodsService.getOne(new QueryWrapper<Goods>().eq("id", gid));
        if (good == null || !good.isIsactive()) {
            return RespBean.error("弃用失败，商品不存在或已是弃用状态");
        }
        good.setIsactive(false);
        return goodsService.updateById(good) ? RespBean.success("弃用商品成功") : RespBean.error("服务器错误，弃用商品失败，请联系管理员");
    }

    @ApiOperation(value = "兑换商品")
    @PostMapping("/exc/{gid}")
    public RespBean exchangeGood(@PathVariable("gid") String gid, @RequestParam("uid") String uid) {
        if (ObjectUtils.isEmpty(gid) || ObjectUtils.isEmpty(uid)) {
            return RespBean.error("数据错误");
        }
        return goodsService.excGood(gid, uid);
    }


}
