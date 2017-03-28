/**
 * LinkIacWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package iace.webservice.linkiacClient;

public interface LinkIacWS extends javax.xml.rpc.Service {
	public java.lang.String getLinkIacWSSoapAddress();

	public iace.webservice.linkiacClient.LinkIacWSSoap getLinkIacWSSoap() throws javax.xml.rpc.ServiceException;

	public iace.webservice.linkiacClient.LinkIacWSSoap getLinkIacWSSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
