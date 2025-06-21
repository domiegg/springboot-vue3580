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
 * 资源 实体类
 * @author 
 * 
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_natural_resources")
@TableName("a_natural_resources")
@ApiModel(value = "资源")
public class NaturalResources extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源名称")
    private String title;

    @ApiModelProperty(value = "资源介绍")
    private String content;

    @ApiModelProperty(value = "资源图片")
    private String image;

    @ApiModelProperty(value = "分配地点")
    private String address;
}