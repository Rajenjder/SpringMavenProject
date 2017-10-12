<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 

<style type="text/css">
    .bs-example{
    	margin: 10px;
    }
	/* Fix alignment issue of label on extra small devices in Bootstrap 3.2 */
    .form-horizontal .control-label{
        padding-top: 7px;
    }
</style>
<script>
var app = angular.module('myapp', []);
app.controller('myappcontroller', function($scope, $http,$window) {
  $scope.responseList = []
 
  $scope.userform = {
	  id : "",  
	  name : "",
	  salary: "",
	  designation : ""
   };
  getUserDetails();

  function getUserDetails() {//$window.alert("getUserDetails==>>");
  $http({
    method : 'GET',
    url : '/SpringMVC/viewemp'
   }).then(function successCallback(response) {
    
   $scope.responseList = response.data;
   //$window.alert("data-->>"+response.data)
   }, function errorCallback(response) {
  // $window.alert("response.statusText-->>"+response.statusText)
    console.log(response.statusText);
   });
  }
   
   $scope.addEmp = function() 
  {//$window.alert("addEmp==>>"+angular.toJson($scope.userform));
  
    var response = $http.post('/SpringMVC/save', angular.toJson($scope.userform));
			response.success(function(data, status, headers, config) {
			//$window.alert("data-->>"+data);
				$scope.responseList = data;
				//getUserDetails();
			});
			response.error(function(data, status, headers, config) {
				$window.alert( "Exception details: " + JSON.stringify({data: data}));
			});
			
			clearForm();
  }  
  
  $scope.editUser = function(user) 
 {
 $window.alert( "responseList.salary-->>"+user.salary);
 $window.alert( "responseList.name-->>"+user.name);
  $scope.userform.salary = user.salary;
    $scope.userform.name = user.name;
    $scope.userform.designation = user.designation;
 
 
 disableName();
 } 
  function disableName()
  {
    document.getElementById("id").disabled = true;
  }
    /* $scope.addEmp = function() 
  {
    $http({
    method : 'POST',
    url : '/SpringMVC/save',
    data : angular.toJson($scope.userform),
    headers : {
    'Content-Type' : 'application/json'
    }
    }).then(function successCallback(response) {//.then( getUserDetails(),clearForm()).success(function(data){
        //$scope.responseList= data
        getUserDetails();
      });
      
  }   */
 
  
  
    /* $scope.addEmp = function() 
  {$window.alert("addEmp==>>"+angular.toJson($scope.userform));
  
    $http.post({
    method : 'POST',
    headers: {'Content-Type': 'application/json'},
    url : '/SpringMVC/save',
    data : angular.toJson($scope.userform),
    dataType : 'json'     
    
    }).then(function successCallback(response) {$window.alert("*********");
   $scope.responseList = response.data;
   //$window.alert("data-->>"+response.data)
   }, function errorCallback(response) {
  // $window.alert("response.statusText-->>"+response.statusText)
  $window.alert("*****error****");
    $window.alert(response.statusText);
   });
  }  */
  
  function clearForm() {//$window.alert("clearForm==>>");
  $scope.userform.id = "";
  $scope.userform.salary = "";
    $scope.userform.name = "";
    $scope.userform.designation = "";
    document.getElementById("id").disabled = false;
  };
  
  }); 
 
 </script>
</head>
<body ng-app="myapp" ng-controller="myappcontroller">
<!-- <a href="empform">Add Employee</a>   -->
<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Add Employee</a>  

<!-- <a >View Employees</a> -->

<div id="collapseOne" class="panel-collapse collapse ">
<div class="bs-example">
<form class="form-horizontal">
	<div class="form-group">
	    <label  class="control-label col-xs-2">ID:</label>
	    <div class="col-xs-6 ">
	        <input type="text" name="id" ng-model="userform.id" class="form-control" id="id" placeholder="ID">
	    </div>
	</div>
	<div class="form-group">
	    <label  class="control-label col-xs-2">Name:</label>
	    <div class="col-xs-6 ">
	        <input type="text" name="name" ng-model="userform.name" class="form-control" id="userform.name" placeholder="Name">
	    </div>
	</div>
	<div class="form-group">
	    <label  class="control-label col-xs-2">Salary:</label>
	    <div class="col-xs-6 ">
	        <input type="text" name="salary" ng-model="userform.salary" class="form-control" id="userform.salary" placeholder="Salary">
	    </div>
	</div>
	<div class="form-group">
	    <label  class="control-label col-xs-2">Designation:</label>
	    <div class="col-xs-6 ">
	        <input type="text" name="designation" ng-model="userform.designation" class="form-control" id="userform.designation" placeholder="Designation">
	    </div>
	</div>
	<div class="form-group">
            <div class="col-xs-offset-2 col-xs-6">
                <button type="button" ng-click="addEmp()" value="Save" class="btn btn-primary">Save / Update</button>
            </div>
        </div>

</form>
</div>
</div>





<div class="table-responsive">
<table class="table table-bordered table-striped table-hover"> 
 <thead> 
<tr><th>Id</th><th>Name</th><th>Salary</th><th>Designation</th><th>Actions</th></tr></thead>
<tbody>
<tr ng-repeat="listitems in responseList">
	  <td>{{listitems.id}}</td>
	  <td>{{listitems.name}}</td>
      <td>{{listitems.salary}}</td>
      <td>{{listitems.designation}}</td>
      <td><a ng-click="editUser(listitems)">Edit</a> | <a href="#">Delete</a></td>
</tr>
</tbody>

</table>   
</div>





</body>
</html>