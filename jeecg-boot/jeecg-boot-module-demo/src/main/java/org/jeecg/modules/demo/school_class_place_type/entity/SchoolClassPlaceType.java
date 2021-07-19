package org.jeecg.modules.demo.school_class_place_type.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 上课地点类型
 * @Author: jeecg-boot
 * @Date:   2021-07-19
 * @Version: V1.0
 */
@ApiModel(value="school_class_place_type对象", description="上课地点类型")
@Data
@TableName("school_class_place_type")
public class SchoolClassPlaceType implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**校区*/
	@Excel(name = "校区", width = 15, dictTable = "school_campus", dicText = "campus_name", dicCode = "id")
    @Dict(dictTable = "school_campus", dicText = "campus_name", dicCode = "id")
    @ApiModelProperty(value = "校区")
    private java.lang.String campusName;
	/**类型编号*/
	@Excel(name = "类型编号", width = 15)
    @ApiModelProperty(value = "类型编号")
    private java.lang.String typeNum;
	/**上课地点类型*/
	@Excel(name = "上课地点类型", width = 15)
    @ApiModelProperty(value = "上课地点类型")
    private java.lang.String typeName;
	/**维护人*/
	@Excel(name = "维护人", width = 15)
    @ApiModelProperty(value = "维护人")
    private java.lang.String typeAdmin;
	/**维护时间*/
	@Excel(name = "维护时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "维护时间")
    private java.util.Date editTime;
}
