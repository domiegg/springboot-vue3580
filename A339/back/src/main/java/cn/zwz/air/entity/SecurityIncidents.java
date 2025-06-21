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
 * 安全事件 实体类
 * @author
 *
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_security_incidents")
@TableName("a_security_incidents")
@ApiModel(value = "安全事件")
public class SecurityIncidents extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "事件名称")
    private String title;

    @ApiModelProperty(value = "事件介绍")
    private String content;
}