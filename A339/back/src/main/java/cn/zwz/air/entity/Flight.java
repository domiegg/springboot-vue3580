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
 * 航班 实体类
 * @author
 *
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_flight")
@TableName("a_flight")
@ApiModel(value = "航班")
public class Flight extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "航班号")
    private String title;

    @ApiModelProperty(value = "航空公司")
    private String company;

    @ApiModelProperty(value = "起飞机场")
    private String ji1;

    @ApiModelProperty(value = "到达机场")
    private String ji2;

    @ApiModelProperty(value = "机票价格")
    private String price;

    @ApiModelProperty(value = "起飞时间")
    private String time1;

    @ApiModelProperty(value = "到达时间")
    private String time2;

    @ApiModelProperty(value = "特殊说明")
    private String remark;

    @ApiModelProperty(value = "登机口")
    private String inKou;
}