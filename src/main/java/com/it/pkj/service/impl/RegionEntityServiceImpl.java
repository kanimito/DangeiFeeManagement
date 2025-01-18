package com.it.pkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.pkj.domain.RegionEntity;
import com.it.pkj.dto.RegionEntityDto;
import com.it.pkj.mapper.RegionEntityMapper;
import com.it.pkj.service.RegionEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
* @author Administrator
* @description 针对表【regionentity】的数据库操作Service实现
* @createDate 2025-01-16 12:42:17
*/
@Service
public class RegionEntityServiceImpl extends ServiceImpl<RegionEntityMapper, RegionEntity>
    implements RegionEntityService {
    @Autowired
    private RegionEntityMapper regionEntityMapper;

    @Override
    public RegionEntity saveRegionEntity(RegionEntityDto regionEntityDto) {
        RegionEntity regionEntity = convertToEntity(regionEntityDto);
        regionEntityMapper.insert(regionEntity);
        return regionEntity;
    }

    @Override
    public RegionEntity updateRegionEntity(Long id, RegionEntityDto regionEntityDto) {
        RegionEntity regionEntity = convertToEntity(regionEntityDto);
        regionEntity.setRegionEntityRegionId(id);
        regionEntityMapper.updateById(regionEntity);
        return regionEntity;
    }

    @Override
    public void deleteRegionEntity(Long id) {
        regionEntityMapper.deleteById(id);
    }

    @Override
    public RegionEntity getRegionEntityById(Long id) {
        return regionEntityMapper.selectById(id);
    }

    @Override
    public IPage<RegionEntity> getRegionEntities(String name, int page, int size) {
        QueryWrapper<RegionEntity> queryWrapper = new QueryWrapper<>();
        if (name!= null) {
            queryWrapper.like("region_entity_region_name", name);
        }
        IPage<RegionEntity> pageObj = new Page<>(page, size);
        return regionEntityMapper.selectPage(pageObj, queryWrapper);
    }

    private RegionEntity convertToEntity(RegionEntityDto regionEntityDto) {
        RegionEntity regionEntity = new RegionEntity();
        regionEntity.setRegionEntityRegionName(regionEntityDto.getRegionEntityRegionName());
        regionEntity.setRegionEntityRegionWeight(regionEntityDto.getRegionEntityRegionWeight());
        return regionEntity;
    }
}




