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
        <form name="registerForm" method="POST" action="index">
            <input type="hidden" name="command" value="userRegister"/>
            <h3><%out.write(MessageManager.getInstance().getProperty(MessageManager.FIRST_NAME));%></h3>               
            <input type="text" name="firstName" value="">            
            <h3><%out.write(MessageManager.getInstance().getProperty(MessageManager.SECOND_NAME));%></h3>               
            <input type="text" name="secondName" value="">            
            <h3><%out.write(MessageManager.getInstance().getProperty(MessageManager.PHONE));%></h3>               
            <input type="text" name="phone" value="">            
            <h3><%out.write(MessageManager.getInstance().getProperty(MessageManager.STREET));%></h3>               
            <input type="text" name="street" value="">            
            <h3><%out.write(MessageManager.getInstance().getProperty(MessageManager.HOUSE));%></h3>               
            <input type="text" name="house" value="">            
            <h3><%out.write(MessageManager.getInstance().getProperty(MessageManager.APARTMENT));%></h3>               
            <input type="text" name="apartment" value="">            
            <h3><%out.write(MessageManager.getInstance().getProperty(MessageManager.LOGIN));%></h3>               
            <input type="text" name="login" value="">            
            <h3><%out.write(MessageManager.getInstance().getProperty(MessageManager.PASSWORD));%></h3>            
            <input type="password" name="password" value="">  
            <br> <br>            
            <button type="submit" class="btn btn-success" data-toggle="modal" data-target="#myModal"><%out.write(MessageManager.getInstance().getProperty(MessageManager.REGISTER));%></button>         
        </form>     
    </div>
    