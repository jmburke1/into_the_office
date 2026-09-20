/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2026 Jason Burke
 */
package intotheoffice;

import org.junit.jupiter.api.Test;

class MainAppTest {
    @Test
    void testMain() {
        String[] testArgs = {"test", "args"};
        try {
            MainApp.main(testArgs);
        } catch(Exception e) {
        }
    }

}