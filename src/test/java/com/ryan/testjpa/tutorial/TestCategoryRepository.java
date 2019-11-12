package com.ryan.testjpa.tutorial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class TestCategoryRepository {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void getCategory() {
        Category c1 = new Category();
        c1.setParentId(0L);
        c1.setName("category1");
        categoryRepository.save(c1);

        Category c2 = new Category();
        c2.setParentId(c1.getId());
        c2.setName("category2 parent1");
        categoryRepository.save(c2);

        Category c3 = new Category();
        c3.setParentId(c2.getId());
        c3.setName("category3 parent2");
        categoryRepository.save(c3);

        Category c4 = new Category();
        c4.setParentId(c3.getId());
        c4.setName("category4 parent3");
        categoryRepository.save(c4);

        Long id = this.getParentCategory(c4).getId();
        System.out.println("last find id is : " + id);
    }

    private Category getParentCategory(Category category) {
        System.out.println("==========");
        System.out.println("parent id : " + category.getParentId());
        System.out.println(category.getName());
        if(category.getParentId() == 0L) {
            return category;
        } else {
            System.out.println("parent id is not Top Category");
            Optional<Category> parentCategory = categoryRepository.findById(category.getParentId());
            return this.getParentCategory(parentCategory.get());
        }
    }

}
