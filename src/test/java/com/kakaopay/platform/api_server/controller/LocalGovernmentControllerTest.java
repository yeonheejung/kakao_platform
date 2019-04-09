package com.kakaopay.platform.api_server.controller;


import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentSupportResponse;
import com.kakaopay.platform.api_server.service.LocalGovernmentSupportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@WebMvcTest(LocalGovernmentSupportController.class)
public class LocalGovernmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LocalGovernmentSupportService localGovernmentSupportService;

    @InjectMocks
    private LocalGovernmentSupportController localGovernmentSupportController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(localGovernmentSupportController)
                .build();
    }

    @Test
    public void testGetLocalGovernmentSupportList() throws Exception {

        //given
        given(localGovernmentSupportService.getLocalGovernmentSupportList()).willReturn(localGovernmentSupportResponse());

        mockMvc.perform(get("/api/v1/local-government-support"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }

    @Test
    public void testGetLocalGovernmentSupport() throws Exception {
        //given
        given(localGovernmentSupportService.getLocalGovernmentSupport("강릉시")).willReturn(localGovernmentSupportResponse().get(0));

        //when
        final ResultActions actions = mockMvc.perform(get("/api/v1/local-government-support/search")
                .param("region", "강릉시")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
        //then
        actions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data.region").value("강릉시"))
                .andDo(print());
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
