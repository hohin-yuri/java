    <div class="jumbotron"> 
        <c:choose>
              <c:when test="${not empty requestScope.error}">           
                  <h1>${requestScope.error}</h1>
              </c:when>          
              <c:otherwise>
                  <h1><%out.write(MessageManager.getInstance().getProperty(MessageManager.LOGIN_FORM));%></h1>
              </c:otherwise>    
        </c:choose>        
        
        <p class="lead">
        <form name="loginForm" method="POST" action="index">
            <input type="hidden" name="command" value="userLogin"/>
            <h3><%out.write(MessageManager.getInstance().getProperty(MessageManager.LOGIN));%></h3>               
            <input type="text" name="login" value="">            
            <h3><%out.write(MessageManager.getInstance().getProperty(MessageManager.PASSWORD));%></h3>            
            <input type="password" name="password" value="">  
            <br> <br>
            <button type="submit" class="btn btn-success" data-toggle="modal" data-target="#myModal"><%out.write(MessageManager.getInstance().getProperty(MessageManager.ENTER));%></button>         
            <a href="index?command=registerPage">
                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal"><%out.write(MessageManager.getInstance().getProperty(MessageManager.REGISTER));%></button>                     
            </a>
        </form>     
    </div>
    