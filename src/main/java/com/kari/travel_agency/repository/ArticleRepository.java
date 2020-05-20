package com.kari.travel_agency.repository;

import com.kari.travel_agency.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
