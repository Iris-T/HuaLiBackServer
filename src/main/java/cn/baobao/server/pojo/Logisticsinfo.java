package cn.baobao.server.pojo;

import java.io.Serializable;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Iris
 * @since 2022-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("logisticsinfo")
@ApiModel(value = "Logisticsinfo对象", description = "")
public class Logisticsinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("物流ID")
    private String id;

    @ApiModelProperty("商品ID")
    private String gid;

    @ApiModelProperty("用户ID")
    private String uid;

    @ApiModelProperty("物流信息创建时间")
    private LocalDate created;
}
