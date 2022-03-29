package cn.baobao.server.controller;

import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.utils.FileUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Iris 2022/3/1
 */
@RestController("/image")
public class ImgController {
    private final String ERRORINFO_FLODER = "errinfos/";
    private final String GOOD_FLODER = "goods/";
    private final String PLOG_FOLDER = "plogs/";

    @Autowired
    private FileUtils fileUtils;

//    @ApiOperation(value = "获取错误信息图片链接")
//    @GetMapping(value = "/e/{eid}/{img}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public byte[] getErrorInfoImage(@PathVariable("eid") String eid, @PathVariable("img") String imgName) {
//        return fileUtils.getImg(imgName, ERRORINFO_FLODER, null, eid);
//    }
//
//    @ApiOperation(value = "获取商品详情图片链接")
//    @GetMapping(value = "/g/{gid}/{img}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public byte[] getGoodImage(@PathVariable("gid") String gid, @PathVariable("img") String imgName) {
//        return fileUtils.getImg(imgName, GOOD_FLODER, null, gid);
//    }
//
//    @ApiOperation(value = "获取Plog图片图片链接")
//    @GetMapping(value = "/p/{uid}/{pid}/{img}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public byte[] getPlogImage(@PathVariable("uid") String uid, @PathVariable("pid") String pid, @PathVariable("img") String imgName) {
//        return fileUtils.getImg(imgName, PLOG_FOLDER, uid, pid);
//    }
}
