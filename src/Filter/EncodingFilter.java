package Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * 编码处理的过滤器
 *
 * 实现过滤器的步骤:
 * 1.创建普通的类，实现javax.servlet.Filter接口的init,doFilter,destroy方法
 * 2.在web.xml文件中配置过滤器
 * <filter>
		<filter-name>EncodingFilter</filter-name>
		<filter-class>ht.Filter.EncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>utf-8</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>EncodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	3.通过init方法读取web.xml文件中配置的初始化参数
	4.实现doFilter方法，进行编码处理
	5.过滤器配置好以后是系统自动运行，不需要调用,否则整个系统将不执行任何请求
	注意事项：
		1.在doFilter方法中一定要执行chain.doFilter(request,response);语句
 * */
public class EncodingFilter implements javax.servlet.Filter{

    private FilterConfig config;
    private String encoding;
    public void destroy() {
        config=null;
    }

    //过滤器的进行过滤处理的主要方法
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if(encoding!=null && !encoding.equals("")){
            //编码处理
            request.setCharacterEncoding(encoding);

        }
        //该语句非常重要，如果没有执行，程序将不会收的任何请求
        chain.doFilter(request, response);
    }
    //初始化方法,读取配置参数
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        encoding = config.getInitParameter("encoding");
    }

}
