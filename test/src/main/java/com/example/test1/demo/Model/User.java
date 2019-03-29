package com.example.test1.demo.Model;

import com.example.test1.demo.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.*;

@Builder
@Entity(name="t_user")
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "likeOrganizations")
@EqualsAndHashCode(exclude = "likeOrganizations")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String nickName;
    private String realName;
    @Column(columnDefinition = "text")
    private String profile;//头像地址
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    private String grade;
    private String studentNumber;
    @ManyToOne(cascade = CascadeType.ALL)//级联操作不是很清楚
    @JoinColumn(name = "major_id")
    private Major major; //外键引用
    private String phone;
    private String email;
    private Calendar birthday;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="academy_id")
    private Academy academy; //外键
    @Column(columnDefinition = "text")
    private String userDesc;
    private long likeCount;
    private long joinCount;
    private Calendar createTime;
    private Calendar updateTime;
    private int isManager;
    private int isBan;
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch = FetchType.EAGER)//管理关系
    private List<ManagerOrganization> managerOrganizations;
    //用户私信相关
    @OneToMany(mappedBy ="user" ,cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<PrivateMessage> privateMessages;
    @OneToMany(mappedBy ="friend",cascade = CascadeType.ALL,fetch=FetchType.EAGER )
    private Set<PrivateMessage> privateMessages2;
    @OneToMany(mappedBy ="sender" ,cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<PrivateMessage> privateMessages3;
    @OneToMany(mappedBy ="receiver",cascade = CascadeType.ALL,fetch=FetchType.EAGER )
    private Set<PrivateMessage> privateMessages4;
    //组织私信相关
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<OrganizationMessage> organizationMessages;
    @OneToMany(mappedBy = "receiver",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<OrganizationMessage> organizationMessagesReceiver;
    //评论相关
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<ActivityComment> comments;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<ActivityScore> activityScores;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<OrganizationScore> organizationScores;
    //报名关注相关
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="t_signUp",joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "activity_id", referencedColumnName = "activityId"))
    private Set<Activity> activities=new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "t_like_Activity",joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "activity_id", referencedColumnName = "activityId"))
    private Set<Activity> likeActivities=new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "likeUsers" })
    @JoinTable(name="t_like_organization",joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "organization_id", referencedColumnName = "organizationId"))
    private Set<Organization> likeOrganizations=new HashSet<>();
    //标签
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="t_user_tags",joinColumns = @JoinColumn(name="user_id",referencedColumnName = "userId"),inverseJoinColumns = @JoinColumn(name="tag_id",referencedColumnName = "tagId"))
    private Set<Tags> tags=new HashSet<>();
}
