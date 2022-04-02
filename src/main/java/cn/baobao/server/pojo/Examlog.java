package cn.baobao.server.pojo;

import java.io.Serializable;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Iris
 * @since 2022-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Accessors(chain = true)
@TableName("examlog")
@ApiModel(value = "Examlog对象", description = "")
public class Examlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("答题记录主键")
    private String id;

    @ApiModelProperty("答题用户ID")
    private String uid;

    @ApiModelProperty("当日答题次数")
    private Integer cnt;

    @ApiModelProperty("答题日期")
    private LocalDate date;
}
