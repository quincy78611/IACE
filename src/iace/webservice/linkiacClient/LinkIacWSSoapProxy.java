package iace.webservice.linkiacClient;

public class LinkIacWSSoapProxy implements iace.webservice.linkiacClient.LinkIacWSSoap {
	private String _endpoint = null;
	private iace.webservice.linkiacClient.LinkIacWSSoap linkIacWSSoap = null;

	public LinkIacWSSoapProxy() {
		_initLinkIacWSSoapProxy();
	}

	public LinkIacWSSoapProxy(String endpoint) {
		_endpoint = endpoint;
		_initLinkIacWSSoapProxy();
	}

	private void _initLinkIacWSSoapProxy() {
		try {
			linkIacWSSoap = (new iace.webservice.linkiacClient.LinkIacWSLocator()).getLinkIacWSSoap();
			if (linkIacWSSoap != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) linkIacWSSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) linkIacWSSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (linkIacWSSoap != null)
			((javax.xml.rpc.Stub) linkIacWSSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public iace.webservice.linkiacClient.LinkIacWSSoap getLinkIacWSSoap() {
		if (linkIacWSSoap == null)
			_initLinkIacWSSoapProxy();
		return linkIacWSSoap;
	}

	public java.lang.String getPatentInfo(java.lang.String requestxml) throws java.rmi.RemoteException {
		if (linkIacWSSoap == null)
			_initLinkIacWSSoapProxy();
		return linkIacWSSoap.getPatentInfo(requestxml);
	}

	public java.lang.String getIEKView(java.lang.String requestxml) throws java.rmi.RemoteException {
		if (linkIacWSSoap == null)
			_initLinkIacWSSoapProxy();
		return linkIacWSSoap.getIEKView(requestxml);
	}

	public java.lang.String checkMbrAuth(java.lang.String requestxml) throws java.rmi.RemoteException {
		if (linkIacWSSoap == null)
			_initLinkIacWSSoapProxy();
		return linkIacWSSoap.checkMbrAuth(requestxml);
	}

	public java.lang.String checkMbrReg(java.lang.String requestxml) throws java.rmi.RemoteException {
		if (linkIacWSSoap == null)
			_initLinkIacWSSoapProxy();
		return linkIacWSSoap.checkMbrReg(requestxml);
	}

	public java.lang.String addMbrInfo(java.lang.String requestxml) throws java.rmi.RemoteException {
		if (linkIacWSSoap == null)
			_initLinkIacWSSoapProxy();
		return linkIacWSSoap.addMbrInfo(requestxml);
	}

	public java.lang.String modifyMbrStatus(java.lang.String requestxml) throws java.rmi.RemoteException {
		if (linkIacWSSoap == null)
			_initLinkIacWSSoapProxy();
		return linkIacWSSoap.modifyMbrStatus(requestxml);
	}

	public java.lang.String modifyMbrPwd(java.lang.String requestxml) throws java.rmi.RemoteException {
		if (linkIacWSSoap == null)
			_initLinkIacWSSoapProxy();
		return linkIacWSSoap.modifyMbrPwd(requestxml);
	}

	public java.lang.String modifyMbrInfo(java.lang.String requestxml) throws java.rmi.RemoteException {
		if (linkIacWSSoap == null)
			_initLinkIacWSSoapProxy();
		return linkIacWSSoap.modifyMbrInfo(requestxml);
	}

}