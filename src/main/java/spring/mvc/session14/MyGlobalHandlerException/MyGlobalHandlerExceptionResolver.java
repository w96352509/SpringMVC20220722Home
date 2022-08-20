package spring.mvc.session14.MyGlobalHandlerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;



@Component
public class MyGlobalHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("session14/error");
		mv.addObject("ex" ,ex);
		mv.addObject("referer" , request.getHeader("referer"));
		return mv;
	}

	
	
}
