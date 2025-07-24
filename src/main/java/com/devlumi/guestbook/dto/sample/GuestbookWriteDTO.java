package com.devlumi.guestbook.dto.sample;

import com.devlumi.guestbook.entity.Guestbook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GuestbookWriteDTO {
  private Long gno;
  private String title;
  private String content;
  private String writer;

  public GuestbookWriteDTO(Guestbook guestbook) {
    this.gno = guestbook.getGno();
    this.title = guestbook.getTitle();
    this.content = guestbook.getContent();
    this.writer = guestbook.getWriter();
  }

  public Guestbook toEntity() {//여기서 예외 처리
    return Guestbook.builder().content(content).title(title).writer(writer).build();
  }
}
