package org.jeecg.modules.demo.school_class_place_type.service.impl;

import org.jeecg.modules.demo.school_class_place_type.entity.SchoolPlaceCtrl;
import org.jeecg.modules.demo.school_class_place_type.mapper.SchoolPlaceCtrlMapper;
import org.jeecg.modules.demo.school_class_place_type.service.ISchoolPlaceCtrlService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 上课地点管理
 * @Author: jeecg-boot
 * @Date:   2021-07-19
 * @Version: V1.0
 */
@Service
public class SchoolPlaceCtrlServiceImpl extends ServiceImpl<SchoolPlaceCtrlMapper, SchoolPlaceCtrl> implements ISchoolPlaceCtrlService {
	
	@Autowired
	private SchoolPlaceCtrlMapper schoolPlaceCtrlMapper;
	
	@Override
	public List<SchoolPlaceCtrl> selectByMainId(String mainId) {
		return schoolPlaceCtrlMapper.selectByMainId(mainId);
	}
}
