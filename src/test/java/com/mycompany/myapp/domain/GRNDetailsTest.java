package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class GRNDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GRNDetails.class);
        GRNDetails gRNDetails1 = new GRNDetails();
        gRNDetails1.setId(1L);
        GRNDetails gRNDetails2 = new GRNDetails();
        gRNDetails2.setId(gRNDetails1.getId());
        assertThat(gRNDetails1).isEqualTo(gRNDetails2);
        gRNDetails2.setId(2L);
        assertThat(gRNDetails1).isNotEqualTo(gRNDetails2);
        gRNDetails1.setId(null);
        assertThat(gRNDetails1).isNotEqualTo(gRNDetails2);
    }
}
