/**
 * LinkIacWSLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package iace.webservice.linkiacClient;

public class LinkIacWSLocator extends org.apache.axis.client.Service implements iace.webservice.linkiacClient.LinkIacWS {

    public LinkIacWSLocator() {
    }


    public LinkIacWSLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LinkIacWSLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LinkIacWSSoap
    private java.lang.String LinkIacWSSoap_address = "http://iekweb2.iek.org.tw/itisExt/ws/linkiacws.asmx";

    public java.lang.String getLinkIacWSSoapAddress() {
        return LinkIacWSSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LinkIacWSSoapWSDDServiceName = "LinkIacWSSoap";

    public java.lang.String getLinkIacWSSoapWSDDServiceName() {
        return LinkIacWSSoapWSDDServiceName;
    }

    public void setLinkIacWSSoapWSDDServiceName(java.lang.String name) {
        LinkIacWSSoapWSDDServiceName = name;
    }

    public iace.webservice.linkiacClient.LinkIacWSSoap getLinkIacWSSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LinkIacWSSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLinkIacWSSoap(endpoint);
    }

    public iace.webservice.linkiacClient.LinkIacWSSoap getLinkIacWSSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            iace.webservice.linkiacClient.LinkIacWSSoapStub _stub = new iace.webservice.linkiacClient.LinkIacWSSoapStub(portAddress, this);
            _stub.setPortName(getLinkIacWSSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLinkIacWSSoapEndpointAddress(java.lang.String address) {
        LinkIacWSSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (iace.webservice.linkiacClient.LinkIacWSSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                iace.webservice.linkiacClient.LinkIacWSSoapStub _stub = new iace.webservice.linkiacClient.LinkIacWSSoapStub(new java.net.URL(LinkIacWSSoap_address), this);
                _stub.setPortName(getLinkIacWSSoapWSDDServiceName());
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
        if ("LinkIacWSSoap".equals(inputPortName)) {
            return getLinkIacWSSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "LinkIacWS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "LinkIacWSSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LinkIacWSSoap".equals(portName)) {
            setLinkIacWSSoapEndpointAddress(address);
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
