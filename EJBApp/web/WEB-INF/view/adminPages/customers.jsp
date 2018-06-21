
<div class="tab-content">   
  <div class="tab-pane active" id="customers">
  	<table class="table">
            <thead>
              <tr>
                <th>id</th>
                <th>login</th>
                <th>f.name</th>
                <th>s.name</th>
                <th>phone</th>
                <th>street</th>
                <th>house</th>
                <th>apartment</th>
              </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${notBannedCustomers}" varStatus="status">                                                                       
                        <tr>
                        <td>${customer.id}</td>
                        <td>${customer.login}</td>                        
                        <td>${customer.firstName}</td>
                        <td>${customer.secondName}</td>
                        <td>${customer.phone}</td>
                        <td>${customer.address.getStreet()}</td>
                        <td>${customer.address.getHouse()}</td>
                        <td>${customer.address.getApartment()}</td>                                              
                        <td>
                            <form name="BanForm" method = "POST" action ="admin">
                                <input type="hidden" name = "command"  value="addToBanList">
                                <input type="hidden" name = "customerId"  value=${customer.id}>                            
                                <button type="submit" class="btn btn-xs btn-danger">Ban</button>                            
                            </form>                                
                        </td>                                                                            
                        </tr>                                              

                </c:forEach>                
            </tbody>
          </table>
  </div>
</div>
