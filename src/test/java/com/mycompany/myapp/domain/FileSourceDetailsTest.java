package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class FileSourceDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileSourceDetails.class);
        FileSourceDetails fileSourceDetails1 = new FileSourceDetails();
        fileSourceDetails1.setId(1L);
        FileSourceDetails fileSourceDetails2 = new FileSourceDetails();
        fileSourceDetails2.setId(fileSourceDetails1.getId());
        assertThat(fileSourceDetails1).isEqualTo(fileSourceDetails2);
        fileSourceDetails2.setId(2L);
        assertThat(fileSourceDetails1).isNotEqualTo(fileSourceDetails2);
        fileSourceDetails1.setId(null);
        assertThat(fileSourceDetails1).isNotEqualTo(fileSourceDetails2);
    }
}
