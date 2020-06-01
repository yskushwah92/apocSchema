package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AuditLogDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuditLogDetails.class);
        AuditLogDetails auditLogDetails1 = new AuditLogDetails();
        auditLogDetails1.setId(1L);
        AuditLogDetails auditLogDetails2 = new AuditLogDetails();
        auditLogDetails2.setId(auditLogDetails1.getId());
        assertThat(auditLogDetails1).isEqualTo(auditLogDetails2);
        auditLogDetails2.setId(2L);
        assertThat(auditLogDetails1).isNotEqualTo(auditLogDetails2);
        auditLogDetails1.setId(null);
        assertThat(auditLogDetails1).isNotEqualTo(auditLogDetails2);
    }
}
