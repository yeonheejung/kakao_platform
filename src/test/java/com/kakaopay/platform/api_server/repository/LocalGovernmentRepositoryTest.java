package com.kakaopay.platform.api_server.repository;

import com.kakaopay.platform.api_server.model.LocalGovernment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LocalGovernmentRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private LocalGovernmentRepository localGovernmentRepository;

    @Test
    public void LocalGovernment_이름으로_검색_테스트() {

        LocalGovernment localGovernment = LocalGovernment.builder()
                .localGovernmentName("테스트지자체명")
                .build();
        testEntityManager.persist(localGovernment);

        assertThat(localGovernmentRepository.findByLocalGovernmentName("테스트지자체명"), is(localGovernment));

    }
}
