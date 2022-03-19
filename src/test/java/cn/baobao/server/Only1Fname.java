package cn.baobao.server;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Iris 2022/3/1
 */
public class Only1Fname {

    @Test
    public void getFileName() {
        String uuid = UUID.randomUUID().toString().split("-")[0];
        String llt = LocalDateTime.now().toString().split("T")[0].replace("-", "").concat(uuid);
        System.out.println(llt);
        System.out.println(llt.length());
    }
}
