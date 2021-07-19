package org.jeecg.modules.demo.school_class_inf.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
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
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 班级信息
 * @Author: jeecg-boot
 * @Date:   2021-07-19
 * @Version: V1.0
 */
@Data
@TableName("school_class_inf")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="school_class_inf对象", description="班级信息")
public class SchoolClassInf implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
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
	/**年级*/
	@Excel(name = "年级", width = 15, dicCode = "grade")
	@Dict(dicCode = "grade")
    @ApiModelProperty(value = "年级")
    private java.lang.String grade;
	/**班主任*/
	@Excel(name = "班主任", width = 15)
    @ApiModelProperty(value = "班主任")
    private java.lang.String headTec;
	/**班级*/
	@Excel(name = "班级", width = 15)
    @ApiModelProperty(value = "班级")
    private java.lang.String className;
}
