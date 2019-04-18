package ita.springboot.application.fileUploadTests;

import ita.springboot.application.service.FileStorageService;
import ita.springboot.application.web.FileUploadController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.InputStream;


@RunWith(SpringRunner.class)
public class FileUploadIntegrationTests {


    private InputStream is;

    private MockMvc mockMvc;

    @MockBean
    private FileStorageService fileStorageService;

    @Spy
    @InjectMocks
    private FileUploadController fileUploadController  = new FileUploadController();

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(fileUploadController).build();
        is = fileUploadController.getClass().getClassLoader().getResourceAsStream("E:\\Šola\\registration-login-springboot-security-thymeleaf\\src\\test\\java\\resources\\iris.csv");
    }

    @Test
    public void testUploadFile() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "iris.csv", "multipart/form-data", is);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/fileupload").file(mockMultipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertNotNull(result.getResponse().getContentAsString());
        Assert.assertEquals("iris.csv", result.getResponse().getContentAsString());
    }

}



