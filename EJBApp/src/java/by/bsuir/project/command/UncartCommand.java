package by.bsuir.project.command;

import by.bsuir.project.entity.Cart;
import by.bsuir.project.entity.Item;
import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.Parameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UncartCommand implements ICommand {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Cart cart = (Cart) request.getSession().getAttribute(Parameters.CART);
        /*Getting removing product id*/
        String productId = request.getParameter(Parameters.PRODUCT_ID);

        /*Searching product in cart with {@productId}*/
        for (Item item : cart.getItems()) {
            if (item.getProduct().getId() == Integer.parseInt(productId)) {
                /*Getting cart size*/
                int size = item.getQuantity();

                if (size > 1) {
                    item.setQuantity(--size);
                } else {
                    cart.getItems().remove(item);
                    if (cart.getCartSize() == 0) {
                        request.getSession().removeAttribute(Parameters.CART);
                    }
                }
                break;
            }
        }

        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CART_PAGE_PATH);
    }

}
