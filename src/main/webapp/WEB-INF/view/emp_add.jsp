<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<%@ page contentType="text/html; charset=UTF-8"%>
<head>
<title>Add Employee</title>
</head>
<body>
<div class="w-50 p-3">
	<h2>Add Employee</h2>
	<hr />
		<form id="theform">
  <div class="form-group row">
    <label for="title" class="col-sm-2 col-form-label">Name</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="name" value="">
    </div>
  </div>
  <div class="form-group row">
    <label for="title" class="col-sm-2 col-form-label">Salary</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="salary" value="">
    </div>
  </div>
  <div class="form-group row">
    <label for="title" class="col-sm-2 col-form-label">Department</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="departmentId" value="">
    </div>
  </div>
	  <button type="submit" id="thesubmit" class="btn btn-primary">Submit</button>
	</form>
</div>
</body>
<script type="text/javascript">
$('#theform').on('submit', function(event){
    	event.preventDefault();
    	$("#thesubmit").val("wait..");
    	
	 	var book = {
            name: $("#name").val(),
            salary:$("#salary").val(),
            departmentId:$("#departmentId").val(),
        }

        $.ajax({
            url: '/api/employee/add',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(book),
            success: function (data) {
                window.location.replace("/employee/index");
            },
            fail: function () {
                $("#thesubmit").val("Submit");
            }
        });
});
</script>
</html>