package br.com.alura.comex.comex.controllers;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AutenticacaoControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        public void shouldReturn400HttpErrorIfUserDataIsWrong() throws Exception {
                URI uri = new URI("/auth");
                String json = new JSONObject()
                                .put("email", "teste@1234.email.com")
                                .put("senha", "123456")
                                .toString();

                mockMvc.perform(MockMvcRequestBuilders
                                .post(uri)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().is(400));
        }

        @Test
        public void shouldReturn200IfAllIsRight() throws Exception {
                URI uri = new URI("/auth");
                String json = new JSONObject()
                                .put("email", "teste@teste.com")
                                .put("senha", "123456")
                                .toString();

                mockMvc.perform(MockMvcRequestBuilders
                                .post(uri)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().is(200));
        }

        @Test
        public void shouldReturn400HttpErrorForUserDataIsIncomplete() throws Exception {
                URI uri = new URI("/auth");
                String json = new JSONObject()
                                .put("email", "teste@1234.email.com")
                                .toString();

                mockMvc.perform(MockMvcRequestBuilders
                                .post(uri)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().is(400));
        }

}
