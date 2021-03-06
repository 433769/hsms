package cz.muni.fi.pa165.skupina06.team02.rms.app.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Vojtech Prusa
 *
 */
@Component
public class CustomSavedRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    final static Logger log = LoggerFactory.getLogger(CustomSavedRequestAwareAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws ServletException, IOException {
        final SavedRequest savedRequest = requestCache.getRequest(request, response);
        log.info("onAuthenticationSuccess");
        
        if (savedRequest == null) {
            //clearAuthenticationAttributes(request);
            return;
        }
        final String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
            requestCache.removeRequest(request, response);
            //clearAuthenticationAttributes(request);
            return;
        }

        //clearAuthenticationAttributes(request);
    }

    public void setRequestCache(final RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}