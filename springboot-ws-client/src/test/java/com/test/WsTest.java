package com.test;
import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import com.service.ServiceImpl;
import com.service.ServiceImplProxy;

public class WsTest {

	@Test
	public void test() {
		ServiceImpl s = new ServiceImplProxy("http://127.0.0.1:8090/test/api1");
		try {
			assertTrue("test1()".equals(s.test1()));
		} catch (RemoteException e) {
			fail("exceptoin happend");
			e.printStackTrace();
		}
		try {
			assertTrue("test2()".equals(s.test2()));
		} catch (RemoteException e) {
			fail("exceptoin happend");
			e.printStackTrace();
		}
	}

}
