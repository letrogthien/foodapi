package com.foodapi.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.FeedBack;
import com.foodapi.demo.models.Shop;
import com.foodapi.demo.models.DTO.FeedBackDto;
import com.foodapi.demo.models.DTO.ShopDto;
import com.foodapi.demo.repositories.FeedBackRepository;

@Service
public class FeedBackService {
    @Autowired
    FeedBackRepository feedBackRepository;




    



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
}
