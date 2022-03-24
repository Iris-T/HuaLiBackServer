package cn.baobao.server.utils;

import cn.baobao.server.pojo.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 文件操作工具类
 * @author Iris 2022/3/1
 */
@Component
public class FileUtils {

    private final String prePath = "E:/serverImgs/";

    private final Logger logger = LoggerFactory.getLogger("FileInfo");

    /**
     * 删除单个文件
     * @param   fileName    被删除文件的文件名
     */
    public void deleteFile(String fileName) {
        File file = new File(prePath.concat(fileName).concat(".jpg"));
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            if (file.delete()) {
                logger.info("===删除文件".concat(fileName).concat("成功==="));
            } else {
                logger.error("===删除文件".concat(fileName).concat("失败==="));
            }
        }
    }

    public void saveImg(String imgName, MultipartFile image) throws IOException {
        image.transferTo(new File(prePath.concat(imgName).concat(".jpg")));
    }

    /**
     * 获取唯一文件名
     * 由年月日(YYYYMMDD)+UUID前8位组成
     * @return String
     */
    public String getOnly1FileName() {
        String uuid = UUID.randomUUID().toString().split("-")[0];
        return LocalDateTime.now().toString().split("T")[0].replace("-", "").concat(uuid);
    }


}
