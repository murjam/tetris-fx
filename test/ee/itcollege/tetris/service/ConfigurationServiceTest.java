package ee.itcollege.tetris.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigurationServiceTest {

	@Test
	public void test() {
		ConfigurationService service = new ConfigurationService();
		try {
			service.loadFile("test/test.properties");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("value", service.getStringValue("key"));
		assertEquals("ajee", service.getStringValue("test.with.dots"));
		assertEquals(123, service.getIntValue("numeric"));
		assertEquals("testing    whitespace", service.getStringValue("whitespace"));
		assertEquals(null, service.getStringValue("notpresent"));
	}

}
