package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ShippingLocationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShippingLocation.class);
        ShippingLocation shippingLocation1 = new ShippingLocation();
        shippingLocation1.setId(1L);
        ShippingLocation shippingLocation2 = new ShippingLocation();
        shippingLocation2.setId(shippingLocation1.getId());
        assertThat(shippingLocation1).isEqualTo(shippingLocation2);
        shippingLocation2.setId(2L);
        assertThat(shippingLocation1).isNotEqualTo(shippingLocation2);
        shippingLocation1.setId(null);
        assertThat(shippingLocation1).isNotEqualTo(shippingLocation2);
    }
}
