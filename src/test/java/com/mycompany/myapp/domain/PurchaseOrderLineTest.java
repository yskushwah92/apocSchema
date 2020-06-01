package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PurchaseOrderLineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseOrderLine.class);
        PurchaseOrderLine purchaseOrderLine1 = new PurchaseOrderLine();
        purchaseOrderLine1.setId(1L);
        PurchaseOrderLine purchaseOrderLine2 = new PurchaseOrderLine();
        purchaseOrderLine2.setId(purchaseOrderLine1.getId());
        assertThat(purchaseOrderLine1).isEqualTo(purchaseOrderLine2);
        purchaseOrderLine2.setId(2L);
        assertThat(purchaseOrderLine1).isNotEqualTo(purchaseOrderLine2);
        purchaseOrderLine1.setId(null);
        assertThat(purchaseOrderLine1).isNotEqualTo(purchaseOrderLine2);
    }
}
