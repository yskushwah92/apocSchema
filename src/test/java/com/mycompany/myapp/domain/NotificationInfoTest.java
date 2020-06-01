package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class NotificationInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationInfo.class);
        NotificationInfo notificationInfo1 = new NotificationInfo();
        notificationInfo1.setId(1L);
        NotificationInfo notificationInfo2 = new NotificationInfo();
        notificationInfo2.setId(notificationInfo1.getId());
        assertThat(notificationInfo1).isEqualTo(notificationInfo2);
        notificationInfo2.setId(2L);
        assertThat(notificationInfo1).isNotEqualTo(notificationInfo2);
        notificationInfo1.setId(null);
        assertThat(notificationInfo1).isNotEqualTo(notificationInfo2);
    }
}
