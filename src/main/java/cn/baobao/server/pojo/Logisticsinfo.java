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

    @ApiModelProperty("审核人（管理员）ID")
    private String aid;

    @ApiModelProperty("物流信息创建时间")
    private LocalDate created;

    @ApiModelProperty("审核状态")
    private boolean ischeck;

    @ApiModelProperty("审核时间")
    private LocalDate modified;
}
