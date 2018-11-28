package com.jane.controller;

import com.jane.dao.UserRepository;
import com.jane.model.User;
import com.jane.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
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

    @PersistenceContext
    private EntityManager entityManager;

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
                Path id = root.get("id");
                Predicate predicateId = cb.gt(id,5);
                return predicateId;
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
    @GetMapping("/test17")
    public Page<User> test17(){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        int page = 1;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page,pageSize,sort);

        //通常使用 Specification 的匿名内部类
        /**
         * @param *root: 代表查询的实体类.
         * @param query: 可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
         * 来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
         *代表一个specific的顶层查询对象，它包含着查询的各个部分，比如：select 、from、where、group by、order by。
         *
         * @param *cb: CriteriaBuilder 对象.用于构造标准查询、复合条件、表达式、排序等 ;创建 Criteria 相关对象的工厂.
         * 当然可以从中获取到 Predicate 对象;可以通过createQuery的方式获取CriteriaQuery实例
         * @return: *Predicate 类型, 代表一个查询条件.代表Criteria查询的根对象，定义了实体类型，能为将来导航获得想要的结果，它与SQL查询中的FROM子句类似
         */
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path id = root.get("id");
                List<Predicate> predicates=new ArrayList<Predicate>();
                Predicate predicateId = cb.gt(id,5);
                predicates.add(predicateId);
                Path<User> email = root.get("email");
                Predicate predicateEmail = cb.equal(email, "aa@qq.com");
                predicates.add(predicateEmail);
                Predicate endPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
//                Predicate endPredicate = cb.and((Predicate[]) predicates.toArray());
                return endPredicate;
            }
        };
        System.out.println(specification.toString());
        Page<User> userPage = userRepository.findAll(specification,  pageable);
        System.out.println("***********************");
        return userPage;
    }
    @GetMapping("/test18")
    public Page<User> test18(){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        int page = 1;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page,pageSize,sort);

        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.disjunction();
//                return cb.conjunction();
            }
        };
        Page<User> userPage = userRepository.findAll(specification,  pageable);
        return userPage;
    }
    @GetMapping("/test19")
    public Page<User> test19(){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        int page = 1;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page,pageSize,sort);

        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> addressPath = root.get("address");
                Path<Object> expression = addressPath.get("city");
//                String[] names = StringUtils.split("address.city", ".");
//                Path expression = root.get(names[0]);
//                for (int i = 1; i < names.length; i++) {
//                    expression = expression.get(names[i]);
//                }
                Predicate predicate = cb.equal(expression, "beijing");
                return predicate;
            }
        };
        Page<User> userPage = userRepository.findAll(specification,  pageable);
        return userPage;
    }

    @GetMapping("/test20")
    public Page<User> test20(){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        int page = 1;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page,pageSize,sort);

        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> addressPath = root.get("address");
                Path<Object> expression = addressPath.get("city");
                Predicate predicate = cb.equal(expression, "beijing");
                return predicate;
            }
        };
        Page<User> userPage = userRepository.findAll(specification,  pageable);
        return userPage;
    }
    @GetMapping("/test21")
    public Page<User> test21(){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        int page = 1;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page,pageSize,sort);

        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path id = root.get("id");
                List<Predicate> predicates=new ArrayList<Predicate>();
                Predicate predicateId = cb.gt(id,5);
                predicates.add(predicateId);
                Path<User> email = root.get("email");
                Predicate predicateEmail = cb.equal(email, "aa@qq.com");
                predicates.add(predicateEmail);
                Predicate endPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
//                Predicate endPredicate = cb.and((Predicate[]) predicates.toArray());
                //添加where条件
                query.where(endPredicate);
                 //指定查询项，select后面的东西
                query.multiselect(id,email,cb.count(id));
                //分组
                query.groupBy(id);
                //排序
                query.orderBy(cb.asc(id));
                //筛选
                query.having(cb.greaterThan(id,0));
                //获取最终的Predicate
                Predicate restriction = query.getRestriction();
                return restriction;
            }
        };
        Page<User> userPage = userRepository.findAll(specification,  pageable);
        return userPage;
    }

    @GetMapping("/test22")
    public List<User> test22(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //User指定了查询结果返回至自定义对象
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Path id = root.get("id");
        List<Predicate> predicates=new ArrayList<Predicate>();
        Predicate predicateId = cb.equal(id,1);
        predicates.add(predicateId);
        Path<User> email = root.get("email");
        Predicate predicateEmail = cb.equal(email, "aa@qq.com");
        predicates.add(predicateEmail);
        Predicate endPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
//                Predicate endPredicate = cb.and((Predicate[]) predicates.toArray());
        //添加where条件
        query.where(endPredicate);
         //指定查询项，select后面的东西
//        query.multiselect(id,email);
        //分组
        query.groupBy(id);
        //排序
        query.orderBy(cb.asc(id));
        //筛选
        query.having(cb.greaterThan(id,0));
        TypedQuery<User> q = entityManager.createQuery(query);
        List<User> result = q.getResultList();
        for (User user : result) {
            //打印查询结果
            System.out.println(user.toString());
        }
        return result;
    }
}
