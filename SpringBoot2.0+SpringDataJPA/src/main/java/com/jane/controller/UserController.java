package com.jane.controller;

import com.jane.dao.UserRepository;
import com.jane.model.User;
import com.jane.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.util.List;

/**
 * Created by Janus on 2018/9/8.
 */
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServcie userServcie;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){

        User user = userRepository.getOne(id);
        System.out.println(user);
        List<User> list = userRepository.findByEmail("bb@qq.com");
        System.out.println(list.get(0));
        return user;
    }

    @GetMapping("/user")
    public User insertUser(User user){
        User save = userRepository.save(user);
        return save;
    }

    @GetMapping("/test3")
    public List<User> test3(String lastName,Integer id){
        List<User> users = userRepository.getByLastNameStartingWithAndIdLessThan(lastName, id);
        return users;
    }
    @GetMapping("/test4")
    public List<User> test4(Integer id){
        List<User> users =userRepository.getByAddressIdGreaterThan(id);
        return users;
    }
    @GetMapping("/test5")
    public List<User> test5(Integer id){
        List<User> users =userRepository.getByAddress_IdGreaterThan(id);
        return users;
    }
    @GetMapping("/test6")
    public User test6(){
        User person = userRepository.getMaxIdPerson();
        return person;
    }
    @GetMapping("/test7")
    public User test7(String lastName,String email){
        User person = userRepository.testQueryAnnotationParams1(lastName,email);
        return person;
    }
    @GetMapping("/test8")
    public User test8(String lastName,String email){
        User person = userRepository.testQueryAnnotationParams2(lastName,email);
        return person;
    }

    @GetMapping("/test9")
    public List<User> test8(String lastName){
        List<User> users = userRepository.findBylastName(lastName);
        return users;
    }
    @GetMapping("/test10")
    public long test10(){
        long count = userRepository.getTotalCount();
        return count;
    }

    @GetMapping("/test11")
    public int test11(Integer id,String email){
        int i = userServcie.updateEmailById(id, email);
        return i;
    }

    @GetMapping("/test12")
    public Page<User> test12(){
//        Sort sort = new Sort();
        int page = 1;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page,pageSize);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }
    @GetMapping("/test13")
    public Page<User> test13(){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        int page = 1;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page,pageSize,sort);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }
    @GetMapping("/test14")
    public Page<User> test14(){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        int page = 1;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page,pageSize,sort);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }

    /**
     * 目标: 实现带查询条件的分页. id > 5 的条件
     *
     * 调用 JpaSpecificationExecutor 的 Page<T> findAll(Specification<T> spec, Pageable pageable);
     * Specification: 封装了 JPA Criteria 查询的查询条件
     * Pageable: 封装了请求分页的信息: 例如 pageNo, pageSize, Sort
     */
    @GetMapping("/test15")
    public Page<User> test15(){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        int page = 1;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page,pageSize,sort);

        //通常使用 Specification 的匿名内部类
        Specification<User> specification = new Specification<User>() {
            /**
             * @param *root: 代表查询的实体类.
             * @param query: 可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
             * 来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
             * @param *cb: CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到 Predicate 对象
             * @return: *Predicate 类型, 代表一个查询条件.
             */
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path path = root.get("id");
                Predicate predicate = cb.gt(path,5);
                return predicate;
            }
        };
        Page<User> userPage = userRepository.findAll(specification,  pageable);
        return userPage;
    }

    @GetMapping("/test16")
    public User test16(Integer id){
        User user = userRepository.getUserById(id);
        return user;
    }

}
