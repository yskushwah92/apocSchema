package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class CaseStatusDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseStatusDetails.class);
        CaseStatusDetails caseStatusDetails1 = new CaseStatusDetails();
        caseStatusDetails1.setId(1L);
        CaseStatusDetails caseStatusDetails2 = new CaseStatusDetails();
        caseStatusDetails2.setId(caseStatusDetails1.getId());
        assertThat(caseStatusDetails1).isEqualTo(caseStatusDetails2);
        caseStatusDetails2.setId(2L);
        assertThat(caseStatusDetails1).isNotEqualTo(caseStatusDetails2);
        caseStatusDetails1.setId(null);
        assertThat(caseStatusDetails1).isNotEqualTo(caseStatusDetails2);
    }
}
