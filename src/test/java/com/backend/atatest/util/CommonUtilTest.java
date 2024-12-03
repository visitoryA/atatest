package com.backend.atatest.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class CommonUtilTest {

    @Test
    void convertStrToIntWorksProperly() {
        assertEquals(11, CommonUtil.convertStrToInt("11.03"));
        assertEquals(2005, CommonUtil.convertStrToInt("2,005.5"));
        assertEquals(35456, CommonUtil.convertStrToInt("35456"));

        assertNull(CommonUtil.convertStrToInt(null));
        assertNull(CommonUtil.convertStrToInt(""));
        assertNull(CommonUtil.convertStrToInt(" "));
    }

    @Test
    void appendStrBuilderWorksProperly() {
        StringBuilder stringBuilder = new StringBuilder();
        CommonUtil.appendStrBuilder(stringBuilder, "test");
        String actualResult = stringBuilder.toString();
        assertEquals("test", actualResult);

        CommonUtil.appendStrBuilder(stringBuilder, "test_1");
        actualResult = stringBuilder.toString();
        assertEquals("test, test_1", actualResult);
    }
}
