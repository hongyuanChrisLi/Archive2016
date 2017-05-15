<%@ include file="mgrt-header.jsp" %>
			
		<script type="text/javascript" src="JS/jquery-2.2.4.min.js"></script>
		<script type="text/javascript">
				 var auto = setInterval( function ()
				 {
							$('#monitor').load('log-loader.jsp').fadeIn("slow");
				 }, 2000); // refresh every 5000 milliseconds
		</script>
			
			<DIV id=contentwrapper>
				<ul>
					<li><a href="mgrt-req.jsp">Home</a></li>
					<li><a href="system.jsp">System</a></li>
					<li><a class=active_item href="#">Monitor</a></li>
				</ul>
				
				<DIV class=innertube>
					<br>
					<INPUT type=Submit value=Cancel class=submit> 
					<br>
					<DIV id=rcorners2>
							<DIV id = 'monitor'></DIV>
					</DIV>
				</DIV>
		 </DIV>	

<%@ include file="footer.jsp" %>