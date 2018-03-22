import org.dom4j.io.SAXReader;
//import org.w3c.dom.Element;
import org.dom4j.Document;
import java.io.File;
import org.dom4j.Element;

public class getXml {
  
    public String getWSXml()throws Exception{  
        //创建SAXReader对象  
        SAXReader reader = new SAXReader();  
        //读取文件 转换成Document  
        Document document = reader.read(new File("WStest1/src/wsXML/wsXML.xml"));  
        //document转换为String字符串  
        String documentStr = document.asXML();  
        System.out.println("document 字符串：" + documentStr);  
        return documentStr;
    /*
        //获取根节点  
        Element root = document.getRootElement();  
        //根节点转换为String字符串  
        String rootStr = root.asXML();  
        System.out.println("root 字符串：" + rootStr);  
        //获取其中student1节点  
        Element student1Node = root.element("student1");  
        //student1节点转换为String字符串  
        String student1Str = student1Node.asXML();  
        System.out.println("student1 字符串：" + student1Str);  
     */
    } 
}
