package com.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

/**
 * 国际化语言解析器
 * 
 * @author zincredible
 * @date 2019/03/22 08:56:37
 */
public class MyLocaleResolver implements LocaleResolver {

	private static final String I18N_LANGUAGE = "language";
	private static final String I18N_LANGUAGE_SESSION = "i18n_language_session";

	@Override
	public Locale resolveLocale(HttpServletRequest req) {
		String i18n_language = req.getParameter(I18N_LANGUAGE);
		Locale locale = null;
		if (!StringUtils.isEmpty(i18n_language)) {
			String[] language = i18n_language.split("_");
			locale = new Locale(language[0], language[1]);

			// 将国际化语言保存到session
			HttpSession session = req.getSession();
			session.setAttribute(I18N_LANGUAGE_SESSION, locale);
		} else {
			// 如果没有带国际化参数，则从session中获取
			HttpSession session = req.getSession();
			Locale localeInSession = (Locale) session.getAttribute(I18N_LANGUAGE_SESSION);
			if (localeInSession != null) {
				locale = localeInSession;
			}
		}
		// 如果上面都没获取到，则使用系统默认
		if (locale == null) {
			locale = Locale.getDefault();
		}
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		// TODO Auto-generated method stub

	}
}