
<div class="tab-content"> 
  <div class="tab-pane active" id="orders">
  	<table class="table">
            <thead>
              <tr>                
                <th>id</th>
                <th>date</th>
                <th>customer id</th>                
                <th>customer fisrt name</th>                
                <th>customer second name</th>                
                <th>product title</th>
                <th>quantity</th>                
              </tr>
            </thead>
            <tbody> 
            <c:if test="${not empty orders}">       
                <c:forEach var="order" items="${orders}">            
                    <td>${order.getId()}</td>
                    <td>${order.getDate()}</td>
                    <td>${order.getCustomerId()}</td>
                    <td>${order.getCustomerFirstName()}</td>
                    <td>${order.getCustomerSecondName()}</td>
                    <td></td> <td></td>
                    <c:forEach var="item" items="${order.getProducts()}" >            
                        <tr> 
                            <td></td> <td></td> <td></td> <td></td> <td></td>
                            <td>${item.getProduct().getTitle()}</td>
                            <td>${item.getQuantity()}</td>
                        </tr>                         
                    </c:forEach>                     
                </c:forEach> 
            </c:if>
            </tbody>
          </table>
  </div>
</div>
