import org.dom4j.io.SAXReader;
//import org.w3c.dom.Element;
import org.dom4j.Document;
import java.io.File;
import org.dom4j.Element;

public class getXml {
  
    public String getWSXml()throws Exception{  
        //����SAXReader����  
        SAXReader reader = new SAXReader();  
        //��ȡ�ļ� ת����Document  
        Document document = reader.read(new File("WStest1/src/wsXML/wsXML.xml"));  
        //documentת��ΪString�ַ���  
        String documentStr = document.asXML();  
        System.out.println("document �ַ�����" + documentStr);  
        return documentStr;
    /*
        //��ȡ���ڵ�  
        Element root = document.getRootElement();  
        //���ڵ�ת��ΪString�ַ���  
        String rootStr = root.asXML();  
        System.out.println("root �ַ�����" + rootStr);  
        //��ȡ����student1�ڵ�  
        Element student1Node = root.element("student1");  
        //student1�ڵ�ת��ΪString�ַ���  
        String student1Str = student1Node.asXML();  
        System.out.println("student1 �ַ�����" + student1Str);  
     */
    } 
}
