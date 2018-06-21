<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


  <div class="tab-pane" id="categories">
  	<table class="table">
            <thead>
              <tr>
                <th>#</th>
                <th>Title</th>
                <th>Description</th>
                <th>Image url</th>
                <th>Option</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
                <td>
                	<button type="button" class="btn btn-sm btn-danger">Delete</button>
                </td>
              </tr>
              <tr>
                <td>2</td>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
                <td>
				  <button type="button" class="btn btn-sm btn-danger">Delete</button>
                </td>
              </tr>
              <tr>
                <td>3</td>
                <td>Larry</td>
                <td>the Bird</td>
                <td>@twitter</td>
                <td>
                	<button type="button" class="btn btn-sm btn-danger">Delete</button>
                </td>
              </tr>
            </tbody>
          </table>

          <h3>Add good</h3>

          <form role="form">
	        <div class="form-group">
	          <label for="name">Title</label>
	          <input type="text" class="form-control" id="name" placeholder="Good title">
	        </div>
	        <div class="form-group">
	          <label for="exampleInputEmail1">Description</label>
	          <textarea class="form-control" id="exampleInputEmail1"></textarea>
	        </div>
	        <div class="form-group">
	          <label for="exampleInputPassword1">Image</label>
	          <input type="file" class="form-control" id="exampleInputPassword1" placeholder="Your phone">
	        </div>
	        
	        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal" style="margin-left: 42%; margin-bottom: 15px">Submit</button>
          </form>
     </div>
   </div>

