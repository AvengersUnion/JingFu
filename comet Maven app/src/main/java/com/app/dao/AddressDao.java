package com.app.dao;

import java.util.List;

import com.app.entity.Address;

public interface AddressDao {
	List<String> getIdsAddr(String userId);
	void deleteAddrById(String addrId);
	
	List<Address> getAddressList(String userId);
	void updateAddr(Address address);
	void addAddress(Address address);
	Address getAddressById(String id);
	//void deleteAddrById(String id);
}
