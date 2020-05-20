/**
 * ServiceImplService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.service;

public interface ServiceImplService extends javax.xml.rpc.Service {
    public java.lang.String getServiceImplPortAddress();

    public com.service.ServiceImpl getServiceImplPort() throws javax.xml.rpc.ServiceException;

    public com.service.ServiceImpl getServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
