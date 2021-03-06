package com.app.controller;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.entity.Address;
import com.app.service.AddressService;

@Controller
@RequestMapping("/address/")
public class AddressController {
	
	Logger logger = LoggerFactory.getLogger(AddressController.class);
	@Resource(name = "addressService")
	AddressService addressService;

	/**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4])|(18[0,1,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
	/**
	 * 根据地址id删除地址
	 * 
	 * @param addrId
	 * @return
	 */
	@RequestMapping("delete.action")
	@ResponseBody
	public String deleteAddrById(String id) {
		System.out.println("删除地址：id:"+id);
		if (null != id && null != addressService.getAddressById(id)) {
			addressService.deleteAddrById(id);
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * 根据用用户的id查询所有的地址
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("all.action")
	@ResponseBody
	public List<Address> getAddressList(Integer userId) {
		System.out.println("获取所有地址");
		return addressService.getAddressList(userId);
	}

	/**
	 * 添加地址
	 * 
	 * @param country
	 * @param province
	 * @param city
	 * @param county
	 * @param village
	 * @param pn
	 * @return
	 */
	@RequestMapping(value = "add.action", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String addAddress(String customerId,String uName,String uIphone,String community, String houseNumber) {
		logger.info("添加地址：customerId:"+customerId+",uName:"+uName+",uIphone:"+uIphone+",community:"+community+",houseNumber:"+houseNumber);
		System.out.println("添加地址：customerId:"+customerId+",uName:"+uName+",uIphone:"+uIphone+",community:"+community+",houseNumber:"+houseNumber);
		if (null != customerId && null != community && null != houseNumber) {
			if (Pattern.matches(REGEX_MOBILE, uIphone)) {
				Address address = new Address();
				UUID uuid = UUID.randomUUID();
				address.setId(uuid.toString());
				address.setuName(uName);
				address.setuIphone(uIphone);
				address.setCustomer_id(Integer.parseInt(customerId));
				address.setCommunity(community);
				address.setHouseNumber(houseNumber);
				addressService.addAddress(address);
				return "1";
			}
			return "0";
		} else {
			return "0";
		}
	}

	/**
	 * 更新地址
	 * 
	 * @param id
	 * @param country
	 * @param province
	 * @param city
	 * @param county
	 * @param village
	 * @param pn
	 */
	@RequestMapping(value = "update.action", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String updateAddr(String id,String uName,String uIphone,String community, String houseNumber) {
		System.out.println("更新地址：id："+id+",uName:"+uName+",uIphone:"+uIphone+",community:"+community+",houseNumber:"+houseNumber);
		if (null != id && null != addressService.getAddressById(id)) {
			Address address = new Address();
			address.setId(id);
			address.setuName(uName);
			address.setuIphone(uIphone);
			address.setCommunity(community);
			address.setHouseNumber(houseNumber);
			addressService.updateAddr(address);
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * 查询单个地址
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("find.action")
	@ResponseBody
	public Address getAddressById(String id) {
		System.out.println("查询单个地址：id:"+id);
		return addressService.getAddressById(id);
	}
}
