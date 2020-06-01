package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ModelInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModelInfo.class);
        ModelInfo modelInfo1 = new ModelInfo();
        modelInfo1.setId(1L);
        ModelInfo modelInfo2 = new ModelInfo();
        modelInfo2.setId(modelInfo1.getId());
        assertThat(modelInfo1).isEqualTo(modelInfo2);
        modelInfo2.setId(2L);
        assertThat(modelInfo1).isNotEqualTo(modelInfo2);
        modelInfo1.setId(null);
        assertThat(modelInfo1).isNotEqualTo(modelInfo2);
    }
}
