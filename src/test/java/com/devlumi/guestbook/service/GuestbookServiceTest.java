package com.devlumi.guestbook.service;

import com.devlumi.guestbook.dto.GuestbookDTO;
import com.devlumi.guestbook.repository.GuestbookRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest
public class GuestbookServiceTest {
  @Autowired
  private GuestbookService service;

  @Test
  public void testExist(){
    log.info(service);
  }

  @Test
  public void registerTest(){
    Long gno = service.register(GuestbookDTO.builder().title("제목쓰기").content("내용쓰기").writer("글쓰기 쓰기").build());
    Assertions.assertNotNull(gno);
    log.info(gno);
  }

  @Test
  public void readOneTest(){
    long gno = 102L;
    GuestbookDTO guestboot = service.read(gno);
    log.info(guestboot);
    Assertions.assertEquals(102L, guestboot.getGno());
    Assertions.assertEquals("title.....100",guestboot.getTitle());
    Assertions.assertEquals("Content...100",guestboot.getContent());
    Assertions.assertEquals("user...100",guestboot.getWriter());
  }

  @Test
  public void readAllTest(){
    List<GuestbookDTO> guestbooks = service.readAll();
    log.info(guestbooks);
  }
}
