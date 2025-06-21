package cn.zwz.air.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * 行李 实体类
 * @author
 *
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_luggage")
@TableName("a_luggage")
@ApiModel(value = "行李")
public class Luggage extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "乘客ID")
    private String userId;

    @ApiModelProperty(value = "乘客")
    private String userName;

    @ApiModelProperty(value = "行李内容")
    private String content;

    @ApiModelProperty(value = "行李流向")
    private String status;
}