package by.bsuir.project.command;

import by.bsuir.project.entity.Cart;
import by.bsuir.project.entity.exception.CommandException;
import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.Parameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateCartCommand implements ICommand {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        /*Getting request parameters*/
        String product = request.getParameter(Parameters.PRODUCT_ID);
        String quantityStr = request.getParameter(Parameters.QUANTITY);
        int quantity;

        /*Poduct amount check*/
        if ((quantity = Validator.validateProductAmount(quantityStr, request)) != -1) {
            if (product != null && product.length() > 1) {
                product = product.substring(0, product.length() - 1);

                int productId = Integer.parseInt(product);

                Cart cart = (Cart) request.getSession().getAttribute(Parameters.CART);

                /*Cart updation*/
                if (productId > 0) {
                    cart.update(productId, quantity);
                }
            }
        }
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CART_PAGE_PATH);
    }

}
