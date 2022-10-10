package com.example.xml.test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/9/20-22:49
 * @Description:
 */
public class ParseXMLByDom4jTest {

    /**
     * 解析SQL映射配置文件
     */
    @Test
    public void testParseSqlMapperXML() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document mapperDocument = reader.read(ClassLoader.getSystemResourceAsStream("CarMapper.xml"));
        Element mapperElement = mapperDocument.getRootElement();
        System.out.println("命名空间: " + mapperElement.attributeValue("namespace"));

        // 获取mapper下的所有子标签
        List<Element> elements = mapperElement.elements();
        elements.forEach(elem -> {
            System.out.print("id=" + elem.attributeValue("id"));
            System.out.print(" resultType=" + elem.attributeValue("resultType"));
            String mybatisSql = elem.getTextTrim();
            String sql = mybatisSql.replaceAll("#\\{[0-9A-Za-z_$]*}", "?");
            System.out.println(" Sql语句: " + sql);
        });
    }

    /**
     * 解析MyBatis的核心配置文件
     */
    @Test
    public void testParseMyBatisConfigXML() throws DocumentException {
        // 创建SAXReader对象
        SAXReader reader = new SAXReader();
        // 都XML文件
        Document xmlDocument = reader.read(ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml"));

        /*// 获取文档的根标签
        Element rootElement = xmlDocument.getRootElement();
        System.out.println(rootElement.getName());*/

        // 获取default默认的环境id
        // xpath 是做标签路径匹配的，能够让我们快速定位XML文件中的元素，/ 代表根
        String xpath = "/configuration/environments";
        Element environments = (Element) xmlDocument.selectSingleNode(xpath);
        String defaultEnvironmentId = environments.attributeValue("default");
        System.out.println("默认环境: " + defaultEnvironmentId);

        // 根据默认的环境id来获取对应的具体的environment环境标签
        xpath = "/configuration/environments/environment[@id='"+ defaultEnvironmentId +"']";
        Element defaultEnvironment = (Element) xmlDocument.selectSingleNode(xpath);

        // 获取environment标签下的transactionManager标签
        Element transactionalManager = defaultEnvironment.element("transactionManager");
        System.out.println("事务管理器类型: " + transactionalManager.attributeValue("type"));

        // 获取dataSource标签
        Element dataSource = defaultEnvironment.element("dataSource");
        System.out.println("数据源的类型: " + dataSource.attributeValue("type"));

        // 获取dataSource下的所以子标签(property)
        List<Element> propertyElements = dataSource.elements();
        propertyElements.forEach(propertyElem -> {
            System.out.println(propertyElem.attributeValue("name") + "=" + propertyElem.attributeValue("value"));
        });

        System.out.println("==============分割线================");
        System.out.println("解析mapper标签");
        // 获取所有的mapper标签
        // 不想从根下获取，想获取所有的mapper标签，可以像下面这样写
        xpath = "//mapper";
        List<Node> mapperElements  = xmlDocument.selectNodes(xpath);
        mapperElements.forEach(mapper -> {
            Element mapperElem = (Element) mapper;
            System.out.println("SQL映射XML文件: " + mapperElem.attributeValue("resource"));
        });
    }
}
