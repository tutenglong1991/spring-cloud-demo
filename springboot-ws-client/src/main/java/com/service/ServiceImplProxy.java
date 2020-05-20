package com.service;

public class ServiceImplProxy implements com.service.ServiceImpl {
  private String _endpoint = null;
  private com.service.ServiceImpl serviceImpl = null;
  
  public ServiceImplProxy() {
    _initServiceImplProxy();
  }
  
  public ServiceImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceImplProxy();
  }
  
  private void _initServiceImplProxy() {
    try {
      serviceImpl = (new com.service.ServiceImplServiceLocator()).getServiceImplPort();
      if (serviceImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceImpl != null)
      ((javax.xml.rpc.Stub)serviceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.service.ServiceImpl getServiceImpl() {
    if (serviceImpl == null)
      _initServiceImplProxy();
    return serviceImpl;
  }
  
  public java.lang.String test1() throws java.rmi.RemoteException{
    if (serviceImpl == null)
      _initServiceImplProxy();
    return serviceImpl.test1();
  }
  
  public java.lang.String test2() throws java.rmi.RemoteException{
    if (serviceImpl == null)
      _initServiceImplProxy();
    return serviceImpl.test2();
  }
  
  
}