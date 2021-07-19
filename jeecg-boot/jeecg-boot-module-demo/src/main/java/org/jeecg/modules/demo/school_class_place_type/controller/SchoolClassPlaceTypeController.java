package org.jeecg.modules.demo.school_class_place_type.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.school_class_place_type.entity.SchoolPlaceCtrl;
import org.jeecg.modules.demo.school_class_place_type.entity.SchoolClassPlaceType;
import org.jeecg.modules.demo.school_class_place_type.vo.SchoolClassPlaceTypePage;
import org.jeecg.modules.demo.school_class_place_type.service.ISchoolClassPlaceTypeService;
import org.jeecg.modules.demo.school_class_place_type.service.ISchoolPlaceCtrlService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 上课地点类型
 * @Author: jeecg-boot
 * @Date:   2021-07-19
 * @Version: V1.0
 */
@Api(tags="上课地点类型")
@RestController
@RequestMapping("/school_class_place_type/schoolClassPlaceType")
@Slf4j
public class SchoolClassPlaceTypeController {
	@Autowired
	private ISchoolClassPlaceTypeService schoolClassPlaceTypeService;
	@Autowired
	private ISchoolPlaceCtrlService schoolPlaceCtrlService;
	
	/**
	 * 分页列表查询
	 *
	 * @param schoolClassPlaceType
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "上课地点类型-分页列表查询")
	@ApiOperation(value="上课地点类型-分页列表查询", notes="上课地点类型-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SchoolClassPlaceType schoolClassPlaceType,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SchoolClassPlaceType> queryWrapper = QueryGenerator.initQueryWrapper(schoolClassPlaceType, req.getParameterMap());
		Page<SchoolClassPlaceType> page = new Page<SchoolClassPlaceType>(pageNo, pageSize);
		IPage<SchoolClassPlaceType> pageList = schoolClassPlaceTypeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param schoolClassPlaceTypePage
	 * @return
	 */
	@AutoLog(value = "上课地点类型-添加")
	@ApiOperation(value="上课地点类型-添加", notes="上课地点类型-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SchoolClassPlaceTypePage schoolClassPlaceTypePage) {
		SchoolClassPlaceType schoolClassPlaceType = new SchoolClassPlaceType();
		BeanUtils.copyProperties(schoolClassPlaceTypePage, schoolClassPlaceType);
		schoolClassPlaceTypeService.saveMain(schoolClassPlaceType, schoolClassPlaceTypePage.getSchoolPlaceCtrlList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param schoolClassPlaceTypePage
	 * @return
	 */
	@AutoLog(value = "上课地点类型-编辑")
	@ApiOperation(value="上课地点类型-编辑", notes="上课地点类型-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SchoolClassPlaceTypePage schoolClassPlaceTypePage) {
		SchoolClassPlaceType schoolClassPlaceType = new SchoolClassPlaceType();
		BeanUtils.copyProperties(schoolClassPlaceTypePage, schoolClassPlaceType);
		SchoolClassPlaceType schoolClassPlaceTypeEntity = schoolClassPlaceTypeService.getById(schoolClassPlaceType.getId());
		if(schoolClassPlaceTypeEntity==null) {
			return Result.error("未找到对应数据");
		}
		schoolClassPlaceTypeService.updateMain(schoolClassPlaceType, schoolClassPlaceTypePage.getSchoolPlaceCtrlList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "上课地点类型-通过id删除")
	@ApiOperation(value="上课地点类型-通过id删除", notes="上课地点类型-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		schoolClassPlaceTypeService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "上课地点类型-批量删除")
	@ApiOperation(value="上课地点类型-批量删除", notes="上课地点类型-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.schoolClassPlaceTypeService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "上课地点类型-通过id查询")
	@ApiOperation(value="上课地点类型-通过id查询", notes="上课地点类型-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SchoolClassPlaceType schoolClassPlaceType = schoolClassPlaceTypeService.getById(id);
		if(schoolClassPlaceType==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(schoolClassPlaceType);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "上课地点管理通过主表ID查询")
	@ApiOperation(value="上课地点管理主表ID查询", notes="上课地点管理-通主表ID查询")
	@GetMapping(value = "/querySchoolPlaceCtrlByMainId")
	public Result<?> querySchoolPlaceCtrlListByMainId(@RequestParam(name="id",required=true) String id) {
		List<SchoolPlaceCtrl> schoolPlaceCtrlList = schoolPlaceCtrlService.selectByMainId(id);
		return Result.OK(schoolPlaceCtrlList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param schoolClassPlaceType
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SchoolClassPlaceType schoolClassPlaceType) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<SchoolClassPlaceType> queryWrapper = QueryGenerator.initQueryWrapper(schoolClassPlaceType, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<SchoolClassPlaceType> queryList = schoolClassPlaceTypeService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<SchoolClassPlaceType> schoolClassPlaceTypeList = new ArrayList<SchoolClassPlaceType>();
      if(oConvertUtils.isEmpty(selections)) {
          schoolClassPlaceTypeList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          schoolClassPlaceTypeList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<SchoolClassPlaceTypePage> pageList = new ArrayList<SchoolClassPlaceTypePage>();
      for (SchoolClassPlaceType main : schoolClassPlaceTypeList) {
          SchoolClassPlaceTypePage vo = new SchoolClassPlaceTypePage();
          BeanUtils.copyProperties(main, vo);
          List<SchoolPlaceCtrl> schoolPlaceCtrlList = schoolPlaceCtrlService.selectByMainId(main.getId());
          vo.setSchoolPlaceCtrlList(schoolPlaceCtrlList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "上课地点类型列表");
      mv.addObject(NormalExcelConstants.CLASS, SchoolClassPlaceTypePage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("上课地点类型数据", "导出人:"+sysUser.getRealname(), "上课地点类型"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<SchoolClassPlaceTypePage> list = ExcelImportUtil.importExcel(file.getInputStream(), SchoolClassPlaceTypePage.class, params);
              for (SchoolClassPlaceTypePage page : list) {
                  SchoolClassPlaceType po = new SchoolClassPlaceType();
                  BeanUtils.copyProperties(page, po);
                  schoolClassPlaceTypeService.saveMain(po, page.getSchoolPlaceCtrlList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
