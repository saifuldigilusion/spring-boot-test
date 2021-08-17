<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css"  crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>

<%@ page contentType="text/html; charset=UTF-8"%>
<head>
<title>List Department</title>
</head>
<body>
	<div class="w-50 p-3">
	<h2>List Department</h2>
	<a href="/book/index">Book</a> | <a href="/department/index">Department</a> | <a href="/employee/index">Employee</a>
	<hr />
	<table id="books" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Action</th>
			</tr>
		</thead>
	</table>
	<button id="new" onclick="window.location.replace('/department/add');">New</button>
	</div>
</body>
<script type="text/javascript">
function deleteItem(id) {
	$.ajax({
        url: '/api/department/delete/' + id,
        type: 'delete',
        contentType: 'application/json',
        success: function (data) {
            window.location.replace("/department/index");
        }
    });
}

$(document).ready(function() {
    $('#books').DataTable( {
        "ajax": { "url": "/api/department/list",
        	"dataSrc": "result"},
        "columns": [
        	{"data": "id"},
        	{"data": "name"},
        	{"data": "id", "render": function ( data, type, row, meta ) {
        		return "<button onclick=\"window.location.replace('/department/detail/" + data + "');\">Edit</button> <button onclick=deleteItem(" + data + ");>Delete</button>";
        		}
        	}
        	],
        "paging":   false,
        "ordering": false,
        "info":     false
    } );
} );
</script>
</html>