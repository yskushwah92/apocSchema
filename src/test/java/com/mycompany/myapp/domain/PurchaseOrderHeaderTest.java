package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PurchaseOrderHeaderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseOrderHeader.class);
        PurchaseOrderHeader purchaseOrderHeader1 = new PurchaseOrderHeader();
        purchaseOrderHeader1.setId(1L);
        PurchaseOrderHeader purchaseOrderHeader2 = new PurchaseOrderHeader();
        purchaseOrderHeader2.setId(purchaseOrderHeader1.getId());
        assertThat(purchaseOrderHeader1).isEqualTo(purchaseOrderHeader2);
        purchaseOrderHeader2.setId(2L);
        assertThat(purchaseOrderHeader1).isNotEqualTo(purchaseOrderHeader2);
        purchaseOrderHeader1.setId(null);
        assertThat(purchaseOrderHeader1).isNotEqualTo(purchaseOrderHeader2);
    }
}
