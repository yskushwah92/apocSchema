package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PaymentInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentInfo.class);
        PaymentInfo paymentInfo1 = new PaymentInfo();
        paymentInfo1.setId(1L);
        PaymentInfo paymentInfo2 = new PaymentInfo();
        paymentInfo2.setId(paymentInfo1.getId());
        assertThat(paymentInfo1).isEqualTo(paymentInfo2);
        paymentInfo2.setId(2L);
        assertThat(paymentInfo1).isNotEqualTo(paymentInfo2);
        paymentInfo1.setId(null);
        assertThat(paymentInfo1).isNotEqualTo(paymentInfo2);
    }
}
