package com.zhenghao.admin.auth.controller;

import com.zhenghao.admin.auth.entity.SysMenuEntity;
import com.zhenghao.admin.auth.service.SysMenuService;
import com.zhenghao.admin.common.annotation.SysLog;
import com.zhenghao.admin.common.controller.AbstractController;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.vo.TreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单controller
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月8日 下午2:03:22
 * SysMenuController.java
 */
@RestController
@RequestMapping("${zh-admin.api.prefix}/sys/menu")
public class SysMenuController extends AbstractController {

    private final SysMenuService sysMenuService;

    @Autowired
    public SysMenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    /**
     * 菜单列表
     *
     * @param params
     * @return
     */
    @GetMapping("")
    public Result<Page<SysMenuEntity>> listMenu(@RequestParam Map<String, Object> params) {
        return sysMenuService.listMenu(params);
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SysMenuEntity> info(@PathVariable("id") Long id) {
        return sysMenuService.getMenuById(id);
    }

    /**
     * 选择除按钮的菜单(添加、修改)
     *
     * @return
     */
    @GetMapping("/tree")
    public Result<List<TreeVO>> select(@RequestParam Map<String, Object> params) {
        return sysMenuService.listTree(params);
    }

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    @SysLog("新增菜单")
    @PostMapping("")
    public Result save(@RequestBody @Valid SysMenuEntity menu, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        menu.setCreatorId(getUserId());
        return sysMenuService.saveMenu(menu);
    }

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    @SysLog("修改菜单")
    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody @Valid SysMenuEntity menu, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        menu.setId(id);
        menu.setModifierId(getUserId());
        return sysMenuService.updateMenu(menu);
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @SysLog("删除菜单")
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable("id") Long id) {
        return sysMenuService.remove(id);
    }
}
