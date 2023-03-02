package com.portfolio.blog.serviceImpl;

import com.portfolio.blog.dto.PostReplyDTO;
import com.portfolio.blog.entity.PostReply;
import com.portfolio.blog.repository.PostReplyRepository;
import com.portfolio.blog.service.PostReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class PostReplyServiceImpl implements PostReplyService {
        private  final PostReplyRepository postReplyRepository;
    @Override
    public void savePostReply(PostReplyDTO postReplyDTO){
        PostReply postReply =  postReplyDTO.createPostReply();
        postReplyRepository.save(postReply);
    }

    @Override
    public List<PostReply> findByBlogPost_pnum(Long pnum) {
        return postReplyRepository.findByBlogPost_pnum(pnum);
    }
}
