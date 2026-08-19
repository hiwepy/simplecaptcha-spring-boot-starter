package nl.captcha.spring.boot;

import java.util.NoSuchElementException;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = SimpleCaptchaProperties.PREFIX)
/**
 * <p>Configuration properties for SimpleCaptcha.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
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

    /**
     * <p>Returns the get.</p>
     * @return the get
     */
		public String get() {
			return captchaType;
		}

    /**
     * <p>Equals.</p>
     * @param captchaType
     * @return the equals
     */
		public boolean equals(CaptchaType captchaType) {
			return this.compareTo(captchaType) == 0;
		}

    /**
     * <p>Equals.</p>
     * @param captchaType
     * @return the equals
     */
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

    /**
     * <p>Returns the captcha type.</p>
     * @return the get captcha type
     */
	public CaptchaType getCaptchaType() {
		return captchaType;
	}

    /**
     * <p>Sets the captcha type.</p>
     * @param captchaType
     */
	public void setCaptchaType(CaptchaType captchaType) {
		this.captchaType = captchaType;
	}

    /**
     * <p>Returns the simple pattern.</p>
     * @return the get simple pattern
     */
	public String getSimplePattern() {
		return simplePattern;
	}

    /**
     * <p>Sets the simple pattern.</p>
     * @param simplePattern
     */
	public void setSimplePattern(String simplePattern) {
		this.simplePattern = simplePattern;
	}

    /**
     * <p>Returns the chinese pattern.</p>
     * @return the get chinese pattern
     */
	public String getChinesePattern() {
		return chinesePattern;
	}

    /**
     * <p>Sets the chinese pattern.</p>
     * @param chinesePattern
     */
	public void setChinesePattern(String chinesePattern) {
		this.chinesePattern = chinesePattern;
	}

    /**
     * <p>Returns the sticky pattern.</p>
     * @return the get sticky pattern
     */
	public String getStickyPattern() {
		return stickyPattern;
	}

    /**
     * <p>Sets the sticky pattern.</p>
     * @param stickyPattern
     */
	public void setStickyPattern(String stickyPattern) {
		this.stickyPattern = stickyPattern;
	}

    /**
     * <p>Returns the width.</p>
     * @return the get width
     */
	public int getWidth() {
		return width;
	}

    /**
     * <p>Sets the width.</p>
     * @param width
     */
	public void setWidth(int width) {
		this.width = width;
	}

    /**
     * <p>Returns the height.</p>
     * @return the get height
     */
	public int getHeight() {
		return height;
	}

    /**
     * <p>Sets the height.</p>
     * @param height
     */
	public void setHeight(int height) {
		this.height = height;
	}
	
}
