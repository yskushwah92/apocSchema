package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class OCRRawExtractionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OCRRawExtraction.class);
        OCRRawExtraction oCRRawExtraction1 = new OCRRawExtraction();
        oCRRawExtraction1.setId(1L);
        OCRRawExtraction oCRRawExtraction2 = new OCRRawExtraction();
        oCRRawExtraction2.setId(oCRRawExtraction1.getId());
        assertThat(oCRRawExtraction1).isEqualTo(oCRRawExtraction2);
        oCRRawExtraction2.setId(2L);
        assertThat(oCRRawExtraction1).isNotEqualTo(oCRRawExtraction2);
        oCRRawExtraction1.setId(null);
        assertThat(oCRRawExtraction1).isNotEqualTo(oCRRawExtraction2);
    }
}
