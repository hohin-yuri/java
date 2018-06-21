  
<h1 class="text-muted"><%out.write(MessageManager.getInstance().getProperty(MessageManager.CART));%></h1>
 <table class="table table-striped">
   <thead>
     <tr>       
       <th>#</th>
       <th><%out.write(MessageManager.getInstance().getProperty(MessageManager.DENOMINATION));%></th>              
       <th><%out.write(MessageManager.getInstance().getProperty(MessageManager.WEIGHT));%></th>
       <th><%out.write(MessageManager.getInstance().getProperty(MessageManager.CALORIES));%></th>       
       <th><%out.write(MessageManager.getInstance().getProperty(MessageManager.PRICE));%></th>       
       <th><%out.write(MessageManager.getInstance().getProperty(MessageManager.COUNT));%></th>                     
     </tr>
   </thead>
   <tbody>
     <tr>
        <c:if test="${sessionScope.cart.getItems()!=null}">                      
        <c:forEach var="item" items="${sessionScope.cart.items}" varStatus="counter">                                             
         <tr>  
           <td>${counter.count}</td>
           <td>${item.getProduct().getTitle()}</td>                               
           <td>${item.getProduct().getWeight()}</td>
           <td>${item.getProduct().getCalories()}</td>
           <td>${item.getProduct().getPrice()}</td>
           
           <form name="updateCartForm" method="POST" action="cart">
                <input type="hidden" name="command" value="updateCart"/>    
                <input type="hidden" name="productId" value=${item.getProduct().getId()}/>    
                <td> <input type="text" name="quantity" class="input-sm" id="name" placeholder=${item.getQuantity()}></td>                               
                <td> <button type="submit" class="btn btn-sm btn-danger" style="margin-left: 20px"><%out.write(MessageManager.getInstance().getProperty(MessageManager.SAVE));%></button></td>                               
           </form>  
         </tr>                   
        </c:forEach>  
         <tr>
            <td></td>                   
            <td></td>
            <td></td>
            <td></td>            
            <td><c:if test="${sessionScope.cart.getTotal()!=0}">
                    ${sessionScope.cart.getTotal()}
                </c:if>
            </td>
            <td>  
                <form name="PurchaseForm" method="POST" action="cart">
                    <input type="hidden" name="command" value="purchase">                
                    <button type="submit" class="btn btn-sm btn-danger" style="margin-left: 20px"><%out.write(MessageManager.getInstance().getProperty(MessageManager.BUY));%></button>                
                </form>
               
            </td>           
            <td>
                <form name="UncartAllForm" method="POST" action="cart">
                    <input type="hidden" name="command" value="uncartAll">                
                    <button type="submit" class="btn btn-sm btn-danger" style="margin-left: 20px"><%out.write(MessageManager.getInstance().getProperty(MessageManager.UNCART_ALL));%></button>                
                </form>
            </td>            
          </tr>          
        </c:if>             
     <tr>
       <td><a href="index?command=getCategories" type="button" class="btn btn-info"><i class="fa fa-angle-double-left"></i><%out.write(MessageManager.getInstance().getProperty(MessageManager.BACK_TO_STORE));%></a>
       </td>       
     </tr>
   </tbody>
 </table>

