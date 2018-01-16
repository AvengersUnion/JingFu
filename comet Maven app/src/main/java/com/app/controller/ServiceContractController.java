package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.common.BaseResult;
import com.app.entity.ServiceContracts;
import com.app.service.ServiceContractService;

@Controller
@RequestMapping("contract")
public class ServiceContractController {

	@Resource(name="serviceContractService")
	private ServiceContractService serviceContractService;
	/**
	 * 返回所有的协议
	 * @return
	 */
	@RequestMapping("all.do")
	@ResponseBody
	public List<ServiceContracts> getContractList() {
		
		return serviceContractService.getContractList();
	}
	/**
	 * 根据id返回协议
	 * @param id
	 * @return
	 */
	@RequestMapping("findById.do")
	@ResponseBody
	public ServiceContracts getContractById(Integer id) {

		if (null==id||id<1) {
			return null;
		}
		return serviceContractService.getContractById(id);
	}

	/**
	 * 添加协议
	 * @param contractTitle
	 * @param contractDetails
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult addContracts(String contractTitle,String contractDetails) {
		//获取当前时间
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(new Date());
		//实例化服务协议对象
		ServiceContracts serviceContracts=new ServiceContracts();
		//加入时间
		serviceContracts.setCreateTime(date);
		//加入标题
		serviceContracts.setContractTitle(contractTitle);
		//加入类容
		serviceContracts.setContractDetails(contractDetails);
		serviceContracts.setState(0);
		serviceContractService.addContracts(serviceContracts);
		return BaseResult.ok();
	}

	/**
	 * 更改服务协议
	 * @param id
	 * @param contractTitle
	 * @param contractDetails
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult updateContract(Integer id,String contractTitle,String contractDetails) {
		//实例化服务协议对象
		ServiceContracts serviceContracts=new ServiceContracts();
		if (null==id||id<1||null==serviceContractService.getContractById(id)) {
			return BaseResult.build(500, "操作错误");
		}
		//加入要修改的id
		serviceContracts.setId(id);
		//加入要修改的标题
		serviceContracts.setContractTitle(contractTitle);
		//加入要修改的类容
		serviceContracts.setContractDetails(contractDetails);
		serviceContractService.updateContract(serviceContracts);
		return BaseResult.ok();
	}

	/**
	 * 根据id删除单个服务协议
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteById")
	@ResponseBody
	public BaseResult deleteContractById(Integer id) {
		
		if (null==id||id<1||null==serviceContractService.getContractById(id)) {
			return BaseResult.build(500, "操作错误");
		}
		serviceContractService.deleteContractById(id);
		return BaseResult.ok();
	}
	/**
	 * 根据多个id删除协议
	 * @param ids
	 * @return
	 */
	@RequestMapping("deletes")
	@ResponseBody
	public BaseResult deleteContracts(String ids) {
		if (null==ids||ids.equals("")) {
			return BaseResult.build(500, "操作错误");
		}else if (!ids.contains(",")) {
			Integer id=Integer.parseInt(ids);
			if (null==serviceContractService.getContractById(id)||id<1) {
				return BaseResult.build(500, "操作错误");
			}
			serviceContractService.deleteContractById(id);
			return BaseResult.ok();
		} else {
			String[] postcode = ids.split(",");
			for (int i = 0; i < postcode.length; i++) {
				int id=Integer.parseInt(postcode[i]);
				if (null==serviceContractService.getContractById(id)||id<1) {
					return BaseResult.build(500, "操作失败");
				}
			}
			for (int i = 0; i < postcode.length; i++) {
				int id=Integer.parseInt(postcode[i]);
				serviceContractService.deleteContractById(id);
			}
			return BaseResult.ok();
		}
	}
}
