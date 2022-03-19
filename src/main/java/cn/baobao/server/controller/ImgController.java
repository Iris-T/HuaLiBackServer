package cn.baobao.server.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Iris 2022/3/1
 */
@RestController
public class ImgController {
    private final String prePath = "E:/serverImgs/";

    @ApiOperation(value = "获取图片")
    @GetMapping(value = "/image/{filename}", produces = "image/jpeg")
    public void getImage(@PathVariable("filename") String filename, HttpServletResponse resp) throws IOException {
        BufferedImage image = ImageIO.read(Files.newInputStream(Paths.get(prePath.concat(filename).concat(".jpg"))));
        resp.setHeader("Cache-Control", "no-store, no-cache");
        resp.setContentType("image/jpeg");
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }
}
