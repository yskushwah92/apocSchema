package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class CurrencyExchangeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CurrencyExchange.class);
        CurrencyExchange currencyExchange1 = new CurrencyExchange();
        currencyExchange1.setId(1L);
        CurrencyExchange currencyExchange2 = new CurrencyExchange();
        currencyExchange2.setId(currencyExchange1.getId());
        assertThat(currencyExchange1).isEqualTo(currencyExchange2);
        currencyExchange2.setId(2L);
        assertThat(currencyExchange1).isNotEqualTo(currencyExchange2);
        currencyExchange1.setId(null);
        assertThat(currencyExchange1).isNotEqualTo(currencyExchange2);
    }
}
