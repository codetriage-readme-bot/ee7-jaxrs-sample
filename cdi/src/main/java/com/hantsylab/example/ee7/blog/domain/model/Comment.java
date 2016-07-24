/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylab.example.ee7.blog.domain.model;

import com.hantsylab.example.ee7.blog.domain.support.AbstractAuditableEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author hantsy
 */
@Entity
@Table(name = "comments")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends AbstractAuditableEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "content")
    @NotBlank
    @NonNull
    private String content;

    @ManyToOne()
    @JoinColumn(name = "post_id")
    private Post post;

}
