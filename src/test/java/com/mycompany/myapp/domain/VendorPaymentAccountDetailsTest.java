package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class VendorPaymentAccountDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VendorPaymentAccountDetails.class);
        VendorPaymentAccountDetails vendorPaymentAccountDetails1 = new VendorPaymentAccountDetails();
        vendorPaymentAccountDetails1.setId(1L);
        VendorPaymentAccountDetails vendorPaymentAccountDetails2 = new VendorPaymentAccountDetails();
        vendorPaymentAccountDetails2.setId(vendorPaymentAccountDetails1.getId());
        assertThat(vendorPaymentAccountDetails1).isEqualTo(vendorPaymentAccountDetails2);
        vendorPaymentAccountDetails2.setId(2L);
        assertThat(vendorPaymentAccountDetails1).isNotEqualTo(vendorPaymentAccountDetails2);
        vendorPaymentAccountDetails1.setId(null);
        assertThat(vendorPaymentAccountDetails1).isNotEqualTo(vendorPaymentAccountDetails2);
    }
}
