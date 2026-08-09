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
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link SimpleCaptchaAutoConfiguration}.
 *
 * <p>Verifies the auto-configuration activates under the expected conditions
 * and exposes its declared beans.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("SimpleCaptchaAutoConfiguration Tests")
class SimpleCaptchaAutoConfigurationTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner();

    @Test
    @DisplayName("Auto-configuration class can be instantiated")
    void testInstantiation() {
        SimpleCaptchaAutoConfiguration configuration = new SimpleCaptchaAutoConfiguration();
        assertThat(configuration).isNotNull();
    }

    @Test
    @DisplayName("Auto-configuration loads with simple captcha type")
    void testLoadsWhenSimpleCaptchaTypeSet() {
        runner.withUserConfiguration(SimpleCaptchaAutoConfiguration.class)
                .withPropertyValues("simplecaptcha.captcha-type=simple")
                .run(context -> {
                    assertThat(context).hasSingleBean(SimpleCaptchaAutoConfiguration.class);
                    assertThat(context).hasBean("simpleCaptchaServlet");
                });
    }

    @Test
    @DisplayName("Auto-configuration loads with chinese captcha type")
    void testLoadsWhenChineseCaptchaTypeSet() {
        runner.withUserConfiguration(SimpleCaptchaAutoConfiguration.class)
                .withPropertyValues("simplecaptcha.captcha-type=chinese")
                .run(context -> {
                    assertThat(context).hasSingleBean(SimpleCaptchaAutoConfiguration.class);
                    assertThat(context).hasBean("chineseCaptchaServlet");
                });
    }

    @Test
    @DisplayName("Auto-configuration loads with sticky captcha type")
    void testLoadsWhenStickyCaptchaTypeSet() {
        runner.withUserConfiguration(SimpleCaptchaAutoConfiguration.class)
                .withPropertyValues("simplecaptcha.captcha-type=sticky")
                .run(context -> {
                    assertThat(context).hasSingleBean(SimpleCaptchaAutoConfiguration.class);
                    assertThat(context).hasBean("stickyCaptchaServlet");
                });
    }

    @Test
    @DisplayName("No captcha servlet beans when no captcha-type property")
    void testNoServletBeansWhenPropertyAbsent() {
        runner.withUserConfiguration(SimpleCaptchaAutoConfiguration.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(SimpleCaptchaAutoConfiguration.class);
                    assertThat(context).doesNotHaveBean("simpleCaptchaServlet");
                    assertThat(context).doesNotHaveBean("chineseCaptchaServlet");
                    assertThat(context).doesNotHaveBean("stickyCaptchaServlet");
                });
    }
}
