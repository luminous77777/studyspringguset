package com.devlumi.guestbook.service;

import com.devlumi.guestbook.dto.GuestbookDTO;
import com.devlumi.guestbook.dto.PageRequestDTO;
import com.devlumi.guestbook.dto.PageResponseDTO;
import com.devlumi.guestbook.entity.Guestbook;

import java.util.List;

public interface GuestbookService {

  Long register(GuestbookDTO guestbookDTO);
  GuestbookDTO read(Long gno);
  List<GuestbookDTO> readAll();

  PageResponseDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO);


  void modify(GuestbookDTO guestbookDTO);
  void remove(Long gno);

  default Guestbook toEntity(GuestbookDTO guestbookDTO) {
    return Guestbook.builder()
            .gno(guestbookDTO.getGno())
            .title(guestbookDTO.getTitle())
            .content(guestbookDTO.getContent())
            .writer(guestbookDTO.getWriter())
            .build();
  }

  default GuestbookDTO toDto(Guestbook guestbook) {
    return guestbook == null ? null : GuestbookDTO.builder()
            .gno(guestbook.getGno())
            .title(guestbook.getTitle())
            .content(guestbook.getContent())
            .writer(guestbook.getWriter())
            .regDate(guestbook.getRegDate())
            .modDate(guestbook.getModDate())
            .build();
  }
}
