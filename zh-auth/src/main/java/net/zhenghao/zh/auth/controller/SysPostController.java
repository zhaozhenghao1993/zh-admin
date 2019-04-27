package net.zhenghao.zh.auth.controller;

import net.zhenghao.zh.common.annotation.SysLog;
import net.zhenghao.zh.common.controller.AbstractController;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.auth.entity.SysPostEntity;
import net.zhenghao.zh.auth.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 岗位管理
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2019/04/27 22:16
 * SysPostController.java
 */
@RestController
@RequestMapping("/sys/post")
public class SysPostController extends AbstractController {

	@Autowired
	private SysPostService sysPostService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@GetMapping("")
	public Page<SysPostEntity> list(@RequestParam Map<String, Object> params) {
		return sysPostService.listPost(params);
	}

    /**
     * 根据id查询详情
     * @param postId
     * @return
     */
    @GetMapping("/{id}")
    public R info(@PathVariable("id") Long postId) {
        return sysPostService.getPostById(postId);
    }

    /**
	 * 新增
	 * @param post
	 * @return
	 */
    @SysLog("新增岗位管理")
    @PostMapping("")
    public R save(@RequestBody SysPostEntity post) {
        post.setCreatorId(getUserId());
        return sysPostService.savePost(post);
    }

    /**
	 * 修改
	 * @param postId
	 * @param post
	 * @return
	 */
    @SysLog("修改岗位管理")
    @PutMapping("/{id}")
    public R update(@PathVariable("id") Long postId, @RequestBody SysPostEntity post) {
        post.setPostId(postId);
        post.setModifierId(getUserId());
        return sysPostService.updatePost(post);
    }

    /**
	 * 删除
	 * @param postId
	 * @return
	 */
    @SysLog("删除岗位管理")
    @DeleteMapping("/{id}")
    public R remove(@PathVariable("id") Long postId) {
        return sysPostService.removePost(postId);
    }

    /**
	 * 批量删除
	 * @param ids
	 * @return
	 */
    @SysLog("批量删除岗位管理")
    @DeleteMapping("")
    public R batchRemove(@RequestBody Long[] ids) {
        return sysPostService.batchRemove(ids);
    }
	
}