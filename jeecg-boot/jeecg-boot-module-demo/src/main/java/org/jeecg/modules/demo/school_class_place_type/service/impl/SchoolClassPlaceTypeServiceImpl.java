package org.jeecg.modules.demo.school_class_place_type.service.impl;

import org.jeecg.modules.demo.school_class_place_type.entity.SchoolClassPlaceType;
import org.jeecg.modules.demo.school_class_place_type.entity.SchoolPlaceCtrl;
import org.jeecg.modules.demo.school_class_place_type.mapper.SchoolPlaceCtrlMapper;
import org.jeecg.modules.demo.school_class_place_type.mapper.SchoolClassPlaceTypeMapper;
import org.jeecg.modules.demo.school_class_place_type.service.ISchoolClassPlaceTypeService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 上课地点类型
 * @Author: jeecg-boot
 * @Date:   2021-07-19
 * @Version: V1.0
 */
@Service
public class SchoolClassPlaceTypeServiceImpl extends ServiceImpl<SchoolClassPlaceTypeMapper, SchoolClassPlaceType> implements ISchoolClassPlaceTypeService {

	@Autowired
	private SchoolClassPlaceTypeMapper schoolClassPlaceTypeMapper;
	@Autowired
	private SchoolPlaceCtrlMapper schoolPlaceCtrlMapper;
	
	@Override
	@Transactional
	public void saveMain(SchoolClassPlaceType schoolClassPlaceType, List<SchoolPlaceCtrl> schoolPlaceCtrlList) {
		schoolClassPlaceTypeMapper.insert(schoolClassPlaceType);
		if(schoolPlaceCtrlList!=null && schoolPlaceCtrlList.size()>0) {
			for(SchoolPlaceCtrl entity:schoolPlaceCtrlList) {
				//外键设置
				entity.setPlcid(schoolClassPlaceType.getId());
				schoolPlaceCtrlMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(SchoolClassPlaceType schoolClassPlaceType,List<SchoolPlaceCtrl> schoolPlaceCtrlList) {
		schoolClassPlaceTypeMapper.updateById(schoolClassPlaceType);
		
		//1.先删除子表数据
		schoolPlaceCtrlMapper.deleteByMainId(schoolClassPlaceType.getId());
		
		//2.子表数据重新插入
		if(schoolPlaceCtrlList!=null && schoolPlaceCtrlList.size()>0) {
			for(SchoolPlaceCtrl entity:schoolPlaceCtrlList) {
				//外键设置
				entity.setPlcid(schoolClassPlaceType.getId());
				schoolPlaceCtrlMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		schoolPlaceCtrlMapper.deleteByMainId(id);
		schoolClassPlaceTypeMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			schoolPlaceCtrlMapper.deleteByMainId(id.toString());
			schoolClassPlaceTypeMapper.deleteById(id);
		}
	}
	
}
