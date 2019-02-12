<?php
	 include 'config.php';
	 
	 // Check whether username or password is set from android	
     if(isset($_POST['username']) && isset($_POST['password']))
     {
		  // Innitialize Variable
		  $result='';
	   	  $username = $_POST['username'];
          $password = $_POST['password'];
		  
		  // Query database for row exist or not
          $sql = "insert into tbl_login (email,password) values ('$username','$password')";
          $res = mysqli_query($conn,$sql);
           $rowcount=mysqli_num_rows($res);
         if($rowcount>0)
{
echo "Success";
  	}
else
{
echo "Failure";
}
	}
?>
