package com.devlumi.guestbook;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@Log4j2
@EnableJpaAuditing
public class GuestApplication {

  public static void main(String[] args) {
    SpringApplication.run(GuestApplication.class, args);
    log.info("GuestbookApplication started 로그 출력");
  }

}
