package com.social.forum.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "forumPost")
public class ForumPostTweet{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forumPostId;

    @Column(length = 500)
    private String postContent;
    private Date postingDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "forumPostTweet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ForumPostComment> forumPostComment = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, optional = false)
    @JoinColumn(name = "user_info_id", nullable = false)
    @JsonBackReference
    private UserInfo userInfo;


}
