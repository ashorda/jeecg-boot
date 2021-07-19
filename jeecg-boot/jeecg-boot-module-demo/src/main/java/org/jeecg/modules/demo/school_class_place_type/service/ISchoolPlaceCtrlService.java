package org.jeecg.modules.demo.school_class_place_type.service;

import org.jeecg.modules.demo.school_class_place_type.entity.SchoolPlaceCtrl;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 上课地点管理
 * @Author: jeecg-boot
 * @Date:   2021-07-19
 * @Version: V1.0
 */
public interface ISchoolPlaceCtrlService extends IService<SchoolPlaceCtrl> {

	public List<SchoolPlaceCtrl> selectByMainId(String mainId);
}
