package com.zhenghao.admin.server.controller.system;

import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.vo.TreeVO;
import com.zhenghao.admin.server.annotation.SysLog;
import com.zhenghao.admin.server.controller.AbstractController;
import com.zhenghao.admin.server.entity.SysOrgEntity;
import com.zhenghao.admin.server.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.zhenghao.admin.common.constant.SystemConstants.API_PREFIX;

/**
 * 系统组织controller
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月8日 下午2:03:22
 * SysMenuController.java
 */
@RestController
@RequestMapping(API_PREFIX + "/sys/org")
public class SysOrgController extends AbstractController {

    private final SysOrgService sysOrgService;

    @Autowired
    public SysOrgController(SysOrgService sysOrgService) {
        this.sysOrgService = sysOrgService;
    }

    /**
     * 组织列表
     *
     * @param params
     * @return
     */
    @GetMapping("")
    public Result<Page<SysOrgEntity>> listOrg(@RequestParam Map<String, Object> params) {
        return sysOrgService.listOrg(params);
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SysOrgEntity> info(@PathVariable("id") Long id) {
        return sysOrgService.getOrgById(id);
    }

    /**
     * 组织树
     *
     * @return
     */
    @GetMapping("/tree")
    public Result<List<TreeVO>> select(@RequestParam Map<String, Object> params) {
        return sysOrgService.listTree(params);
    }

    /**
     * 新增组织
     *
     * @param org
     * @return
     */
    @SysLog("新增组织")
    @PostMapping("")
    public Result save(@RequestBody @Valid SysOrgEntity org, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        org.setCreatorId(getUserId());
        return sysOrgService.saveOrg(org);
    }

    /**
     * 修改组织
     *
     * @param org
     * @return
     */
    @SysLog("修改组织")
    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody @Valid SysOrgEntity org, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        org.setId(id);
        org.setModifierId(getUserId());
        return sysOrgService.updateOrg(org);
    }

    /**
     * 删除组织
     *
     * @param id
     * @return
     */
    @SysLog("删除组织")
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable("id") Long id) {
        return sysOrgService.remove(id);
    }
}
