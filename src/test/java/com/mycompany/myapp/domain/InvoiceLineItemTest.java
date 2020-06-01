package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class InvoiceLineItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceLineItem.class);
        InvoiceLineItem invoiceLineItem1 = new InvoiceLineItem();
        invoiceLineItem1.setId(1L);
        InvoiceLineItem invoiceLineItem2 = new InvoiceLineItem();
        invoiceLineItem2.setId(invoiceLineItem1.getId());
        assertThat(invoiceLineItem1).isEqualTo(invoiceLineItem2);
        invoiceLineItem2.setId(2L);
        assertThat(invoiceLineItem1).isNotEqualTo(invoiceLineItem2);
        invoiceLineItem1.setId(null);
        assertThat(invoiceLineItem1).isNotEqualTo(invoiceLineItem2);
    }
}
