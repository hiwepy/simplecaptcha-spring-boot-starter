package nl.captcha.spring.boot;

import javax.servlet.ServletException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nl.captcha.Captcha;
import nl.captcha.servlet.ChineseCaptchaServlet;
import nl.captcha.servlet.SimpleCaptchaServlet;
import nl.captcha.servlet.StickyCaptchaServlet;

@Configuration
@ConditionalOnClass({ Captcha.class })
@EnableConfigurationProperties(SimpleCaptchaProperties.class)
public class SimpleCaptchaAutoConfiguration {

    private static final String PARAM_HEIGHT = "height";
    private static final String PARAM_WIDTH = "width";
    
    @Bean
	@ConditionalOnMissingBean(name = "simpleCaptchaServlet")
	@ConditionalOnProperty(prefix = SimpleCaptchaProperties.PREFIX, value = "captcha-type", havingValue = "simple")
	public ServletRegistrationBean<SimpleCaptchaServlet> simpleCaptchaServlet(SimpleCaptchaProperties properties) throws ServletException {

		ServletRegistrationBean<SimpleCaptchaServlet> registrationBean = new ServletRegistrationBean<SimpleCaptchaServlet>();
		
		SimpleCaptchaServlet captchaServlet = new SimpleCaptchaServlet();
		
		registrationBean.setServlet(captchaServlet);
		
		// 默认参数
		registrationBean.addInitParameter(PARAM_HEIGHT, Integer.toString(properties.getHeight()));
		registrationBean.addInitParameter(PARAM_WIDTH, Integer.toString(properties.getWidth()));
		registrationBean.addUrlMappings(properties.getSimplePattern());

		return registrationBean;
	}
    
    @Bean
	@ConditionalOnMissingBean(name = "chineseCaptchaServlet")
	@ConditionalOnProperty(prefix = SimpleCaptchaProperties.PREFIX, value = "captcha-type", havingValue = "chinese")
	public ServletRegistrationBean<ChineseCaptchaServlet> chineseCaptchaServlet(SimpleCaptchaProperties properties) throws ServletException {

		ServletRegistrationBean<ChineseCaptchaServlet> registrationBean = new ServletRegistrationBean<ChineseCaptchaServlet>();
		
		ChineseCaptchaServlet captchaServlet = new ChineseCaptchaServlet();
		
		registrationBean.setServlet(captchaServlet);
		
		// 默认参数
		registrationBean.addInitParameter(PARAM_HEIGHT, Integer.toString(properties.getHeight()));
		registrationBean.addInitParameter(PARAM_WIDTH, Integer.toString(properties.getWidth()));
		registrationBean.addUrlMappings(properties.getChinesePattern());

		return registrationBean;
	}
    
    @Bean
	@ConditionalOnMissingBean(name = "stickyCaptchaServlet")
	@ConditionalOnProperty(prefix = SimpleCaptchaProperties.PREFIX, value = "captcha-type", havingValue = "sticky")
	public ServletRegistrationBean<StickyCaptchaServlet> stickyCaptchaServlet(SimpleCaptchaProperties properties) throws ServletException {

		ServletRegistrationBean<StickyCaptchaServlet> registrationBean = new ServletRegistrationBean<StickyCaptchaServlet>();
		
		StickyCaptchaServlet captchaServlet = new StickyCaptchaServlet();
		
		registrationBean.setServlet(captchaServlet);
		
		// 默认参数
		registrationBean.addInitParameter(PARAM_HEIGHT, Integer.toString(properties.getHeight()));
		registrationBean.addInitParameter(PARAM_WIDTH, Integer.toString(properties.getWidth()));
		registrationBean.addUrlMappings(properties.getStickyPattern());

		return registrationBean;
	}
    
	
	
}
