package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * 与keycloak中的用户对于
 * <p>
 * Created by liuyuancheng on 2020/8/14  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"phone"}),
        @UniqueConstraint(columnNames = {"email"}),
})
public class Member extends BaseEntity {

    //允许登录")
    @Column(columnDefinition = "boolean DEFAULT 'true'")
    private Boolean allowLogin;

    //登录名")
    @Column(nullable = false, length = 20)
    private String username;

    //真实姓名")
    @Column(nullable = false, length = 20)
    private String realName;

    //手机号码")
    @Column(length = 20)
    private String phone;

    //电子邮件")
    @Column(length = 100)
    private String email;

    //加入方式，0-其它 1-Excel导入 2-公开链接邀请 3-手动添加")
    @Column(updatable = false, columnDefinition = "int2 DEFAULT 0")
    private Integer joinMethod;

    //归属部门")
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Department> department;

    //归属角色")
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> role;

    public static Optional<Member> findSystemMember() {
        return find("username", "system").firstResultOptional();
    }

    /**
     * 获取member的上级部门
     *
     * @param memberId
     * @return
     */
    public static List<Department> findMemberBelongs(Long memberId) {
        return find("SELECT d FROM Department d LEFT JOIN DepartmentMember dm ON d.id=dm.departmentId WHERE dm.memberId=?1",
                memberId).list();
    }

    public static List<Member> findByDepartmentId(Long departmentId) {
        Department department = Department.findById(departmentId);
        return find("SELECT m FROM ").list();
    }
}
