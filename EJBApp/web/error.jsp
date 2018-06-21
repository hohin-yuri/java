    <div class="jumbotron">
	<div class="hero-unit center">
            <h1>Error!</h1> 
            <br>
            <h1><small><font face="Tahoma" color="red">${requestScope.error}</font></small></h1>   
            <c:if test="${requestScope['javax.servlet.error.message']!=null}">                                                     
                <li>Exception message: <c:out value="${requestScope['javax.servlet.error.message']}" /></li>
                <li>Request URI: <c:out value="${requestScope['javax.servlet.error.request_uri']}" /></li>
                <li>Servlet name: <c:out value="${requestScope['javax.servlet.error.servlet_name']}" /></li>
                <li>Status code: <c:out value="${requestScope['javax.servlet.error.status_code']}" /></li>
            </c:if>
          <br>          
        </div>
    </div>
     