package cn.baobao.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.apache.catalina.startup.ExpandWar.deleteDir;

/**
 * 文件操作工具类
 * @author Iris 2022/3/1
 */
@Component
public class FileUtils {

    @Value("${file.image.path}")
    private String PRE_PATH;
    private final Logger logger = LoggerFactory.getLogger("FileInfo");

    /**
     * 删除对应ID文件夹
     * @param   id      被删除文件夹的名称
     * @param   folder  删除路径
     */
    public void delImg(String id, String folder) {
        File file = new File(String.format("%s/%s/%s", PRE_PATH, folder, id));
        // 路径为文件且不为空则进行删除
        if (file.isFile()) {
            file.delete(); // 删除文件
        } else {
            deleteDir(file);
        }
    }

    /**
     * 存储plog -- 以用户id作为大文件夹，以pid做小文件夹
     */
    public boolean saveImg(String imgName, MultipartFile image, String folder, String uid, String id) {
        try {
            createDir(folder, uid, id);
            if (ObjectUtils.isEmpty(uid)) {
                image.transferTo(new File(String.format("%s%s/%s/%s.jpg", PRE_PATH, folder, id, imgName)));
            } else {
                image.transferTo(new File(String.format("%s%s/%s/%s/%s.jpg", PRE_PATH, folder, uid, id, imgName)));
            }
            logger.info(String.format("===存储图片%s成功===", imgName));
            return true;
        } catch (IOException e) {
            logger.error("===存储图片失败，原因是===>"+e.getMessage()+"===");
            return false;
        }
    }

    /**
     * 获取到图片的流文件
     * @param imgName 图片名
     * @param folder 文件夹
     * @param uid 用户ID（可为空）
     * @param id 图片相关记录ID
     * @return
     */
    public byte[] getImg(String imgName, String folder, String uid, String id) {
        String imageLink = (uid == null)
            ? String.format("%s%s%s/%s.jpg", PRE_PATH, folder, id, imgName)
            : String.format("%s%s%s/%s/%s.jpg", PRE_PATH, folder, uid, id, imgName);
        File file = new File(imageLink);
        byte[] bytes = new byte[0];
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
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

    public void createDir(String folder, String uid, String id) {
        if (!ObjectUtils.isEmpty(uid)) {
            File uFile = new File(String.format("%s%s/%s", PRE_PATH, "plogs", uid));
            uFile.mkdir();
        }
        if (!ObjectUtils.isEmpty(id)) {
            File file = new File(String.format("%s%s/%s/%s", PRE_PATH, folder, uid, id));
            file.mkdir();
        }
    }
}
