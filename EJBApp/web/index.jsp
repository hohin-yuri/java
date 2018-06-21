       
<div class="jumbotron">
 <h1><%out.write(MessageManager.getInstance().getProperty(MessageManager.THANKS));%></h1>           
</div>
         
<div class="row">        
    <c:forEach var="category" items="${categories}">            
        <div class="col-lg-6">                                
            <h2>${category.title}</h2>                  
            <a class="btn btn-default" href="index?command=getProducts&category=${category.id}" role="button">                                   
                 <img class="img-thumbnail" src="${initParam.categoryImagePath}${category.title}.jpg"
                 alt="${category.title}">                                                
            </a>
        </div>                         
    </c:forEach>  
</div>

 
