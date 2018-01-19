package com.app.service;

import java.util.List;

import com.app.entity.Address;

public interface AddressService {
	List<String> getIdsAddr(Integer userId);
	void deleteAddrById(String addrId);
	
	List<Address> getAddressList(Integer userId);
	void updateAddr(Address address);
	void addAddress(Address address);
	Address getAddressById(String id);
}
