package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 回复管理
 *
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ReplyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 所属分类
     */
    private Integer tagId;

    /**
     * 回复用户
     */
    private Long userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 所属帖子
     */
    private Integer postId;

    /**
     * 被回复用户
     */
    private Long replyUserId;

    /**
     * 发送时间
     */
    private String sendCreate;

    /**
     * 删除标识
     */
    private Integer deleteFlag;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String title;

    @TableField(exist = false)
    private String studentId;
}
