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
 * 贴子消息
 *
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PostInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 贴子标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片
     */
    private String images;

    /**
     * 所属用户
     */
    private Long userId;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 浏览量
     */
    private Integer pageviews;

    @TableField(exist = false)
    private Integer tagId;

    /**
     * 所属模块
     */
    private String tagIds;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String studentId;

    /**
     * 删除标识
     */
    private Integer deleteFlag;


}
