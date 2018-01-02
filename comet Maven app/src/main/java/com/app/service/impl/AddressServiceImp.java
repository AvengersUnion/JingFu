package com.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.AddressDao;
import com.app.entity.Address;
import com.app.service.AddressService;
@Service("addressService")
public class AddressServiceImp implements AddressService {

	@Resource
	private AddressDao addressDao;
	public List<String> getIdsAddr(String userId) {
		// TODO Auto-generated method stub
		return addressDao.getIdsAddr(userId);
	}

	public void deleteAddrById(String addrId) {
		// TODO Auto-generated method stub
		addressDao.deleteAddrById(addrId);
	}

	public List<Address> getAddressList(String userId) {
		// TODO Auto-generated method stub
		return addressDao.getAddressList(userId);
	}

	public void updateAddr(Address address) {
		// TODO Auto-generated method stub
		addressDao.updateAddr(address);
	}

	public void addAddress(Address address) {
		// TODO Auto-generated method stub
		addressDao.addAddress(address);
	}

	public Address getAddressById(String id) {
		// TODO Auto-generated method stub
		return addressDao.getAddressById(id);
	}
	
}
