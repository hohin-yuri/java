
<div class="tab-content"> 
  <div class="tab-pane active" id="categories">
      <h2>${requestScope.error}</h2>
  	<h3>Categories</h3>
          <table class="table">
            <thead>
              <tr>
                <td>ID</td>
                <td>Title</td>                                           
              </tr>
            </thead>
            <tbody>
              <c:forEach var="category" items="${categories}">  
              <tr>
                <td>${category.id}</td>
                <td>${category.title}</td>                
              </tr>
              </c:forEach> 
            </tbody>
          </table>
        <h3>Products</h3>
        <table class="table">
            <thead>
              <tr>
                <td>Category id</td>
                <td>Title</td>                            
                <td>Description</td>
                <td>Weight</td>
                <td>Price</td>
                <td>Calories</td>                
              </tr>
            </thead>
            <tbody>
              <c:forEach var="product" items="${products}">  
              <tr>
                <td>${product.categoryId}</td>
                <td>${product.title}</td>
                <td>${product.description}</td>
                <td>${product.weight}</td>
                <td>${product.price}</td>
                <td>${product.calories}</td>                               
                <td>                	
                
                     <form name="product" method="POST" action="admin">
                        <input type="hidden" name="command" value="deleteProduct"/>  
                        <input type="hidden" name="productId" value=${product.id}/>  
                        <button type="submit" class="btn btn-xs btn-danger" data-toggle="modal" data-target="#myModal" style="margin-left: 42%; margin-bottom: 15px">Delete</button>         
                    </form>                    
                </td>
              </tr>
              </c:forEach> 
            </tbody>
          </table>
          
          <h3>Add product</h3>
            <form name="product" method="POST" action="admin">
               <input type="hidden" name="command" value="addProduct"/>
                    <div class="form-group">
                      <label for="name">Id</label>
                      <input type="text" name="categoryId" class="form-control" id="name" placeholder="Category id">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputEmail1">Title</label>
                      <input type="text" name="title" class="form-control" id="exampleInputEmail1" placeholder="Product title">            
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">Description</label>
                      <input type="text" name="description" class="form-control" id="exampleInputPassword1" placeholder="Description">
                    </div>
                    <div class="form-group">
                      <label for="adress">Weight</label>
                      <input type="text" name="weight" class="form-control" id="adress" placeholder="Product weight">
                    </div>
                    <div class="form-group">
                      <label for="adress">Price</label>
                      <input type="text" name="price" class="form-control" id="adress" placeholder="Product price">
                    </div>
                    <div class="form-group">
                      <label for="adress">Calories</label>
                      <input type="text" name="calories" class="form-control" id="adress" placeholder="Product calories">
                    </div>                
                    <button type="submit" class="btn btn-success" data-toggle="modal" data-target="#myModal" style="margin-left: 42%; margin-bottom: 15px"><%out.write(MessageManager.getInstance().getProperty(MessageManager.SUBMIT));%></button>         
            </form>
     </div>
   </div>
