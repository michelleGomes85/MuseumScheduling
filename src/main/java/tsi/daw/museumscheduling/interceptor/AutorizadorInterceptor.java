package tsi.daw.museumscheduling.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tsi.daw.museumscheduling.enums.UserProfile;
import tsi.daw.museumscheduling.interfaces.PagesName;

public class AutorizadorInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    	String uri = request.getRequestURI();

        if (uri.endsWith(PagesName.ROOT) || uri.endsWith(PagesName.HOME_PAGE) || uri.endsWith(PagesName.LOGIN_PAGE) || uri.endsWith(PagesName.SCHEDULING_PAGE) || uri.contains("resources")) 
            return true;

        Object userLogin = request.getSession().getAttribute("user");
        
        if (userLogin == null) {
            response.sendRedirect(PagesName.LOGIN_PAGE);
            return false;
        }

        if (uri.endsWith(PagesName.EMPLOYEE_REGISTRATION_PAGE)) {
            UserProfile userProfile = (UserProfile) request.getSession().getAttribute("userProfile");
            
            if (UserProfile.ADMIN.equals(userProfile)) {
                return true;  
            } else {
                response.sendRedirect(PagesName.DEFAULT_REDIRECT);
                return false;
            }
        }

        return true;
    }
}
