package localhost.BOI;

public class ServiceSoapProxy implements localhost.BOI.ServiceSoap {
  private String _endpoint = null;
  private localhost.BOI.ServiceSoap serviceSoap = null;
  
  public ServiceSoapProxy() {
    _initServiceSoapProxy();
  }
  
  public ServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceSoapProxy();
  }
  
  private void _initServiceSoapProxy() {
    try {
      serviceSoap = (new localhost.BOI.ServiceLocator()).getServiceSoap();
      if (serviceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceSoap != null)
      ((javax.xml.rpc.Stub)serviceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public localhost.BOI.ServiceSoap getServiceSoap() {
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap;
  }
  
  public java.lang.String setOK(java.lang.String tokens, java.lang.String OK) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.setOK(tokens, OK);
  }
  
  public void invoke(java.lang.String from, java.lang.String token, java.lang.String funcName, java.lang.String parameters, javax.xml.rpc.holders.BooleanHolder invokeResult, javax.xml.rpc.holders.StringHolder result) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    serviceSoap.invoke(from, token, funcName, parameters, invokeResult, result);
  }
  
  public void BOIInvoke(java.lang.String from, java.lang.String to, java.lang.String token, java.lang.String funcName, java.lang.String parameters, javax.xml.rpc.holders.BooleanHolder BOIInvokeResult, javax.xml.rpc.holders.StringHolder result) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    serviceSoap.BOIInvoke(from, to, token, funcName, parameters, BOIInvokeResult, result);
  }
  
  
}