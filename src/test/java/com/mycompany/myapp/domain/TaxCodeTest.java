package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TaxCodeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaxCode.class);
        TaxCode taxCode1 = new TaxCode();
        taxCode1.setId(1L);
        TaxCode taxCode2 = new TaxCode();
        taxCode2.setId(taxCode1.getId());
        assertThat(taxCode1).isEqualTo(taxCode2);
        taxCode2.setId(2L);
        assertThat(taxCode1).isNotEqualTo(taxCode2);
        taxCode1.setId(null);
        assertThat(taxCode1).isNotEqualTo(taxCode2);
    }
}
