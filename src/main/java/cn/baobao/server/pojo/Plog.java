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
@TableName("plog")
@ApiModel(value = "Plog对象", description = "")
public class Plog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("帖子ID")
    private String id;

    @ApiModelProperty("用户ID")
    private String uid;

    @ApiModelProperty("图片地址数组，以,分隔")
    private String addrs;

    @ApiModelProperty("plog描述")
    private String log;

    @ApiModelProperty("plog创建时间")
    private LocalDate created;

    @ApiModelProperty("plog审核状态")
    private Integer ischeck;

    @ApiModelProperty("审核时间")
    private LocalDate checkdate;
}
