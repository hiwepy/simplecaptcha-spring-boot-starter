/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package nl.captcha.spring.boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {@link SimpleCaptchaProperties}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("SimpleCaptchaProperties Tests")
class SimpleCaptchaPropertiesTest {

    @Test
    @DisplayName("Default constructor creates non-null instance with defaults")
    void testDefaultInstance() {
        SimpleCaptchaProperties props = new SimpleCaptchaProperties();
        assertThat(props).isNotNull();
        assertThat(props.getCaptchaType()).isEqualTo(SimpleCaptchaProperties.CaptchaType.SIMPLE);
        assertThat(props.getSimplePattern()).isEqualTo("/simple-captcha.jpg");
        assertThat(props.getChinesePattern()).isEqualTo("/chinese-captcha.jpg");
        assertThat(props.getStickyPattern()).isEqualTo("/sticky-captcha.jpg");
        assertThat(props.getWidth()).isEqualTo(200);
        assertThat(props.getHeight()).isEqualTo(50);
    }

    @Test
    @DisplayName("PREFIX constant has expected value")
    void testPREFIXConstant() {
        assertThat(SimpleCaptchaProperties.PREFIX).isEqualTo("simple-captcha");
    }

    @Test
    @DisplayName("DEFAULT_CAPTCHA_TIMEOUT constant has expected value")
    void testDefaultCaptchaTimeout() {
        assertThat(SimpleCaptchaProperties.DEFAULT_CAPTCHA_TIMEOUT).isEqualTo(60000L);
    }

    @Test
    @DisplayName("captchaType getter/setter works correctly")
    void testCaptchaTypeGetterSetter() {
        SimpleCaptchaProperties props = new SimpleCaptchaProperties();
        props.setCaptchaType(SimpleCaptchaProperties.CaptchaType.CHINESE);
        assertThat(props.getCaptchaType()).isEqualTo(SimpleCaptchaProperties.CaptchaType.CHINESE);
        props.setCaptchaType(SimpleCaptchaProperties.CaptchaType.STICKY);
        assertThat(props.getCaptchaType()).isEqualTo(SimpleCaptchaProperties.CaptchaType.STICKY);
    }

    @Test
    @DisplayName("simplePattern getter/setter works correctly")
    void testSimplePatternGetterSetter() {
        SimpleCaptchaProperties props = new SimpleCaptchaProperties();
        props.setSimplePattern("/custom/simple");
        assertThat(props.getSimplePattern()).isEqualTo("/custom/simple");
    }

    @Test
    @DisplayName("chinesePattern getter/setter works correctly")
    void testChinesePatternGetterSetter() {
        SimpleCaptchaProperties props = new SimpleCaptchaProperties();
        props.setChinesePattern("/custom/chinese");
        assertThat(props.getChinesePattern()).isEqualTo("/custom/chinese");
    }

    @Test
    @DisplayName("stickyPattern getter/setter works correctly")
    void testStickyPatternGetterSetter() {
        SimpleCaptchaProperties props = new SimpleCaptchaProperties();
        props.setStickyPattern("/custom/sticky");
        assertThat(props.getStickyPattern()).isEqualTo("/custom/sticky");
    }

    @Test
    @DisplayName("width getter/setter works correctly")
    void testWidthGetterSetter() {
        SimpleCaptchaProperties props = new SimpleCaptchaProperties();
        props.setWidth(300);
        assertThat(props.getWidth()).isEqualTo(300);
    }

    @Test
    @DisplayName("height getter/setter works correctly")
    void testHeightGetterSetter() {
        SimpleCaptchaProperties props = new SimpleCaptchaProperties();
        props.setHeight(100);
        assertThat(props.getHeight()).isEqualTo(100);
    }

    @Test
    @DisplayName("CaptchaType.get() returns correct string value")
    void testCaptchaTypeGet() {
        assertThat(SimpleCaptchaProperties.CaptchaType.SIMPLE.get()).isEqualTo("simple");
        assertThat(SimpleCaptchaProperties.CaptchaType.CHINESE.get()).isEqualTo("chinese");
        assertThat(SimpleCaptchaProperties.CaptchaType.STICKY.get()).isEqualTo("sticky");
    }

    @Test
    @DisplayName("CaptchaType.equals(CaptchaType) works correctly")
    void testCaptchaTypeEqualsType() {
        assertThat(SimpleCaptchaProperties.CaptchaType.SIMPLE.equals(SimpleCaptchaProperties.CaptchaType.SIMPLE)).isTrue();
        assertThat(SimpleCaptchaProperties.CaptchaType.SIMPLE.equals(SimpleCaptchaProperties.CaptchaType.CHINESE)).isFalse();
    }

    @Test
    @DisplayName("CaptchaType.equals(String) works correctly")
    void testCaptchaTypeEqualsString() {
        assertThat(SimpleCaptchaProperties.CaptchaType.SIMPLE.equals("simple")).isTrue();
        assertThat(SimpleCaptchaProperties.CaptchaType.SIMPLE.equals("SIMPLE")).isTrue();
        assertThat(SimpleCaptchaProperties.CaptchaType.SIMPLE.equals("chinese")).isFalse();
    }

    @Test
    @DisplayName("CaptchaType.valueOfIgnoreCase finds correct type")
    void testCaptchaTypeValueOfIgnoreCase() {
        assertThat(SimpleCaptchaProperties.CaptchaType.valueOfIgnoreCase("simple")).isEqualTo(SimpleCaptchaProperties.CaptchaType.SIMPLE);
        assertThat(SimpleCaptchaProperties.CaptchaType.valueOfIgnoreCase("CHINESE")).isEqualTo(SimpleCaptchaProperties.CaptchaType.CHINESE);
        assertThat(SimpleCaptchaProperties.CaptchaType.valueOfIgnoreCase("Sticky")).isEqualTo(SimpleCaptchaProperties.CaptchaType.STICKY);
    }

    @Test
    @DisplayName("CaptchaType.valueOfIgnoreCase throws for unknown key")
    void testCaptchaTypeValueOfIgnoreCaseThrows() {
        assertThatThrownBy(() -> SimpleCaptchaProperties.CaptchaType.valueOfIgnoreCase("unknown"))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("CaptchaType.values() returns all types")
    void testCaptchaTypeValues() {
        SimpleCaptchaProperties.CaptchaType[] values = SimpleCaptchaProperties.CaptchaType.values();
        assertThat(values).hasSize(3);
        assertThat(values).contains(
                SimpleCaptchaProperties.CaptchaType.SIMPLE,
                SimpleCaptchaProperties.CaptchaType.CHINESE,
                SimpleCaptchaProperties.CaptchaType.STICKY
        );
    }
}
