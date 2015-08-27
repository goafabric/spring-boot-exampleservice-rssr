package org.goafabric.common.spring.service.resilience;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by amautsch on 21.05.2015.
 */
public class ListCacheKeyGeneratorTest {

    @Test
    public void testCreateKeyNoParams() throws Exception {
        final List<Object> cacheKey =
                ListCacheKeyGenerator.createKey("testClass", "testMethod");
        assertThat(cacheKey).hasSize(2);
        assertThat(cacheKey.get(0)).isEqualTo("testClass");
        assertThat(cacheKey.get(1)).isEqualTo("testMethod");
    }

    @Test
    public void testCreateKeyTwoParams() throws Exception {
        final List<Object> cacheKey =
                ListCacheKeyGenerator.createKey("testClass", "testMethod",
                        100, "test");

        assertThat(cacheKey).hasSize(4);
        assertThat(cacheKey.get(0)).isEqualTo("testClass");
        assertThat(cacheKey.get(1)).isEqualTo("testMethod");
        assertThat(cacheKey.get(2)).isEqualTo(100);
        assertThat(cacheKey.get(3)).isEqualTo("test");
    }

    @Test
    public void testEquals() {
        final List<Object> cacheKey1 =
                ListCacheKeyGenerator.createKey("testClass", "testMethod",
                        100, "test");

        final List<Object> cacheKey2 =
                ListCacheKeyGenerator.createKey("testClass", "testMethod",
                        100, "test");
        assertThat(cacheKey1).isEqualTo(cacheKey2);
    }
}