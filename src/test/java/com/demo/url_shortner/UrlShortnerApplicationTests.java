package com.demo.url_shortner;

import com.demo.url_shortner.utils.ValidationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UrlShortnerApplicationTests {

	@Test
	void testURLs() {
		String validUrl = "https://example.com";
		String invalidUrl = "htp://this-url-is-invalid";
		String emptyUrl = "";
		assertTrue(ValidationUtils.isURLValid(validUrl), "A valid URL should return true");
		assertFalse(ValidationUtils.isURLValid(invalidUrl), "An invalid URL should return false");
		assertFalse(ValidationUtils.isURLValid(emptyUrl), "An empty URL should return false");
	}
}
