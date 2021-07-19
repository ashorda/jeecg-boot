package org.jeecg.modules.demo.school_chose_inf.controller;

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
import org.jeecg.modules.demo.school_chose_inf.entity.SchoolChosenInf;
import org.jeecg.modules.demo.school_chose_inf.service.ISchoolChosenInfService;

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
 * @Description: 班级选课统计
 * @Author: jeecg-boot
 * @Date:   2021-07-19
 * @Version: V1.0
 */
@Api(tags="班级选课统计")
@RestController
@RequestMapping("/school_chose_inf/schoolChosenInf")
@Slf4j
public class SchoolChosenInfController extends JeecgController<SchoolChosenInf, ISchoolChosenInfService> {
	@Autowired
	private ISchoolChosenInfService schoolChosenInfService;
	
	/**
	 * 分页列表查询
	 *
	 * @param schoolChosenInf
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "班级选课统计-分页列表查询")
	@ApiOperation(value="班级选课统计-分页列表查询", notes="班级选课统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SchoolChosenInf schoolChosenInf,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SchoolChosenInf> queryWrapper = QueryGenerator.initQueryWrapper(schoolChosenInf, req.getParameterMap());
		Page<SchoolChosenInf> page = new Page<SchoolChosenInf>(pageNo, pageSize);
		IPage<SchoolChosenInf> pageList = schoolChosenInfService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param schoolChosenInf
	 * @return
	 */
	@AutoLog(value = "班级选课统计-添加")
	@ApiOperation(value="班级选课统计-添加", notes="班级选课统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SchoolChosenInf schoolChosenInf) {
		schoolChosenInfService.save(schoolChosenInf);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param schoolChosenInf
	 * @return
	 */
	@AutoLog(value = "班级选课统计-编辑")
	@ApiOperation(value="班级选课统计-编辑", notes="班级选课统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SchoolChosenInf schoolChosenInf) {
		schoolChosenInfService.updateById(schoolChosenInf);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "班级选课统计-通过id删除")
	@ApiOperation(value="班级选课统计-通过id删除", notes="班级选课统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		schoolChosenInfService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "班级选课统计-批量删除")
	@ApiOperation(value="班级选课统计-批量删除", notes="班级选课统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.schoolChosenInfService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "班级选课统计-通过id查询")
	@ApiOperation(value="班级选课统计-通过id查询", notes="班级选课统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SchoolChosenInf schoolChosenInf = schoolChosenInfService.getById(id);
		if(schoolChosenInf==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(schoolChosenInf);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param schoolChosenInf
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SchoolChosenInf schoolChosenInf) {
        return super.exportXls(request, schoolChosenInf, SchoolChosenInf.class, "班级选课统计");
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
        return super.importExcel(request, response, SchoolChosenInf.class);
    }

}
