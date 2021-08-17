<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<%@ page contentType="text/html; charset=UTF-8"%>
<head>
<title>Update Department</title>
</head>
<body>
<div class="w-50 p-3">
	<h2>Update Department</h2>
	<hr />
		<form id="theform">
  <div class="form-group row">
  	<label for="title" class="col-sm-2 col-form-label">ID</label>
    <div class="col-sm-10">
      <input type="text" class="form-control-text" id="id" value="">
    </div>
    <label for="title" class="col-sm-2 col-form-label">Name</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="name" value="">
    </div>
  </div>
	  <button type="submit" id="thesubmit" class="btn btn-primary">Submit</button>
	</form>
</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	var pathname = window.location.pathname;
	
	$.ajax({
        url: '/api' + pathname,
        type: 'get',
        contentType: 'application/json',
        success: function (data) {
        	$("#id").val(data.result.id);
        	$("#name").val(data.result.name);
        }
    });
});

$('#theform').on('submit', function(event){
    	event.preventDefault();
    	$("#thesubmit").val("wait..");
    	
	 	var book = {
	 		id: $("#id").val(),
            name: $("#name").val()
        }

        $.ajax({
            url: '/api/department/update',
            type: 'put',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(book),
            success: function (data) {
                window.location.replace("/department/index");
            },
            fail: function () {
                $("#thesubmit").val("Submit");
            }
        });
});
</script>
</html>