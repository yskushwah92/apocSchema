package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class CommunicationConfigurationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommunicationConfiguration.class);
        CommunicationConfiguration communicationConfiguration1 = new CommunicationConfiguration();
        communicationConfiguration1.setId(1L);
        CommunicationConfiguration communicationConfiguration2 = new CommunicationConfiguration();
        communicationConfiguration2.setId(communicationConfiguration1.getId());
        assertThat(communicationConfiguration1).isEqualTo(communicationConfiguration2);
        communicationConfiguration2.setId(2L);
        assertThat(communicationConfiguration1).isNotEqualTo(communicationConfiguration2);
        communicationConfiguration1.setId(null);
        assertThat(communicationConfiguration1).isNotEqualTo(communicationConfiguration2);
    }
}
