
package by.bsuir.project.entity.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;


public class CategoryTag extends TagSupport {    
    private int category;

    public void setCategory(int category) {
        this.category = category;
    }
    
    @Override
    public int doStartTag() throws JspException {                
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpSession session = request.getSession();        
        session.setAttribute("category", category);
        return SKIP_BODY;    
    }    
}
