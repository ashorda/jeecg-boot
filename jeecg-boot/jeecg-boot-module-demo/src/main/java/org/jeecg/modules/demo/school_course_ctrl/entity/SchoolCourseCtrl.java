package org.jeecg.modules.demo.school_course_ctrl.entity;

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
 * @Description: 课程管理
 * @Author: jeecg-boot
 * @Date:   2021-07-19
 * @Version: V1.0
 */
@Data
@TableName("school_course_ctrl")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="school_course_ctrl对象", description="课程管理")
public class SchoolCourseCtrl implements Serializable {
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
    private java.lang.String campus;
	/**课程名称*/
	@Excel(name = "课程名称", width = 15)
    @ApiModelProperty(value = "课程名称")
    private java.lang.String courseName;
	/**任课教师*/
	@Excel(name = "任课教师", width = 15)
    @ApiModelProperty(value = "任课教师")
    private java.lang.String courseTeacher;
	/**课程类型*/
	@Excel(name = "课程类型", width = 15, dicCode = "course_type")
	@Dict(dicCode = "course_type")
    @ApiModelProperty(value = "课程类型")
    private java.lang.String courseType;
	/**上课地点*/
	@Excel(name = "上课地点", width = 15, dictTable = "school_place_ctrl", dicText = "place_name", dicCode = "place_name")
	@Dict(dictTable = "school_place_ctrl", dicText = "place_name", dicCode = "place_name")
    @ApiModelProperty(value = "上课地点")
    private java.lang.String coursePlace;
	/**选课人数*/
	@Excel(name = "选课人数", width = 15)
    @ApiModelProperty(value = "选课人数")
    private java.lang.String numOfCourse;
	/**上课性质*/
	@Excel(name = "上课性质", width = 15)
    @ApiModelProperty(value = "上课性质")
    private java.lang.String courseStatus;
	/**上课时间*/
	@Excel(name = "上课时间", width = 15)
    @ApiModelProperty(value = "上课时间")
    private java.lang.String courseTime;
	/**选课班级*/
	@Excel(name = "选课班级", width = 15, dictTable = "school_class_inf", dicText = "grade,class_name", dicCode = "id")
	@Dict(dictTable = "school_class_inf", dicText = "grade,class_name", dicCode = "id")
    @ApiModelProperty(value = "选课班级")
    private java.lang.String chosenClass;
}
