# hibernateDemo
Connecting Jersey rest api with mysql db using hibernate jpa

create a persistence.xml file in resources/META-INF directory of src for db credentials

```
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.0"
    xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">

   <persistence-unit name="hibernateDemo">
   		<provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>

        <class>com.vinayakborhade.hibernateDemo</class>

        <properties>
            <property name="javax.persistence.jdbc.driver"
                      value="com.mysql.jdbc.Driver" />

            <property name="javax.persistence.jdbc.url"
                      value="--your db url goes here--" />
		<!-- sample url for mysql using jdbc driver  
			jdbc:mysql://localhost:3306/test4		 -->

            <property name="javax.persistence.jdbc.user"
                      value="--your db user name goes here--" />

            <property name="javax.persistence.jdbc.password"
                      value="--your password goes here--" />
<!-- 
            <property name="hibernate.show_sql"
                      value="true" />
            <property name="hibernate.hbm2ddl.auto"
                      value="update" />
                      -->
        </properties>
   </persistence-unit>
   
</persistence>

```
