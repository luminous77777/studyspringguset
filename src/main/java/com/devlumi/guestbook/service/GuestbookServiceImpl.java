package com.devlumi.guestbook.service;


import com.devlumi.guestbook.dto.GuestbookDTO;
import com.devlumi.guestbook.dto.PageRequestDTO;
import com.devlumi.guestbook.dto.PageResponseDTO;
import com.devlumi.guestbook.entity.Guestbook;
import com.devlumi.guestbook.entity.QGuestbook;
import com.devlumi.guestbook.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class GuestbookServiceImpl implements GuestbookService {
  GuestbookRepository repository;

  private final GuestbookRepository guestbookRepository;

  public Long register(GuestbookDTO guestbookDTO) {
    return repository.save(toEntity(guestbookDTO)).getGno();
  }


//  public Long register(GuestbookWriteDTO dto) {
//    return repository.save(dto.toEntity()).getGno();
//  }


  public GuestbookDTO read(Long gno) {
    return toDto(repository.findById(gno).orElse(null));
  }

  @Transactional(readOnly = true)
  public List<GuestbookDTO> readAll() {
    return repository.findAll(Sort.by(Sort.Direction.DESC, "gno")).stream().map(this::toDto).toList();
  }

  @Override
  public PageResponseDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO) {
    BooleanBuilder getSearch = getSearch(pageRequestDTO);
    return new PageResponseDTO<>(repository.findAll(getSearch, pageRequestDTO.getPageable(Sort.by(Sort.Direction.DESC, "gno"))),
            this::toDto);

  }


//  public List<GuestbookDTO> readAll() {return repository.findAll().stream().map(GuestbookListDTO::new).toList();}


  public void modify(GuestbookDTO guestbookDTO) {
    repository.save(toEntity(guestbookDTO));
  }


  public void remove(Long gno) {
      repository.deleteById(gno);
  }

  private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
    String type =  pageRequestDTO.getType();

    BooleanBuilder booleanBuilder = new BooleanBuilder();

    QGuestbook qGuestbook = QGuestbook.guestbook;

    String keyword = pageRequestDTO.getKeyword();

    BooleanExpression expression = qGuestbook.gno.gt(0L);

    booleanBuilder.and(expression);

    if(type == null || type.trim().length() == 0){
      return booleanBuilder;
    }

    BooleanBuilder conditionBuilder = new BooleanBuilder();

    if(type.contains("t")){
      conditionBuilder.or(qGuestbook.title.contains(keyword));
    }

    if(type.contains("c")){
      conditionBuilder.or(qGuestbook.content.contains(keyword));
    }

    if(type.contains("w")){
      conditionBuilder.or(qGuestbook.writer.contains(keyword));
    }

    booleanBuilder.and(conditionBuilder);

    return booleanBuilder;
   }
}
