package cn.baobao.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.apache.catalina.startup.ExpandWar.deleteDir;

/**
 * 文件操作工具类
 * @author Iris 2022/3/1
 */
@Component
public class FileUtils {

    private final String PRE_PATH = "E:/serverImgs/";
    private final Logger logger = LoggerFactory.getLogger("FileInfo");

    /**
     * 删除对应ID文件夹
     * @param   id      被删除文件夹的名称
     * @param   folder  删除路径
     */
    public void delImg(String id, String folder) {
        File file = new File(PRE_PATH+"/"+folder+"/"+id);
        // 路径为文件且不为空则进行删除
        if (file.isFile()) {
            file.delete(); // 删除文件
        } else {
            deleteDir(file);
        }
    }

    public boolean saveImg(String imgName, MultipartFile image, String folder, String id) {
        try {
            File file = new File(PRE_PATH + folder + "/" + id);
            if (!file.exists()) {
                file.mkdir();
            }
            // 以gid为文件夹分类
            image.transferTo(new File(PRE_PATH +folder+"/"+id+"/"+imgName+".jpg"));
            logger.info("===添加图片"+imgName+"成功===");
            return true;
        } catch (IOException e) {
            logger.error("===添加图片失败，原因是："+e.getMessage()+"===");
            return false;
        }
    }

    public BufferedImage getImg(String imgName, String folder, String id) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Files.newInputStream(Paths.get(PRE_PATH +folder+"/"+id+"/"+imgName+".jpg")));
            logger.info("===图片"+imgName+"成功找到===");
        } catch (IOException e) {
            logger.error("===图片未找到，将使用error.jpg===");
        }
        return image == null ? getErrorImg() : image;
    }

    private BufferedImage getErrorImg() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Files.newInputStream(Paths.get(PRE_PATH + "static/error.jpg")));
            logger.info("===error.jpg成功找到===");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 获取唯一文件名
     * 由年月日(YYYYMMDD)+UUID前8位组成
     * @return String
     */
    public String getOnly1FileName() {
        String uuid = UUID.randomUUID().toString().split("-")[0];
        String time = LocalDateTime.now().toString().split("T")[0].replace("-", "");
        return time.concat(uuid);
    }


}
