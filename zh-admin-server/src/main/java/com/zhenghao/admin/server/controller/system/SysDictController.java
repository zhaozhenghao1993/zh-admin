package com.zhenghao.admin.server.controller.system;

import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.server.annotation.SysLog;
import com.zhenghao.admin.server.controller.AbstractController;
import com.zhenghao.admin.server.entity.SysDictEntity;
import com.zhenghao.admin.server.entity.SysDictItemEntity;
import com.zhenghao.admin.server.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.zhenghao.admin.common.constant.SystemConstants.API_PREFIX;

/**
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2021/10/23 21:00
 * SysDictController.java
 */
@RestController
@RequestMapping(API_PREFIX + "/sys/dict")
public class SysDictController extends AbstractController {

    private final SysDictService sysDictService;

    @Autowired
    public SysDictController(SysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }

    /**
     * 列表
     *
     * @param params
     * @return
     */
    @GetMapping("")
    public Result<Page<SysDictEntity>> list(@RequestParam Map<String, Object> params) {
        return sysDictService.listSysDict(params);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SysDictEntity> info(@PathVariable("id") Long id) {
        return sysDictService.getSysDictById(id);
    }

    /**
     * 新增
     *
     * @param sysDict
     * @return
     */
    @SysLog("新增数据字典")
    @PostMapping("")
    public Result save(@RequestBody @Valid SysDictEntity sysDict, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        sysDict.setCreatorId(getUserId());
        return sysDictService.saveSysDict(sysDict);
    }

    /**
     * 修改
     *
     * @param id
     * @param sysDict
     * @return
     */
    @SysLog("修改数据字典")
    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody @Valid SysDictEntity sysDict, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        sysDict.setId(id);
        sysDict.setModifierId(getUserId());
        return sysDictService.updateSysDict(sysDict);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @SysLog("删除数据字典")
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable("id") Long id) {
        return sysDictService.removeSysDict(id);
    }


    /**
     * 列表
     *
     * @param params
     * @return
     */
    @GetMapping("/{dictCode}/item")
    public Result<Page<SysDictItemEntity>> listDictItem(@RequestParam Map<String, Object> params,
                                                        @PathVariable("dictCode") String dictCode) {
        return sysDictService.listSysDictItem(params, dictCode);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{dictCode}/item/{id}")
    public Result<SysDictItemEntity> dictItemInfo(@PathVariable("id") Long id) {
        return sysDictService.getSysDictItemById(id);
    }

    /**
     * 新增
     *
     * @param sysDictItem
     * @return
     */
    @SysLog("新增数据字典项目")
    @PostMapping("/{dictCode}/item")
    public Result saveDictItem(@PathVariable("dictCode") String dictCode, @RequestBody @Valid SysDictItemEntity sysDictItem, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        sysDictItem.setCreatorId(getUserId());
        sysDictItem.setDictCode(dictCode);
        return sysDictService.saveSysDictItem(sysDictItem);
    }

    /**
     * 修改
     *
     * @param id
     * @param sysDictItem
     * @return
     */
    @SysLog("修改数据字典项目")
    @PutMapping("/{dictCode}/item/{id}")
    public Result updateDictItem(@PathVariable("dictCode") String dictCode,
                                 @PathVariable("id") Long id,
                                 @RequestBody @Valid SysDictItemEntity sysDictItem,
                                 BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        sysDictItem.setId(id);
        sysDictItem.setModifierId(getUserId());
        sysDictItem.setDictCode(dictCode);
        return sysDictService.updateSysDictItem(sysDictItem);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @SysLog("删除数据字典项目")
    @DeleteMapping("/{dictCode}/item/{id}")
    public Result removeDictItem(@PathVariable("id") Long id) {
        return sysDictService.removeSysDictItem(id);
    }

}