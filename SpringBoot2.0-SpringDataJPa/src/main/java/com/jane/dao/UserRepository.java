package com.jane.dao;

/**
 * Created by Janus on 2018/9/8.
 */

import com.jane.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;

public interface UserRepository
        extends UserDao,JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {

    /**
     * 符合查询方法规范的几个方法
     * @param email
     * @return
     */
    List<User> findByEmail(String email);

    //where lastName like ?% and id <?
    List<User> getByLastNameStartingWithAndIdLessThan(String lastName,Integer id);

    //WHERE User.addressId > ?
    List<User> getByAddressIdGreaterThan(Integer id);

    //WHERE a.id > ?
    List<User> getByAddress_IdGreaterThan(Integer id);

    /**
     * @Query注解，使用JPQL查询语言
     * @return
     */
    @Query("select u from User u where u.id=(select max(u2.id) from User u2)")
    User getMaxIdPerson();

    /**
     * 索引参数
     * @param lastName
     * @param email
     * @return
     */
    @Query("select u from User u where u.lastName=?1 and u.email=?2")
    User testQueryAnnotationParams1(String lastName,String email);

    /**
     * 命名参数
     * @param lastName
     * @param email
     * @return
     */
    @Query("select u from User u where u.lastName=:lastName and u.email=:email")
    User testQueryAnnotationParams2(@Param("lastName") String lastName, @Param("email") String email);

    /**
     * 模糊查询 like关键字
     * @param lastName
     * @return
     */
    @Query("select u from User u where u.lastName LIKE ?1%")
    List<User> findBylastName (String lastName );

    /**
     * 本地查询，执行原生SQL
     * @return
     */

    @Query(nativeQuery = true,value = "select count(1) from tb_user")
    long getTotalCount();

    /**
     * @Modifying +@Transactional 实现JPQLupdate or delete
     * @param id
     * @param email
     * @return
     */
    @Modifying
    @Query("update User  u set u.email = :email where u.id = :id")
    int updateEmailById(@Param("id") Integer id,@Param("email") String email);

    /**
     * 带查询条件的分页
     * @param spec
     * @param pageable
     * @return
     */
    @Override
    Page<User> findAll(@Nullable Specification<User> spec, Pageable pageable);
}
