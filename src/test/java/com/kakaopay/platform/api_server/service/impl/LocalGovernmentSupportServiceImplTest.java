package com.kakaopay.platform.api_server.service.impl;

import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentSupportResponse;
import com.kakaopay.platform.api_server.repository.LocalGovernmentRepository;
import com.kakaopay.platform.api_server.repository.LocalGovernmentSupportRepository;
import com.kakaopay.platform.api_server.service.LocalGovernmentSupportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalGovernmentSupportServiceImplTest {

    @InjectMocks
    LocalGovernmentSupportServiceImpl localGovernmentSupportServiceImpl;

    @Mock
    LocalGovernmentSupportRepository localGovernmentSupportRepository;

    @Mock
    LocalGovernmentRepository localGovernmentRepository;

    @Test
    public void testGetLocalGovernmentByRegion() {
        //given

    }
    @Test
    public void testGetLocalGovernmentSupportList() {

        //given
        given(localGovernmentSupportServiceImpl.getLocalGovernmentSupportList()).willReturn(localGovernmentSupportResponse());

        //when
        final List<LocalGovernmentSupportResponse> localGovernmentSupportResponse = localGovernmentSupportServiceImpl.getLocalGovernmentSupportList();

        //then
        assertThat(localGovernmentSupportResponse, is(notNullValue()));
    }

    @Test
    public void testGetLocalGovernmentSupport() {

        // given
        String region = "강릉시";
        given(localGovernmentSupportServiceImpl.getLocalGovernmentSupport(region)).willReturn(localGovernmentSupportResponse().get(0));

        //when
        final LocalGovernmentSupportResponse localGovernmentSupportResponse = localGovernmentSupportServiceImpl.getLocalGovernmentSupport(region);

        //then
        assertThat(localGovernmentSupportResponse.getId(), is(1L));
        assertThat(localGovernmentSupportResponse.getRegion(), is("강릉시"));
        assertThat(localGovernmentSupportResponse.getTarget(), is("강릉시 소재 중소기업으로서 강릉시장이 추천한 자"));
        assertThat(localGovernmentSupportResponse.getUsage(), is("운전"));
        assertThat(localGovernmentSupportResponse.getLimit(), is("추천금액 이내"));
        assertThat(localGovernmentSupportResponse.getRate(), is("3.00%"));
        assertThat(localGovernmentSupportResponse.getInstitute(), is("강릉시"));
        assertThat(localGovernmentSupportResponse.getMgmt(), is("강릉지점"));
        assertThat(localGovernmentSupportResponse.getReception(), is("강릉시 소재 영업점"));

    }

    private List<LocalGovernmentSupportResponse> localGovernmentSupportResponse() {

        List<LocalGovernmentSupportResponse> localGovernmentSupportResponseList = new ArrayList<>();
        LocalGovernmentSupportResponse localGovernmentSupport1 = new LocalGovernmentSupportResponse(
                1L,
                "강릉시",
                "강릉시 소재 중소기업으로서 강릉시장이 추천한 자",
                "운전",
                "추천금액 이내",
                "3.00%",
                "강릉시",
                "강릉지점",
                "강릉시 소재 영업점");

        LocalGovernmentSupportResponse localGovernmentSupport2 = new LocalGovernmentSupportResponse(
                2L,
                "강원도",
                "강원도 소재 중소기업으로서 강원도지사가 추천한 자",
                "운전",
                "8억원 이내",
                "3%~5%",
                "강원도",
                "춘천지점",
                "강원도 소재 영업점");

        localGovernmentSupportResponseList.add(localGovernmentSupport1);
        localGovernmentSupportResponseList.add(localGovernmentSupport2);

        return localGovernmentSupportResponseList;
    }
}
