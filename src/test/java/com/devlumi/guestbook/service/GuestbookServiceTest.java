package com.devlumi.guestbook.service;

import com.devlumi.guestbook.dto.GuestbookDTO;
import com.devlumi.guestbook.dto.PageRequestDTO;
import com.devlumi.guestbook.dto.PageResponseDTO;
import com.devlumi.guestbook.repository.GuestbookRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

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
  public void TestRegister(){
    Long gno = service.register(GuestbookDTO.builder().title("제목쓰기").content("내용쓰기").writer("글쓰기 쓰기").build());
    Assertions.assertNotNull(gno);
    log.info(gno);
  }

  @Test
  public void TestReadOne(){
    long gno = 102L;
    GuestbookDTO dto = service.read(gno);
    GuestbookDTO expect = GuestbookDTO.builder().title("title.....100").content("Content...100").writer("user...100").build();
    log.info(dto);

    Assertions.assertEquals(dto.getTitle(), expect.getTitle());
    Assertions.assertEquals(dto.getContent(), expect.getContent());
    Assertions.assertEquals(dto.getWriter(), expect.getWriter());
  }

  @Test
  public void readAllTest(){
//    List<GuestbookDTO> guestbooks = service.readAll();
//    log.info(guestbooks);
    service.readAll().forEach(log::info);
  }

  @Test
  @Transactional
  @Commit
  public void testModify(){
    Long gno = 102L;
    GuestbookDTO dto = service.read(gno);
    dto.setContent("수정내용");
    service.modify(dto);
  }

  @Test
  public void testRemove(){
    service.remove(1808L);
  }

  @Test
  public void testPageList(){
    PageResponseDTO<?,?> dto = service.getList(PageRequestDTO.builder().page(8).size(5).build());
    log.info(dto);
  }
}
