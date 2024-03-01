package com.example.demoservice.service;

import com.example.demoservice.model.cassandra.ClassPost;
import com.example.demoservice.model.cassandra.DemoClass;
import com.example.demoservice.model.payload.DemoClassPayload;
import com.example.demoservice.repository.ClassPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Date-2/29/2024
 * By Sardor Tokhirov
 * Time-3:28 PM (GMT+5)
 */
@Service
@RequiredArgsConstructor
public class ClassPostService {

    private final ClassPostRepository classPostRepository;

public List<DemoClassPayload> getDemoPayload(List<DemoClass> demoClasses) {
    List<UUID> postIds = demoClasses.stream()
            .map(DemoClass::getPostId)
            .collect(Collectors.toList());

    List<ClassPost> classPosts = classPostRepository.findByPostIds(postIds);

    return demoClasses.stream()
            .flatMap(demo -> classPosts.stream()
                    .filter(classPost -> classPost.getPostId().equals(demo.getPostId()))
                    .map(classPost -> new DemoClassPayload(
                            classPost.getPostId(),
                            classPost.getTeacherId(),
                            classPost.getTitle(),
                            classPost.getDemoTime(),
                            classPost.getIntroVideoImgLink()
                            )))
            .collect(Collectors.toList());
}
}
