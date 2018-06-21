
package by.bsuir.project.entity.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;


public class CurrentJspTag extends TagSupport {    
    
    
    @Override
    public int doStartTag() throws JspException {                
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();       
        String path = request.getRequestURI();         
        String url = path.replace(request.getContextPath(), "");                
        request.getSession().setAttribute("currentJsp", url);
        return SKIP_BODY;    
    }    
}
