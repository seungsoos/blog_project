package com.portfolio.blog.api;

import com.portfolio.blog.dto.MemberSearchDTO;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.MemberRepositoryCostom;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog-information-api")
public class BolgApi {
    private static final Logger logger = LoggerFactory.getLogger(BolgApi.class);

    final private MemberRepositoryCostom memberRepositoryCostom;

    @PostMapping("/memberSearch")
    public ResponseEntity<Page<Member>> memberSearch(@RequestBody @NonNull HashMap<String, String> map, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5, page = 0)
    Pageable pageable){

        logger.info(""+map.get("searchBy"));
        logger.info(""+map.get("searchQuery"));

        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");

        MemberSearchDTO memberSearchDTO = new MemberSearchDTO();


        memberSearchDTO.setSearchBy(map.get("searchBy"));
        memberSearchDTO.setSearchQuery(map.get("searchQuery"));
        logger.info(""+memberSearchDTO);


        Page<Member> members = memberRepositoryCostom.getMemberList(memberSearchDTO, pageable);

        return new ResponseEntity<Page<Member>>(members, resHeaders, HttpStatus.OK);
    }

    @PostMapping("/add")
    public String test1(){
        logger.info("check....");
        return "home";
    }

}
