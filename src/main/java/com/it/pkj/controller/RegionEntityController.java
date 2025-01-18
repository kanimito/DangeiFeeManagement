package com.it.pkj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.it.pkj.dto.RegionEntityDto;
import com.it.pkj.domain.RegionEntity;
import com.it.pkj.service.RegionEntityService;
import com.it.pkj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regionentities")
public class RegionEntityController {
    @Autowired
    private RegionEntityService regionEntityService;

    // 保存地区信息
    @PostMapping("/save")
    public Result<RegionEntity> saveRegionEntity(@RequestBody RegionEntityDto regionEntityDto) {
        try {
            RegionEntity regionEntity = regionEntityService.saveRegionEntity(regionEntityDto);
            return Result.success(regionEntity);
        } catch (Exception e) {
            return Result.error(500, "保存地区信息失败：" + e.getMessage());
        }
    }

    // 更新地区信息
    @PostMapping("/update/{id}")
    public Result<RegionEntity> updateRegionEntity(@PathVariable Long id, @RequestBody RegionEntityDto regionEntityDto) {
        try {
            RegionEntity regionEntity = regionEntityService.updateRegionEntity(id, regionEntityDto);
            return Result.success(regionEntity);
        } catch (Exception e) {
            return Result.error(500, "更新地区信息失败：" + e.getMessage());
        }
    }

    // 删除地区信息
    @PostMapping("/delete/{id}")
    public Result<Void> deleteRegionEntity(@PathVariable Long id) {
        try {
            regionEntityService.deleteRegionEntity(id);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(500, "删除地区信息失败：" + e.getMessage());
        }
    }

    // 获取地区信息
    @GetMapping("/{id}")
    public Result<RegionEntity> getRegionEntityById(@PathVariable Long id) {
        try {
            RegionEntity regionEntity = regionEntityService.getRegionEntityById(id);
            if (regionEntity == null) {
                return Result.error(404, "未找到地区信息");
            }
            return Result.success(regionEntity);
        } catch (Exception e) {
            return Result.error(500, "获取地区信息失败：" + e.getMessage());
        }
    }

    // 分页查询地区信息
    @GetMapping
    public Result<IPage<RegionEntity>> getRegionEntities(
            @RequestParam(required = false) String name,
            @RequestParam int page,
            @RequestParam int size) {
        try {
            IPage<RegionEntity> regionEntities = regionEntityService.getRegionEntities(name, page, size);
            return Result.success(regionEntities);
        } catch (Exception e) {
            return Result.error(500, "分页查询地区信息失败：" + e.getMessage());
        }
    }
}