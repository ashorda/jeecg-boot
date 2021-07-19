package org.jeecg.modules.demo.school_class_place_type.service;

import org.jeecg.modules.demo.school_class_place_type.entity.SchoolPlaceCtrl;
import org.jeecg.modules.demo.school_class_place_type.entity.SchoolClassPlaceType;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 上课地点类型
 * @Author: jeecg-boot
 * @Date:   2021-07-19
 * @Version: V1.0
 */
public interface ISchoolClassPlaceTypeService extends IService<SchoolClassPlaceType> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(SchoolClassPlaceType schoolClassPlaceType,List<SchoolPlaceCtrl> schoolPlaceCtrlList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(SchoolClassPlaceType schoolClassPlaceType,List<SchoolPlaceCtrl> schoolPlaceCtrlList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
