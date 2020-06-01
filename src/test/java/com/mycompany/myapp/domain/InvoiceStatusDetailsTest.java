package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class InvoiceStatusDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceStatusDetails.class);
        InvoiceStatusDetails invoiceStatusDetails1 = new InvoiceStatusDetails();
        invoiceStatusDetails1.setId(1L);
        InvoiceStatusDetails invoiceStatusDetails2 = new InvoiceStatusDetails();
        invoiceStatusDetails2.setId(invoiceStatusDetails1.getId());
        assertThat(invoiceStatusDetails1).isEqualTo(invoiceStatusDetails2);
        invoiceStatusDetails2.setId(2L);
        assertThat(invoiceStatusDetails1).isNotEqualTo(invoiceStatusDetails2);
        invoiceStatusDetails1.setId(null);
        assertThat(invoiceStatusDetails1).isNotEqualTo(invoiceStatusDetails2);
    }
}
