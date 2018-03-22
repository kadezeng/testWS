import localhost.BOI.Service; 
import localhost.BOI.ServiceLocator; 
import localhost.BOI.ServiceSoap; 
import localhost.BOI.ServiceSoapProxy; 
import localhost.BOI.ServiceSoapStub;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Scanner; 

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.dom4j.Attribute;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.io.OutputFormat;
//import org.w3c.dom.Element;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import org.dom4j.Element;
import javax.xml.rpc.holders.*;

import java.util.Date;
import java.util.Map;
import java.text.SimpleDateFormat;






public class test {
	//private static getXml testXML;

	/** 
     * read the data from excel and format into XML, then call the ERP web ervice
     * @param args 
	 * @throws IOException 
	 * @throws JSONException 
	 * @throws DocumentException 
     */  
    public static void main(String[] args) throws IOException, JSONException, DocumentException {  

        File jsFile = new File("src/wsXML/cost center.txt");
        String jsContent= FileUtils.readFileToString(jsFile,"UTF-8");
        JSONObject jsonObject=new JSONObject(jsContent);  
        //System.out.println("cost center code is : " + jsonObject.getString("aaa"));

        
    	
    	Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter year(yyyy):");   
        String year = sc.nextLine();  //读取字符串型输入   
        System.out.println("Please Enter month(mm):");   
        String month = sc.nextLine();    //读取整型输入 
        System.out.println("Please Enter batch id:");  
        String tmp_bzs_id = sc.nextLine();    //读取整型输入   
        System.out.println("year:" + year +"\n" + "month:"+month); 
        System.out.println("confirm ? Y or N ");
        String confirm = sc.nextLine(); 
        sc.close();
        if (!"Y".equals(confirm) && !"y".equals(confirm)) {
        	return;
        }
        
        // set the parameter for the web service
        SAXReader reader = new SAXReader();  
        //��ȡ�ļ� ת����Document  
        File wsFile = new File("src/wsXML/wsXML.xml");
        Document document = reader.read(wsFile);  
        //documentת��ΪString�ַ���  
        String xmlStr = document.asXML(); 
    // commented the ws function on2018-02-01
    // uncommented the ws function on 2018-02-27 
         
        /*try {           
        	ServiceSoapProxy proxy = new ServiceSoapProxy();  
            proxy.setEndpoint("http://172.18.67.24:9101/GAMC/esb/service?wsdl");  
            //����web service�ṩ�ķ��� 
            final String ws_from = "BZS<>gamC2010#";
            final String ws_token = "aaaaaaaa-2018-0120-0001-bbbbbbbbbbbb";
            final String ws_funcName = "OAVoucherCreateNE_FA6";
            
            SAXReader reader = new SAXReader();  
            //��ȡ�ļ� ת����Document  
            File wsFile = new File("src/wsXML/wsXML.xml");
            Document document = reader.read(wsFile);  
            //documentת��ΪString�ַ���  
            String documentStr = document.asXML();  
            System.out.println("document string" + documentStr);  

            

            String ws_parameters = documentStr;
            BooleanHolder ws_invokeResult = new BooleanHolder();
            StringHolder ws_result = new StringHolder();
       //     String wsResult = "";

            proxy.invoke(ws_from,ws_token,ws_funcName,ws_parameters,ws_invokeResult,ws_result);
              
            System.out.println("ws_invokeResult : " + ws_invokeResult.value);  
            System.out.println("ws_result : " + ws_result.value);  
              
        } catch (RemoteException e) {  
            e.printStackTrace();  
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		*/
		
    	
    	/* added by Kade on 2018-02-01
    	 * to test the excel reading function
    	 */
    	try {
    		
            InputStream is = new FileInputStream("D:\\testexcel\\travel+expense+reimbursement.xls");
            //operExcel excelReader = new operExcel();
            travelExpensiveLine contentLine = new travelExpensiveLine();
            // 对读取Excel表格标题测试
   /*         String[] title = excelReader.readExcelTitle(is);
            System.out.println("获得Excel表格的标题:");
            for (String s : title) {
                System.out.print(s + " ");
            }

            // 对读取Excel表格内容测试
            Map<Integer, String> map = excelReader.readExcelContent(is);
            System.out.println("获得Excel表格的内容:");
            for (int i = 1; i <= map.size(); i++) {
                System.out.println(map.get(i));
            }
  */          
            // 对excel表格行数进行测试
            /*
            int excelRowNum = excelReader.getExcelRowCount(is);
            System.out.println(excelRowNum);
            is.close();
            contentLine = excelReader.readExcelLine(is, 1);
            System.out.println(contentLine.accountCode);
            */
           
            
            // Set the XML format
            
            OutputFormat format = new OutputFormat();  
            format.setEncoding("UTF-8");  
            format.setIndent(true); //设置是否缩进
            format.setIndent("  "); //以四个空格方式实现缩进
            format.setNewlines(true);  
            
            
           
            
            
            POIFSFileSystem fs = new POIFSFileSystem(is);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
	        // 得到总行数
	        int rowNum = sheet.getLastRowNum();
            String[] strArr = new String[rowNum];
	        // 正文内容应该从第二行开始,第一行为表头的标题
	        for (int i = 1; i <= rowNum; i++) {
	        	//contentLine.init();
	        	HSSFRow row = sheet.getRow(i);
	        	
	        	//format the XML string
	        	 // create a new XML object
	            // <?xml version="1.0" encoding="UTF-8"?>
	            // <Interface Sender="OA" Receiver="ERP" Billtype="OAVoucherCreateNE_FA6">
	            //  <Bill>
	            //	  <BillHeader>
	            //    </BillHeader>
	            //    <BillBody>
	            //      <Entry>
	            //      </Entry>
	            //      <Entry>
	            //      </Entry>
	            //      <Entry>
	            //      </Entry>
	            //    </BillBody>
	            //  </Bill>
	            // </Interface>
	            Document xmlDocument = DocumentHelper.createDocument();
	            // add a root node and attribute (interface)
	            Element root = xmlDocument.addElement("Interface");
	            root.addAttribute("Sender", "OA");
	            root.addAttribute("Receiver", "ERP");
	            root.addAttribute("Billtype", "OAVoucherCreateNE_FA6");
	            // add sub-root node -> Bill
	            Element bill = root.addElement("Bill");
	            // add sub-element node -> BillHeader
	            Element billHeader = bill.addElement("BillHeader");
	            // add elements under BillHeader
	            Element doc_id = billHeader.addElement("doc_id"); //unique is ok ?
	            String tmp_flowid = getCellFormatValue(row.getCell(9)).trim();
	            doc_id.setText(tmp_flowid);
	            Element action = billHeader.addElement("action");
	            action.setText("I");
	            Element check = billHeader.addElement("check");
	            check.setText("1");
	            Element company = billHeader.addElement("company");
	            company.setText("890");
	            Element fiscal_year = billHeader.addElement("fiscal_year");
	            fiscal_year.setText(year); // use the finalization date
	            Element accounting_period = billHeader.addElement("accounting_period");
	            accounting_period.setText(month);  // use the finalization date
	            Element transaction_type = billHeader.addElement("transaction_type");
	            transaction_type.setText("BA1");
	            Element cash_account = billHeader.addElement("cash_account");
	            cash_account.setText("10020101");
	            // 报账系统凭证号  need to confirm with infor ERP
	            Element bzs_id = billHeader.addElement("bzs_id");
	            Integer tmp_bzs = Integer.parseInt(tmp_bzs_id) + i - 1;
	            bzs_id.setText(tmp_bzs.toString());
	            Element erp_id = billHeader.addElement("erp_id");
	            erp_id.setText("");
	            Element erp_ln = billHeader.addElement("erp_ln");
	            erp_ln.setText("");
	            Element currency = billHeader.addElement("currency");
	            currency.setText("CNY");
	            Element exchange_rate = billHeader.addElement("exchange_rate");
	            exchange_rate.setText("1.000000");
	            Element amount = billHeader.addElement("amount");  // need to be set from excel
	            String tmp_amount = getCellFormatValue(row.getCell(14)).trim();
	            amount.setText(tmp_amount);
	            Element dbcr = billHeader.addElement("dbcr");
	            dbcr.setText("2");
	            Element enterdate = billHeader.addElement("enterdate"); // need to be set from excel
	            String tmp_enterdate = getCellFormatValue(row.getCell(21)).trim();
	            enterdate.setText(tmp_enterdate);
	            Element enter = billHeader.addElement("enter"); // need to be set from excel (staff id)
	            String tmp_enter = getCellFormatValue(row.getCell(6)).trim();
	            enter.setText(tmp_enter);
	            Element vendor_cust = billHeader.addElement("vendor_cust");
	            vendor_cust.setText("");
	            Element xml_abstract = billHeader.addElement("abstract"); // need to be set from excel
	            String tmp_abstract = getCellFormatValue(row.getCell(1)).trim();
	            xml_abstract.setText(tmp_abstract);
	            Element payment_type = billHeader.addElement("payment_type");
	            payment_type.setText("7");   // need to confirm with zhangzhen
	            Element invoice_no = billHeader.addElement("invoice_no");
	            invoice_no.setText("");

	            

	            
	            // add sub-element node -> BillBody
	            Element billBody = bill.addElement("BillBody");
	            // entry 1 -> for the management fee excludes VAT
	            Element entry1 = billBody.addElement("Entry");
	            Element entry_id1 = entry1.addElement("entry_id");
	            entry_id1.setText("1");
	            Element account_code1 = entry1.addElement("account_code");
	            account_code1.setText("660215");
	            Element abstract21 = entry1.addElement("abstract2"); // need to be set from excel
	            abstract21.setText(tmp_abstract);
	            Element amount21 = entry1.addElement("amount2");   // need to be set from excel
	            String tmp_amountEXTax = getCellFormatValue(row.getCell(16)).trim();
	            amount21.setText(tmp_amountEXTax);
	            Element dbcr21 = entry1.addElement("dbcr2");
	            dbcr21.setText("1");
	            Element cvat1 = entry1.addElement("cvat");
	            cvat1.setText("Z");
	            Element cash_flow1 = entry1.addElement("cash_flow");
	            cash_flow1.setText("C09");
	            Element dim11 = entry1.addElement("dim1"); // need to convert from the cost center.
	            String tmp_costCenter = getCellFormatValue(row.getCell(11)).trim();
	            System.out.print(tmp_costCenter);
	            String costCenterCode = "";
	            try {
	            	costCenterCode = jsonObject.getString(tmp_costCenter);
	            } catch (JSONException e) {
	                System.out.println("未找到指定的费用中心!参考excel第" + i + "行 : " + tmp_costCenter);
	                System.out.println("或者检查费用中心配置文件");
	                e.printStackTrace();
	                return;
	            }
	            
	            dim11.setText(costCenterCode);
	            Element dim21 = entry1.addElement("dim2");
	            dim21.setText("");
	            Element dim31 = entry1.addElement("dim3");
	            dim31.setText("");
	            Element dim41 = entry1.addElement("dim4");
	            dim41.setText("");
	            Element dim51 = entry1.addElement("dim5");
	            dim51.setText("");
	            Element vendor_cust21 = entry1.addElement("vendor_cust2");
	            vendor_cust21.setText("");
	            Element payment_type21 = entry1.addElement("payment_type2"); 
	            payment_type21.setText("7");     // need to confirm with zhangzhen
	            Element invoice_no21 = entry1.addElement("invoice_no2");
	            invoice_no21.setText("");
	            
	            // entry 2
	            // built if there is any VAT in the form
	            if (!tmp_amountEXTax.equals(tmp_amount)) {
	            	Element entry2 = billBody.addElement("Entry");
		            Element entry_id2 = entry2.addElement("entry_id");
		            entry_id2.setText("2");
		            Element account_code2 = entry2.addElement("account_code");
		            account_code2.setText("2221010101");
		            Element abstract22 = entry2.addElement("abstract2");  // need to be set from excel
		            abstract22.setText(tmp_abstract);
		            Element amount22 = entry2.addElement("amount2");  // need to be set from excel
		            String tmp_amountVAT = getCellFormatValue(row.getCell(15)).trim();
		            amount22.setText(tmp_amountVAT);
		            Element dbcr22 = entry2.addElement("dbcr2");
		            dbcr22.setText("1");
		            Element cvat2 = entry2.addElement("cvat");
		            cvat2.setText("Z");
		            Element cash_flow2 = entry2.addElement("cash_flow");
		            cash_flow2.setText("C09");
		            Element dim12 = entry2.addElement("dim1"); // need to convert from the cost center.
		            dim12.setText(costCenterCode);
		            Element dim22 = entry2.addElement("dim2");
		            dim22.setText("");
		            Element dim32 = entry2.addElement("dim3");
		            dim32.setText("");
		            Element dim42 = entry2.addElement("dim4");
		            dim42.setText("");
		            Element dim52 = entry2.addElement("dim5");
		            dim52.setText("");
		            Element vendor_cust22 = entry2.addElement("vendor_cust2");
		            vendor_cust22.setText("");
		            Element payment_type22 = entry2.addElement("payment_type2");
		            payment_type22.setText("7");     // need to confirm with zhangzhen
		            Element invoice_no22 = entry2.addElement("invoice_no2");
		            invoice_no22.setText("");
	            }
	            
	            
	            StringWriter sw = new StringWriter();
	            XMLWriter XW = new XMLWriter(sw,format);
	            XW.write(xmlDocument);

	            // Test below
	            String documentStr = sw.toString(); 
	            strArr[i-1] = documentStr.replaceAll("\n\n","\n"); //即将两个换行符转换为一个。
	            //System.out.println("document string" + "\n" + strArr[i-1]);
	        }
	        // close the workbook
	        wb.close();
	        // loop the XML array and call the web service 
	        for (int i = 0; i < rowNum; i++) {
	        	try {   
	        		System.out.println(strArr[i]);
	                ServiceSoapProxy proxy = new ServiceSoapProxy();  
	                proxy.setEndpoint("http://172.18.67.24:9101/GAMC/esb/service?wsdl"); 
	                String ws_from = "BZS<>gamC2010#";
	                String ws_funcName = "OAVoucherCreateNE_FA6";
	                
	        		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");//设置日期格式
	                String ws_token = "gacneerp-"+df.format(new Date()).toString() + "-" + String.format("%011d", i);
	                System.out.print(ws_token);
	                String ws_parameters = strArr[i];
	                //String ws_parameters = xmlStr;
	                BooleanHolder ws_invokeResult = new BooleanHolder();
	                StringHolder ws_result = new StringHolder();
	           //     String wsResult = "";

	                proxy.invoke(ws_from,ws_token,ws_funcName,ws_parameters,ws_invokeResult,ws_result);
	                  
	                System.out.println("ws_invokeResult : " + ws_invokeResult.value);  
	                System.out.println("ws_result : " + ws_result.value);  
	                  
	            } catch (RemoteException e) {  
	                e.printStackTrace();  
	            } catch (Exception e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}  
	        }
            

        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        } 
    	
  
    }

	private static travelExpensiveLine travelExpensiveLine() {
		// TODO Auto-generated method stub
		return null;
	} 
	
	private static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
}
