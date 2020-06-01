package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class MailBoxTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MailBox.class);
        MailBox mailBox1 = new MailBox();
        mailBox1.setId(1L);
        MailBox mailBox2 = new MailBox();
        mailBox2.setId(mailBox1.getId());
        assertThat(mailBox1).isEqualTo(mailBox2);
        mailBox2.setId(2L);
        assertThat(mailBox1).isNotEqualTo(mailBox2);
        mailBox1.setId(null);
        assertThat(mailBox1).isNotEqualTo(mailBox2);
    }
}
