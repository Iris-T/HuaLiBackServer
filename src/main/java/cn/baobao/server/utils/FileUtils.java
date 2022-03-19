package cn.baobao.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
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
     * @param   imgName    被删除文件的文件名
     */
    public void deleteFile(String imgName) {
        File file = new File(prePath.concat(imgName).concat(".jpg"));
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            if (file.delete()) {
                logger.info("===删除文件".concat(imgName).concat("成功==="));
            } else {
                logger.error("===删除文件".concat(imgName).concat("失败==="));
            }
        }
    }

    /**
     * 获取唯一文件名
     * 由年月日(YYYYMMDD)+UUID前8位组成
     * @return String
     */
    public String getFileName() {
        String uuid = UUID.randomUUID().toString().split("-")[0];
        return LocalDateTime.now().toString().split("T")[0].replace("-", "").concat(uuid);
    }


}
