package cn.baobao.server;

import cn.baobao.server.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

/**
 * @author Iris 2022/3/1
 */
public class FileUtilTest {

    @Autowired
    private FileUtils fileUtils;

    @Test
    public void getFileName() throws NoSuchMethodException {
        String uuid = UUID.randomUUID().toString().toUpperCase().split("-")[0];
        String time = String.valueOf(new Date().getTime());
        System.out.println(uuid.concat(time));
    }
}
