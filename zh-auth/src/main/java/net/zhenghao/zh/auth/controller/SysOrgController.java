package net.zhenghao.zh.auth.controller;

import net.zhenghao.zh.auth.entity.SysOrgEntity;
import net.zhenghao.zh.auth.service.SysOrgService;
import net.zhenghao.zh.common.annotation.SysLog;
import net.zhenghao.zh.common.controller.AbstractController;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.vo.TreeVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统组织controller
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月8日 下午2:03:22
 * SysMenuController.java
 */
@RestController
@RequestMapping("${zh-admin.api.prefix}/sys/org")
public class SysOrgController extends AbstractController {

	@Resource
	private SysOrgService sysOrgService;

	/**
	 * 组织列表
	 * @param params
	 * @return
	 */
	@GetMapping("")
	public Page<SysOrgEntity> listOrg(@RequestParam Map<String, Object> params) {
		return sysOrgService.listOrg(params);
	}

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Result<SysOrgEntity> info(@PathVariable("id") Long id) {
		return sysOrgService.getOrgById(id);
	}
	
	/**
	 * 组织树
	 * @return
	 */
	@GetMapping("/tree")
	public Result<List<TreeVO>> select(@RequestParam Map<String, Object> params) {
		return sysOrgService.listTree(params);
	}
	
	/**
	 * 新增组织
	 * @param org
	 * @return
	 */
	@SysLog("新增组织")
	@PostMapping("")
	public Result save(@RequestBody SysOrgEntity org) {
		org.setCreatorId(getUserId());
		return sysOrgService.saveOrg(org);
	}

	/**
	 * 修改组织
	 * @param org
	 * @return
	 */
	@SysLog("修改组织")
	@PutMapping("/{id}")
	public Result update(@PathVariable("id") Long id, @RequestBody SysOrgEntity org) {
		org.setId(id);
		org.setModifierId(getUserId());
		return sysOrgService.updateOrg(org);
	}
	
	/**
	 * 删除组织
	 * @param id
	 * @return
	 */
	@SysLog("删除组织")
	@DeleteMapping("/{id}")
	public Result remove(@PathVariable("id") Long id) {
		return sysOrgService.remove(id);
	}
}
