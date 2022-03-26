package cn.baobao.server.controller;

import cn.baobao.server.utils.FileUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final Logger logger = LoggerFactory.getLogger("ImageController");

    @Autowired
    private FileUtils fileUtils;

    @ApiOperation(value = "获取图片")
    @GetMapping(value = "/e/{eid}/{img}", produces = "image/jpeg")
    public void getErrorInfoImage(@PathVariable("eid") String eid, @PathVariable("img") String imgName, HttpServletResponse resp) {
        packResp(resp, eid, imgName, ERRORINFO_FLODER);
    }

    @ApiOperation(value = "获取商品详情图片")
    @GetMapping(value = "/g/{gid}/{img}", produces = "image/jpeg")
    public void getGoodImage(@PathVariable("gid") String gid, @PathVariable("img") String imgName, HttpServletResponse resp) {
        packResp(resp, gid, imgName, GOOD_FLODER);
    }

    private void packResp(HttpServletResponse resp, String gid, String imgName, String folder) {
            BufferedImage image = fileUtils.getImg(imgName, folder, gid);
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
