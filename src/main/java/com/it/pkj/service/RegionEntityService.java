package com.it.pkj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.it.pkj.domain.RegionEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.it.pkj.dto.RegionEntityDto;

/**
* @author Administrator
* @description 针对表【regionentity】的数据库操作Service
* @createDate 2025-01-16 12:42:17
*/
public interface RegionEntityService extends IService<RegionEntity> {
    RegionEntity saveRegionEntity(RegionEntityDto regionEntityDto);
    RegionEntity updateRegionEntity(Long id, RegionEntityDto regionEntityDto);
    void deleteRegionEntity(Long id);
    RegionEntity getRegionEntityById(Long id);
    IPage<RegionEntity> getRegionEntities(String name, int page, int size);
}
