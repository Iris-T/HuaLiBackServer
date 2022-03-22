package cn.baobao.server.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author Iris
 * @since 2022-03-22
 */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }
    public Integer getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Integer currentPoint) {
        this.currentPoint = currentPoint;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", phone=" + phone +
            ", password=" + password +
            ", totalPoint=" + totalPoint +
            ", currentPoint=" + currentPoint +
            ", name=" + name +
            ", address=" + address +
        "}";
    }
}
