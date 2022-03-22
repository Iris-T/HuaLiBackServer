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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getAddrs() {
        return addrs;
    }

    public void setAddrs(String addrs) {
        this.addrs = addrs;
    }
    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
    public Integer getIscheck() {
        return ischeck;
    }

    public void setIscheck(Integer ischeck) {
        this.ischeck = ischeck;
    }
    public LocalDate getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(LocalDate checkdate) {
        this.checkdate = checkdate;
    }

    @Override
    public String toString() {
        return "Plog{" +
            "id=" + id +
            ", uid=" + uid +
            ", addrs=" + addrs +
            ", log=" + log +
            ", created=" + created +
            ", ischeck=" + ischeck +
            ", checkdate=" + checkdate +
        "}";
    }
}
