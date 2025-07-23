package com.devlumi.guestbook.repository;

import com.devlumi.guestbook.entity.Guestbook;

import com.devlumi.guestbook.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class GuestbookRepositoryTest {

  @Autowired
  private GuestbookRepository repository;


  @Test
  public void testExist(){
    log.info(repository);
  }

  @Test
  public void testInsert(){
    repository.save(Guestbook.builder().title("제목").content("내용").writer("작성자").build());
  }

  @Test
  public void insertDummies(){
    IntStream.rangeClosed(1,300).forEach(i->{

      Guestbook guestbook = Guestbook.builder()
              .title("title....." + i)
              .content("Content..." + i)
              .writer("user..." + i)
              .build();

      log.info(repository.save(guestbook));
    });
  }

  @Test
  public void testQuerty1(){
    Pageable pageable =  PageRequest.of(0, 10, Sort.by("gno"));

    QGuestbook qGuestbook = QGuestbook.guestbook;

    String keyword = "1";

    BooleanBuilder builder = new BooleanBuilder();

    BooleanExpression expression = qGuestbook.title.contains(keyword);

    builder.and(expression);

    Page<Guestbook> result = repository.findAll(builder, pageable);

    result.stream().forEach(guestbook -> log.info("{}",guestbook));
  }

  @Test
  public void testQuerty2(){
    Pageable pageable =  PageRequest.of(0, 10, Sort.by("gno"));

    QGuestbook qGuestbook = QGuestbook.guestbook;

    String keyword = "1";

    BooleanBuilder builder = new BooleanBuilder();

    BooleanExpression expression1 = qGuestbook.title.contains(keyword);
    BooleanExpression expression2 = qGuestbook.content.contains(keyword);
    BooleanExpression be = expression1.or(expression2);
    builder.and(be);
    builder.and(qGuestbook.gno.gt(0));

    Page<Guestbook> result = repository.findAll(builder, pageable);

    result.stream().forEach(guestbook -> log.info("{}",guestbook));
  }
}
