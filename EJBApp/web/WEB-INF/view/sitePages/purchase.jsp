<h2>${requestScope.error}</h2>
<form role="form" name="form" method="POST" action="cart">
  <input type="hidden" name="command" value="confirm"/>
  <div class="form-group">
    <label for="name"><%out.write(MessageManager.getInstance().getProperty(MessageManager.NAME));%></label>
    <input type="text" name="firstName" class="form-control" id="name" disabled placeholder=${sessionScope.customer.getFirstName()}>
  </div>
  <div class="form-group">
    <label for="exampleInputEmail1"><%out.write(MessageManager.getInstance().getProperty(MessageManager.SECOND_NAME));%></label>
    <input type="text" name="secondName" class="form-control" id="exampleInputEmail1"  disabled placeholder=${sessionScope.customer.getSecondName()}>            
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1"><%out.write(MessageManager.getInstance().getProperty(MessageManager.PHONE));%></label>
    <input type="text" name="phone" class="form-control" id="exampleInputPassword1" placeholder="Your phone">
  </div>
  <div class="form-group">
    <label for="adress"><%out.write(MessageManager.getInstance().getProperty(MessageManager.STREET));%></label>
    <input type="text" name="street" class="form-control" id="adress" placeholder="Your street">
  </div>
  <div class="form-group">
    <label for="adress"><%out.write(MessageManager.getInstance().getProperty(MessageManager.HOUSE));%></label>
    <input type="text" name="house" class="form-control" id="adress" placeholder="Your house">
  </div>
  <div class="form-group">
    <label for="adress"><%out.write(MessageManager.getInstance().getProperty(MessageManager.APARTMENT));%></label>
    <input type="text" name="apartment" class="form-control" id="adress" placeholder="Your apartment">
  </div>                
   <button type="submit" class="btn btn-success" data-toggle="modal" data-target="#myModal" style="margin-left: 42%; margin-bottom: 15px"><%out.write(MessageManager.getInstance().getProperty(MessageManager.SUBMIT));%></button>         
</form>


 