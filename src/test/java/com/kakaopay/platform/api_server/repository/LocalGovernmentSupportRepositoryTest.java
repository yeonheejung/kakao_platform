package com.kakaopay.platform.api_server.repository;

import com.kakaopay.platform.api_server.model.LocalGovernment;
import com.kakaopay.platform.api_server.model.LocalGovernmentSupport;
import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentSupportResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LocalGovernmentSupportRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    private LocalGovernmentSupportRepository localGovernmentSupportRepository;

    @Test
    public void LocalGovernmentSupport_Local_Government로_검색_테스트() {

        LocalGovernment localGovernment = LocalGovernment.builder()
                .localGovernmentName("강릉시")
                .build();
        testEntityManager.persist(localGovernment);

        LocalGovernmentSupport localGovernmentSupport = LocalGovernmentSupport.builder()
                .localGovernment(localGovernment)
                .target("강릉시 소재 중소기업으로서 강릉시장이 추천한 자")
                .supportUsage("운전")
                .supportLimit(null)
                .rateMin(new BigDecimal(3.00))
                .rateMax(new BigDecimal(3.00))
                .institute("강릉시")
                .mgmt("강릉지점")
                .reception("강릉시 소재 영업점")
                .build();

        testEntityManager.persist(localGovernmentSupport);

        assertThat(localGovernmentSupportRepository.findByLocalGovernment(localGovernment), is(localGovernmentSupport));
    }

    @Test
    public void LocalGovernmentSupport_지자체명_K개_지원금액_내림차순_정렬_테스트() {

        LocalGovernment localGovernment1 = LocalGovernment.builder()
                .localGovernmentName("강원도")
                .build();

        LocalGovernment localGovernment2 = LocalGovernment.builder()
                .localGovernmentName("광명시")
                .build();

        LocalGovernment localGovernment3 = LocalGovernment.builder()
                .localGovernmentName("남양주시")
                .build();

        LocalGovernment localGovernment4 = LocalGovernment.builder()
                .localGovernmentName("김포시")
                .build();

        testEntityManager.persist(localGovernment1);
        testEntityManager.persist(localGovernment2);
        testEntityManager.persist(localGovernment3);
        testEntityManager.persist(localGovernment4);

        LocalGovernmentSupport localGovernmentSupport1 = LocalGovernmentSupport.builder()
                .localGovernment(localGovernment1)
                .supportLimit(20000L)
                .rateMin(new BigDecimal(1.00))
                .rateMax(new BigDecimal(2.00))
                .build();

        LocalGovernmentSupport localGovernmentSupport2 = LocalGovernmentSupport.builder()
                .localGovernment(localGovernment2)
                .supportLimit(20000L)
                .rateMin(new BigDecimal(1.00))
                .rateMax(new BigDecimal(3.00))
                .build();

        LocalGovernmentSupport localGovernmentSupport3 = LocalGovernmentSupport.builder()
                .localGovernment(localGovernment3)
                .supportLimit(500000L)
                .rateMin(new BigDecimal(2.00))
                .rateMax(new BigDecimal(4.00))
                .build();

        LocalGovernmentSupport localGovernmentSupport4 = LocalGovernmentSupport.builder()
                .localGovernment(localGovernment4)
                .supportLimit(900000L)
                .rateMin(new BigDecimal(3.00))
                .rateMax(new BigDecimal(3.00))
                .build();

        testEntityManager.persist(localGovernmentSupport1);
        testEntityManager.persist(localGovernmentSupport2);
        testEntityManager.persist(localGovernmentSupport3);
        testEntityManager.persist(localGovernmentSupport4);

        List<LocalGovernmentSupport> localGovernmentSupportList = localGovernmentSupportRepository.findByOrderBySupportLimitAndAverageRate(3);

        assertThat(localGovernmentSupportList, hasSize(3));
        assertThat(localGovernmentSupportList.get(0), is(localGovernmentSupport4));
        assertThat(localGovernmentSupportList.get(1), is(localGovernmentSupport3));
        assertThat(localGovernmentSupportList.get(2), is(localGovernmentSupport1));

    }

    @Test
    public void LocalGovernmentSupport_평균보전비율_가장작은_추천기관명() {
        LocalGovernmentSupport localGovernmentSupport1 = LocalGovernmentSupport.builder()
                .rateMin(new BigDecimal(1.00))
                .rateMax(new BigDecimal(2.00))
                .build();

        LocalGovernmentSupport localGovernmentSupport2 = LocalGovernmentSupport.builder()
                .rateMin(new BigDecimal(1.00))
                .rateMax(new BigDecimal(3.00))
                .build();

        LocalGovernmentSupport localGovernmentSupport3 = LocalGovernmentSupport.builder()
                .rateMin(new BigDecimal(2.00))
                .rateMax(new BigDecimal(4.00))
                .build();

        LocalGovernmentSupport localGovernmentSupport4 = LocalGovernmentSupport.builder()
                .rateMin(new BigDecimal(3.00))
                .rateMax(new BigDecimal(3.00))
                .build();

        testEntityManager.persist(localGovernmentSupport1);
        testEntityManager.persist(localGovernmentSupport2);
        testEntityManager.persist(localGovernmentSupport3);
        testEntityManager.persist(localGovernmentSupport4);

        LocalGovernmentSupport localGovernmentSupport = localGovernmentSupportRepository.findTopByOrderByAverageRate();

        assertThat(localGovernmentSupport, is(localGovernmentSupport1));

    }
}
