package ita.springboot.application.fileUploadTests;


import ita.springboot.application.exception.StorageException;
import ita.springboot.application.service.FileStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FileUploadServiceTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private FileStorageService fileStorageService;


    @Test
    public void shouldSaveUploadedFile() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "testupload.txt",
                "text/plain", "Spring Framework".getBytes());
        this.mvc.perform(multipart("/fileupload").file(mockMultipartFile))
                .andExpect(status().isFound())
                .andExpect(header().string("Location","/success"));

        then(this.fileStorageService).should().uploadFile(mockMultipartFile);
    }

}
