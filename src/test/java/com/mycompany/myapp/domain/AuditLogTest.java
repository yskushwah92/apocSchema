package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AuditLogTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuditLog.class);
        AuditLog auditLog1 = new AuditLog();
        auditLog1.setId(1L);
        AuditLog auditLog2 = new AuditLog();
        auditLog2.setId(auditLog1.getId());
        assertThat(auditLog1).isEqualTo(auditLog2);
        auditLog2.setId(2L);
        assertThat(auditLog1).isNotEqualTo(auditLog2);
        auditLog1.setId(null);
        assertThat(auditLog1).isNotEqualTo(auditLog2);
    }
}
