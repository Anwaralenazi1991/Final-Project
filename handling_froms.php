<?php 
session_start();
include('ordersimple/header.php');
$username = $_POST['Username'];
$password = $_POST['password'];

 ?>

<?php 
		$sql="select * from resturants where name='$username'";
		$query=$conn->query($sql);
		if($row=$query->fetch_array()){
		
	

			if($row["password"] == $password){
			    $_SESSION["userId"] = $row["id"];
				echo "<script type='text/javascript'>window.top.location='ordersimple/product.php';</script>"; exit;
			
			}
		}else{
			echo "Username and Password is Wrong..";
		}
						
					
					
			
					?>
