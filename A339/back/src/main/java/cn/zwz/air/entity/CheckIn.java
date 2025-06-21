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
 * 登机手续 实体类
 * @author 
 * 
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_check_in")
@TableName("a_check_in")
@ApiModel(value = "登机手续")
public class CheckIn extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "乘客ID")
    private String userId;

    @ApiModelProperty(value = "乘客")
    private String userName;

    @ApiModelProperty(value = "航班号")
    private String luggage;

    @ApiModelProperty(value = "起飞机场")
    private String ji1;

    @ApiModelProperty(value = "起飞时间")
    private String time1;

    @ApiModelProperty(value = "登机信息")
    private String content;
}