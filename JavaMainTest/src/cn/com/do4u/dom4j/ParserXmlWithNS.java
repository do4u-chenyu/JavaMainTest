package cn.com.do4u.dom4j;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Copyright: Copyright (c) 2013
 * Company: 中科软科技股份有限公司
 *
 * @author ChenYu
 * @Date 15-1-4 下午1:41
 */
public class ParserXmlWithNS {

public static void main(String[] args) throws Exception{
    SAXReader saxReader = new SAXReader();
    Map namespace = new HashMap();
    namespace.put("topay","webservice.ppbalancing.sinosoft.com");
    namespace.put("soapenv","http://schemas.xmlsoap.org/soap/envelope/");
    namespace.put("ns3","http://dto.webservice.ppbalancing.sinosoft.com");
    saxReader.getDocumentFactory().setXPathNamespaceURIs(namespace);
    Document document = saxReader.read(new File("C:\\IDEA_Project\\MyGitHub\\JavaMainTest\\JavaMainTest\\temp\\cardNotest.xml"));
    Node element = document.selectSingleNode("/soapenv:Envelope/soapenv:Body/topay:toPaymentProxy/arg0/ns3:cardNo");
    String text = element.getText();
    System.out.println(text);
}

}
