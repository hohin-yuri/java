     
      <div class="row">               
        <h2>${requestScope.error}</h2>
        <c:forEach var="product" items="${products}">            
            <div class="col-lg-6" >                                    
                    <h2>${product.title}</h2>                           
                    <img class="img-thumbnail" src="${initParam.productImagePath}${product.title}.jpg"
                         alt="${product.title}" background-size: cover>                                                        
                <p>${product.description} (${product.weight},${product.calories} <%out.write(MessageManager.getInstance().getProperty(MessageManager.CALORIES));%>)</p>                
                <p>   
                      <c:choose>                            
                        <c:when test="${not empty sessionScope.customer}">  
                             <form name="UncartAllForm" method="POST" action="cart">
                                <input type="hidden" name="command" value="addToCart">                
                                <input type="hidden" name="product" value=${product.id}>                
                                <button type="submit" class="btn btn-success"><%out.write(MessageManager.getInstance().getProperty(MessageManager.ADD));%></button>
                             </form>
                             
                             <form name="SaveProductForm" method="POST" action="cart">
                                <input type="hidden" name="command" value="saveProduct">                
                                <input type="hidden" name="product" value=${product.id}>                
                                <button type="submit" class="btn btn-success"><%out.write(MessageManager.getInstance().getProperty(MessageManager.SAVE));%><i class="fa fa-shopping-cart"></i></button>
                             </form>                                                              
                        </c:when>                    
                      </c:choose>                         
                      <h3><label for="id"><%out.write(MessageManager.getInstance().getProperty(MessageManager.PRICE));%>: ${product.price}</label></h3>
                </p>                
            </div>                         
        </c:forEach>            
      </div>
