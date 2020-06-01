package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class OCRExtractionEngineInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OCRExtractionEngineInfo.class);
        OCRExtractionEngineInfo oCRExtractionEngineInfo1 = new OCRExtractionEngineInfo();
        oCRExtractionEngineInfo1.setId(1L);
        OCRExtractionEngineInfo oCRExtractionEngineInfo2 = new OCRExtractionEngineInfo();
        oCRExtractionEngineInfo2.setId(oCRExtractionEngineInfo1.getId());
        assertThat(oCRExtractionEngineInfo1).isEqualTo(oCRExtractionEngineInfo2);
        oCRExtractionEngineInfo2.setId(2L);
        assertThat(oCRExtractionEngineInfo1).isNotEqualTo(oCRExtractionEngineInfo2);
        oCRExtractionEngineInfo1.setId(null);
        assertThat(oCRExtractionEngineInfo1).isNotEqualTo(oCRExtractionEngineInfo2);
    }
}
