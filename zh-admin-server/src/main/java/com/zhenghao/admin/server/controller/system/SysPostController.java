package com.zhenghao.admin.server.controller.system;

import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.server.annotation.SysLog;
import com.zhenghao.admin.server.controller.AbstractController;
import com.zhenghao.admin.server.entity.SysPostEntity;
import com.zhenghao.admin.server.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.zhenghao.admin.common.constant.SystemConstants.API_PREFIX;

/**
 * 岗位管理
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/27 22:16
 * SysPostController.java
 */
@RestController
@RequestMapping(API_PREFIX + "/sys/post")
public class SysPostController extends AbstractController {

    private final SysPostService sysPostService;

    @Autowired
    public SysPostController(SysPostService sysPostService) {
        this.sysPostService = sysPostService;
    }

    /**
     * 列表
     *
     * @param params
     * @return
     */
    @GetMapping("")
    public Result<Page<SysPostEntity>> list(@RequestParam Map<String, Object> params) {
        return sysPostService.listPost(params);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SysPostEntity> info(@PathVariable("id") Long id) {
        return sysPostService.getPostById(id);
    }

    /**
     * 岗位list
     *
     * @return
     */
    @GetMapping("/select")
    public Result<List<SysPostEntity>> listPost() {
        return sysPostService.listPost();
    }

    /**
     * 新增
     *
     * @param post
     * @return
     */
    @SysLog("新增岗位")
    @PostMapping("")
    public Result save(@RequestBody @Valid SysPostEntity post, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        post.setCreatorId(getUserId());
        return sysPostService.savePost(post);
    }

    /**
     * 修改
     *
     * @param id
     * @param post
     * @return
     */
    @SysLog("修改岗位")
    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody @Valid SysPostEntity post, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        post.setId(id);
        post.setModifierId(getUserId());
        return sysPostService.updatePost(post);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @SysLog("删除岗位")
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable("id") Long id) {
        return sysPostService.removePost(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @SysLog("批量删除岗位")
    @DeleteMapping("")
    public Result batchRemove(@RequestBody Long[] ids) {
        return sysPostService.batchRemove(ids);
    }

}