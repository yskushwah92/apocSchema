package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class GLAccountDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GLAccountDetails.class);
        GLAccountDetails gLAccountDetails1 = new GLAccountDetails();
        gLAccountDetails1.setId(1L);
        GLAccountDetails gLAccountDetails2 = new GLAccountDetails();
        gLAccountDetails2.setId(gLAccountDetails1.getId());
        assertThat(gLAccountDetails1).isEqualTo(gLAccountDetails2);
        gLAccountDetails2.setId(2L);
        assertThat(gLAccountDetails1).isNotEqualTo(gLAccountDetails2);
        gLAccountDetails1.setId(null);
        assertThat(gLAccountDetails1).isNotEqualTo(gLAccountDetails2);
    }
}
