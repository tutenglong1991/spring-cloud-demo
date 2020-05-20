/**
 * ServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.service;

public class ServiceImplServiceLocator extends org.apache.axis.client.Service implements com.service.ServiceImplService {

    public ServiceImplServiceLocator() {
    }


    public ServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServiceImplPort
    private java.lang.String ServiceImplPort_address = "http://127.0.0.1:8090/test/api1";

    public java.lang.String getServiceImplPortAddress() {
        return ServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServiceImplPortWSDDServiceName = "ServiceImplPort";

    public java.lang.String getServiceImplPortWSDDServiceName() {
        return ServiceImplPortWSDDServiceName;
    }

    public void setServiceImplPortWSDDServiceName(java.lang.String name) {
        ServiceImplPortWSDDServiceName = name;
    }

    public com.service.ServiceImpl getServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServiceImplPort(endpoint);
    }

    public com.service.ServiceImpl getServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.service.ServiceImplServiceSoapBindingStub _stub = new com.service.ServiceImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServiceImplPortEndpointAddress(java.lang.String address) {
        ServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.service.ServiceImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                com.service.ServiceImplServiceSoapBindingStub _stub = new com.service.ServiceImplServiceSoapBindingStub(new java.net.URL(ServiceImplPort_address), this);
                _stub.setPortName(getServiceImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ServiceImplPort".equals(inputPortName)) {
            return getServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.com/", "ServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.com/", "ServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServiceImplPort".equals(portName)) {
            setServiceImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
