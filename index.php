<?php
session_start();
?>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>LAO SYSTEM</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="./style.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
<!-- partial:index.partial.html -->
<div class="login">
	<h1>LAO SYSTEM </h1>
    <form action ="handling_froms.php" method="post">
    	<input id="username" type="text" name="Username" placeholder="please enter username" />
		<br>
        <input id="password" type="password" name="password" placeholder="Password"  />
        <button id="submit" name="login">Login</button>
    </form>
</div>
<script>
  $(document).ready(function() {
  //  $("#submit").click(function() {

   //     event.preventDefault(); // prevent PageReLoad

   //    var ValidEmail = $('#username').val() === 'admin'; // User validate
	//   var ValidPassword = $('#password').val() === 'admin'; // Password validate

    //    if (ValidEmail === true && ValidPassword === true) { // if ValidEmail & ValidPassword
       //     $('.valid').css('display', 'block');
           // window.location = "ordersimple/product.php"; // go to home.html
      //  }
      //  else {
           // $('.error').css('display', 'block'); // show error msg
      //  }
    //});
});
</script>

</body>
</html>


