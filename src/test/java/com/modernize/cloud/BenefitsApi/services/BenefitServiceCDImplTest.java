package com.modernize.cloud.BenefitsApi.services;


import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.modernize.cloud.BenefitsApi.model.BenefitCD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BenefitServiceCDImplTest {

    public static final Long ID = 2L;
    public static final String DESCRIPTION = "A new description.";

    BenefitServiceCD benefitService;

    Database benefitRepository = mock(Database.class, RETURNS_DEEP_STUBS);


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        benefitService = new BenefitServiceCDImpl(benefitRepository);
    }

    @Test
    void findAll() throws Exception {
        //given
        List<BenefitCD> benefitList = Arrays.asList(new BenefitCD(), new BenefitCD(), new BenefitCD());

        when(benefitRepository
                .getAllDocsRequestBuilder()
                .includeDocs(true)
                .build()
                .getResponse()
                .getDocsAs(BenefitCD.class))
                .thenReturn(benefitList);

        //when
        List<BenefitCD> benefitResponseList = benefitRepository
                .getAllDocsRequestBuilder()
                .includeDocs(true)
                .build()
                .getResponse()
                .getDocsAs(BenefitCD.class);

        //then
        assertEquals(3, benefitResponseList.size());
    }

    @Test
    void findById() throws Exception {
        //given
        String ID = "myId";

        BenefitCD savedBenefit = new BenefitCD();
        savedBenefit.setDescription(DESCRIPTION);
        savedBenefit.set_id(ID);

        when(benefitRepository.find(BenefitCD.class, ID)).thenReturn(savedBenefit);

        //when
        BenefitCD benefitResponse = benefitService.findById(ID);

        //then
        assertEquals(savedBenefit.getDescription(), benefitResponse.getDescription());
    }

    @Test
    void save() throws Exception {

        String ID = "myId";

        //given
        BenefitCD benefitRequest = new BenefitCD();
        benefitRequest.setDescription(DESCRIPTION);

        BenefitCD savedBenefit = new BenefitCD();
        savedBenefit.setDescription(DESCRIPTION);
        savedBenefit.set_id(ID);

        when(benefitRepository.post(benefitRequest)).thenReturn(new Response());

        //when
        benefitService.save(benefitRequest);

        //then
        verify(benefitRepository, times(1)).post(any(BenefitCD.class));
    }

    @Test
    void update() {

        String ID = "myId";
        String REV = "rev1";

        //given
        BenefitCD benefitRequest = new BenefitCD();
        benefitRequest.setDescription(DESCRIPTION);
        benefitRequest.set_id(ID);
        benefitRequest.set_rev(REV);

        BenefitCD benefit = new BenefitCD();
        benefit.setDescription(DESCRIPTION);
        benefit.set_id(ID);

        when(benefitRepository.update(benefitRequest)).thenReturn(new Response());

        //when
        benefitService.update(ID, benefitRequest);

        //then
        verify(benefitRepository, times(1)).update(any(BenefitCD.class));
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
        String ID = "myId";
        String REV = "rev1";

        BenefitCD benefitRequest = new BenefitCD();
        benefitRequest.set_id(ID);
        benefitRequest.set_rev(REV);
        benefitRequest.setDescription(DESCRIPTION);

        when(benefitRepository.find(BenefitCD.class, ID)).thenReturn(benefitRequest);

        benefitService.deleteById(ID);

        verify(benefitRepository, times(1))
                .remove(benefitRequest.get_id(), benefitRequest.get_rev());
    }
}