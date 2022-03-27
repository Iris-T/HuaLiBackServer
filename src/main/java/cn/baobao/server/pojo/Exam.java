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
 * @since 2022-03-27
 */
@ApiModel(value = "Exam对象", description = "")
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("题目主键")
    private String id;

    @ApiModelProperty("题目描述")
    private String detail;

    @ApiModelProperty("选项A内容")
    private String a;

    @ApiModelProperty("选项B内容")
    private String b;

    @ApiModelProperty("选项C内容")
    private String c;

    @ApiModelProperty("选项D内容")
    private String d;

    @ApiModelProperty("正确选项")
    private String ans;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    @Override
    public String toString() {
        return "Exam{" +
            "id=" + id +
            ", detail=" + detail +
            ", a=" + a +
            ", b=" + b +
            ", c=" + c +
            ", d=" + d +
            ", ans=" + ans +
        "}";
    }
}
