package cn.baobao.server.controller;

import cn.baobao.server.utils.FileUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Iris 2022/3/1
 */
@RestController("/image")
public class ImgController {
    private final String ERRORINFO_FLODER = "errinfos/";
    private final String GOOD_FLODER = "goods/";
    private final String PLOG_FOLDER = "plogs/";
    private final Logger logger = LoggerFactory.getLogger("ImageController");

    @Autowired
    private FileUtils fileUtils;

    @ApiOperation(value = "获取图片")
    @GetMapping(value = "/e/{eid}/{img}", produces = "image/jpeg")
    public void getErrorInfoImage(@PathVariable("eid") String eid, @PathVariable("img") String imgName, HttpServletResponse resp) {
        packResp(resp, null, eid, imgName, ERRORINFO_FLODER);
    }

    @ApiOperation(value = "获取商品详情图片")
    @GetMapping(value = "/g/{gid}/{img}", produces = "image/jpeg")
    public void getGoodImage(@PathVariable("gid") String gid, @PathVariable("img") String imgName, HttpServletResponse resp) {
        packResp(resp, null, gid, imgName, GOOD_FLODER);
    }

    @ApiOperation(value = "获取Plog图片")
    @GetMapping(value = "/p/{uid}/{pid}/{img}", produces = "image/jpeg")
    public void getPlogImage(@PathVariable("uid") String uid, @PathVariable("pid") String pid, @PathVariable("img") String img, HttpServletResponse resp) {
        packResp(resp, uid, pid, img, PLOG_FOLDER);
    }

    private void packResp(HttpServletResponse resp, String uid, String id, String imgName, String folder) {
            BufferedImage image = (ObjectUtils.isEmpty(uid))
                    ? fileUtils.getImg(imgName, folder, null, id)
                    : fileUtils.getImg(imgName, folder, uid, id);
            resp.setHeader("Cache-Control", "no-store, no-cache");
            resp.setContentType("image/jpeg");
            try {
                ServletOutputStream out = resp.getOutputStream();
                ImageIO.write(image, "JPG", out);
                out.flush();
                out.close();
            } catch (IOException e) {
                logger.error("图片输出出错");
            }
    }
}
