package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class InvoiceHeaderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceHeader.class);
        InvoiceHeader invoiceHeader1 = new InvoiceHeader();
        invoiceHeader1.setId(1L);
        InvoiceHeader invoiceHeader2 = new InvoiceHeader();
        invoiceHeader2.setId(invoiceHeader1.getId());
        assertThat(invoiceHeader1).isEqualTo(invoiceHeader2);
        invoiceHeader2.setId(2L);
        assertThat(invoiceHeader1).isNotEqualTo(invoiceHeader2);
        invoiceHeader1.setId(null);
        assertThat(invoiceHeader1).isNotEqualTo(invoiceHeader2);
    }
}
