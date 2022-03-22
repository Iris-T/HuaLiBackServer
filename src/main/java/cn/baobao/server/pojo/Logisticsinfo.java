package cn.baobao.server.pojo;

import java.io.Serializable;
import java.time.LocalDate;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Logisticsinfo{" +
            "id=" + id +
            ", gid=" + gid +
            ", uid=" + uid +
            ", created=" + created +
        "}";
    }
}
