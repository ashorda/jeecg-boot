package org.jeecg.modules.demo.school_course_ctrl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.school_course_ctrl.entity.SchoolCourseCtrl;
import org.jeecg.modules.demo.school_course_ctrl.service.ISchoolCourseCtrlService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 课程管理
 * @Author: jeecg-boot
 * @Date:   2021-07-19
 * @Version: V1.0
 */
@Api(tags="课程管理")
@RestController
@RequestMapping("/school_course_ctrl/schoolCourseCtrl")
@Slf4j
public class SchoolCourseCtrlController extends JeecgController<SchoolCourseCtrl, ISchoolCourseCtrlService> {
	@Autowired
	private ISchoolCourseCtrlService schoolCourseCtrlService;
	
	/**
	 * 分页列表查询
	 *
	 * @param schoolCourseCtrl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "课程管理-分页列表查询")
	@ApiOperation(value="课程管理-分页列表查询", notes="课程管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SchoolCourseCtrl schoolCourseCtrl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SchoolCourseCtrl> queryWrapper = QueryGenerator.initQueryWrapper(schoolCourseCtrl, req.getParameterMap());
		Page<SchoolCourseCtrl> page = new Page<SchoolCourseCtrl>(pageNo, pageSize);
		IPage<SchoolCourseCtrl> pageList = schoolCourseCtrlService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param schoolCourseCtrl
	 * @return
	 */
	@AutoLog(value = "课程管理-添加")
	@ApiOperation(value="课程管理-添加", notes="课程管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SchoolCourseCtrl schoolCourseCtrl) {
		schoolCourseCtrlService.save(schoolCourseCtrl);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param schoolCourseCtrl
	 * @return
	 */
	@AutoLog(value = "课程管理-编辑")
	@ApiOperation(value="课程管理-编辑", notes="课程管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SchoolCourseCtrl schoolCourseCtrl) {
		schoolCourseCtrlService.updateById(schoolCourseCtrl);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "课程管理-通过id删除")
	@ApiOperation(value="课程管理-通过id删除", notes="课程管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		schoolCourseCtrlService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "课程管理-批量删除")
	@ApiOperation(value="课程管理-批量删除", notes="课程管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.schoolCourseCtrlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "课程管理-通过id查询")
	@ApiOperation(value="课程管理-通过id查询", notes="课程管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SchoolCourseCtrl schoolCourseCtrl = schoolCourseCtrlService.getById(id);
		if(schoolCourseCtrl==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(schoolCourseCtrl);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param schoolCourseCtrl
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SchoolCourseCtrl schoolCourseCtrl) {
        return super.exportXls(request, schoolCourseCtrl, SchoolCourseCtrl.class, "课程管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SchoolCourseCtrl.class);
    }

}
