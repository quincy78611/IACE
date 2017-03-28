/**
 * LinkIacWSSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package iace.webservice.linkiacClient;

public interface LinkIacWSSoap extends java.rmi.Remote {
	public java.lang.String getPatentInfo(java.lang.String requestxml) throws java.rmi.RemoteException;

	public java.lang.String getIEKView(java.lang.String requestxml) throws java.rmi.RemoteException;

	public java.lang.String checkMbrAuth(java.lang.String requestxml) throws java.rmi.RemoteException;

	public java.lang.String checkMbrReg(java.lang.String requestxml) throws java.rmi.RemoteException;

	public java.lang.String addMbrInfo(java.lang.String requestxml) throws java.rmi.RemoteException;

	public java.lang.String modifyMbrStatus(java.lang.String requestxml) throws java.rmi.RemoteException;

	public java.lang.String modifyMbrPwd(java.lang.String requestxml) throws java.rmi.RemoteException;

	public java.lang.String modifyMbrInfo(java.lang.String requestxml) throws java.rmi.RemoteException;
}
