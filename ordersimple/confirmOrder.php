<?php
	include('conn.php');

	$id=$_GET['purchase'];

//	$pname=$_POST['pname'];
	//$category=$_POST['category'];
	//$price=$_POST['price'];

	//$sql="select * from product where productid='$id'";
	//$query=$conn->query($sql);
	//$row=$query->fetch_array();

	//$fileinfo=PATHINFO($_FILES["photo"]["name"]);

	//if (empty($fileinfo['filename'])){
	//	$location = $row['photo'];
	//}
	//else{
	//	$newFilename=$fileinfo['filename'] ."_". time() . "." . $fileinfo['extension'];
	//	move_uploaded_file($_FILES["photo"]["tmp_name"],"upload/" . $newFilename);
	//	$location="upload/" . $newFilename;
	//}

	$sql="update purchase set STATUS='CONFIRMED' where purchaseid='$id'";
	$conn->query($sql);

	header('location:sales.php');
?>