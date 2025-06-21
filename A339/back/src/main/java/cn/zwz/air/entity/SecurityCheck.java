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
 * 安检 实体类
 * @author
 *
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_security_check")
@TableName("a_security_check")
@ApiModel(value = "安检")
public class SecurityCheck extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "乘客ID")
    private String userId;

    @ApiModelProperty(value = "乘客")
    private String userName;

    @ApiModelProperty(value = "安检过程")
    private String content1;

    @ApiModelProperty(value = "安检结果")
    private String content2;
}