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
 * @since 2022-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("errorinfo")
@ApiModel(value = "Errorinfo对象", description = "")
public class Errorinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("错误信息ID")
    private String id;

    @ApiModelProperty("图片地址")
    private String imgaddr;

    @ApiModelProperty("错误信息")
    private String info;

    @ApiModelProperty("创建时间")
    private LocalDate created;

    @ApiModelProperty("管理员查看时间")
    private LocalDate checked;

    @ApiModelProperty("是否被查看")
    private Boolean ischecked;
}
