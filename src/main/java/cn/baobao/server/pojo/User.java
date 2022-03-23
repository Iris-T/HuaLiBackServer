package cn.baobao.server.pojo;

import java.io.Serializable;

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
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    private String id;

    @ApiModelProperty("用户手机号")
    private String phone;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户历史获得积分，总积分")
    private Integer totalPoint;

    @ApiModelProperty("用户当前积分")
    private Integer currentPoint;

    @ApiModelProperty("收货人")
    private String name;

    @ApiModelProperty("收货地址")
    private String address;
}
