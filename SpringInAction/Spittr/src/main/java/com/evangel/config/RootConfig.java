package com.evangel.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
// @Import(DataConfig.class)
@ComponentScan(basePackages = { "com.evangel" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
// @ComponentScan(basePackages = { "com.evangel" }, excludeFilters = {
// @Filter(type = FilterType.CUSTOM, value = RootConfig.WebPackage.class) })
public class RootConfig {
	// public static class WebPackage extends RegexPatternTypeFilter {
	// public WebPackage() {
	// super(Pattern.compile("spittr\\.web"));
	// }
	// }
}
