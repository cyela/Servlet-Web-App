<<<<<<< HEAD
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans 
http://www.springframework.org/schema/beans/spring-beans.xsd">


<bean id="db" class="org.apache.commons.dbcp.BasicDataSource">
<property name="driverClassName" value="oracle.jdbc.driver.OracleDriver" />
<property name="url" value="jdbc:oracle:thin:@localhost:1521:xe" />
<property name="username" value="hr" />
<property name="password" value="hr" />
</bean>

<bean id="sf" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
  <property name="dataSource" ref="db"></property>
  
  <property name="mappingResources">
  <list>
  <value>EmpHibernate</value>
  </list>
  </property>
  
  <property name="hibernateProperties">
    <props>
  <prop key="hibernate.dialect">org.hibernate.dialect.Oracle10gDialect</prop>
    <prop key="hbm2ddl.auto">update</prop>
    </props>
   </property>
</bean>

<bean id="jt" class="org.springframework.orm.hibernate3.HibernateTemplate">
<property name="sessionFactory" ref="sf" />
</bean>

<bean id="ed" class="EmpDao">
<property name="ht" ref="jt" />  
</bean>

</beans>
=======
### Network Based Web Application
#### Its a E-Commerce Shopping Application built for both Admin and Customer operations. Following are the operations that can be performed by the Respective User
* Admin
  * Adding products
  * Updating products
  * Deleting products
* Customer
  * Registering into System
  * Login into Website
  * Updating Address
  * Adding product to Cart
  * Updating/ Deleting the Product
  * Placing the order

* Technologies: 
  * Dynamic Web Application
  * Apache Tomcat
  * Servlet
  * JSP
  * JSTL 
  * MySQL
>>>>>>> 35461489789e93dbb894fd0c5ebef9577ad9d928
