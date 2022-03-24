package cn.baobao.server.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * @author Iris 2022/3/24
 */
@Component
public class CommonUtils {

    /**
     * 获取唯一文件名
     * 由年月日(YYYYMMDD)+UUID前8位组成
     * @return String
     */
    public String getOnly1UserName() {
        String uuid = UUID.randomUUID().toString().toUpperCase().split("-")[0];
        String date = LocalDateTime.now().toString().replace("-", "").split("T")[0];
        return date.concat(uuid);
    }

    public String getOnly1Id() {
        String uuid = UUID.randomUUID().toString().toUpperCase().split("-")[0];
        // 当前时间
        String time = String.valueOf(new Date().getTime());
        return uuid.concat(time);
    }


}
