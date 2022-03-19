package cn.baobao.server.controller;

import cn.baobao.server.pojo.RespBean;
import cn.baobao.server.utils.ClassifyUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 识别服务接口
 * @author Iris 2022/3/2
 */
@RestController
public class DiscernController {

    @Autowired
    private ClassifyUtils classifyUtils;

    @PostMapping("/discern")
    @ApiOperation(value = "识别服务接口")
    @ResponseBody
    public RespBean discernImage(@RequestPart("image") MultipartFile image) {

        BufferedImage srcImage;
        String res = null;
        RespBean respBean = new RespBean();
        try {
            FileInputStream in = (FileInputStream) image.getInputStream();
            srcImage = ImageIO.read(in);
            res = classifyUtils.predict(srcImage);
        } catch (IOException e) {
            e.printStackTrace();
            return RespBean.error("识别出错，请联系管理员");
        } finally {
            if (ObjectUtils.isEmpty(res)) {
                respBean.setCode(500);
                respBean.setMessage("识别出错，请联系管理员");
            } else {
                respBean.setCode(200);
                respBean.setMessage("识别成功");
                respBean.setObj(res);
            }
        }
        return respBean;
    }

}
