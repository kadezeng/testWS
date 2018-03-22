/**
 * ServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.BOI;

public interface ServiceSoap extends java.rmi.Remote {

    /**
     * 设置tokens(多个时逗号分隔)的数据状态，允许的状态值：Cancel、OK
     */
    public java.lang.String setOK(java.lang.String tokens, java.lang.String OK) throws java.rmi.RemoteException;

    /**
     * 不带目标系统的公用接口入口(默认目标系统为ERP) Invoke<BR>
     * public bool Invoke(string from, string token, string funcName, string
     * parameters, out string result) <br>
     * 函数布尔返回值表示成功与否<br>
     * from:来源系统的信息，前3位固定来源系统编号 + 可设置的通讯握手字符串(如111111)，已用过的系统编号有ERP、DMS、MES、SWS、BZS(IT用Java平台开发的报账系统)、EDM(EDMC系统Java平台开发)<BR>
     * token:每次调用生成一个新的GUID<BR>
     * funcName：业务接口点英文描述 + _  + 3位接口点编号<BR>
     * parameters:数据XML序列字符串经UTF-8编码<BR>
     * result:异步数据处理时用 OK 表示接收成功，否则给出接收失败的原因  同步数据处理时 1:生成的单据编号 否则 0:错误原因
     */
    public void invoke(java.lang.String from, java.lang.String token, java.lang.String funcName, java.lang.String parameters, javax.xml.rpc.holders.BooleanHolder invokeResult, javax.xml.rpc.holders.StringHolder result) throws java.rmi.RemoteException;

    /**
     * 带目标系统的公用接口入口 BOIInvoke
     * public bool BOIInvoke(string from, string to, string token, string
     * funcName, string parameters, out string result) <br>
     * 函数布尔返回值表示成功与否<br>
     * from:来源系统的信息，前3位固定来源系统编号 + 可设置的通讯握手字符串(如111111)，已用过的系统编号有ERP、DMS、MES、SWS、BZS(IT用Java平台开发的报账系统)、EDM(EDMC系统Java平台开发)<BR>
     * to:3位目标系统编号
     * token:每次调用生成一个新的GUID<BR>
     * funcName：业务接口点英文描述 + _  + 3位接口点编号<BR>
     * parameters:数据XML序列字符串经UTF-8编码<BR>
     * result:异步数据处理时用 OK 表示接收成功，否则给出接收失败的原因  同步数据处理时 1:生成的单据编号 否则 0:错误原因
     */
    public void BOIInvoke(java.lang.String from, java.lang.String to, java.lang.String token, java.lang.String funcName, java.lang.String parameters, javax.xml.rpc.holders.BooleanHolder BOIInvokeResult, javax.xml.rpc.holders.StringHolder result) throws java.rmi.RemoteException;
}
