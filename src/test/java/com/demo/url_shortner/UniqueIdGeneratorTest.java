package com.demo.url_shortner;


import com.demo.url_shortner.utils.UniqueIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UniqueIdGeneratorTest {

    private UniqueIdGenerator idGenerator;

    @BeforeEach
    void setUp() {
        idGenerator = UniqueIdGenerator.getInstance();
    }

    @Test
    void testGenerateUniqueId() {
        String id1 = idGenerator.generateUniqueId();
        String id2 = idGenerator.generateUniqueId();
        assertNotNull(id1, "Generated unique ID cannot be null");
        assertNotEquals(id1, id2, "Generated unique IDs should be different");
    }
}
