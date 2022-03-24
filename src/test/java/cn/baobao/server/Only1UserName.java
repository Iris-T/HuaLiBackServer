package cn.baobao.server;

import cn.baobao.server.utils.CommonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Iris 2022/3/24
 */
public class Only1UserName {

    @Test
    public void getOnly1UserName() {
        String uuid = UUID.randomUUID().toString().toUpperCase().split("-")[0];
        String date = LocalDateTime.now().toString().replace("-", "").split("T")[0];
        System.out.println("uuid>>>".concat(uuid));
        System.out.println("date>>>".concat(date));
    }
}
