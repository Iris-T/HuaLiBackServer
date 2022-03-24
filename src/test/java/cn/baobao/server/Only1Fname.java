package cn.baobao.server;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * @author Iris 2022/3/1
 */
public class Only1Fname {

    @Test
    public void getFileName() throws NoSuchMethodException {
        String uuid = UUID.randomUUID().toString().toUpperCase().split("-")[0];
        String time = String.valueOf(new Date().getTime());
        System.out.println(uuid.concat(time));
    }
}
