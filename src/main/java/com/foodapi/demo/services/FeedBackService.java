package com.foodapi.demo.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.boot.query.FetchDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.FeedBack;
import com.foodapi.demo.models.Product;
import com.foodapi.demo.models.QFeedBack;
import com.foodapi.demo.models.Shop;
import com.foodapi.demo.models.User;
import com.foodapi.demo.models.DTO.FeedBackDto;
import com.foodapi.demo.models.DTO.ShopDto;
import com.foodapi.demo.repositories.FeedBackRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class FeedBackService {
    @Autowired
    private FeedBackRepository feedBackRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    public FeedBack addFeedBack(FeedBack feedBack) {

        return feedBackRepository.save(feedBack);
    }

    public void deleteFeedBack(Integer id) {
        feedBackRepository.deleteById(id);
    }

    public List<FeedBackDto> getByProductId(Integer productId) {
        return convertListFeedBackDtos(feedBackRepository.findByProduct_Id(productId));
    }

    public List<FeedBackDto> convertListFeedBackDtos(List<FeedBack> feedBacks) {
        return feedBacks.stream()
                .map(feedback -> new FeedBackDto(
                        feedback.getId(),
                        feedback.getUser().getId(),
                        feedback.getTime(),
                        feedback.getContent(),
                        feedback.getRateing(),
                        feedback.getProduct().getId()))
                .collect(Collectors.toList());
    }

    public FeedBackDto convertFeedBackDto(FeedBack feedback) {
        return new FeedBackDto(feedback.getId(),
                feedback.getUser().getId(),
                feedback.getTime(),
                feedback.getContent(),
                feedback.getRateing(),
                feedback.getProduct().getId());
    }

    public double averageRating(Integer id) {
        QFeedBack qFeedBack = QFeedBack.feedBack;
        return jpaQueryFactory.select(qFeedBack.rateing.avg())
                .from(qFeedBack)
                .where(qFeedBack.product.id.eq(id))
                .fetchOne();
    }

    public long sumComment(Integer id){
        return feedBackRepository.countByProduct_Id(id);
    }
}
