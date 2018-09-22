package nl.captcha.spring.boot;

import java.util.NoSuchElementException;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = SimpleCaptchaProperties.PREFIX)
public class SimpleCaptchaProperties {

	public static final String PREFIX = "simple-captcha";
	public static final long DEFAULT_CAPTCHA_TIMEOUT = 60 * 1000;
	

	public enum CaptchaType {

		/**
		 * SimpleCaptchaServlet：随机生成5个字符（数字+字符）放在page中，页面刷新会重新生成。
		 */
		SIMPLE("simple"), 
		/**
		 * ChineseCaptchaServlet：随机生成5个中文字符，结果也会放在session中存储，刷新页面不会改变值，继承SimpleCaptchaServlet。
		 */
		CHINESE("chinese"), 
		/**
		 * StickyCaptchaServlet：随机生成5个字符（数字、字母），结果会放在session中，刷新页面不会改变值，继承SimpleCaptchaServlet。
		 */
		STICKY("sticky");
		
		private final String captchaType;

		CaptchaType(String captchaType) {
			this.captchaType = captchaType;
		}

		public String get() {
			return captchaType;
		}

		public boolean equals(CaptchaType captchaType) {
			return this.compareTo(captchaType) == 0;
		}

		public boolean equals(String captchaType) {
			return this.compareTo(CaptchaType.valueOfIgnoreCase(captchaType)) == 0;
		}

		public static CaptchaType valueOfIgnoreCase(String key) {
			for (CaptchaType captchaType : CaptchaType.values()) {
				if (captchaType.get().equalsIgnoreCase(key)) {
					return captchaType;
				}
			}
			throw new NoSuchElementException("Cannot found captchaType with key '" + key + "'.");
		}

	}
	
	private CaptchaType captchaType = CaptchaType.SIMPLE;
	
	private String simplePattern = "/simple-captcha.jpg";
	private String chinesePattern = "/chinese-captcha.jpg";
	private String stickyPattern = "/sticky-captcha.jpg";
	private int width = 200;
	private int height = 50;

	public CaptchaType getCaptchaType() {
		return captchaType;
	}

	public void setCaptchaType(CaptchaType captchaType) {
		this.captchaType = captchaType;
	}

	public String getSimplePattern() {
		return simplePattern;
	}

	public void setSimplePattern(String simplePattern) {
		this.simplePattern = simplePattern;
	}

	public String getChinesePattern() {
		return chinesePattern;
	}

	public void setChinesePattern(String chinesePattern) {
		this.chinesePattern = chinesePattern;
	}

	public String getStickyPattern() {
		return stickyPattern;
	}

	public void setStickyPattern(String stickyPattern) {
		this.stickyPattern = stickyPattern;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
