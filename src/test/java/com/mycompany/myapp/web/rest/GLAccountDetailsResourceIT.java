package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.GLAccountDetails;
import com.mycompany.myapp.repository.GLAccountDetailsRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link GLAccountDetailsResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GLAccountDetailsResourceIT {

    private static final String DEFAULT_ACCOUNT_NO = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_CHART_OF_ACCOUNTS = "AAAAAAAAAA";
    private static final String UPDATED_CHART_OF_ACCOUNTS = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_ID = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_TYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_BALANCE_SHEET = false;
    private static final Boolean UPDATED_IS_BALANCE_SHEET = true;

    private static final String DEFAULT_TEXT_FOR_CRITERION = "AAAAAAAAAA";
    private static final String UPDATED_TEXT_FOR_CRITERION = "BBBBBBBBBB";

    private static final String DEFAULT_SOME_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_SOME_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_CAT_TAX_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TAX_CAT_TAX_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCYKEY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCYKEY = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVE_COA = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVE_COA = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVE_CO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVE_CO_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_POSTING_WITHOUT_TAX_ALLOWED = "AAAAAAAAAA";
    private static final String UPDATED_POSTING_WITHOUT_TAX_ALLOWED = "BBBBBBBBBB";

    private static final String DEFAULT_POSTING_BLOCK = "AAAAAAAAAA";
    private static final String UPDATED_POSTING_BLOCK = "BBBBBBBBBB";

    private static final String DEFAULT_POST_AT_PROFIT_CENTRE = "AAAAAAAAAA";
    private static final String UPDATED_POST_AT_PROFIT_CENTRE = "BBBBBBBBBB";

    private static final String DEFAULT_POST_AT_COST_CENTRE = "AAAAAAAAAA";
    private static final String UPDATED_POST_AT_COST_CENTRE = "BBBBBBBBBB";

    private static final String DEFAULT_POST_AT_SEGMENT = "AAAAAAAAAA";
    private static final String UPDATED_POST_AT_SEGMENT = "BBBBBBBBBB";

    private static final String DEFAULT_POST_AT_INTERNAL_ORDER = "AAAAAAAAAA";
    private static final String UPDATED_POST_AT_INTERNAL_ORDER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private GLAccountDetailsRepository gLAccountDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGLAccountDetailsMockMvc;

    private GLAccountDetails gLAccountDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GLAccountDetails createEntity(EntityManager em) {
        GLAccountDetails gLAccountDetails = new GLAccountDetails()
            .accountNo(DEFAULT_ACCOUNT_NO)
            .country(DEFAULT_COUNTRY)
            .chartOfAccounts(DEFAULT_CHART_OF_ACCOUNTS)
            .categoryId(DEFAULT_CATEGORY_ID)
            .accountType(DEFAULT_ACCOUNT_TYPE)
            .isBalanceSheet(DEFAULT_IS_BALANCE_SHEET)
            .textForCriterion(DEFAULT_TEXT_FOR_CRITERION)
            .someText(DEFAULT_SOME_TEXT)
            .taxCatTaxCode(DEFAULT_TAX_CAT_TAX_CODE)
            .currencykey(DEFAULT_CURRENCYKEY)
            .activeCOA(DEFAULT_ACTIVE_COA)
            .activeCoCode(DEFAULT_ACTIVE_CO_CODE)
            .postingWithoutTaxAllowed(DEFAULT_POSTING_WITHOUT_TAX_ALLOWED)
            .postingBlock(DEFAULT_POSTING_BLOCK)
            .postAtProfitCentre(DEFAULT_POST_AT_PROFIT_CENTRE)
            .postAtCostCentre(DEFAULT_POST_AT_COST_CENTRE)
            .postAtSegment(DEFAULT_POST_AT_SEGMENT)
            .postAtInternalOrder(DEFAULT_POST_AT_INTERNAL_ORDER)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return gLAccountDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GLAccountDetails createUpdatedEntity(EntityManager em) {
        GLAccountDetails gLAccountDetails = new GLAccountDetails()
            .accountNo(UPDATED_ACCOUNT_NO)
            .country(UPDATED_COUNTRY)
            .chartOfAccounts(UPDATED_CHART_OF_ACCOUNTS)
            .categoryId(UPDATED_CATEGORY_ID)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .isBalanceSheet(UPDATED_IS_BALANCE_SHEET)
            .textForCriterion(UPDATED_TEXT_FOR_CRITERION)
            .someText(UPDATED_SOME_TEXT)
            .taxCatTaxCode(UPDATED_TAX_CAT_TAX_CODE)
            .currencykey(UPDATED_CURRENCYKEY)
            .activeCOA(UPDATED_ACTIVE_COA)
            .activeCoCode(UPDATED_ACTIVE_CO_CODE)
            .postingWithoutTaxAllowed(UPDATED_POSTING_WITHOUT_TAX_ALLOWED)
            .postingBlock(UPDATED_POSTING_BLOCK)
            .postAtProfitCentre(UPDATED_POST_AT_PROFIT_CENTRE)
            .postAtCostCentre(UPDATED_POST_AT_COST_CENTRE)
            .postAtSegment(UPDATED_POST_AT_SEGMENT)
            .postAtInternalOrder(UPDATED_POST_AT_INTERNAL_ORDER)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return gLAccountDetails;
    }

    @BeforeEach
    public void initTest() {
        gLAccountDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createGLAccountDetails() throws Exception {
        int databaseSizeBeforeCreate = gLAccountDetailsRepository.findAll().size();
        // Create the GLAccountDetails
        restGLAccountDetailsMockMvc.perform(post("/api/gl-account-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gLAccountDetails)))
            .andExpect(status().isCreated());

        // Validate the GLAccountDetails in the database
        List<GLAccountDetails> gLAccountDetailsList = gLAccountDetailsRepository.findAll();
        assertThat(gLAccountDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        GLAccountDetails testGLAccountDetails = gLAccountDetailsList.get(gLAccountDetailsList.size() - 1);
        assertThat(testGLAccountDetails.getAccountNo()).isEqualTo(DEFAULT_ACCOUNT_NO);
        assertThat(testGLAccountDetails.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testGLAccountDetails.getChartOfAccounts()).isEqualTo(DEFAULT_CHART_OF_ACCOUNTS);
        assertThat(testGLAccountDetails.getCategoryId()).isEqualTo(DEFAULT_CATEGORY_ID);
        assertThat(testGLAccountDetails.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testGLAccountDetails.isIsBalanceSheet()).isEqualTo(DEFAULT_IS_BALANCE_SHEET);
        assertThat(testGLAccountDetails.getTextForCriterion()).isEqualTo(DEFAULT_TEXT_FOR_CRITERION);
        assertThat(testGLAccountDetails.getSomeText()).isEqualTo(DEFAULT_SOME_TEXT);
        assertThat(testGLAccountDetails.getTaxCatTaxCode()).isEqualTo(DEFAULT_TAX_CAT_TAX_CODE);
        assertThat(testGLAccountDetails.getCurrencykey()).isEqualTo(DEFAULT_CURRENCYKEY);
        assertThat(testGLAccountDetails.getActiveCOA()).isEqualTo(DEFAULT_ACTIVE_COA);
        assertThat(testGLAccountDetails.getActiveCoCode()).isEqualTo(DEFAULT_ACTIVE_CO_CODE);
        assertThat(testGLAccountDetails.getPostingWithoutTaxAllowed()).isEqualTo(DEFAULT_POSTING_WITHOUT_TAX_ALLOWED);
        assertThat(testGLAccountDetails.getPostingBlock()).isEqualTo(DEFAULT_POSTING_BLOCK);
        assertThat(testGLAccountDetails.getPostAtProfitCentre()).isEqualTo(DEFAULT_POST_AT_PROFIT_CENTRE);
        assertThat(testGLAccountDetails.getPostAtCostCentre()).isEqualTo(DEFAULT_POST_AT_COST_CENTRE);
        assertThat(testGLAccountDetails.getPostAtSegment()).isEqualTo(DEFAULT_POST_AT_SEGMENT);
        assertThat(testGLAccountDetails.getPostAtInternalOrder()).isEqualTo(DEFAULT_POST_AT_INTERNAL_ORDER);
        assertThat(testGLAccountDetails.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testGLAccountDetails.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testGLAccountDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createGLAccountDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gLAccountDetailsRepository.findAll().size();

        // Create the GLAccountDetails with an existing ID
        gLAccountDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGLAccountDetailsMockMvc.perform(post("/api/gl-account-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gLAccountDetails)))
            .andExpect(status().isBadRequest());

        // Validate the GLAccountDetails in the database
        List<GLAccountDetails> gLAccountDetailsList = gLAccountDetailsRepository.findAll();
        assertThat(gLAccountDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGLAccountDetails() throws Exception {
        // Initialize the database
        gLAccountDetailsRepository.saveAndFlush(gLAccountDetails);

        // Get all the gLAccountDetailsList
        restGLAccountDetailsMockMvc.perform(get("/api/gl-account-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gLAccountDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountNo").value(hasItem(DEFAULT_ACCOUNT_NO)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].chartOfAccounts").value(hasItem(DEFAULT_CHART_OF_ACCOUNTS)))
            .andExpect(jsonPath("$.[*].categoryId").value(hasItem(DEFAULT_CATEGORY_ID)))
            .andExpect(jsonPath("$.[*].accountType").value(hasItem(DEFAULT_ACCOUNT_TYPE)))
            .andExpect(jsonPath("$.[*].isBalanceSheet").value(hasItem(DEFAULT_IS_BALANCE_SHEET.booleanValue())))
            .andExpect(jsonPath("$.[*].textForCriterion").value(hasItem(DEFAULT_TEXT_FOR_CRITERION)))
            .andExpect(jsonPath("$.[*].someText").value(hasItem(DEFAULT_SOME_TEXT)))
            .andExpect(jsonPath("$.[*].taxCatTaxCode").value(hasItem(DEFAULT_TAX_CAT_TAX_CODE)))
            .andExpect(jsonPath("$.[*].currencykey").value(hasItem(DEFAULT_CURRENCYKEY)))
            .andExpect(jsonPath("$.[*].activeCOA").value(hasItem(DEFAULT_ACTIVE_COA)))
            .andExpect(jsonPath("$.[*].activeCoCode").value(hasItem(DEFAULT_ACTIVE_CO_CODE)))
            .andExpect(jsonPath("$.[*].postingWithoutTaxAllowed").value(hasItem(DEFAULT_POSTING_WITHOUT_TAX_ALLOWED)))
            .andExpect(jsonPath("$.[*].postingBlock").value(hasItem(DEFAULT_POSTING_BLOCK)))
            .andExpect(jsonPath("$.[*].postAtProfitCentre").value(hasItem(DEFAULT_POST_AT_PROFIT_CENTRE)))
            .andExpect(jsonPath("$.[*].postAtCostCentre").value(hasItem(DEFAULT_POST_AT_COST_CENTRE)))
            .andExpect(jsonPath("$.[*].postAtSegment").value(hasItem(DEFAULT_POST_AT_SEGMENT)))
            .andExpect(jsonPath("$.[*].postAtInternalOrder").value(hasItem(DEFAULT_POST_AT_INTERNAL_ORDER)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getGLAccountDetails() throws Exception {
        // Initialize the database
        gLAccountDetailsRepository.saveAndFlush(gLAccountDetails);

        // Get the gLAccountDetails
        restGLAccountDetailsMockMvc.perform(get("/api/gl-account-details/{id}", gLAccountDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gLAccountDetails.getId().intValue()))
            .andExpect(jsonPath("$.accountNo").value(DEFAULT_ACCOUNT_NO))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.chartOfAccounts").value(DEFAULT_CHART_OF_ACCOUNTS))
            .andExpect(jsonPath("$.categoryId").value(DEFAULT_CATEGORY_ID))
            .andExpect(jsonPath("$.accountType").value(DEFAULT_ACCOUNT_TYPE))
            .andExpect(jsonPath("$.isBalanceSheet").value(DEFAULT_IS_BALANCE_SHEET.booleanValue()))
            .andExpect(jsonPath("$.textForCriterion").value(DEFAULT_TEXT_FOR_CRITERION))
            .andExpect(jsonPath("$.someText").value(DEFAULT_SOME_TEXT))
            .andExpect(jsonPath("$.taxCatTaxCode").value(DEFAULT_TAX_CAT_TAX_CODE))
            .andExpect(jsonPath("$.currencykey").value(DEFAULT_CURRENCYKEY))
            .andExpect(jsonPath("$.activeCOA").value(DEFAULT_ACTIVE_COA))
            .andExpect(jsonPath("$.activeCoCode").value(DEFAULT_ACTIVE_CO_CODE))
            .andExpect(jsonPath("$.postingWithoutTaxAllowed").value(DEFAULT_POSTING_WITHOUT_TAX_ALLOWED))
            .andExpect(jsonPath("$.postingBlock").value(DEFAULT_POSTING_BLOCK))
            .andExpect(jsonPath("$.postAtProfitCentre").value(DEFAULT_POST_AT_PROFIT_CENTRE))
            .andExpect(jsonPath("$.postAtCostCentre").value(DEFAULT_POST_AT_COST_CENTRE))
            .andExpect(jsonPath("$.postAtSegment").value(DEFAULT_POST_AT_SEGMENT))
            .andExpect(jsonPath("$.postAtInternalOrder").value(DEFAULT_POST_AT_INTERNAL_ORDER))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingGLAccountDetails() throws Exception {
        // Get the gLAccountDetails
        restGLAccountDetailsMockMvc.perform(get("/api/gl-account-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGLAccountDetails() throws Exception {
        // Initialize the database
        gLAccountDetailsRepository.saveAndFlush(gLAccountDetails);

        int databaseSizeBeforeUpdate = gLAccountDetailsRepository.findAll().size();

        // Update the gLAccountDetails
        GLAccountDetails updatedGLAccountDetails = gLAccountDetailsRepository.findById(gLAccountDetails.getId()).get();
        // Disconnect from session so that the updates on updatedGLAccountDetails are not directly saved in db
        em.detach(updatedGLAccountDetails);
        updatedGLAccountDetails
            .accountNo(UPDATED_ACCOUNT_NO)
            .country(UPDATED_COUNTRY)
            .chartOfAccounts(UPDATED_CHART_OF_ACCOUNTS)
            .categoryId(UPDATED_CATEGORY_ID)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .isBalanceSheet(UPDATED_IS_BALANCE_SHEET)
            .textForCriterion(UPDATED_TEXT_FOR_CRITERION)
            .someText(UPDATED_SOME_TEXT)
            .taxCatTaxCode(UPDATED_TAX_CAT_TAX_CODE)
            .currencykey(UPDATED_CURRENCYKEY)
            .activeCOA(UPDATED_ACTIVE_COA)
            .activeCoCode(UPDATED_ACTIVE_CO_CODE)
            .postingWithoutTaxAllowed(UPDATED_POSTING_WITHOUT_TAX_ALLOWED)
            .postingBlock(UPDATED_POSTING_BLOCK)
            .postAtProfitCentre(UPDATED_POST_AT_PROFIT_CENTRE)
            .postAtCostCentre(UPDATED_POST_AT_COST_CENTRE)
            .postAtSegment(UPDATED_POST_AT_SEGMENT)
            .postAtInternalOrder(UPDATED_POST_AT_INTERNAL_ORDER)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restGLAccountDetailsMockMvc.perform(put("/api/gl-account-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGLAccountDetails)))
            .andExpect(status().isOk());

        // Validate the GLAccountDetails in the database
        List<GLAccountDetails> gLAccountDetailsList = gLAccountDetailsRepository.findAll();
        assertThat(gLAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
        GLAccountDetails testGLAccountDetails = gLAccountDetailsList.get(gLAccountDetailsList.size() - 1);
        assertThat(testGLAccountDetails.getAccountNo()).isEqualTo(UPDATED_ACCOUNT_NO);
        assertThat(testGLAccountDetails.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testGLAccountDetails.getChartOfAccounts()).isEqualTo(UPDATED_CHART_OF_ACCOUNTS);
        assertThat(testGLAccountDetails.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);
        assertThat(testGLAccountDetails.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testGLAccountDetails.isIsBalanceSheet()).isEqualTo(UPDATED_IS_BALANCE_SHEET);
        assertThat(testGLAccountDetails.getTextForCriterion()).isEqualTo(UPDATED_TEXT_FOR_CRITERION);
        assertThat(testGLAccountDetails.getSomeText()).isEqualTo(UPDATED_SOME_TEXT);
        assertThat(testGLAccountDetails.getTaxCatTaxCode()).isEqualTo(UPDATED_TAX_CAT_TAX_CODE);
        assertThat(testGLAccountDetails.getCurrencykey()).isEqualTo(UPDATED_CURRENCYKEY);
        assertThat(testGLAccountDetails.getActiveCOA()).isEqualTo(UPDATED_ACTIVE_COA);
        assertThat(testGLAccountDetails.getActiveCoCode()).isEqualTo(UPDATED_ACTIVE_CO_CODE);
        assertThat(testGLAccountDetails.getPostingWithoutTaxAllowed()).isEqualTo(UPDATED_POSTING_WITHOUT_TAX_ALLOWED);
        assertThat(testGLAccountDetails.getPostingBlock()).isEqualTo(UPDATED_POSTING_BLOCK);
        assertThat(testGLAccountDetails.getPostAtProfitCentre()).isEqualTo(UPDATED_POST_AT_PROFIT_CENTRE);
        assertThat(testGLAccountDetails.getPostAtCostCentre()).isEqualTo(UPDATED_POST_AT_COST_CENTRE);
        assertThat(testGLAccountDetails.getPostAtSegment()).isEqualTo(UPDATED_POST_AT_SEGMENT);
        assertThat(testGLAccountDetails.getPostAtInternalOrder()).isEqualTo(UPDATED_POST_AT_INTERNAL_ORDER);
        assertThat(testGLAccountDetails.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testGLAccountDetails.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGLAccountDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingGLAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = gLAccountDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGLAccountDetailsMockMvc.perform(put("/api/gl-account-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gLAccountDetails)))
            .andExpect(status().isBadRequest());

        // Validate the GLAccountDetails in the database
        List<GLAccountDetails> gLAccountDetailsList = gLAccountDetailsRepository.findAll();
        assertThat(gLAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGLAccountDetails() throws Exception {
        // Initialize the database
        gLAccountDetailsRepository.saveAndFlush(gLAccountDetails);

        int databaseSizeBeforeDelete = gLAccountDetailsRepository.findAll().size();

        // Delete the gLAccountDetails
        restGLAccountDetailsMockMvc.perform(delete("/api/gl-account-details/{id}", gLAccountDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GLAccountDetails> gLAccountDetailsList = gLAccountDetailsRepository.findAll();
        assertThat(gLAccountDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
