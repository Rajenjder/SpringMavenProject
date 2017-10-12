<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
 <%@ page import="java.util.ArrayList" %> 
 <%@ page import="java.util.List" %> 
 <%@ page import="com.beans.Emp" %>
 <html >
 <head></head>
 <body ng-app="myapp">
 
<table border="2" width="70%" cellpadding="2">  
<tr><th>Id</th><th>Name</th><th>Salary</th><th>Designation</th></tr>
<tr ng-repeat="listitems in jsonEmpList">
	  <td>{{listitems.id}}</td>
	  <td>{{listitems.name}}</td>
      <td>{{listitems.salary}}</td>
      <td>{{listitems.designation}}</td>
</tr>

</table> 


<%-- <table border="2" width="70%" cellpadding="2">  
<tr><th>Id</th><th>Name</th><th>Salary</th><th>Designation</th></tr>
   <c:forEach var="emp" items="${jsonEmpList}">   
   <tr>  
   <td>${emp.id}</td>  
   <td>${emp.name}</td>  
   <td>${emp.salary}</td>  
   <td>${emp.designation}</td>  
   </tr>  
   </c:forEach>  
   </table> --%>
   
   
<script type="text/javascript" src="../webjars/angularjs/1.3.14/angular.min.js" ></script>
<script type="text/javascript" src="../webjars/angularjs/1.3.14/angular.js" ></script>
<script src="../webjars/angularjs/1.3.14/angular-resource.js" type="text/javascript"></script>
<script src="../webjars/angularjs/1.3.14/angular-route.js" type="text/javascript"></script>


</body> 
</html>   