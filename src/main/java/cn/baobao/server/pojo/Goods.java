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
 * @since 2022-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("goods")
@ApiModel(value = "Goods对象", description = "")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品ID")
    private String id;

    @ApiModelProperty("商品名")
    private String name;

    @ApiModelProperty("商品图片，以,分隔")
    private String imgs;

    @ApiModelProperty("商品详情")
    private String details;

    @ApiModelProperty("所需积分")
    private Integer cost;

    @ApiModelProperty("上架时间")
    private LocalDate created;

    @ApiModelProperty("上架管理员ID")
    private String aid;

    @ApiModelProperty("商品是否启用")
    private boolean isactive;
}
