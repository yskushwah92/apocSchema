package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class InvoiceHeaderTaxDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceHeaderTaxDetails.class);
        InvoiceHeaderTaxDetails invoiceHeaderTaxDetails1 = new InvoiceHeaderTaxDetails();
        invoiceHeaderTaxDetails1.setId(1L);
        InvoiceHeaderTaxDetails invoiceHeaderTaxDetails2 = new InvoiceHeaderTaxDetails();
        invoiceHeaderTaxDetails2.setId(invoiceHeaderTaxDetails1.getId());
        assertThat(invoiceHeaderTaxDetails1).isEqualTo(invoiceHeaderTaxDetails2);
        invoiceHeaderTaxDetails2.setId(2L);
        assertThat(invoiceHeaderTaxDetails1).isNotEqualTo(invoiceHeaderTaxDetails2);
        invoiceHeaderTaxDetails1.setId(null);
        assertThat(invoiceHeaderTaxDetails1).isNotEqualTo(invoiceHeaderTaxDetails2);
    }
}
