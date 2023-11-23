<?php include('header.php'); ?>
<body>
<?php include('navbar.php'); ?>
<div class="container">
	<h1 class="page-header text-center">Order</h1>
	<table class="table table-striped table-bordered">
		<thead>
			<th>Date</th>
			<th>Customer</th>
			<th>App name</th>
			<th>Price</th>
			<th>Details</th>
			<th>Address</th>
			<th>Status</th>

		</thead>
		<tbody>
			<?php 
				$sql="select * from purchase order by purchaseid desc";
				$query=$conn->query($sql);
				while($row=$query->fetch_array()){
					?>
					<tr>
						<td><?php echo date('M d, Y h:i A', strtotime($row['date_purchase'])) ?></td>
						<td><?php echo $row['customer']; ?></td>
						<td><?php echo $row['AppName']; ?></td>
						<td class="text-right">&#8369; <?php echo number_format($row['total'], 2); ?></td>
						<td><a href="#details<?php echo $row['purchaseid']; ?>" data-toggle="modal" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> View </a>
							<?php include('sales_modal.php'); ?>
							
							<td><?php echo $row['address']; ?></td>



						</td>
						
						<?php if($row['STATUS'] == null){?>
						<td><a href="#confirmOrder<?php echo $row['purchaseid']; ?>" data-toggle="modal" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-ok"></span> Confirm </a>
						<?php include('order_modal.php'); ?>
						<?php }else{ ?>
						<td> Confirmed </td>
						<?php } ?>
					

						</td>

					</tr>
					<?php
					
				}
				
			?>
			
		</tbody>
	</table>
</div>
</body>
</html>