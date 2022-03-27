package cn.baobao.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Iris 2022/3/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdsWithAns {

    private List<String> ids;
    private List<String> ans;

}
